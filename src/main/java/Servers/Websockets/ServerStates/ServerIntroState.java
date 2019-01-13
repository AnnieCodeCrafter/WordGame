package Servers.Websockets.ServerStates;

import Servers.Websockets.ServerStates.Interfaces.SendMessages;
import Servers.Websockets.ServerStates.Interfaces.ServerState;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerIntroState implements ServerState {


    private static List<Session> sessionList = new ArrayList<>();
    private static SendMessages SenderMesssages;




    public ServerIntroState(SendMessages sm){
        SenderMesssages = sm;
    }

    @Override
    public void SessionMapper(String message, Session session) {

    }

    @Override
    public void add(Session session) {

    }

    @Override
    public void remove(Session session) {

    }
}
