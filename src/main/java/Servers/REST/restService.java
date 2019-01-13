package Servers.REST;


import Database.IPreparedStatements;
import Database.PreparedStatements;
import Models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/authentication")
public class restService {

   PreparedStatements statements = new PreparedStatements();


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
    public Response addPlayer(PlayerDTO playerRequest) {

        System.out.println("[Server addPlayer]");

        // Check request
        if (playerRequest == null) {
            // Client error 400 - Bad Request
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        // Add player
        String playerName = playerRequest.getPlayerName();
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
    public Response loginPlayer(PlayerDTO playerRequest) {

        System.out.println("Server [loginPlayer]");

        // Check request
        if (playerRequest == null) {
            // Client error 400 - Bad Request
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }
        // see if player exists

        if(!statements.CheckUser(playerRequest.getPlayerName(), playerRequest.getPlayerPass())) {
            return Response.status(400).entity(RestResponseHelper.getErrorResponseString()).build();
        }

        return Response.status(200).entity(RestResponseHelper.getSuccessResponse(true)).build();
    }
}
