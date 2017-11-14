package team5.mavs.com.mavericksm;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Avinash on 11/20/2016.
 */
public class Downloader extends AsyncTask<String,Integer,String> {

    Context c;
    String address;
    ListView v;

    ProgressDialog pd;

    public Downloader(Context c, String address, ListView v) {
        this.c = c;
        this.address = address;
        this.v = v;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Fetch Data");
        pd.setMessage("Fetching Data...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(String... voids) {

        String data = downloadData();
        return data;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if(s != null)
        {
            Parser parser = new Parser(c,v,s);
            parser.execute();
        }
        else
        {
            Toast.makeText(c,"Unable to download data", Toast.LENGTH_LONG).show();
        }
    }

private String downloadData()
{

    InputStream is = null;
    String line = null;


    try {
        URL url = new URL(address);
        HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
        is = new BufferedInputStream(httpURLConnection.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuffer stringBuffer = new StringBuffer();

        if(br != null)
        {
            while((line=br.readLine())!=null)
            {
                stringBuffer.append(line+"\n");
            }
        }
        else
        {
            return null;
        }

        return stringBuffer.toString();
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
        }
    finally
    {
        if(is != null)
        {
            try {
                is.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    return null;
}

}