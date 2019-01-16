package Servers.REST;

public class PlayerDTO {

    private String name = "";
    private String password = "";
    private int playerID = 0;
    private String dummy = "dummy";

    public PlayerDTO() {

    }

    public PlayerDTO(String name, String playerPass, int playerID) {

        this.name = name;
        this.password = playerPass;
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayerPass() {
        return password;
    }


    @Override
    public String toString() {return "player id: " + playerID + ", player name: " + name + ", player password: " + password;}

}
