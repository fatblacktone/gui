package restjava;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Add {

    public static void addUser(User user) throws Exception {
        
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials("admin", "taliesin"));

            HttpPost httppost = new HttpPost("http://localhost:3000/users");
            httppost.addHeader("accept", "application/json");
            
            System.out.println("executing request " + httppost.getRequestLine());
            
            StringEntity input = new StringEntity("{\"email\":\""+user.getEmail()+"\",\"firstname\":\""+user.getForname()+"\",\"grad_year\":"+user.getGradYear()+",\"jobs\":true,\"phone\":\""+user.getPhone()+"\",\"surname\":\""+user.getSurname()+"\"}");            
            input.setContentType("application/json");
            httppost.setEntity(input);
            
            
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            System.out.println("----------------------------------------");
            
            
            
            System.out.println(response.getStatusLine());                                             
            
            if (entity != null) {
                InputStream instream = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                // do something useful with the response                
                String readin;
                while((readin = reader.readLine()) != null) {
                    System.out.println(readin);
                }
                

                
                
            }
            EntityUtils.consume(entity);

        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }
}
