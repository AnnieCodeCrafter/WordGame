package Client.Websockets;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientLauncher {
    private static final int PORT = 8095;
    private static final String endpoint = "/endpoint";
    private static final String host = "ws://0.0.0.0";

    public static void startClient(Class<?> endpointClass){

        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            String uri = host + ":" + PORT + endpoint;
            System.out.println("Connecting to " + uri);
            container.connectToServer(endpointClass, URI.create(uri));
        } catch (Exception ex) {
            Logger.getLogger(endpointClass.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String argv[]){
        startClient(ActiveClientEndpoint.class);
    }

}
