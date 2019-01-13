package Models;

import java.util.Timer;

public class GameTimer {

    private Timer playerTimer;
    private Timer overallGameTimer;

    public GameTimer(Timer playerTimer, Timer gameTimer) {
        this.playerTimer = playerTimer;
        this.overallGameTimer = gameTimer;
    }

    public GameTimer() {

    }

    public Timer getPlayerTimer() {
        return playerTimer;
    }

    public Timer getOverallGameTimer() {
        return overallGameTimer;
    }


}
