package Library;

import java.util.*;

public class GameLevelController {
    private final GameLevel gameLevel;
    private final int height;
    private final int width;
    private final HashMap<Tile.Color, Double> probabilities;
    private final double rareProbability;
    private Tile[][] board;
    private int points;
    private int movesLeft;
    private GameLevelControllerEventListener listener;

    public GameLevelController(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        board = gameLevel.getBoard();
        height = board.length;
        width = board[0].length;
        probabilities = gameLevel.getProbabilities();
        rareProbability = gameLevel.getRareProbability();
        points = 0;
        movesLeft = gameLevel.getMoves();
        listener = new GameLevelControllerEventListener() {
            @Override
            public void afterRemove() {

            }

            @Override
            public void afterDrop() {

            }

            @Override
            public void afterFill() {

            }

            @Override
            public void afterReshuffle() {

            }

            @Override
            public void afterProcess() {

            }

            @Override
            public void beforeRemove() {

            }
        };
        populateBoard();
    }

    public GameLevelController(GameLevel gameLevel, GameLevelControllerEventListener listener) {
        this(gameLevel);
        this.listener = listener;
    }

    /**
     * populates new tiles in place of null
     */
    public void populateBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                if (board[i][j] == null)
                    board[i][j] = Generator.randomTile(probabilities, rareProbability);
        }
    }

    private boolean isInBoard(int x, int y) {
        return (x >= 0) && (x < width) && (y >= 0) && (y < height);
    }

    //    check if coords are inside of the board and the two tiles are adjacent
    private boolean _isMoveValid(int x1, int y1, int x2, int y2) {
        if (!(isInBoard(x1, y1) && isInBoard(x2, y2))) {
            return false;
        }

//        list of adjacent tiles to tile1
        int[][] adjacentToX = new int[][]{
                new int[]{x1, y1 + 1},
                new int[]{x1, y1 - 1},
                new int[]{x1 + 1, y1},
                new int[]{x1 - 1, y1}
        };

//        check if tile2 is one of those adjacent tiles
        for (int[] cell : adjacentToX) {
            if (cell[0] == x2 && cell[1] == y2) {
                return true;
            }
        }

        return false;
    }

    private List<int[]> BFS(int x, int y) {
        int[][] adjacent = new int[][]{
                new int[]{0, 1},
                new int[]{0, -1},
                new int[]{1, 0},
                new int[]{-1, 0}
        };
        Tile.Color color = board[y][x].getColor();

        boolean[][] visited = new boolean[height][width];
        List<int[]> queue = new ArrayList<>();
        List<int[]> reachable = new ArrayList<>();

        reachable.add(new int[]{x, y});

        visited[y][x] = true;

        for (int[] adj : adjacent) {
            int[] adjTile = new int[]{x + adj[0], y + adj[1]};
            queue.add(adjTile);
        }

        while (queue.size() > 0) {
            int[] temp = queue.remove(0);

            for (int[] adj : adjacent) {
                int newX = temp[0] + adj[0];
                int newY = temp[1] + adj[1];

                if (isInBoard(newX, newY) && !visited[newY][newX]) {
                    visited[newY][newX] = true;
                    if (color.equals(board[newY][newX].getColor())) {
                        int[] toAdd = new int[]{newX, newY};
                        queue.add(toAdd);
                        reachable.add(toAdd);
                    }
                }
            }
        }

        return reachable;
    }

    /**
     * Check whether a match3 pattern exists e.g.
     * #@#$
     * #$$$ <- this is a match3 in both the column and the row
     * &&@$
     * <p>
     * $#@@
     *
     * @param x x coord
     * @param y y coord
     * @return true/false
     */
    private boolean checkMatchRow(int x, int y, int n) {
        int countRow = 0;

        Tile.Color color = board[y][x].getColor();

//        check if we have a 3 tiles match in a row or a col
        for (int i = -(n - 1); i <= (n - 1); i++) {
            if (isInBoard(x + i, y) && board[y][x + i].getColor().equals(color)) {
                countRow++;
            } else {
                countRow = 0;
            }

            if (countRow == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether a match3 pattern exists in a column
     *
     * @param x x coord
     * @param y y coord
     * @return true/false
     */
    private boolean checkMatchCol(int x, int y, int n) {
        int countCol = 0;

        Tile.Color color = board[y][x].getColor();

//        check if we have a 3 tiles match in col
        for (int i = -(n - 1); i <= (n - 1); i++) {
            if (isInBoard(x, y + i) && board[y + i][x].getColor().equals(color)) {
                countCol++;
            } else {
                countCol = 0;
            }

            if (countCol == n) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMatch(int x, int y, int n) {
        return checkMatchCol(x, y, n) || checkMatchRow(x, y, n);
    }

    public void swap(int x1, int y1, int x2, int y2) {
        Tile tmp = board[y1][x1];
        board[y1][x1] = board[y2][x2];
        board[y2][x2] = tmp;
    }

    /**
     * "Apply gravity" to a column. e.g.
     * _    _
     * #    _
     * _    _
     * $ -> #
     * _    $
     * $    $
     *
     * @param x column coord
     */
    private void dropTilesColumn(int x) {
        Deque<Tile> stack = new ArrayDeque<>();

//        fill up the stack with remaining tiles and null the entire column
        for (int i = 0; i < height; i++) {
            if (board[i][x] != null) {
                stack.push(board[i][x]);
                board[i][x] = null;
            }
        }

//        build the column back up again
        int stackSize = stack.size();
        for (int i = 0; i < stackSize; i++) {
            board[height - i - 1][x] = stack.pop();
        }
    }

    /**
     * "Apply gravity" to the entire board.
     */
    public void dropTiles() {
        for (int i = 0; i < width; i++) {
            dropTilesColumn(i);
        }
    }

    /**
     * Remove adjacent matching tiles;
     *
     * @param x x coord
     * @param y y coord
     */
    private List<int[]> getListOfMatchTiles(int x, int y) {
        List<int[]> foundTiles = BFS(x, y);
        List<int[]> tilesToRemove = new ArrayList<>();

        //find which tiles are creating match
        for (int[] coords : foundTiles) {
            if (checkMatch(coords[0], coords[1], 3)) {
                tilesToRemove.add(coords);
            }
        }
        return tilesToRemove;
    }

    /**
     * Delete tiles according to the logic of the game
     *
     * @return true if any tile was deleted
     */
    public boolean deleteTiles() {
        List<int[]> tilesToRemove = getTilesToRemove();
        if (tilesToRemove.isEmpty())
            return false;
        for (int[] coords : tilesToRemove) {

            int tempX = coords[0];
            int tempY = coords[1];
            if (board[tempY][tempX] != null) {
                points += board[tempY][tempX].getPoints();
                board[tempY][tempX] = null;
            }
        }
        return true;
    }

    public boolean deleteTilesWithoutPoints() {
        List<int[]> tilesToRemove = getTilesToRemove();
        if (tilesToRemove.isEmpty())
            return false;
        for (int[] coords : tilesToRemove) {

            int tempX = coords[0];
            int tempY = coords[1];
            if (board[tempY][tempX] != null) {
                board[tempY][tempX] = null;
            }
        }
        return true;
    }

    /**
     * get list of coordinates of tiles to remove
     * @return list of coordinates of tiles
     */
    private List<int[]> getTilesToRemove() {

        List<int[]> tilesToDelete = new ArrayList<>();

        //boolean deleted = false;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = board[y][x];
                if (tile == null) continue;

                if (checkMatch(x, y, 3)) {
                    //deleted = true;
                    boolean isMatchRow = checkMatchRow(x, y, 4);
                    boolean isMatchCol = checkMatchCol(x, y, 4);

                    tilesToDelete.addAll(getListOfMatchTiles(x, y));

                    if (isMatchCol) {
                        for (int i = 0; i < height; i++) {
                            tilesToDelete.add(new int[]{x, i});
                        }
                    }
                    if (isMatchRow) {
                        for (int i = 0; i < width; i++) {
                            tilesToDelete.add(new int[]{i, y});
                        }
                    }
                }
            }
        }
        return tilesToDelete;
    }

    /**
     * method to automate all necessary action to play game.
     *
     */
    private void processTiles() {
        boolean isProcessing = true;

        while (isProcessing) {

            listener.beforeRemove();

            isProcessing = deleteTiles();

            listener.afterRemove();

            dropTiles();

            listener.afterDrop();

            populateBoard();

            listener.afterFill();
        }

        listener.afterProcess();

        while(!hasValidMoves())
            generateNewBoard();

        listener.afterReshuffle();

    }

    /**
     * isMoveValid - checks whether the move is valid.
     *
     * @param x1 x coord of the first tile
     * @param y1 y coord of the second tile
     * @param x2 x coord of the first tile
     * @param y2 y coord of the second tile
     * @return true/false
     */
    public boolean isMoveValid(int x1, int y1, int x2, int y2) {
//        check if tiles are inside of the board and if they adjacent
        if (!_isMoveValid(x1, y1, x2, y2)) return false;

//        temporarily swap
        swap(x1, y1, x2, y2);
//        check match3
        boolean result = (checkMatch(x1, y1, 3) || checkMatch(x2, y2, 3));

//         swap back
        swap(x1, y1, x2, y2);

        return result;
    }

    /**
     * checks if there's a valid move on board
     * @return true if there is valid move on board, false otherwise
     */
    public boolean hasValidMoves() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                isMoveValid(x, y, x+1, y);
                isMoveValid(x, y, x, y+1);
                if (isMoveValid(x, y, x+1, y) || isMoveValid(x, y, x, y+1))
                    return true;
            }
        }
        return false;
    }

    /**
     * Clears board and generates new tiles
     */
    public void generateNewBoard() {
        board = new Tile[height][width];
        populateBoard();
    }

    /**
     * Make move on the board and automatically do
     *
     * @param x1 x coord of source tile
     * @param y1 y coord of source tile
     * @param x2 x coord of destination tile
     * @param y2 y coord of destination tile
     * @throws InvalidMoveException thrown when move was invalid
     */
    public void makeMove(int x1, int y1, int x2, int y2) throws InvalidMoveException {
        if (!isMoveValid(x1, y1, x2, y2)) {
            throw new InvalidMoveException("");
        }

        movesLeft--;

        swap(x1, y1, x2, y2);

        processTiles();
    }

    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;
    }

    public int getMovesLeft() {
        return movesLeft;
    }

    public GameLevel getGameLevel() {
        return gameLevel;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public HashMap<Tile.Color, Double> getProbabilities() {
        return probabilities;
    }

    public double getRareProbability() {
        return rareProbability;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int point){
        this.points = point;
    }

}