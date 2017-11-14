package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfCourses  extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    CourseDataAdapter courseDataAdapter;
    ListView listProf;
    Button btLogout;
    String name,id,course_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_courses);
        btLogout = (Button)findViewById(R.id.btLogout);

        btLogout.setOnClickListener(this);

        name = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");

        listProf = (ListView) findViewById(R.id.listProf);
        courseDataAdapter = new CourseDataAdapter(this, R.layout.activity_raw_course);
        listProf.setAdapter(courseDataAdapter);
        json_string = getIntent().getExtras().getString("json_course");
        String course_name, time, location;
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;

            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                course_id = JO.getString("course_id");
                course_name = JO.getString("course_name");
                time = JO.getString("time");
                location = JO.getString("location");

                CourseData data = new CourseData(course_id, course_name, time, location);
                courseDataAdapter.add(data);
                count++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        listProf.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            //String item = courseDataAdapter.getItem(i).toString();
            Intent intent = new Intent(this, ProfessorScreen.class);
        intent.putExtra("name", name);
        intent.putExtra("id", id);
        intent.putExtra("course_id", course_id);
            startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogout:
                startActivity(new Intent(this, Login.class));
                Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_LONG).show();

                break;
        }
    }
}
