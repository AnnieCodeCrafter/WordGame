package Models;

import Servers.REST.PlayerDTO;

import java.sql.Time;
import java.util.Timer;

public class User {

    private String userName;
    private String pword;
    private int id;
    private String SessionID;
    private int wins;
    private int points;

   private GameTimer gameTimer;


    public User() {

    }

    public User(String username, String password) {
        this.userName = username;
        this.pword = password;
    }

    public User(int id, String username, String password) {
        this.userName = username;
        this.pword = password;
        this.id = id;
    }

    public User(String username, String password, String sessionID) {
        this.userName = username;
        this.pword = password;
        this.SessionID = sessionID;
    }

    public Timer GetPlayerTimer() {
        return gameTimer.getPlayerTimer();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public String getPword() {
        return this.pword;
    }

    public int getId() {
        return this.id;
    }

    public int getWins() {
        return this.wins;
    }

    public int getPoints() {
        return this.points;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String sessionID) {
        this.SessionID = sessionID;
    }



    public PlayerDTO createDTO() {
        return new PlayerDTO(userName, pword, id);
    }
}
