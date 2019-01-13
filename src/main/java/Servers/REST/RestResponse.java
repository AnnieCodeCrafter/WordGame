package Servers.REST;

import java.util.List;

public class RestResponse {

    // Indicates whether REST call was successful
    private boolean success;

    // List of pets
    private List<PlayerDTO> players;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
