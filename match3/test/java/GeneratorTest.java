import Library.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class GeneratorTest {

    @Test
    public void shouldGenerateOnlyBrownTile() {
        HashMap<Tile.Color, Double> prob = new HashMap<>();
        prob.put(Tile.Color.BROWN, 1.0);
        for (int i = 0; i < 100000; i++) {
            Tile tile = Generator.randomTile(prob, 0.0);
            assert tile != null;
            if (!tile.getColor().equals(Tile.Color.BROWN))
                fail("Tiles should only be brown");
        }
    }

    @Test
    public void shouldGenerateOrangeAndBrownTilesWithEqualDistribution() {
        HashMap<Tile.Color, Double> prob = new HashMap<>();
        prob.put(Tile.Color.BROWN, 0.5);
        prob.put(Tile.Color.ORANGE, 0.5);
        int brownCount = 0, orangeCount = 0;
        for (int i = 0; i < 100000; i++) {
            Tile tile = Generator.randomTile(prob, 0.0);
            assert tile != null;
            switch(tile.getColor()) {
                case BROWN -> brownCount++;
                case ORANGE -> orangeCount++;
                default -> fail("Color not from defined range or null");
            }
        }
        assertEquals(50000, brownCount, 1000);
        assertEquals(50000, orangeCount, 1000);
    }

    @Test
    public void shouldGenerate4TilesTypesWithEqualDistribution() {
        HashMap<Tile.Color, Double> prob = new HashMap<>();
        prob.put(Tile.Color.BROWN, 0.25);
        prob.put(Tile.Color.ORANGE, 0.25);
        prob.put(Tile.Color.PINK, 0.25);
        prob.put(Tile.Color.GREEN, 0.25);
        int brownCount = 0, orangeCount = 0, pinkCount = 0, greenCount = 0;
        for (int i = 0; i < 100000; i++) {
            Tile tile = Generator.randomTile(prob, 0.0);
            assert tile != null;
            switch(tile.getColor()) {
                case BROWN -> brownCount++;
                case ORANGE -> orangeCount++;
                case PINK -> pinkCount++;
                case GREEN -> greenCount++;
                default -> fail("Color not from defined range or null");
            }
        }
        assertEquals(25000, brownCount, 500);
        assertEquals(25000, orangeCount, 500);
        assertEquals(25000, pinkCount, 500);
        assertEquals(25000, greenCount, 500);
    }

    @Test
    public void shouldThrowInvalidConfigException() {
        HashMap<Tile.Color, Double> prob = new HashMap<>();
        prob.put(Tile.Color.BROWN, 0.35);
        prob.put(Tile.Color.ORANGE, 0.45);
        prob.put(Tile.Color.PINK, 0.55);
        prob.put(Tile.Color.GREEN, 0.65);
        assertThrows(InvalidConfigException.class, () -> Generator.randomTile(prob, 0.0));
    }

    @Test
    public void shouldGenerate2TypesOfTilesWith2to8Ratio() {
        HashMap<Tile.Color, Double> prob = new HashMap<>();
        prob.put(Tile.Color.BROWN, 0.2);
        prob.put(Tile.Color.ORANGE, 0.8);
        int brownCount = 0, orangeCount = 0;
        for (int i = 0; i < 100000; i++) {
            Tile tile = Generator.randomTile(prob, 0.0);
            assert tile != null;
            switch(tile.getColor()) {
                case BROWN -> brownCount++;
                case ORANGE -> orangeCount++;
                default -> fail("Color not from defined range or null");
            }
        }
        assertEquals(20000, brownCount, 1000);
        assertEquals(80000, orangeCount, 1000);
    }

    @Test
    public void shouldGenerateAllTilesWithAlmostEqualDistribution() {
        HashMap<Tile.Color, Double> prob = new HashMap<>();
        prob.put(Tile.Color.BROWN, 0.142);
        prob.put(Tile.Color.BLUE, 0.142);
        prob.put(Tile.Color.GREEN, 0.142);
        prob.put(Tile.Color.PURPLE, 0.142);
        prob.put(Tile.Color.YELLOW, 0.142);
        prob.put(Tile.Color.ORANGE, 0.142);
        prob.put(Tile.Color.PINK, 0.148);
        HashMap<Tile.Color, Integer> count = new HashMap<>();


        for (int i = 0; i < 1000000; i++) {
            Tile tile = Generator.randomTile(prob, 0.0);
            assert tile != null;
            int temp = count.get(tile.getColor()) == null ? 0 : count.get(tile.getColor());
            count.put(tile.getColor(), ++temp);

        }

        for (Map.Entry<Tile.Color, Integer> entry : count.entrySet()) {
            if (entry.getKey().equals(Tile.Color.PINK))
                assertEquals( 148000, entry.getValue(), 1000);
            else
                assertEquals(142000, entry.getValue(), 1000);
        }
    }

    @Test
    public void shouldGenerateRareTilesWithProbability1to2Ratio() {
        HashMap<Tile.Color, Double> prob = new HashMap<>();
        prob.put(Tile.Color.BROWN, 1.0);
        int rareCount = 0;
        for (int i = 0; i < 100000; i++) {
            Tile tile = Generator.randomTile(prob, 0.5);
            assert tile != null;
            if (tile.isRare())
                rareCount++;
        }
        assertEquals(50000, rareCount, 500);
    }

}

