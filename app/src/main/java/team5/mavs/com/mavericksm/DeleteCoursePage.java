package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class DeleteCoursePage extends Activity implements View.OnClickListener {

    TextView courseid,coursename,time,location,professor;
    String id,name,course_id;
    Button button,btDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course_page);

        courseid = (TextView)findViewById(R.id.tvCourseID);
        coursename = (TextView)findViewById(R.id.tvCourseName);
        time = (TextView)findViewById(R.id.tvTime);
        location = (TextView)findViewById(R.id.tvLocation);
        professor = (TextView)findViewById(R.id.tvProfessor);
        button = (Button)findViewById(R.id.button);
        btDelete = (Button)findViewById(R.id.btDelete);

        Intent myIntent = getIntent();
        name = getIntent().getExtras().getString("name");
        button.setText(myIntent.getStringExtra("course_id"));
        course_id = getIntent().getExtras().getString("course_id");
        id = getIntent().getExtras().getString("id");
        courseid.setText(myIntent.getStringExtra("course_id"));
        coursename.setText(myIntent.getStringExtra("course_name"));
        time.setText(myIntent.getStringExtra("time"));
        location.setText(myIntent.getStringExtra("location"));
        professor.setText(myIntent.getStringExtra("professor"));

        btDelete.setOnClickListener(this);
    }

    public void onClick(View view) {
        new BackgroundTask().execute();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {

        String add_info_url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            add_info_url = "http://mavsuta.co.nf/DeleteCourse.php";

        }

        @Override
        protected String doInBackground(String... args) {

            try {

                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("course_id","UTF-8")+"="+URLEncoder.encode(course_id,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();

                return "Course Deleted Successfully...";

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
