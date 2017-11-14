package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class displayListView extends Activity implements View.OnClickListener {


    ListView listView;
    String message_sender,message_date,message_title,message_body;

    public static ArrayList<HashMap<String,String>> SingleRecord;
    AnnDataAdapter adapter;
    String name,id,from;
    Button btLogout,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);
        name = getIntent().getExtras().getString("name");
        id = getIntent().getExtras().getString("id");

        from = getIntent().getExtras().getString("from");

        btLogout = (Button)findViewById(R.id.btLogout);
        back = (Button)findViewById(R.id.back);
        btLogout.setOnClickListener(this);
        back.setOnClickListener(this);
        listView = (ListView)findViewById(R.id.listView);
        ParseData();

    }
    public void ParseData(){
        String URL;
        if(from.equals("Student"))
        {
            URL = "http://mavsuta.co.nf/FetchMessage.php";
        }
        else
        {
            URL = "http://mavsuta.co.nf/FetchAnnouncement.php";
        }



        JsonArrayRequest stringReq = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {

            @Override

            public void onResponse(JSONArray response) {
                ArrayList<HashMap<String,String>> IndividualList= new ArrayList<>();
                for (int i=0;i<response.length();i++){
                    // Assign the response to an TextView
                    try{



                        JSONObject jsonObject = response.getJSONObject(i);
                        message_sender = jsonObject.getString("message_sender");
                        message_date = jsonObject.getString("message_date");
                        message_title = jsonObject.getString("message_title");
                        message_body = jsonObject.getString("message_body");

                        HashMap<String,String> data=new HashMap<>();
                        data.put("message_sender",message_sender);
                        data.put("message_date",message_date);
                        data.put("message_title",message_title);
                        data.put("message_body",message_body);




                        IndividualList.add(data);
                    }

                    catch(JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                adapter= new AnnDataAdapter(displayListView.this,IndividualList);
                listView.setAdapter(adapter);


                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogout:
                startActivity(new Intent(this, Login.class));
                Toast.makeText(getApplicationContext(), "Logged Out Successfully", Toast.LENGTH_LONG).show();

                break;
            case R.id.back:

                if(from.equals("stud"))
                {
                    Intent i = new Intent(this, Student.class);
                    i.putExtra("name", name);
                    i.putExtra("id", id);
                    i.putExtra(from,from);
                    startActivity(i);

                }else
                {
                    Intent i = new Intent(this, Professor.class);
                    i.putExtra("name", name);
                    i.putExtra("id", id);
                    i.putExtra(from,from);
                    startActivity(i);

                }

                break;
        }
    }
}
