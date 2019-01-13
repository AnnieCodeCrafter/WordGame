package Servers.Websockets.ServerStates.Interfaces;

import javax.websocket.Session;
import java.util.Map;

public interface SendMessages {

    public abstract void sendMap(Map<Session, String> sessionMessages);
    public abstract void ChangeStateTo(ServerState newState);
}
