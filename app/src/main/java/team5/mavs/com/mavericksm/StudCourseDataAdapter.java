package team5.mavs.com.mavericksm;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avinash on 11/22/2016.
 */
public class StudCourseDataAdapter extends ArrayAdapter {

    List list = new ArrayList();
    public StudCourseDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(StudCourseData object) {
        super.add(object);
        list.add(object);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHolder dataHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.activity_stud_course_raw,parent,false);
            dataHolder = new DataHolder();
            dataHolder.tv_CourseID = (TextView)row.findViewById(R.id.tvCourseID);
            dataHolder.tv_CourseName = (TextView)row.findViewById(R.id.tvCourseName);
            dataHolder.tv_Time = (TextView)row.findViewById(R.id.tvTime);
            dataHolder.tv_Location = (TextView)row.findViewById(R.id.tvLocation);
            dataHolder.tv_Professor = (TextView)row.findViewById(R.id.tvProfessor);

            row.setTag(dataHolder);
        }
        else
        {
            dataHolder = (DataHolder)row.getTag();
        }
        StudCourseData data = (StudCourseData) this.getItem(position);
        dataHolder.tv_CourseID.setText(data.getCourse_id());
        dataHolder.tv_CourseName.setText(data.getCourse_name());
        dataHolder.tv_Time.setText(data.getTime());
        dataHolder.tv_Location.setText(data.getLocation());
        dataHolder.tv_Professor.setText(data.getProfessor());
        return row;
    }

    static class DataHolder
    {
        TextView tv_CourseID,tv_CourseName,tv_Time,tv_Location,tv_Professor;
    }
}
