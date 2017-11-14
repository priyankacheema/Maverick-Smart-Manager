package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DeleteCourse extends Activity {
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DeleteCDataAdapter deleteCDataAdapter;
    ListView listView;
    String course_id;
    String course_name;
    String time;
    String location;
    String professor;
    public static ArrayList<HashMap<String, String>> SingleRecord;
    NewCourseAdapter adapter;
    String name, id1;
    Button btLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);

        listView = (ListView) findViewById(R.id.listDelete);
        ParseData();

    }

    public void ParseData() {
        String URL = "http://mavsuta.co.nf/FetchCourse.php";
        JsonArrayRequest stringReq = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

            @Override

            public void onResponse(JSONArray response) {
                ArrayList<HashMap<String, String>> IndividualList = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    // Assign the response to an TextView
                    try {


                        JSONObject jsonObject = response.getJSONObject(i);
                        course_id = jsonObject.getString("course_id");
                        course_name = jsonObject.getString("course_name");
                        time = jsonObject.getString("time");
                        location = jsonObject.getString("location");
                        professor = jsonObject.getString("professor");

                        HashMap<String, String> data = new HashMap<>();
                        data.put("course_id", course_id);
                        data.put("course_name", course_name);
                        data.put("time", time);
                        data.put("location", location);
                        data.put("professor", professor);


                        IndividualList.add(data);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new NewCourseAdapter(DeleteCourse.this, IndividualList);
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent nextIntent = new Intent(getApplicationContext(), DeleteCoursePage.class);
                        HashMap<String, String> hashMenuItems;
                        hashMenuItems = SingleRecord.get(position);
                        nextIntent.putExtra("id", id1);
                        nextIntent.putExtra("name", name);
                        nextIntent.putExtra("course_id", hashMenuItems.get("course_id"));
                        nextIntent.putExtra("course_name", hashMenuItems.get("course_name"));
                        nextIntent.putExtra("time", hashMenuItems.get("time"));
                        nextIntent.putExtra("location", hashMenuItems.get("location"));
                        nextIntent.putExtra("professor", hashMenuItems.get("professor"));
                        startActivity(nextIntent);


                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Supervisor induvidual list", error.getMessage());


            }
        });

        AppController.getmInstance().addToRequestQueue(stringReq);

    }
}
