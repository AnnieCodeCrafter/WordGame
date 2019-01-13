package Servers.REST;

public class PlayerDTO {

    private String playerName;
    private String playerPass;
    private int playerID;

    public PlayerDTO() {

    }

    public PlayerDTO(String playerName, String playerPass, int playerID) {
        this.playerName = playerName;
        this.playerPass = playerPass;
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerPass() {
        return playerPass;
    }


    @Override
    public String toString() {return "player id: " + playerID + "player name: " + playerName + " player password: " + playerPass;}

}
