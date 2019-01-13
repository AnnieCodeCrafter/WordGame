package Client.REST;

import Servers.REST.PlayerDTO;
import org.junit.Assert;
import org.junit.Test;

public class RestClientTest {

    RestClient client = new RestClient();

    @Test
    public void testRest() {
        PlayerDTO player = client.getPlayer(1);

        Assert.assertNotNull(player);
    }
}