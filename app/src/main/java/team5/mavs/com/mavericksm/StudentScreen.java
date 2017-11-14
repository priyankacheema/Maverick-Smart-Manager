package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class StudentScreen extends Activity implements View.OnClickListener {

    Button btStDropC,btStudName,btViewMessages,btSUploadA,btViewStudy,btStCCourse,btViewAtt;
    String name,id;
    String COURSE_STRING,course_string;
    String course_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_screen);
        Intent myIntent = getIntent();
        course_id = myIntent.getStringExtra("course_id");
        id = getIntent().getExtras().getString("id");
        btStDropC = (Button)findViewById(R.id.btStDropC);
        btViewMessages = (Button)findViewById(R.id.btViewMessages);
        btSUploadA = (Button)findViewById(R.id.btSUploadA);
        btViewStudy = (Button)findViewById(R.id.btViewStudy);
        btStCCourse = (Button)findViewById(R.id.btStCCourse);
        btViewAtt = (Button)findViewById(R.id.btViewAtt);

        name = getIntent().getExtras().getString("name");
        btStudName = (Button)findViewById(R.id.btStudName);
        btStudName.setText(course_id);
        btStDropC.setOnClickListener(this);
        btViewAtt.setOnClickListener(this);
        btSUploadA.setOnClickListener(this);
        btStCCourse.setOnClickListener(this);
        btViewMessages.setOnClickListener(this);

        btViewStudy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String from = "Student";
        switch (view.getId()) {
            case R.id.btLogout:
                startActivity(new Intent(this, Login.class));
                Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_LONG).show();

                break;
            case R.id.btStDropC:
                new BackgroundTask().execute();
                Intent a =new Intent(this,Student.class);
                a.putExtra("name",name);
                a.putExtra("id", id);
                startActivity(a);
                break;
            case R.id.btViewMessages:
                Intent b =new Intent(this,displayListView.class);
                b.putExtra("name",name);
                b.putExtra("id", id);
                b.putExtra("from", from);
                startActivity(b);
                break;
            case R.id.btSUploadA:
                Intent aa =new Intent(this,Assignment.class);
                aa.putExtra("name",name);
                aa.putExtra("id", id);
                aa.putExtra("from", from);
                aa.putExtra("course_id",course_id);
                startActivity(aa);
                break;
            case R.id.btViewStudy:
                Intent i =new Intent(this,DownloadStudyMaterials.class);
                i.putExtra("name",name);
                i.putExtra("id", id);
                i.putExtra("from", from);
                i.putExtra("course_id",course_id);
                startActivity(i);
                break;
            case R.id.btStCCourse:
                Intent v =new Intent(this,ViewGrades.class);
                v.putExtra("name",name);
                v.putExtra("id", id);
                v.putExtra("from", from);
                v.putExtra("course_id",course_id);
                startActivity(v);
                break;
            case R.id.btViewAtt:
                Intent vt =new Intent(this,ViewAttendance.class);
                vt.putExtra("name",name);
                vt.putExtra("id", id);
                vt.putExtra("from", from);
                vt.putExtra("course_id",course_id);
                startActivity(vt);
                break;

        }

    }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {

        String add_info_url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            add_info_url = "http://mavsuta.co.nf/DeleteC.php";

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

                String data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
                        URLEncoder.encode("course_id","UTF-8")+"="+URLEncoder.encode(course_id,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();

                return "Course is Dropped Successfully...";

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
