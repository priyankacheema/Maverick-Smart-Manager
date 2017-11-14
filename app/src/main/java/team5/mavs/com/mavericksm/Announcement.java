package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class Announcement extends Activity {

    EditText title,body,time;
    String stitle,sbody,stime,sender_name,id,from;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        id = getIntent().getExtras().getString("id");
        title = (EditText)findViewById(R.id.etEventTitle);
        body = (EditText)findViewById(R.id.etEventDesc);
        time = (EditText)findViewById(R.id.etEventTime);
        from = getIntent().getExtras().getString("professor");
        intent = getIntent();
        sender_name = intent.getStringExtra("name");
    }
    public void onEventClick(View view)
    {
        stitle = title.getText().toString();
        sbody = body.getText().toString();
        stime = time.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(stitle,sbody,stime,sender_name);
        if((from.toString().trim()).equals("professor")){
            //Toast.makeText(getApplicationContext(),from, Toast.LENGTH_LONG).show();
            Intent b =new Intent(this,ProfessorScreen.class);
            b.putExtra("id",id);
            b.putExtra("name",sender_name);
            startActivity(b);
            finish();
        }
        else
        {
            //Toast.makeText(getApplicationContext(),from+"not called", Toast.LENGTH_LONG).show()
            Intent a =new Intent(this,MainActivity.class);
            a.putExtra("id",id);
            a.putExtra("name",sender_name);
            startActivity(a);
            finish();
        }
    }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {

        String title,body,time,name;
        String add_event_url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            add_event_url = "http://mavsuta.co.nf/NewMessage.php";

        }

        @Override
        protected String doInBackground(String... args) {

            title = args[0];
            body = args[1];
            time = args[2];
            name = args[3];


            try {
                URL url = new URL(add_event_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("title","UTF-8")+"="+URLEncoder.encode(title,"UTF-8")+"&"+
                        URLEncoder.encode("body","UTF-8")+"="+URLEncoder.encode(body,"UTF-8")+"&"+
                        URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"+
                        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(sender_name,"UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();

                return "Announcement sent Successfully...";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
        }
    }

}
