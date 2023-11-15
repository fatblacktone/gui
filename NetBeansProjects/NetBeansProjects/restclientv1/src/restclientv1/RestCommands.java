/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restclientv1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author mathewkeegan
 */
public class RestCommands {

    ArrayList<User> usersArray = new ArrayList<User>();
    String username, password;

    public RestCommands(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public ArrayList<User> getAllUsers() throws IOException {
        usersArray.clear();
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials(username, password));

            HttpGet httpget = new HttpGet("http://localhost:3000/users");
            httpget.addHeader("accept", "application/json");

            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                String readin;
                String parts[] = null;

                while ((readin = reader.readLine()) != null) {
                    String noQuotes = removeCharacter(readin, '"');
                    parts = noQuotes.split(",");
                }

                for (int counter = 1; counter < parts.length;) {
                    String email = parts[counter++].split(":")[1];
                    String firstname = parts[counter++].split(":")[1];
                    String gradYear = parts[counter++].split(":")[1];
                    int id = Integer.parseInt(parts[counter++].split(":")[1]);
                    Boolean jobs = Boolean.parseBoolean(parts[counter++].split(":")[1]);
                    //counter++;//skip two lines
                    String phoneNumber = parts[counter++].split(":")[1];
                    String surname = parts[counter++].split(":")[1];
                    counter += 2;//skip two lines
                    User tempUser = new User(firstname, surname, phoneNumber, gradYear, jobs, email, "", "", id);
                    usersArray.add(tempUser);
                    System.out.println(usersArray.get(usersArray.size() - 1).toString());
                }
            }
            EntityUtils.consume(entity);

        } finally {
            httpclient.getConnectionManager().shutdown();
        }

        return usersArray;
    }

    public String removeCharacter(String input, char toRemove) {
        char[] newString = new char[input.length()];
        int modifier = 0;
        for (int i = 0; i < input.length(); i++) {
            if (toRemove != input.charAt(i)) {
                newString[i - modifier] = input.charAt(i);
            } else {
                modifier++;
            }
        }
        return new String(newString);
    }

    public void deleteUser(User user) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials(username, password));

            HttpDelete httpdelete = new HttpDelete("http://localhost:3000/users/" + user.getId());
            httpdelete.addHeader("accept", "application/json");

            HttpResponse response = httpclient.execute(httpdelete);
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }

    }

    public void addUser(User user) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials(username, password));

            HttpPost httppost = new HttpPost("http://localhost:3000/users");
            httppost.addHeader("accept", "application/json");

            StringEntity input = new StringEntity("{\"email\":\"" + user.getEmail() + "\",\"firstname\":\"" + user.getForename() + "\",\"grad_year\":" + user.getGradYear() + ",\"jobs\":" + user.getJobs() + ",\"phone\":\"" + user.getPhone() + "\",\"surname\":\"" + user.getSurname() + "\",\"login\":\"" + user.getUsername() + "\",\"password\":\"" + user.getPassword() + "\",\"password_confirmation\":\"" + user.getPassword() + "\"}");

            input.setContentType("application/json");
            httppost.setEntity(input);

            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
            System.out.println(response.getStatusLine());
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    public void editUser(User user) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();

        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials(username, password));

            HttpPut httpput = new HttpPut("http://localhost:3000/users/" + user.getId());
            httpput.addHeader("accept", "application/json");

            StringEntity input = new StringEntity("{\"email\":\"" + user.getEmail() + "\",\"firstname\":\"" + user.getForename() + "\",\"grad_year\":" + user.getGradYear() + ",\"jobs\":" + user.getJobs() + ",\"phone\":\"" + user.getPhone() + "\",\"surname\":\"" + user.getSurname() + "\"}");

            input.setContentType("application/json");
            httpput.setEntity(input);

            HttpResponse response = httpclient.execute(httpput);
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    public void getAllBroadcasts() throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials(username, password));

            HttpGet httpget = new HttpGet("http://localhost:3000/broadcasts");
            httpget.addHeader("accept", "application/json");

            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));

                String readin;
                while ((readin = reader.readLine()) != null) {
                    splitString(readin);
                }

            }
            EntityUtils.consume(entity);

        } finally {
            httpclient.getConnectionManager().shutdown();
        }

    }
    
     public String splitString(String in){
        String input;
        input = in.replaceAll("\"", "");        
        //input = input.replaceAll("]","");
        String parts[] = input.split(" ");
        for(int i=0;i<parts.length;i++){
            System.out.println(parts[i]);
        }
        
        return input;
        
    }

    
}
