package Library;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/** Generates tiles according to probabilities
 */
public class Generator {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Generates random tiles according to probabilities
     * @param probabilities - HashMap representing probability of each tile
     * @param rareProbability - probability (0-1) that tile will be rare
     * @throws InvalidConfigException if sum of probabilities is != 1
     * @return random Tile
     */
    public static Tile randomTile(HashMap<Tile.Color, Double> probabilities, double rareProbability) {

        double sum = probabilities.values().stream().reduce(0.0, Double::sum);
        boolean isRare = false;
        if (Math.abs(sum-1.0)>0.001)
            throw new InvalidConfigException("Sum of all probabilities for color must be equal 1");

        double rand = RANDOM.nextDouble();

        if (rand < rareProbability)
            isRare = true;

        rand = RANDOM.nextDouble();

        double border = 0.0, nextBorder = 0.0;
        for (Map.Entry<Tile.Color, Double> entry : probabilities.entrySet()) {
            nextBorder += entry.getValue();
            if (border <= rand && rand < nextBorder) {
                return new Tile(entry.getKey(), isRare);
            }
        }
        return null;
    }
}
