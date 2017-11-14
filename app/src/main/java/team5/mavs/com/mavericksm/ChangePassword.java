package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChangePassword extends Activity implements View.OnClickListener {

    Button btChange;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btChange = (Button)findViewById(R.id.btChange);
        btChange.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btChange:
                Intent cp = new Intent(this, Login.class);
                startActivity(cp);
                Toast.makeText(getApplicationContext(),"Password Changed Successfully.", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
