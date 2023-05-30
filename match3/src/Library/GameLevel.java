package Library;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * Represents game level templates
 */
public class GameLevel {

    public enum DifficultyLevel {
        EASY, MEDIUM, HARD
    }

    private final Tile[][] BOARD;
    private final HashMap<Tile.Color, Double> probabilities;
    private final int GOAL;
    private int time;
    private int moves;
    private DifficultyLevel diffLvl;
    private double rareProbability;

    public GameLevel(Tile[][] board, HashMap<Tile.Color, Double> probabilities, double rareProbability, int goal, int time, int moves, DifficultyLevel diffLvl) {
        this.BOARD = board;
        this.probabilities = probabilities;
        this.GOAL = goal;
        this.time = time;
        this.moves = moves;
        this.diffLvl = diffLvl;
        this.rareProbability = rareProbability;
    }

    /**
     * read level config from file
     * @param configFileName - name of file with game's configuration
     * @throws IOException - exception which is thrown when file with given name is not found
     */
    public GameLevel(String configFileName) throws IOException,
            InvalidConfigException {

        InputStream inputStream;

        Properties properties = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);

        if (!configFileName.matches(".*.properties"))
            throw new InvalidPropertiesFormatException("Wrong file extension");

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("File not found.");
        }

        inputStream.close();


        HashMap<Tile.Color, Double> probabilities = new HashMap<>();
        int height, GOAL, time, moves, width;
        double rareProbability;
        String diffLvl;

        try {
            height = Integer.parseInt(properties.getProperty("height"));
            width = Integer.parseInt(properties.getProperty("width"));
            GOAL = Integer.parseInt(properties.getProperty("goal"));
            time = Integer.parseInt(properties.getProperty("time"));
            moves = Integer.parseInt(properties.getProperty("moves"));
            diffLvl = properties.getProperty("difficultyLevel");
            rareProbability = Double.parseDouble(properties.getProperty("rareProbability"));

            for (Tile.Color color : Tile.Color.values()) {
                probabilities.put(color, Double.parseDouble(properties.getProperty("probability" + color.name())));
            }
        } catch (NumberFormatException e) {
            throw new InvalidConfigException(e.getMessage());
        }

        this.rareProbability = rareProbability;
        this.probabilities = probabilities;
        this.BOARD = new Tile[height][width];
        this.GOAL = GOAL;
        this.time = time;
        this.moves = moves;
        this.diffLvl = Enum.valueOf(DifficultyLevel.class, diffLvl);
    }

    /**
     * @return information about the board of the level
     */
    public Tile[][] getBoard() {
        return BOARD;
    }

    /**
     * @return HashMap representing probability of each tile
     */
    public HashMap<Tile.Color, Double> getProbabilities() {
        return probabilities;
    }

    /**
     * @return information about level goal - required amount of points to pass the level
     */
    public int getGoal() {
        return GOAL;
    }

    /**
     * @return information about time spent on the level
     */
    public int getTime() {
        return time;
    }

    /**
     * increases time by one
     */
    public void increaseTime() {
        time++;
    }

    /**
     * resets time
     */
    public void resetTime() {
        time = 0;
    }

    /**
     * @return moves number
     */
    public int getMoves() {
        return moves;
    }

    /**
     * sets moves amount
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * @return information about difficulty of the level
     */
    public DifficultyLevel getDiffLvl() {
        return diffLvl;
    }

    /**
     * sets difficulty of the level
     */
    public void setDiffLvl(DifficultyLevel diffLvl) {
        this.diffLvl = diffLvl;
    }

    /**
     * @return probability of rare tile
     */
    public double getRareProbability() {
        return rareProbability;
    }

    /**
     * Set probability of rare tile
     * @param rareProbability probability of rare tile
     */
    public void setRareProbability(double rareProbability) {
        this.rareProbability = rareProbability;
    }
}

