package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class StudenList extends Activity {

    android.widget.ListView list_view;
    String course_id;
    String course_name;
    String time;
    String location;
    String professor;
    public static ArrayList<HashMap<String,String>> SingleRecord;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
    }
}
