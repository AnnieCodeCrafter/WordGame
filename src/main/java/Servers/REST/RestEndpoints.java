package Servers.REST;


import Client.Websockets.ActiveClient;
import Client.Websockets.ActiveClientEndpoint;
import Client.Websockets.ClientLauncher;
import Database.PreparedStatements;
import Models.User;
import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/authentication")
public class RestEndpoints {

   PreparedStatements statements = new PreparedStatements();

   private Gson gson = new Gson();

    @GET
    @Path("/player/{playerid}")
    @Produces("application/json")
    public Response getPlayer(@PathParam("playerid") String playerIdString) {

        System.out.println("[Server getPlayer]");


        //Find player

        User user = statements.getUserByID(Integer.parseInt(playerIdString));

        // Check whether player exists
        if (user == null) {
            // Client error 400 - Bad Request
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        // Define response
        return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(user)).build();
    }


    @GET
    @Path("/player/all")
    @Produces("application/json")
    public Response getAllPlayers() {

        System.out.println("[Server getAllPlayers]");

        // Get all players from the store
        List<User> allUsers = statements.getAllUsers();

        // Define response
        return Response.status(200).entity(RestResponseHelper.getAllPlayersResponse(RestResponseHelper.getPlayerDTOList(allUsers))).build();
    }


    @POST
    @Path("/player")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlayer(String requestBody ) {



        PlayerDTO playerRequest = new PlayerDTO();

        try {
            playerRequest = gson.fromJson(requestBody, PlayerDTO.class);
        }

        catch(Exception e)  {

        }
        System.out.println("[Server addPlayer]");

        // Check request
        if (playerRequest == null) {
            // Client error 400 - Bad Request
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        // Add player
        String playerName = playerRequest.getName();
        String playerPass = playerRequest.getPlayerPass();
        User user = new User(playerName, playerPass);
        statements.addUser(playerName, playerPass);

        // Define response
        return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(user)).build();
    }

    @POST
    @Path("/player/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginPlayer(String requestBody) {

        PlayerDTO playerRequest = new PlayerDTO();

        try {
            playerRequest = gson.fromJson(requestBody, PlayerDTO.class);
        }

        catch(Exception e)  {

        }
        System.out.println("Server [loginPlayer]");

        // Check request
        if (playerRequest == null) {
            // Client error 400 - Bad Request
            System.out.println("No player given");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();

        }
        // see if player exists

        System.out.println(playerRequest.getName() + " " + playerRequest.getPlayerPass());

        if(!statements.CheckUser(playerRequest.getName(), playerRequest.getPlayerPass())) {
            System.out.println("Wrong login");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        User user = new User(playerRequest.getName(), playerRequest.getPlayerPass());
        ClientLauncher.startClient(ActiveClientEndpoint.class);



        return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(user)).build();
    }

    @POST
    @Path("/player/signup")
    @Consumes("application/json")
    @Produces("application/json")
    public Response playerSignup(String requestBody) {
        PlayerDTO player = new PlayerDTO();

        try {
            player = gson.fromJson(requestBody, PlayerDTO.class);
        }

        catch(Exception e)  {

        }

        if (player == null) {

            //no player given
            System.out.println("No player");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();

        }

        if(player.getName() == null || player.getPlayerPass() == null) {
            //serialization shizzle
            System.out.println("Player missing values");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        //check if it already exists
        if(statements.CheckUser(player.getName(),player.getPlayerPass())) {

            System.out.println("Player already exists");
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        User user = new User(player.getName(), player.getPlayerPass());
        statements.addUser(player.getName(), player.getPlayerPass());
        return Response.status(200).entity(RestResponseHelper.getSinglePlayerResponse(user)).build();

    }
}
