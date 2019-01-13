package Servers.Websockets.ServerStates;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginSessionController {

    private static List<Session> sessions = new ArrayList<>();
    private WebsocketsCommunicatorService SenderMesssages;

    public LoginSessionController(WebsocketsCommunicatorService messageSender) {
        SenderMesssages = messageSender;
    }


    public void SessionMapper(String message, Session session) {

        String tosend = String.format("from:%s:%s",session.getId(),message);
        NoEcho(session, tosend);
    }


    public void add(Session session) {
        sessions.add(session);
        String special_message = "Newcomer : " + session.getId();
        System.out.println(String.format("Number of sessions has increased: %d",sessions.size()));

        NoEcho(session, special_message);
    }

    public void remove(Session session) {
        sessions.remove(session);
        String special_message = "Left us: " + session.getId();
        System.out.println(String.format("Number of sessions have decreased: %d",sessions.size()));

        NoEcho(session, special_message);


    }

    private void NoEcho(Session session, String special_message)
    {

        Map<Session, String> sessionMessages = new HashMap<Session, String>();
        List<Session>  NoEcho =  new ArrayList<>();
        NoEcho.addAll(sessions);
        NoEcho.remove(session);

        for (Session ses : NoEcho) {
            sessionMessages.put(ses, special_message);
        }
        SenderMesssages.sendMap(sessionMessages);
    }
}
