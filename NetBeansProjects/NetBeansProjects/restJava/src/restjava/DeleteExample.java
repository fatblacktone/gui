package restjava;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class DeleteExample {

    public static void Delete(User user) throws Exception {
        
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials("admin", "taliesin"));

            HttpDelete httpdelete = new HttpDelete("http://localhost:3000/users/"+user.getId());
            httpdelete.addHeader("accept", "application/json");

            
            System.out.println("executing request " + httpdelete.getRequestLine());
            HttpResponse response = httpclient.execute(httpdelete);
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
