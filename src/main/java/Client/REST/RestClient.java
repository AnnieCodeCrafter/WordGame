package Client.REST;

import Servers.REST.PlayerDTO;
import Servers.REST.RestResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RestClient {
    private final String url = "http://localhost:8096/authentication";

    private final Gson gson = new Gson();

    private final int NOTDEFINED = -1;

    public RestClient() {

    }

    public PlayerDTO getPlayer(int id) {
        try {
            String queryGet = "/player/" + id;
            RestResponse response = executeQueryGet(queryGet);
            return response.getPlayers().get(0);
        }

        catch(NullPointerException nullexc) {
            System.out.println(nullexc.getMessage());
            return null;
        }

    }

    public boolean loginPlayer(PlayerDTO playerRequest) {
        String queryGet = "/player/login";
        RestResponse response = executeQueryPost(playerRequest, queryGet);
        PlayerDTO dtp =  response.getPlayers().get(0);
        if(dtp != null) {
            return true;
        }

        else {
            return false;
        }
    }



    private RestResponse executeQueryGet(String queryGet) {

        // Build the query for the REST service
        final String query = url + queryGet;
        System.out.println("[Query Get] : " + query);

        // Execute the HTTP GET request
        HttpGet httpGet = new HttpGet(query);
        return executeHttpUriRequest(httpGet);
    }

    private RestResponse executeQueryPost(PlayerDTO playerRequest, String queryPost) {

        // Build the query for the REST service
        final String query = url + queryPost;
        System.out.println("[Query Post] : " + query);

        // Execute the HTTP POST request
        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(playerRequest));
            httpPost.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeHttpUriRequest(httpPost);
    }

    private RestResponse executeQueryPut(PlayerDTO playerRequest, String queryPut) {

        // Build the query for the REST service
        final String query = url + queryPut;
        System.out.println("[Query Put] : " + query);

        // Execute the HTTP PUT request
        HttpPut httpPut = new HttpPut(query);
        httpPut.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(playerRequest));
            httpPut.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return executeHttpUriRequest(httpPut);
    }

    private RestResponse executeQueryDelete(String queryDelete) {

        // Build the query for the REST service
        final String query = url + queryDelete;
        System.out.println("[Query Delete] : " + query);

        // Execute the HTTP DELETE request
        HttpDelete httpDelete = new HttpDelete(query);
        return executeHttpUriRequest(httpDelete);
    }


    private RestResponse executeHttpUriRequest(HttpUriRequest httpUriRequest) {

        // Execute the HttpUriRequest
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
            System.out.println("[Status Line] : " + response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);
            System.out.println("[Entity] : " + entityString);
            RestResponse restResponse = gson.fromJson(entityString, RestResponse.class);
            return restResponse;
        } catch (IOException e) {
            System.out.println("IOException : " + e.toString());
            RestResponse restResponse = new RestResponse();
            restResponse.setSuccess(false);
            return restResponse;
        } catch (JsonSyntaxException e) {
            System.out.println("JsonSyntaxException : " + e.toString());
            RestResponse restResponse = new RestResponse();
            restResponse.setSuccess(false);
            return restResponse;
        }
    }





}
