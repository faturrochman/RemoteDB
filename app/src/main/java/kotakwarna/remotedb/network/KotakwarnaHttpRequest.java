package kotakwarna.remotedb.network;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar on 6/25/2014.
 */
public class KotakwarnaHttpRequest {

    private String url;

    public KotakwarnaHttpRequest(String url) {
        this.url = url;
    }

    public String getResponse() {

        String response = null;
        Log.i("url ", url);

        response = getJSON(url);
        Log.i("Response ", response);
        return response;
    }

    public String requestPost(List<KotakWarnaParam> params){

        StringBuilder builder = new StringBuilder();
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(params.size());
            for(KotakWarnaParam param:params){
                nameValuePairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                Log.i("param.getKey()", param.getKey());
                Log.i("param.getValue()", param.getValue());
            }
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));



            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == 200){
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while((line = reader.readLine()) != null){
                    builder.append(line);
                }
            } else {
                Log.e("Error","Failed to post Request");
            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        

        return builder.toString();
    }

    // ---Connects using HTTP GET---
    public static InputStream OpenHttpGETConnection(String url) {
        InputStream inputStream = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return inputStream;
    }

    public String getJSON(String address){
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(address);
        try{
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if(statusCode == 200){
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while((line = reader.readLine()) != null){
                    builder.append(line);
                }
            } else {
                Log.e("Error","Failed to get JSON object");
            }
        }catch(ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }

}
