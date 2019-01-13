package Models;

import java.util.Timer;

public class Game {

    private GameTimer gameTimer;
    private GameModus gameModus;

    public Timer getOverallGameTimer() {
        return gameTimer.getOverallGameTimer();
    }

    public GameModus getGameModus() {
        return gameModus;
    }

}

