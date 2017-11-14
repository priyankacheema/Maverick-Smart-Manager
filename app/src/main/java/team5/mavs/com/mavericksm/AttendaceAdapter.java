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
 * Created by Avinash on 11/30/2016.
 */
public class AttendaceAdapter extends BaseAdapter {

    private ArrayList<HashMap<String,String>> list;
    Context context;
    AttendaceAdapter(Context c, ArrayList<HashMap<String, String>> data)
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
        View row = inflater.inflate(R.layout.activity_raw_mark_attendance,parent,false);
        TextView textView9 = (TextView) row.findViewById(R.id.textView9);
        HashMap<String,String> hashMenuItems= new HashMap<>();
        hashMenuItems= list.get(position);

        textView9.setText(hashMenuItems.get("student_name"));




        MarkAttendance Record = new MarkAttendance();
        Record.SingleRecord=list;



        return row;
    }
}

