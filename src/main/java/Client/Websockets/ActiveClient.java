package Client.Websockets;

import Servers.REST.PlayerDTO;

import java.util.Scanner;

import static java.lang.Math.min;

public class ActiveClient  implements Runnable{

    private Thread currentThread;


    private String name;
    private int id;
    private String message="";

    private ActiveClientEndpoint endpoint;

    private static PlayerDTO playerDTO;

    public ActiveClient(ActiveClientEndpoint ace){
        endpoint = ace;
    }

    public void handleIncoming(String message){

    }

    public void littleStart() {
        currentThread = new Thread(this);
        currentThread.start();
    }
//    public void ConsoleOpens() {
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Hello! You have successfully connected!");
//        System.out.println("What is your name?");
//        name = scanner.next();
//
//        System.out.println("So your name is " + name + "? Nice to meet you. Now you can begin messaging others. What do you want to say? Type 'close' if you want send.");
//        getMessage();
//
//
//    }
//
//    public void getMessage() {
//
//
//        Scanner scanner = new Scanner(System.in);
//        message = scanner.next();
//
//        while(!message.equals("close")) {
//            endpoint.send(message);
//            message = scanner.next();
//        }
//
//
//    }

    public void getUser(PlayerDTO playerDTO) {
        this.playerDTO = playerDTO;

        //endpoint.send(playerDTO.toString());

    }

    public void sendUser() {
        System.out.println("sending player " + playerDTO.toString());
        endpoint.send(playerDTO.toString());
    }


    @Override
    public void run() {
        sendUser();
    }
}
