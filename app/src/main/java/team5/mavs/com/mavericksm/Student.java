package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Student extends Activity implements View.OnClickListener {
    Button btStACourse,btStCCourse,btStAnnmt,btLogout,btStudName;
    UserLocalStore userLocalStore;
    String name,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        userLocalStore = new UserLocalStore(this);
        //name = userLocalStore.getName();
        name = getIntent().getExtras().getString("name");
        btStudName = (Button)findViewById(R.id.btStudName);
        btStudName.setText(name);
        btLogout = (Button)findViewById(R.id.btLogout);
        btStACourse = (Button)findViewById(R.id.btStACourse);
        btStCCourse = (Button)findViewById(R.id.btStCCourse);
        btStAnnmt = (Button)findViewById(R.id.btStAnnmt);
        btLogout.setOnClickListener(this);
        btStACourse.setOnClickListener(this);
        btStCCourse.setOnClickListener(this);
        btStAnnmt.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
        id = getIntent().getExtras().getString("id");
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
                case R.id.btStAnnmt:
                    String from = "stud";
                    Intent i =new Intent(this,displayListView.class);
                    i.putExtra("name", name);
                    i.putExtra("id", id);
                    i.putExtra("from",from);
                    startActivity(i);
                    break;

                case R.id.btStACourse:
                    Intent intent =new Intent(this,EnrollCourse.class);
                    intent.putExtra("name", name);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    break;

                case R.id.btStCCourse:
                    Intent a =new Intent(this,StudentCourses.class);
                    a.putExtra("name", name);
                    a.putExtra("id", id);
                    startActivity(a);
                    break;


            }
        }
    }