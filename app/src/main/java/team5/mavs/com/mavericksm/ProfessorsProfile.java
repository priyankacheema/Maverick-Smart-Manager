package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfessorsProfile extends Activity implements View.OnClickListener {


    Button button7, button8;
    String name,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors_profile);

        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);

        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button7:
                Intent i = new Intent(this, Robb.class);
                startActivity(i);
                break;
            case R.id.button8:
                Intent a = new Intent(this, Khalili.class);
                startActivity(a);
                break;
        }
    }
}
