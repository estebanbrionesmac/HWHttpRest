package ejbm.mac.com.httprest;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;




public class MainActivity extends AppCompatActivity {

    TextView tv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById( R.id.tv1) ;


    }

    public void getCall(View view) {
/*        try {
            URL url = new URL("http://itunes.apple.com/search?item=trance");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                readStream(in);



            } catch (Throwable t) {
                Toast.makeText(this, "Request failed: " + t.toString(), Toast.LENGTH_LONG).show();
            }finally {
                urlConnection.disconnect();
            }
        }catch ( Exception ex  ) {
            ex.printStackTrace();

        }*/


/*        String response= "";
        try{
//if you are using https, make sure to import java.net.HttpsURLConnection
            URL url=new URL("http://itunes.apple.com/search?item=trance");

            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
//set the output to true, indicating you are outputting(uploading) POST data
            conn.setDoOutput(true);
//once you set the output to true, you don’t really need to set the request method to post, but I’m doing it anyway
            conn.setRequestMethod("POST");

//Android documentation suggested that you set the length of the data you are sending to the server, BUT
// do NOT specify this length in the header by using conn.setRequestProperty("Content-Length", length);
//use this instead.
            //conn.setFixedLengthStreamingMode(param.getBytes().length);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//send the POST out
//            PrintWriter out = new PrintWriter(conn.getOutputStream());
            //out.print(param);
//            out.close();

//build the string to store the response text from the server


//start listening to the stream
            Scanner inStream = new Scanner(conn.getInputStream());

//process the stream and store it in StringBuilder
            while(inStream.hasNextLine())
                response+=(inStream.nextLine());

        }
//catch some error
        catch(MalformedURLException ex){
            Toast.makeText( this, ex.toString(), Toast.LENGTH_SHORT ).show();
            response = ex.getMessage() ;

        }
// and some more
        catch(Exception ex){
            ex.printStackTrace();
            Toast.makeText( this, ex.toString(), Toast.LENGTH_SHORT ).show();
            response = ex.getMessage() ;
        }*/

/*        String readStream= "<>" ;
        try {
            URL url = new URL("http://itunes.apple.com/search?item=trance");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            readStream = readStream(con.getInputStream());
            // Give output for the command line
            System.out.println(readStream);
        } catch (Exception e) {
            e.printStackTrace();
            readStream = e.getMessage() ;
        }


        tv.setText( readStream );*/

        DownloadWebPageTask task = new DownloadWebPageTask();
        task.execute(new String[] { "http://itunes.apple.com/search?item=trance"/*,"http://www.vogella.com"*/ });

    }





    private class DownloadWebPageTask extends AsyncTask <String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
/*            for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return response;

            */

            String readStream= "<>" ;
            try {

                for (String u : urls) {
                    URL url = new URL( u /* "http://itunes.apple.com/search?item=trance"*/ );
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    readStream = readStream(con.getInputStream());
                }
                // Give output for the command line
                System.out.println(readStream);
            } catch (Exception e) {
                e.printStackTrace();
                readStream = e.getMessage() ;
            }

            return readStream ;


        }

        @Override
         protected void onPostExecute(String result) {
            tv.setText(result);
        }

        private  String readStream(InputStream in) {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

                String nextLine = "";
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage() ;
            }
            return sb.toString();
        }
    }




}
