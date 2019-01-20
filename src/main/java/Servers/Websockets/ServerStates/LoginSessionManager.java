package Servers.Websockets.ServerStates;

import Models.User;
import Servers.REST.PlayerDTO;
import Servers.Websockets.ServerStates.Interfaces.SendMessages;
import Servers.Websockets.ServerStates.Interfaces.ServerState;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginSessionManager implements ServerState {

    public static List<Session> sessionList = new ArrayList<>();
    public static SendMessages SenderMesssages;
    public static ServerIntroState nextstate = null;
    public static Map<Session, String> login = new HashMap<Session, String>();

    public static User user;

    public LoginSessionManager(SendMessages messageSender) {
        SenderMesssages = messageSender;
    }

    public void SessionMapper(String message, Session session) {

        System.out.println("In SessionController");
        Map<Session, String> sessionMessages = new HashMap<Session, String>();


//        if (login.containsKey(session)){
//            String current = login.get(session);
//            if (current.equals("")){
//                if (message.equals("")){
//                    message = "user name:";
//
//                }else{
//                    login.put(session, message);
//                    message = "password:";
//                }
//            }else{
//                //assume that username/password are ok:
//                session.getUserProperties().put("username",current);
//                session.getUserProperties().put("password",message);
//
//                if (nextstate == null){
//                    nextstate = new ServerIntroState(SenderMesssages);
//                }
//                nextstate.add(session);
//                login.remove(session);
//
//                message = "You are now logged in";
//            }
//            sessionMessages.put(session, message);
//            SenderMesssages.sendMap(sessionMessages);
//        }else{
//            if (nextstate==null){
//                System.out.println("ERROR NEXTSTATE MAY NOT BE NULL");
//            }else{
//                nextstate.SessionMapper(message, session);
//            }
//        }

        if(login.containsValue(message)) {
            String[] words = message.split("\\s+");
            String username = words[0];
            String pass = words[1];
            user = new User(username, pass, session.getId());

        }

        String tosend = String.format("from SessionController:%s:%s", session.getId(), message);
        System.out.println(tosend);
    }


    public void add(Session session) {
        sessionList.add(session);
        String special_message = "Newcomer : " + session.getId();
        System.out.println(String.format("Number of sessions has increased: %d",sessionList.size()));
        NoEcho(session, special_message);
    }

    public void remove(Session session) {
        sessionList.remove(session);
        String special_message = "Left us: " + session.getId();
        System.out.println(String.format("Number of sessions have decreased: %d",sessionList.size()));

        NoEcho(session, special_message);


    }

    private void NoEcho(Session session, String special_message)
    {

        Map<Session, String> sessionMessages = new HashMap<Session, String>();
        List<Session>  NoEcho =  new ArrayList<>();
        NoEcho.addAll(sessionList);
        NoEcho.remove(session);

        for (Session ses : NoEcho) {
            sessionMessages.put(ses, special_message);
        }
        SenderMesssages.sendMap(sessionMessages);
    }
}
