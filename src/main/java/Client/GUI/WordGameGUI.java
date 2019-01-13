package Client.GUI;

import Client.REST.RestClient;
import Client.Websockets.ActiveClient;
import Client.Websockets.ActiveClientEndpoint;
import Client.Websockets.ClientLauncher;
import Models.User;
import Servers.REST.PlayerDTO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;

public class WordGameGUI extends Application {

    private Label lobbyTitleLabel;
    private Label loginLabel;
    private Label signupLabel;

    private  TextField textFieldloginUsername;
    private PasswordField passwordFieldLogin;



    public void start(Stage primaryStage) throws Exception {
        GridPane grid;
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));


        //Login
        //login + signup Labels
        loginLabel = new Label("Login");
        signupLabel = new Label("Sign up");
        grid.add(loginLabel, 0, 0);
        grid.add(signupLabel, 1, 0);

        //Login button
        Button buttonLogin = new Button();
        buttonLogin.setDisable(false);
        buttonLogin.setText("Log In");
        buttonLogin.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                try {
                    LoginButtonEvent(event);
                }

                catch(InvocationTargetException exc) {
                    exc.getCause();
                }

            }
        });
        grid.add(buttonLogin, 0, 3);

        //textfield username
        textFieldloginUsername = new TextField();
        grid.add(textFieldloginUsername, 0, 1);


        //textfield password
        passwordFieldLogin = new PasswordField();
        grid.add(passwordFieldLogin, 0, 2);

        //Signup button
        Button buttonSignup = new Button();
        buttonSignup.setText("Sign Up");
        buttonSignup.setDisable(false);
        buttonSignup.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    SignupButtonEvent(event);
                }

                catch(InvocationTargetException exc) {
                    exc.getCause();
                }
            }
        });
        grid.add(buttonSignup, 1, 3);

        //Lobby
        //lobby title
        lobbyTitleLabel = new Label("Lobby");
        grid.add(lobbyTitleLabel, 0, 4);

        //button enter random lobby
        Button buttonEnterRandom = new Button();
        buttonEnterRandom.setDisable(true);
        buttonEnterRandom.setText("Random Lobby");
        buttonEnterRandom.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

                try {
                    IntoRandomLobby(event);
                }

                catch(InvocationTargetException exc) {
                    exc.getCause();
                }

            }
        });
        grid.add(buttonEnterRandom, 0, 5);


        // set root
        Group root = new Group();
        Scene scene = new Scene(root, 1000, 500);
        root.getChildren().add(grid);

        // Define title and assign the scene for main window
        primaryStage.setTitle("WordGame");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }



    // methods
    //methods enter random lobby
    private void IntoRandomLobby(ActionEvent event) throws InvocationTargetException {

        System.out.println("shit");


    }

    //method login
    private void LoginButtonEvent(ActionEvent event) throws InvocationTargetException {
        String loginUserText = textFieldloginUsername.getText();
        String loginPassText = passwordFieldLogin.getText();
        System.out.println(loginUserText + " " + loginPassText);
        RestClient client = new RestClient();
        User user = new User(loginUserText, loginPassText);
        PlayerDTO player = user.createDTO();

        System.out.println(player.getPlayerName() + " " + player.getPlayerPass());

        if(client.loginPlayer(player)) {
            System.out.println("yo");
        }

        else {
            System.out.println("You are not logged in.");
        }




    }

    private void SignupButtonEvent(ActionEvent event) throws InvocationTargetException {
        System.out.println("dumbass");
        String signupUserText = textFieldloginUsername.getText();
        String signupPassText = textFieldloginUsername.getText();

        RestClient client  = new RestClient();

    }

}
