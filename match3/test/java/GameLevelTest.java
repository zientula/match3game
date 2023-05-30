import Library.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import static org.junit.jupiter.api.Assertions.*;

class GameLevelTest {

    /**
     * check if the file is found and properly loaded
     */
    @Test
    void shouldReadConfigFromFile() throws IOException {
        GameLevel gameLevel = new GameLevel("LevelOne.properties");
        assertEquals(150, gameLevel.getGoal());
        assertEquals(0, gameLevel.getTime());
        assertEquals(10, gameLevel.getMoves());
        assertEquals(GameLevel.DifficultyLevel.EASY, gameLevel.getDiffLvl());
        assertEquals(0.2, gameLevel.getProbabilities().get(Tile.Color.BROWN));
        assertEquals(0.2, gameLevel.getProbabilities().get(Tile.Color.ORANGE));
        assertEquals(0.2, gameLevel.getProbabilities().get(Tile.Color.PINK));
        assertEquals(0.1, gameLevel.getProbabilities().get(Tile.Color.BLUE));
        assertEquals(0.1, gameLevel.getProbabilities().get(Tile.Color.GREEN));
        assertEquals(0.1, gameLevel.getProbabilities().get(Tile.Color.PURPLE));
        assertEquals(0.1, gameLevel.getProbabilities().get(Tile.Color.YELLOW));
    }

    /**
     * catch exception when there is no such file
     */
    @Test
    void shouldThrowExceptionWhenFileDoesNotExist() {
        assertThrows(IOException.class, () -> new GameLevel("confg.properties"));
    }

    /**
     * catch the wrong extension exception
     */
    @Test
    void shouldThrowExceptionWhenFileExtensionIsIncorrect() {
        assertThrows(InvalidPropertiesFormatException.class, () -> new GameLevel("config.txt"));
    }

    @Test
    void shouldThrowExceptionWhenConfigHasInvalidValues() {
        assertThrows(InvalidConfigException.class, () -> new GameLevel("invalidConfig.properties"));
    }
}