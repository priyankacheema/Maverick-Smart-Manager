package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfessorScreen extends Activity implements View.OnClickListener {

    Button btSendMsg,btName,btUploadStudy,btUploadAssign,btMarkAtt,btAssignGrade;
    String name,id,course_id;
    public static ArrayList<HashMap<String,String>> SingleRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_screen);
        Button btProfName=(Button) findViewById(R.id.btProfName);
        btUploadStudy = (Button)findViewById(R.id.btUploadStudy);
        btUploadAssign = (Button)findViewById(R.id.btUploadAssign);
        btMarkAtt = (Button)findViewById(R.id.btMarkAtt);
        btName = (Button)findViewById(R.id.btName);
        name = getIntent().getExtras().getString("name");
        course_id = getIntent().getExtras().getString("course_id");

        btName.setText(name);

        btSendMsg = (Button)findViewById(R.id.btSendMsg);
        btSendMsg.setOnClickListener(this);
        btUploadStudy.setOnClickListener(this);
        btUploadAssign.setOnClickListener(this);
        btAssignGrade = (Button)findViewById(R.id.btAssignGrade);
        btAssignGrade.setOnClickListener(this);
        btMarkAtt.setOnClickListener(this);
        name = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");
    }

    @Override
    public void onClick(View view) {
        String from = "professor";
        switch (view.getId()) {
            case R.id.btLogout:
                startActivity(new Intent(this, Login.class));
                Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_LONG).show();

                break;
            case R.id.btSendMsg:
                Intent i = new Intent(this, Announcement.class);
                i.putExtra("name", name);
                i.putExtra("id", id);
                i.putExtra("professor",from);
                startActivity(i);
                break;
            case R.id.btUploadStudy:
                Intent a = new Intent(this, Uploadstudymaterial.class);
                a.putExtra("name", name);
                a.putExtra("id", id);
                a.putExtra("from",from);
                startActivity(a);
                break;
            case R.id.btUploadAssign:
                Intent aa = new Intent(this, UploadAssignment.class);
                aa.putExtra("name", name);
                aa.putExtra("id", id);
                aa.putExtra("from",from);
                startActivity(aa);
                break;
            case R.id.btMarkAtt:
                Intent in = new Intent(this, MarkAttendance.class);
                in.putExtra("name", name);
                in.putExtra("id", id);
                startActivity(in);
                break;
            case R.id.btAssignGrade:
                Intent ag = new Intent(this, AssignGrade.class);
                ag.putExtra("name", name);
                ag.putExtra("id", id);
                ag.putExtra("from",from);
                ag.putExtra("course_id",course_id);
                startActivity(ag);
                break;


        }
    }
}
