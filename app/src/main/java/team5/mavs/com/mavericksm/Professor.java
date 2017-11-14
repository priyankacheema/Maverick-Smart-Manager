package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Professor extends Activity implements View.OnClickListener {
    Button btLogout,btMyCourse,btProfAnnmt;
    UserLocalStore userLocalStore;
    String name,id;
    String json_string,course_string;
    String JSON_STRING,COURSE_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        Button btProfName=(Button) findViewById(R.id.btProfName);
        name = getIntent().getExtras().getString("name");
        btProfName.setText(name);
        id = getIntent().getExtras().getString("id");
        btLogout = (Button)findViewById(R.id.btLogout);
        btMyCourse = (Button)findViewById(R.id.btMyCourse);
        btProfAnnmt = (Button)findViewById(R.id.btProfAnnmt);

        btLogout.setOnClickListener(this);
        btMyCourse.setOnClickListener(this);
        btProfAnnmt.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);


        new BackgroundTasks().execute();
        new CourseTasks().execute();

    }


    class BackgroundTasks extends AsyncTask<Void,Void,String>
    {


        String json_url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            json_url = "http://mavsuta.co.nf/FetchAnnouncement.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
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
            json_string = result;

        }
    }


    class CourseTasks extends AsyncTask<Void,Void,String>
    {


        String course_url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            course_url = "http://mavsuta.co.nf/FetchCourse1.php";
        }

        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(course_url);
                HttpURLConnection httpURLConnection1=(HttpURLConnection)url.openConnection();
                InputStream inputStream1 = httpURLConnection1.getInputStream();
                BufferedReader bufferedReader1=new BufferedReader(new InputStreamReader(inputStream1));

                StringBuilder stringBuilder1 = new StringBuilder();
                while((COURSE_STRING = bufferedReader1.readLine())!=null)
                {
                    stringBuilder1.append(COURSE_STRING+"\n");
                }

                bufferedReader1.close();
                inputStream1.close();
                httpURLConnection1.disconnect();
                return stringBuilder1.toString().trim();
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
        protected void onPostExecute(String result1) {
            course_string = result1;

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogout:

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this,Login.class));
                Toast.makeText(getApplicationContext(),"Logged Out Successfully", Toast.LENGTH_LONG).show();

                break;
            case R.id.btMyCourse:
                Intent i =new Intent(this,ProfCourses.class);
                i.putExtra("json_course",course_string);
                i.putExtra("name", name);
                i.putExtra("id", id);
                startActivity(i);
                break;

            case R.id.btProfAnnmt:
                String from = "prof";
                Intent intent = new Intent(this,displayListView.class);
                intent.putExtra("json_data",json_string);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                intent.putExtra("from",from);
                startActivity(intent);
        }
    }

}
