package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class rawCourse extends Activity {

    RelativeLayout rawCourse;
    TextView tvCourseID1;
    String tvCourseID;
    ListView list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raw_course);
    }

}
