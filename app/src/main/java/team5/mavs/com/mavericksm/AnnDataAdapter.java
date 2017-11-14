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
 * Created by Avinash on 10/25/2016.
 */
public class AnnDataAdapter extends BaseAdapter {


        private ArrayList<HashMap<String,String>> list;
        Context context;
    AnnDataAdapter(Context c, ArrayList<HashMap<String, String>> data)
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
            View row = inflater.inflate(R.layout.activity_raw_layout,parent,false);
            TextView tvSender = (TextView) row.findViewById(R.id.tvSender);
            TextView tvDate = (TextView) row.findViewById(R.id.tvDate);
            TextView tvTitle = (TextView) row.findViewById(R.id.tvTitle);
            TextView tvBody = (TextView) row.findViewById(R.id.tvBody);
            HashMap<String,String> hashMenuItems= new HashMap<>();
            hashMenuItems= list.get(position);

            tvSender.setText(hashMenuItems.get("message_sender"));
            tvDate.setText(hashMenuItems.get("message_date"));
            tvTitle.setText(hashMenuItems.get("message_title"));
            tvBody.setText(hashMenuItems.get("message_body"));




            displayListView Record = new displayListView();
            Record.SingleRecord=list;



            return row;
        }
    }
