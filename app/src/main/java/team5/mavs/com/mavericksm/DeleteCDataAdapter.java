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
 * Created by Avinash on 11/20/2016.
 */
public class DeleteCDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public DeleteCDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(DeleteCData object) {
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
            row = layoutInflater.inflate(R.layout.activity_delete_raw,parent,false);
            dataHolder = new DataHolder();
            dataHolder.tv_course_id = (TextView)row.findViewById(R.id.button4);


            row.setTag(dataHolder);
        }
        else
        {
            dataHolder = (DataHolder)row.getTag();
        }
        DeleteCData data = (DeleteCData)this.getItem(position);
        dataHolder.tv_course_id.setText(data.getCourse_id());
        return row;
    }

    static class DataHolder
    {
        TextView tv_course_id;
    }
}

