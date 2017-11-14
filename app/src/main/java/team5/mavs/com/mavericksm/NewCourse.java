package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
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

public class NewCourse extends Activity {

    Spinner spinnerCourse;
    Object item;
    String name,id;
    UserLocalStore userLocalStore;
    EditText courseid,coursename,department,time,location;
    String scourseid,scoursename,sdepartment,stime,slocation,sprofessor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        name = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");

        spinnerCourse = (Spinner)findViewById(R.id.courseSpinner);

        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            } });

        courseid = (EditText)findViewById(R.id.etCourseID);
        coursename = (EditText)findViewById(R.id.etCourseName);
        department = (EditText)findViewById(R.id.etDepartment);
        time = (EditText)findViewById(R.id.etTime);
        location = (EditText)findViewById(R.id.etLocation);

    }


    public void onCourseClick(View view)
    {
        scourseid = courseid.getText().toString();
        scoursename = coursename.getText().toString();
        sdepartment = department.getText().toString();
        stime = time.getText().toString();
        slocation = location.getText().toString();
        sprofessor = item.toString();

        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(scourseid,scoursename,sdepartment,stime,slocation,sprofessor);
        Intent i =new Intent(this,MainActivity.class);
        i.putExtra("id",id);
        i.putExtra("name",name);
        startActivity(i);
        finish();
    }

    class BackgroundTask extends AsyncTask<String, Void, String>
    {

        String id,name,depart,time,locn,prof;
        String add_info_url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            add_info_url = "http://mavsuta.co.nf/NewCourse.php";

        }

        @Override
        protected String doInBackground(String... args) {

            id = args[0];
            name = args[1];
            depart = args[2];
            time = args[3];
            locn = args[4];
            prof = args[5];


            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
                        URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("depart","UTF-8")+"="+URLEncoder.encode(depart,"UTF-8")+"&"+
                        URLEncoder.encode("time","UTF-8")+"="+URLEncoder.encode(time,"UTF-8")+"&"+
                        URLEncoder.encode("locn","UTF-8")+"="+URLEncoder.encode(locn,"UTF-8")+"&"+
                        URLEncoder.encode("prof","UTF-8")+"="+URLEncoder.encode(prof,"UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS=httpURLConnection.getInputStream();
                IS.close();
                httpURLConnection.disconnect();

                return "New Course Added Successfully...";

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
