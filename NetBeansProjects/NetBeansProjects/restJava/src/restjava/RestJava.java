package restjava;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.print.DocFlavor;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RestJava {

    public static User[] getUsers(String username, String pass) throws Exception {
        User users[] = new User[1000];
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials(username, pass));

            HttpGet httpget = new HttpGet("http://localhost:3000/users.json");

            System.out.println("executing request " + httpget.getRequestLine());
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            System.out.println("----------------------------------------");
            System.out.println(response.getStatusLine());
            if (entity != null) {
                InputStream in = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String tempo = "";
                String parts[] = null;
                while ((tempo = reader.readLine()) != null) {
                    System.out.println(tempo);
                    String noQuotes = StringManagement.removeCharacter(tempo, '"');
                    parts = noQuotes.split(",");
                    for(int i=0;i<parts.length;i++){
                        System.out.println(parts[i]);
                    }
                    
                    
                }
                
                
                //find number of users
                int numberOfUsers = 0;
                for(int counter =1;counter<parts.length;counter++){
                    if(parts[counter].contains("email")){
                        numberOfUsers++;
                    }
                }
                
                System.out.println("shity");
                users = new User[numberOfUsers];
                int arrayPosition =0;
                for(int counter =1;counter<parts.length;){
                    String email = parts[counter++].split(":")[1];
                    String firstname = parts[counter++].split(":")[1];
                    String gradYear = parts[counter++].split(":")[1];
                    int id = Integer.parseInt(parts[counter++].split(":")[1]);
                    counter++;//skip two lines
                    String phoneNumber = parts[counter++].split(":")[1];
                    String lastName = parts[counter++].split(":")[1];
                    counter+=2;//skip two lines
                    users[arrayPosition] = new User(firstname, lastName, phoneNumber, gradYear, email, "", "");
                    users[arrayPosition].setId(id);
                    System.out.println(users[arrayPosition].toString());
                    arrayPosition++;
                }
                System.out.println("Response content length: " + entity.getContentLength());
            }
            
            EntityUtils.consume(entity);

        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
        return users;
    }
}
