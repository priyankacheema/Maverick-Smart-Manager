package team5.mavs.com.mavericksm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Avinash on 11/20/2016.
 */
public class Parser extends AsyncTask<Void,Integer,Integer>{

    public Parser(Context c, ListView lv, String data) {
        this.c = c;
        this.lv = lv;
        this.data = data;
    }

    Context c;
    ListView lv;
    String data;
    ProgressDialog pd;

    ArrayList<String> courses = new ArrayList<>();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Parsing Data....Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        return this.parse();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        pd.dismiss();
        if(integer == 1)
        {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(c,android.R.layout.simple_list_item_1,courses);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(c,courses.get(i), Toast.LENGTH_LONG).show();
                }
            });
        }
        else
        {
            Toast.makeText(c,"Unable to parse data", Toast.LENGTH_LONG).show();
        }
    }

    private int parse()
    {
        String course_id, course_name, time, location;
        try {
            JSONArray js = new JSONArray(data);
            JSONObject jo = null;

            courses.clear();

            for(int i = 0;i<js.length();i++)
            {
                jo = js.getJSONObject(i);


                course_id = jo.getString("course_id");

                courses.add(course_id);
        return 1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
