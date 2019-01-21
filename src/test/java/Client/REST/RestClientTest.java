package Client.REST;

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
}