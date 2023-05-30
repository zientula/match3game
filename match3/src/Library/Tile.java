package Library;

public class Tile {

    /**
     * enum Color represents possible colors for tiles
     */
    public enum Color {
        BROWN, BLUE, GREEN, PURPLE, YELLOW, ORANGE, PINK;
        private int points;

        static {
            BROWN.points = 0;
            BLUE.points = 2;
            GREEN.points = 2;
            PURPLE.points = 2;
            YELLOW.points = 5;
            ORANGE.points = 5;
            PINK.points = 5;
        }

        public int getPoints() {
            return points;
        }
    }

    /**
     * Represents effect of the Tile
     */
    enum Effect {
        ROW, COLUMN, CROSS, BOMB, NONE
    }

    private final int points;
    private final Color color;
    private Effect effect = null;


    /**
     * Creates tile with given parameters, without effect
     * @param color Color of the tile
     * @param isRare is tile rare
     */
    public Tile(Color color, boolean isRare) {
        this.color = color;
        if (isRare) {
            points = 100;
            return;
        }
        points = color.points;
        this.effect = Effect.NONE;
    }

    /**
     * Creates tile with effect
     * @param color Color of the tile
     * @param isRare is tile rare
     * @param effect effect of the tile
     */
    public Tile(Color color, boolean isRare, Effect effect) {
        this(color, isRare);
        this.effect = effect;
    }

    /**
     * @return points value of tile
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return Color of the tile
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return whether the tile is rare
     */
    public boolean isRare() {
        return points == 100;
    }

    /**
     * @return effect of the tile
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Sets effect for tile
     * @param effect Effect of the tile
     */
    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "points=" + points +
                ", color=" + color +
                ", effect=" + effect +
                '}';
    }
}
