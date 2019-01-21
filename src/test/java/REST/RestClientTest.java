package REST;

import Client.REST.RestClient;
import Models.User;
import Servers.REST.PlayerDTO;
import org.junit.Assert;
import org.junit.Test;

public class RestClientTest {

    RestClient client = new RestClient();

    @Test
    public void getPlayer() {
        PlayerDTO player = client.getPlayer(1);

        Assert.assertNotNull(player);
    }

    @Test
    public void testLogin() {

        User user =new User("tom", "mot");
        PlayerDTO player = user.createDTO();
        client.loginPlayer(player);
    }

    @Test
    public void testSignup() {
        User user = new User("bro", "neverguess");
        PlayerDTO player = user.createDTO();
        client.signupPlayer(player);
    }
}