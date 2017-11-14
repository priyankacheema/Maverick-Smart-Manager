package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity implements View.OnClickListener{
    Spinner spinnerruse;
    Object item;
    Button btRegister;
    UserLocalStore userLocalStore;
    EditText etFName,etLName,etUTAID,etEmail,etPhone,etPassword,etCPassword,etNetID,etRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinnerruse = (Spinner)findViewById(R.id.rSpinner);

        spinnerruse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                item = adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            } });

        etFName = (EditText)findViewById(R.id.etFName);
        etLName = (EditText)findViewById(R.id.etLName);
        etUTAID = (EditText)findViewById(R.id.etUTAID);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etPassword = (EditText)findViewById(R.id.etPassword);
        etCPassword = (EditText)findViewById(R.id.etCPassword);
        etNetID = (EditText)findViewById(R.id.etNetID);
        //etRole = (EditText)findViewById(R.id.etRole);

        btRegister = (Button)findViewById(R.id.btRegister);

        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btRegister:

                String fname = etFName.getText().toString();
                String lname = etLName.getText().toString();
                String utaid = etUTAID.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String password = etPassword.getText().toString();
                String netid = etNetID.getText().toString();
                String role = item.toString();

                User user = new User(fname, lname, utaid, email,phone, password, netid, role);
                registerUser(user);
                Toast.makeText(getApplicationContext(), "Registered Successfully.", Toast.LENGTH_LONG).show();
                break;
        }
    }
    private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(Register.this, Login.class);
                startActivity(loginIntent);

            }
        });
    }
}
