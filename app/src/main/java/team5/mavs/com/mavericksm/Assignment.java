package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Assignment extends Activity implements View.OnClickListener{

    Button imageButtond1, imageButtond2, imageButtond3, imageButtond4,imageButtona1,imageButtona2,imageButtona3,imageButtona4;
    Button buttonlg;
    String name,id,course_id,from;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        imageButtond1 = (Button) findViewById(R.id.imageButtond1);
        imageButtond2 = (Button) findViewById(R.id.imageButtond2);
        imageButtond3 = (Button) findViewById(R.id.imageButtond3);
        imageButtond4 = (Button) findViewById(R.id.imageButtond4);
        imageButtona1 = (Button) findViewById(R.id.imageButtona1);
        imageButtona2 = (Button) findViewById(R.id.imageButtona2);
        imageButtona3 = (Button) findViewById(R.id.imageButtona3);
        imageButtona4 = (Button) findViewById(R.id.imageButtona4);
        buttonlg = (Button) findViewById(R.id.buttonlg);
        imageButtond1.setOnClickListener(this);
        imageButtond2.setOnClickListener(this);
        imageButtond3.setOnClickListener(this);
        imageButtond4.setOnClickListener(this);
        imageButtona1.setOnClickListener(this);
        imageButtona2.setOnClickListener(this);
        imageButtona3.setOnClickListener(this);
        imageButtona4.setOnClickListener(this);
        buttonlg.setOnClickListener(this);
        name = getIntent().getExtras().getString("name");
        course_id = getIntent().getExtras().getString("course_id");
        id = getIntent().getExtras().getString("id");
        from = getIntent().getExtras().getString("from");

    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageButtond1:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://mavsuta.co.nf/Assignment1.doc ");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageButtond2:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri1 = Uri.parse("http://mavsuta.co.nf/M01.pdf");
                DownloadManager.Request request1 = new DownloadManager.Request(uri1);
                request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference1 = downloadManager.enqueue(request1);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageButtond3:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri2 = Uri.parse("http://mavsuta.co.nf/M01.pdf");
                DownloadManager.Request request2 = new DownloadManager.Request(uri2);
                request2.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference2 = downloadManager.enqueue(request2);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageButtond4:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri3 = Uri.parse("http://mavsuta.co.nf/M01.pdf");
                DownloadManager.Request request3 = new DownloadManager.Request(uri3);
                request3.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference3 = downloadManager.enqueue(request3);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;

            case R.id.buttonlg:
                Intent ag = new Intent(this, Login.class);
                ag.putExtra("name", name);
                ag.putExtra("id", id);
                ag.putExtra("course_id",course_id);
                startActivity(ag);
                Toast.makeText(getApplicationContext(), "Logged out Successfully", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageButtona1:
                Intent a1 = new Intent(this, UploadAssignment.class);
                a1.putExtra("name", name);
                a1.putExtra("id", id);
                a1.putExtra("course_id",course_id);
                a1.putExtra("from", from);
                startActivity(a1);
                break;
            case R.id.imageButtona2:
                Intent a2 = new Intent(this, UploadAssignment.class);
                a2.putExtra("name", name);
                a2.putExtra("id", id);
                a2.putExtra("course_id",course_id);
                a2.putExtra("from", from);
                startActivity(a2);
                break;
            case R.id.imageButtona3:
                Intent a3 = new Intent(this, UploadAssignment.class);
                a3.putExtra("name", name);
                a3.putExtra("id", id);
                a3.putExtra("course_id",course_id);
                a3.putExtra("from", from);
                startActivity(a3);
                break;
            case R.id.imageButtona4:
                Intent a4 = new Intent(this, UploadAssignment.class);
                a4.putExtra("name", name);
                a4.putExtra("id", id);
                a4.putExtra("course_id",course_id);
                a4.putExtra("from", from);
                startActivity(a4);
                break;



        }

    }
}