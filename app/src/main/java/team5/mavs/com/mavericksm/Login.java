package team5.mavs.com.mavericksm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements View.OnClickListener {

    Button btLogin;
    EditText etUsername,etPassword;
    TextView tvRegHere,tvChange;
    UserLocalStore userLocalStore;
    Object string;
    String id,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btLogin = (Button)findViewById(R.id.btLogin);
        tvRegHere= (TextView)findViewById(R.id.tvRegHere);
        tvRegHere.setOnClickListener(this);
        tvChange= (TextView)findViewById(R.id.tvChange);
        tvChange.setOnClickListener(this);

        btLogin.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User(username, password);

                authenticate(user);
                break;
            case R.id.tvRegHere:
                Intent registerIntent = new Intent(Login.this, Register.class);
                startActivity(registerIntent);
                break;
            case R.id.tvChange:
                Intent cp = new Intent(Login.this, ChangePassword.class);
                startActivity(cp);

                break;
        }
    }

    private void authenticate(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.fetchUserDataAsyncTask(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser == null) {
                    showErrorMessage();
                } else {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect user details");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void logUserIn(User returnedUser) {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);
        String role = userLocalStore.getRole();
        if(role.equals("STUDENT"))
        {
            id = userLocalStore.getEmail();
            name = userLocalStore.getName();
            Intent i =new Intent(this,Student.class);
            i.putExtra("id",id);
            i.putExtra("name",name);
            startActivity(i);
            Toast.makeText(getApplicationContext(),"Login Successfully as STUDENT", Toast.LENGTH_LONG).show();
    }
        if(role.equals("PROFESSOR"))
        {
            id = userLocalStore.getEmail();
            name = userLocalStore.getName();
            Intent a =new Intent(this,Professor.class);
            a.putExtra("id",id);
            a.putExtra("name",name);
            startActivity(a);
            Toast.makeText(getApplicationContext(),"Login Successfully as PROFESSOR", Toast.LENGTH_LONG).show();
        }
        if(role.equals("ADVISER"))
        {
            id = userLocalStore.getEmail();
            name = userLocalStore.getName();
            Intent b =new Intent(this,MainActivity.class);
            b.putExtra("id",id);
            b.putExtra("name",name);
            startActivity(b);
            Toast.makeText(getApplicationContext(),"Login Successfully as ADVISER", Toast.LENGTH_LONG).show();
        }
    }
}

