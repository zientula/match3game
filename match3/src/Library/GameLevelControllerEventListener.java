package Library;

public interface GameLevelControllerEventListener {
    void afterProcess();

    /**
     * Override this with code to execute before tile removal.
     */
    void beforeRemove();

    /**
     * Override this with code to execute after tile removal.
     */
     void afterRemove();

    /**
     * Override this with code to execute after tile tile drop.
     */
    void afterDrop();

    /**
     * Override this with code to execute after tile board fill.
     */
    void afterFill();

    /**
     * Override this with code to execute after tile board reshuffle.
     */
    void afterReshuffle();

     /** Override this with code to execute after tile board process.
     */

}
