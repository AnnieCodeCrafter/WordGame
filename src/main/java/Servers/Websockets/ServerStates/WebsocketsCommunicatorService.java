package Servers.Websockets.ServerStates;

import Servers.Websockets.ServerStates.Interfaces.SendMessages;
import Servers.Websockets.ServerStates.Interfaces.ServerState;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;

@ServerEndpoint("/endpoint")
public class WebsocketsCommunicatorService implements SendMessages {

    private static ServerState ServerCurrentState = null;


    LoginSessionManager sessions = new LoginSessionManager(this);


    public void sendMap(Map<Session, String> messages){
        System.out.println(String.format("Going to send out to %d clients",messages.size()));

        try {
            for (Session ses : messages.keySet())  {
                ses.getBasicRemote().sendText(messages.get(ses));
                System.out.println("onMessage: to =" + ses.getId() + " Message=" + messages.get(ses));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ChangeStateTo(ServerState newState) {
        ServerCurrentState = newState;
    }


    //When it connects
    @OnOpen
    public void onConnect(Session session) {
        System.out.println("onOpen::" + session.getId());
        if (ServerCurrentState == null){
            ServerCurrentState = new LoginSessionManager(this);
        }
        System.out.println("ping onConnect server");
        ServerCurrentState.add(session);

    }

    //When a message is received
    @OnMessage
    public void onText(String message, Session session) {
        System.out.println(String.format("Received message: %s,  from client %s",message, session.getId() ));
        sessions.SessionMapper(message, session);

    }

    //When the connection is closed
    @OnClose
    public void onClose(/*CloseReason reason,*/ Session session) {
        System.out.println("onClose::" +  session.getId());
        sessions.remove(session);

    }

    //When an error is thrown
    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

}
