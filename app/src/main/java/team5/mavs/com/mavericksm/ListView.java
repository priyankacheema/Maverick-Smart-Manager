package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.os.Bundle;

public class ListView extends Activity {



    android.widget.ListView lv;
    String url = "http://mavsuta.co.nf/FetchCourse.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lv = (android.widget.ListView)findViewById(R.id.listView2);
        final Downloader d = new Downloader(this,url,lv);
        d.execute();

    }
}
