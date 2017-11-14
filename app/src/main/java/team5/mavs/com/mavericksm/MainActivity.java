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

public class MainActivity extends Activity implements View.OnClickListener{

    Button btLogout,btCourse,btAnnmt,btDeleteCourse,btAdminfName,btViewProf;
    UserLocalStore userLocalStore;
    String name,id;
    String json_string;
    String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userLocalStore = new UserLocalStore(this);
        name = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");

        Button btAdminfName=(Button) findViewById(R.id.btAdminfName);
        btAdminfName.setText(name);
        btLogout = (Button)findViewById(R.id.btLogout);
        btCourse = (Button)findViewById(R.id.btCourse);
        btAnnmt = (Button)findViewById(R.id.btAnnmt);
        btDeleteCourse= (Button)findViewById(R.id.btDeleteCourse);
        btViewProf= (Button)findViewById(R.id.btViewProf);

        new BackgroundTasks().execute();

        btLogout.setOnClickListener(this);
        btCourse.setOnClickListener(this);
        btAnnmt.setOnClickListener(this);
        btDeleteCourse.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
        btViewProf.setOnClickListener(this);
    }


    class BackgroundTasks extends AsyncTask<Void,Void,String>
    {


        String json_url;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            json_url = "http://mavsuta.co.nf/DeleteCourse.php";
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogout:

                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                startActivity(new Intent(this,Login.class));
                Toast.makeText(getApplicationContext(),"Logged Out Successfully", Toast.LENGTH_LONG).show();

                break;
            case R.id.btCourse:
                Intent a =new Intent(this,NewCourse.class);
                a.putExtra("id",id);
                a.putExtra("name",name);
                startActivity(a);
                break;
            case R.id.btDeleteCourse:
                Intent i =new Intent(this,DeleteCourse.class);
                i.putExtra("id",id);
                i.putExtra("name",name);
                startActivity(i);
                break;

            case R.id.btAnnmt:
                String from = "Advisor";
                Intent intent =new Intent(this,Announcement.class);
                intent.putExtra("name", name);
                intent.putExtra("id",id);
                intent.putExtra("professor",from);
                startActivity(intent);
                break;
            case R.id.btViewProf:
                Intent y =new Intent(this,ProfessorsProfile.class);
                y.putExtra("name", name);
                y.putExtra("id",id);
                startActivity(y);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (authenticate() == true) {
            String role = userLocalStore.getRole();
            if(role == "ADVISER"){
            displayUserDetails();}
        }
        else
        {
            startActivity(new Intent(this,Login.class));
        }
    }

    private boolean authenticate() {
        if (userLocalStore.getLoggedInUser() == null) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            return false;
        }
        return true;
    }

    private void displayUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        //etUsername.setText(user.username);
        //etName.setText(user.name);
        //etAge.setText(user.age + "");
    }
}
