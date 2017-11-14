package team5.mavs.com.mavericksm;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.*;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MarkAttendance extends FragmentActivity implements View.OnClickListener {
    android.widget.ListView list_view;

    String name,id1,id,student_name;
    Button btLogout,btMarkA;
    public static ArrayList<HashMap<String,String>> SingleRecord;
    AttendaceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        btLogout = (Button) findViewById(R.id.btLogout);
        list_view = (ListView) findViewById(R.id.listView4);
        //btLogout.setOnClickListener(this);
        name = getIntent().getExtras().getString("name");
        id1 = getIntent().getExtras().getString("id");
       btMarkA = (Button)findViewById(R.id.btMarkA);
        id = "CSE 5324";
        btMarkA.setOnClickListener(this);
        ParseData();
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btMarkA:
                Intent intent = new Intent(this, ProfessorScreen.class);
                intent.putExtra("name", name);
                intent.putExtra("id", id);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Attendance Marked Successfully", Toast.LENGTH_LONG).show();
                break;
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }

    public void ParseData(){
        String URL = "http://mavsuta.co.nf/StudentList.php";

        JsonArrayRequest stringReq = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

            @Override

            public void onResponse(JSONArray response) {
                ArrayList<HashMap<String,String>> IndividualList= new ArrayList<>();
                for (int i=0;i<response.length();i++){
                    // Assign the response to an TextView
                    try{



                        JSONObject jsonObject = response.getJSONObject(i);
                        student_name = jsonObject.getString("student_name");

                        HashMap<String,String> data=new HashMap<>();
                        data.put("student_name",student_name);




                        IndividualList.add(data);
                    }

                    catch(JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                adapter= new AttendaceAdapter(MarkAttendance.this,IndividualList);
                list_view.setAdapter(adapter);


                list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent nextIntent = new Intent(getApplicationContext(), StudentScreen.class);
                        HashMap<String,String> hashMenuItems;
                        hashMenuItems= SingleRecord.get(position);
                        nextIntent.putExtra("id",id1);
                        nextIntent.putExtra("name",name);
                        nextIntent.putExtra("student_name",hashMenuItems.get("student_name"));


                        startActivity(nextIntent);


                    }
                });
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Supervisor induvidual list", error.getMessage());


            }
        });

        AppController.getmInstance().addToRequestQueue(stringReq);

    }
}
