package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sreedhar on 11/28/2016.
 */

public class AssignGrade extends Activity implements View.OnClickListener {
    Button button6,button7,button8;
    String name,id,course_id;
    public static ArrayList<HashMap<String,String>> SingleRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_assignment);
        Button btProfName=(Button) findViewById(R.id.btProfName);

        name = getIntent().getExtras().getString("name");
        course_id = getIntent().getExtras().getString("course_id");



        button6 = (Button)findViewById(R.id.button6 );
        button6.setOnClickListener(this);
        button7 = (Button)findViewById(R.id.button7 );
        button7.setOnClickListener(this);
        button8 = (Button)findViewById(R.id.button8 );
        button8.setOnClickListener(this);

        id = getIntent().getExtras().getString("id");
    }

    @Override
    public void onClick(View view) {
        String from = "professor";
        switch (view.getId()) {
            case R.id.button6:
                Intent i = new Intent(this, MarkGrades.class);
                i.putExtra("name", name);
                i.putExtra("id", id);
                i.putExtra("course_id",course_id);
                i.putExtra("professor",from);
                startActivity(i);
                break;
            case R.id.button7:
                Intent i1 = new Intent(this, MarkGrades.class);
                i1.putExtra("name", name);
                i1.putExtra("id", id);
                i1.putExtra("course_id",course_id);
                i1.putExtra("professor",from);
                startActivity(i1);
                break;
            case R.id.button8:
                Intent i2 = new Intent(this, MarkGrades.class);
                i2.putExtra("name", name);
                i2.putExtra("id", id);
                i2.putExtra("course_id",course_id);
                i2.putExtra("professor",from);
                startActivity(i2);
                break;




        }
    }
}


