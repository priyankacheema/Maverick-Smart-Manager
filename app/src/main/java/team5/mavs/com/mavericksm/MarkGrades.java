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

public class MarkGrades extends Activity implements View.OnClickListener{

    Button imageButton2, imageButton3, imageButton5, imageButton4;
    Button button13;
    String name,id,course_id;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_grades);
        imageButton2 = (Button) findViewById(R.id.imageButton2);
        imageButton3 = (Button) findViewById(R.id.imageButton3);
        imageButton4 = (Button) findViewById(R.id.imageButton4);
        imageButton5 = (Button) findViewById(R.id.imageButton5);
        button13 = (Button) findViewById(R.id.button13);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton4.setOnClickListener(this);
        imageButton5.setOnClickListener(this);
        button13.setOnClickListener(this);
        name = getIntent().getExtras().getString("name");
        course_id = getIntent().getExtras().getString("course_id");
        id = getIntent().getExtras().getString("id");

    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageButton2:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse("http://mavsuta.co.nf/1001240453.doc");
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageButton3:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri1 = Uri.parse("http://mavsuta.co.nf/M01.pdf");
                DownloadManager.Request request1 = new DownloadManager.Request(uri1);
                request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference1 = downloadManager.enqueue(request1);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageButton4:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri2 = Uri.parse("http://mavsuta.co.nf/M01.pdf");
                DownloadManager.Request request2 = new DownloadManager.Request(uri2);
                request2.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference2 = downloadManager.enqueue(request2);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;
            case R.id.imageButton5:
                downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri3 = Uri.parse("http://mavsuta.co.nf/M01.pdf");
                DownloadManager.Request request3 = new DownloadManager.Request(uri3);
                request3.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference3 = downloadManager.enqueue(request3);
                Toast.makeText(getApplicationContext(), "Downloading file.", Toast.LENGTH_LONG).show();
                break;

            case R.id.button13:
                Intent ag = new Intent(this, ProfessorScreen.class);
                ag.putExtra("name", name);
                ag.putExtra("id", id);
                ag.putExtra("course_id",course_id);
                startActivity(ag);
                Toast.makeText(getApplicationContext(), "Grades uploaded Successfully", Toast.LENGTH_LONG).show();
                break;



        }
    }
}

