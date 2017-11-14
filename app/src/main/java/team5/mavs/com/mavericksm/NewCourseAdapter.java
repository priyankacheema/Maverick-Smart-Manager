package team5.mavs.com.mavericksm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Avinash on 11/24/2016.
 */
public class NewCourseAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,String>> list;
    Context context;
    NewCourseAdapter(Context c, ArrayList<HashMap<String, String>> data)
    {
        context = c;
        list = data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        1. get the root view
        2. use the root view to find other views
        3. set the values
         */
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_enroll_courses_raw,parent,false);
        TextView tvCourseID = (TextView) row.findViewById(R.id.tvCourseID);
        TextView tvCourseName = (TextView) row.findViewById(R.id.tvCourseName);
        TextView tvTime = (TextView) row.findViewById(R.id.tvTime);
        TextView tvLocation = (TextView) row.findViewById(R.id.tvLocation);
        TextView tvProfessor = (TextView) row.findViewById(R.id.tvProfessor);
        HashMap<String,String> hashMenuItems= new HashMap<>();
        hashMenuItems= list.get(position);

        tvCourseID.setText("COURSE ID: " + hashMenuItems.get("course_id"));
        tvCourseName.setText("COURSE NAME: " + hashMenuItems.get("course_name"));
        tvTime.setText("TIME: "+ hashMenuItems.get("time"));
        tvLocation.setText("LOCATION: " + hashMenuItems.get("location"));
        tvProfessor.setText("PROFESSOR: " + hashMenuItems.get("professor"));




        StudentCourses Record = new StudentCourses();
        EnrollCourse Records = new EnrollCourse();
        DeleteCourse dRecord = new DeleteCourse();
        Record.SingleRecord=list;
        Records.SingleRecord=list;
        dRecord.SingleRecord=list;



        return row;
    }
}
