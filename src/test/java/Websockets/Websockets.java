package Websockets;

import Client.Websockets.ActiveClient;
import Client.Websockets.ActiveClientEndpoint;
import Models.User;
import Servers.REST.PlayerDTO;
import org.junit.Test;

public class Websockets {

    @Test
    public void testSend() {
        ActiveClientEndpoint endpoint = new ActiveClientEndpoint();
        ActiveClient client = new ActiveClient(endpoint);
        User user = new User("tom", "mot");
        PlayerDTO player = user.createDTO();
        client.getUser(player);
        client.littleStart();

    }
}
