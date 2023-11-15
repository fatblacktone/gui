package restjava;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class GetExample {

    public static void main(String[] args) throws Exception {
        
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            httpclient.getCredentialsProvider().setCredentials(
                    new AuthScope("localhost", 3000),
                    new UsernamePasswordCredentials("admin", "taliesin"));

            HttpGet httpget = new HttpGet("http://localhost:3000/users");
            httpget.addHeader("accept", "application/json");

            
            System.out.println("executing request " + httpget.getRequestLine());
            HttpResponse response = httpclient.execute(httpget);
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
                

                
                
                //System.out.println("Response content length: " + entity.getContentLength());
            }
            EntityUtils.consume(entity);

        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
    }
}
