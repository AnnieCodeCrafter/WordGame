package Client.GUI;

import Client.REST.RestClient;
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
import org.aopalliance.intercept.Invocation;

import javax.xml.soap.Text;
import java.lang.reflect.InvocationTargetException;

public class WordGameGUI extends Application {

   private Label lobbyTitleLabel;


    private  TextField textFieldloginUsername;
    private PasswordField passwordFieldLogin;

    private Label playerAmount;
    private Label playerIsConnected;

    private Button buttonLogin;
    private Button buttonSignup;
    private Button buttonEnterRandom;


    public void start(Stage primaryStage) throws Exception {
        GridPane grid;
        grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));


        //Login button
        buttonLogin = new Button();
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
        buttonSignup = new Button();
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
        buttonEnterRandom = new Button();
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

        //labels
        playerAmount = new Label("");
        playerIsConnected = new Label("");
        grid.add(playerAmount, 1, 5);
        grid.add(playerIsConnected, 1, 6);

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

        int pl = 0;

       // get the amount of players and put them in the labels
        playerIsConnected.setText("true");
        playerAmount.setText(Integer.toString(pl));


    }

    //method login
    private void LoginButtonEvent(ActionEvent event) throws InvocationTargetException {
        String loginUserText = textFieldloginUsername.getText();
        String loginPassText = passwordFieldLogin.getText();
        System.out.println(loginUserText + " " + loginPassText);
        RestClient client = new RestClient();
        User user = new User(loginUserText, loginPassText);
        PlayerDTO player = user.createDTO();

        System.out.println(player.getName() + " " + player.getPlayerPass());

        if(client.loginPlayer(player)) {
            buttonLogin.setDisable(true);
            buttonSignup.setDisable(true);
            buttonEnterRandom.setDisable(false);

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
