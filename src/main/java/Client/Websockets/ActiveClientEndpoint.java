package Client.Websockets;


import javax.websocket.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ClientEndpoint
public class ActiveClientEndpoint {


    private Session connection;

    private ActiveClient myclient = new ActiveClient(this);


    public void send(String message){
        try {
            System.out.println(String.format("Sending message %s, to %s: ",message, connection.getId()));
            connection.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(ActiveClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        connection = session;
        System.out.println("Connected to endpoint: " + session.getBasicRemote());

        myclient.consoleStart();
    }

    @OnMessage
    public void processMessage(String message) {
        System.out.println("Received message in client: " + message);
        myclient.handleIncoming(message);

    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }


}
