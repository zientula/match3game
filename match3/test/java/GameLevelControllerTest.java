import Library.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameLevelControllerTest {

    @Test
    public void shouldPopulateBoard() throws IOException {
        GameLevel gameLevel = new GameLevel("gameControllerTest2.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        for (int i = 0; i < glc.getWidth(); i++) {
            for (int j = 0; j < glc.getHeight(); j++) {
                assertNotNull(glc.getBoard()[j][i]);
            }
        }
    }

    @Test
    public void shouldCheckIfMoveIsValid() throws IOException {
        GameLevel gameLevel = new GameLevel("gameControllerTest2.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();


        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[1][0] = new Tile(Tile.Color.BLUE, false);
        board[2][0] = new Tile(Tile.Color.BLUE, false);

        board[0][1] = new Tile(Tile.Color.BLUE, false);
        board[1][1] = new Tile(Tile.Color.ORANGE, false);
        board[2][1] = new Tile(Tile.Color.ORANGE, false);

        glc.setBoard(board);
        assertTrue(glc.isMoveValid(0,0,1,0));
        assertFalse(glc.isMoveValid(0,1,1,1));
    }

    @Test
    public void shouldDeleteTilesInRow() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.ORANGE, false);
        board[0][2] = new Tile(Tile.Color.ORANGE, false);

        board[1][0] = new Tile(Tile.Color.ORANGE, false);
        board[1][1] = new Tile(Tile.Color.BROWN, false);
        board[1][2] = new Tile(Tile.Color.YELLOW, false);

        board[2][0] = new Tile(Tile.Color.PURPLE, false);
        board[2][1] = new Tile(Tile.Color.BROWN, false);
        board[2][2] = new Tile(Tile.Color.YELLOW, false);

        glc.setBoard(board);
        glc.deleteTiles();
        assertNull(glc.getBoard()[0][0]);
        assertNull(glc.getBoard()[0][1]);
        assertNull(glc.getBoard()[0][2]);
        assertNotNull(glc.getBoard()[1][0]);
    }

    @Test
    public void shouldDropTiles() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.BROWN, false);
        board[0][2] = new Tile(Tile.Color.YELLOW, false);

        board[1][0] = new Tile(Tile.Color.ORANGE, false);
        board[1][1] = new Tile(Tile.Color.ORANGE, false);
        board[1][2] = new Tile(Tile.Color.ORANGE, false);

        board[2][0] = new Tile(Tile.Color.PURPLE, false);
        board[2][1] = new Tile(Tile.Color.BROWN, false);
        board[2][2] = new Tile(Tile.Color.YELLOW, false);

        glc.setBoard(board);
        glc.deleteTiles();
        assertNull(glc.getBoard()[1][0]);
        assertNull(glc.getBoard()[1][1]);
        assertNull(glc.getBoard()[1][2]);
        assertNotNull(glc.getBoard()[0][0]);

        glc.dropTiles();
        assertNull(glc.getBoard()[0][0]);
        assertNull(glc.getBoard()[0][1]);
        assertNull(glc.getBoard()[0][2]);
    }

    @Test
    public void shouldDeleteTilesInColumn() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.ORANGE, false);
        board[0][2] = new Tile(Tile.Color.BLUE, false);

        board[1][0] = new Tile(Tile.Color.ORANGE, false);
        board[1][1] = new Tile(Tile.Color.BROWN, false);
        board[1][2] = new Tile(Tile.Color.YELLOW, false);

        board[2][0] = new Tile(Tile.Color.ORANGE, false);
        board[2][1] = new Tile(Tile.Color.BROWN, false);
        board[2][2] = new Tile(Tile.Color.YELLOW, false);

        glc.setBoard(board);
        glc.deleteTiles();
        assertNull(glc.getBoard()[0][0]);
        assertNull(glc.getBoard()[1][0]);
        assertNull(glc.getBoard()[2][0]);
        assertNotNull(glc.getBoard()[0][1]);
    }

    @Test
    public void shouldDeleteAllMatch3() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.ORANGE, false);
        board[0][2] = new Tile(Tile.Color.ORANGE, false);

        board[1][0] = new Tile(Tile.Color.BROWN, false);
        board[1][1] = new Tile(Tile.Color.ORANGE, false);
        board[1][2] = new Tile(Tile.Color.YELLOW, false);

        board[2][0] = new Tile(Tile.Color.PINK, false);
        board[2][1] = new Tile(Tile.Color.ORANGE, false);
        board[2][2] = new Tile(Tile.Color.ORANGE, false);

        glc.setBoard(board);
        glc.deleteTiles();
        assertNull(glc.getBoard()[0][0]);
        assertNull(glc.getBoard()[0][1]);
        assertNull(glc.getBoard()[0][2]);
        assertNull(glc.getBoard()[1][1]);
        assertNull(glc.getBoard()[2][1]);
        assertNotNull(glc.getBoard()[2][2]);
    }

    @Test
    public void shouldDeleteTilesInAllMatch3Columns() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.ORANGE, false);
        board[0][2] = new Tile(Tile.Color.BLUE, false);

        board[1][0] = new Tile(Tile.Color.ORANGE, false);
        board[1][1] = new Tile(Tile.Color.BROWN, false);
        board[1][2] = new Tile(Tile.Color.BLUE, false);

        board[2][0] = new Tile(Tile.Color.ORANGE, false);
        board[2][1] = new Tile(Tile.Color.BLUE, false);
        board[2][2] = new Tile(Tile.Color.BLUE, false);

        glc.setBoard(board);
        glc.deleteTiles();
        assertNull(glc.getBoard()[0][0]);
        assertNull(glc.getBoard()[1][0]);
        assertNull(glc.getBoard()[2][0]);
        assertNull(glc.getBoard()[0][2]);
        assertNull(glc.getBoard()[1][2]);
        assertNull(glc.getBoard()[2][2]);
        assertNotNull(glc.getBoard()[0][1]);
        assertNotNull(glc.getBoard()[2][1]);
    }

    @Test
    public void shouldDeleteTilesInAllMatch3Rows() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.ORANGE, false);
        board[0][2] = new Tile(Tile.Color.ORANGE, false);

        board[1][0] = new Tile(Tile.Color.ORANGE, false);
        board[1][1] = new Tile(Tile.Color.BROWN, false);
        board[1][2] = new Tile(Tile.Color.BLUE, false);

        board[2][0] = new Tile(Tile.Color.BLUE, false);
        board[2][1] = new Tile(Tile.Color.BLUE, false);
        board[2][2] = new Tile(Tile.Color.BLUE, false);

        glc.setBoard(board);
        glc.deleteTiles();
        assertNull(glc.getBoard()[0][0]);
        assertNull(glc.getBoard()[0][1]);
        assertNull(glc.getBoard()[0][2]);
        assertNull(glc.getBoard()[2][0]);
        assertNull(glc.getBoard()[2][1]);
        assertNull(glc.getBoard()[2][2]);
        assertNotNull(glc.getBoard()[1][0]);
        assertNotNull(glc.getBoard()[1][2]);
    }

    @Test
    public void shouldDeleteAllTilesInRowAndColumn() throws IOException {
        GameLevel gameLevel = new GameLevel("GameController6x6.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.BLUE, false);
        board[0][1] = new Tile(Tile.Color.ORANGE, false);
        board[0][2] = new Tile(Tile.Color.ORANGE, false);
        board[0][3] = new Tile(Tile.Color.ORANGE, false);
        board[0][4] = new Tile(Tile.Color.ORANGE, false);
        board[0][5] = new Tile(Tile.Color.GREEN, false);

        board[1][0] = new Tile(Tile.Color.ORANGE, false);
        board[1][1] = new Tile(Tile.Color.BROWN, false);
        board[1][2] = new Tile(Tile.Color.ORANGE, false);
        board[1][3] = new Tile(Tile.Color.YELLOW, false);
        board[1][4] = new Tile(Tile.Color.BLUE, false);
        board[1][5] = new Tile(Tile.Color.BLUE, false);

        board[2][0] = new Tile(Tile.Color.BLUE, false);
        board[2][1] = new Tile(Tile.Color.BLUE, false);
        board[2][2] = new Tile(Tile.Color.ORANGE, false);
        board[2][3] = new Tile(Tile.Color.ORANGE, false);
        board[2][4] = new Tile(Tile.Color.YELLOW, false);
        board[2][5] = new Tile(Tile.Color.YELLOW, false);

        board[3][0] = new Tile(Tile.Color.GREEN, false);
        board[3][1] = new Tile(Tile.Color.GREEN, false);
        board[3][2] = new Tile(Tile.Color.ORANGE, false);
        board[3][3] = new Tile(Tile.Color.BLUE, false);
        board[3][4] = new Tile(Tile.Color.BLUE, false);
        board[3][5] = new Tile(Tile.Color.GREEN,false);

        board[4][0] = new Tile(Tile.Color.BLUE, false);
        board[4][1] = new Tile(Tile.Color.BLUE, false);
        board[4][2] = new Tile(Tile.Color.YELLOW, false);
        board[4][3] = new Tile(Tile.Color.ORANGE, false);
        board[4][4] = new Tile(Tile.Color.ORANGE, false);
        board[4][5] = new Tile(Tile.Color.YELLOW, false);

        board[5][0] = new Tile(Tile.Color.BLUE, false);
        board[5][1] = new Tile(Tile.Color.BLUE, false);
        board[5][2] = new Tile(Tile.Color.GREEN, false);
        board[5][3] = new Tile(Tile.Color.GREEN, false);
        board[5][4] = new Tile(Tile.Color.ORANGE, false);
        board[5][5] = new Tile(Tile.Color.ORANGE, false);

        glc.setBoard(board);
        glc.deleteTiles();
        assertNull(glc.getBoard()[0][0]);
        assertNull(glc.getBoard()[0][1]);
        assertNull(glc.getBoard()[0][2]);
        assertNull(glc.getBoard()[0][3]);
        assertNull(glc.getBoard()[0][4]);
        assertNull(glc.getBoard()[0][5]);

        assertNull(glc.getBoard()[0][2]);
        assertNull(glc.getBoard()[1][2]);
        assertNull(glc.getBoard()[2][2]);
        assertNull(glc.getBoard()[3][2]);
        assertNull(glc.getBoard()[4][2]);
        assertNull(glc.getBoard()[5][2]);

        assertNotNull(glc.getBoard()[1][0]);
        assertNotNull(glc.getBoard()[1][3]);
        assertNotNull(glc.getBoard()[2][4]);
        assertNotNull(glc.getBoard()[2][5]);
        assertNotNull(glc.getBoard()[4][1]);
        assertNotNull(glc.getBoard()[5][0]);
    }

    @Test
    public void shouldFailToFindPossibleMove() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.BLUE, false);
        board[0][2] = new Tile(Tile.Color.ORANGE, false);

        board[1][0] = new Tile(Tile.Color.PINK, false);
        board[1][1] = new Tile(Tile.Color.BROWN, false);
        board[1][2] = new Tile(Tile.Color.PINK, false);

        board[2][0] = new Tile(Tile.Color.ORANGE, false);
        board[2][1] = new Tile(Tile.Color.BLUE, false);
        board[2][2] = new Tile(Tile.Color.ORANGE, false);

        glc.setBoard(board);
        assertFalse(glc.hasValidMoves());
    }

    @Test
    public void shouldMakeMove() throws IOException {
        GameLevel gameLevel = new GameLevel("GameControllerDeleteTileTest.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.ORANGE, false);
        board[0][1] = new Tile(Tile.Color.BLUE, false);
        board[0][2] = new Tile(Tile.Color.ORANGE, false);

        board[1][0] = new Tile(Tile.Color.PINK, false);
        board[1][1] = new Tile(Tile.Color.ORANGE, false);
        board[1][2] = new Tile(Tile.Color.PINK, false);

        board[2][0] = new Tile(Tile.Color.ORANGE, false);
        board[2][1] = new Tile(Tile.Color.BLUE, false);
        board[2][2] = new Tile(Tile.Color.ORANGE, false);

        glc.setBoard(board);
        assertThrows(InvalidMoveException.class, () -> glc.makeMove(0,0,1,0));
        assertDoesNotThrow(() -> glc.makeMove(1,1,1,0));
    }

    @Test
    public void shouldCountPointsProperly() throws IOException, InvalidMoveException {
        GameLevel gameLevel = new GameLevel("GameController2x5.properties");
        GameLevelController glc = new GameLevelController(gameLevel);
        Tile[][] board = glc.getBoard();

        board[0][0] = new Tile(Tile.Color.BROWN, false);
        board[0][1] = new Tile(Tile.Color.PINK, false);
        board[0][2] = new Tile(Tile.Color.PINK, false);
        board[0][3] = new Tile(Tile.Color.GREEN, false);
        board[0][4] = new Tile(Tile.Color.GREEN, false);

        board[1][0] = new Tile(Tile.Color.PINK, false);
        board[1][1] = new Tile(Tile.Color.YELLOW, false);
        board[1][2] = new Tile(Tile.Color.YELLOW, false);
        board[1][3] = new Tile(Tile.Color.BROWN, false);
        board[1][4] = new Tile(Tile.Color.YELLOW, false);

        glc.setBoard(board);
        glc.makeMove(4,1,3,1);
        assertTrue(glc.getPoints()>=30);
    }
}

