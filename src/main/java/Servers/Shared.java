package Servers;

import Servers.REST.restServer;
import Servers.Websockets.WebsocketServer;

public class Shared {


    public static void main(String[] args) {
            WebsocketServer.startWebSocketServer();
            restServer.startRest();
    }
}
