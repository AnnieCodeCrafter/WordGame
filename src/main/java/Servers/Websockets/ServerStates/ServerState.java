package Servers.Websockets.ServerStates;

import javax.websocket.Session;

public interface ServerState {

        void SessionMapper(String message, Session session);

        void add(Session session);

        void remove(Session session);
}
