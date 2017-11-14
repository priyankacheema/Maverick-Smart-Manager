package team5.mavs.com.mavericksm;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tundealao on 29/03/15.
 */
public class UserLocalStore {
    SharedPreferences.Editor userLocalDatabaseEditor;
    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }
    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("fname", user.etFName);
        userLocalDatabaseEditor.putString("lname", user.etLName);
        userLocalDatabaseEditor.putString("utaid", user.etUTAID);
        userLocalDatabaseEditor.putString("email", user.etEmail);
        userLocalDatabaseEditor.putString("phone", user.etPhone);
        userLocalDatabaseEditor.putString("password", user.etPassword);
        userLocalDatabaseEditor.putString("netid", user.etNetID);
        userLocalDatabaseEditor.putString("role", user.etRole);
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String fname = userLocalDatabase.getString("fname", "");
        String lname = userLocalDatabase.getString("lname", "");
        String utaid = userLocalDatabase.getString("utaid", "");
        String email = userLocalDatabase.getString("email", "");
        String phone = userLocalDatabase.getString("phone", "");
        String password = userLocalDatabase.getString("password", "");
        String netid = userLocalDatabase.getString("netid", "");
        String role = userLocalDatabase.getString("role", "");

        User user = new User(fname, lname, utaid, email,phone, password, netid, role);
        return user;
    }

    public String getRole() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String role = userLocalDatabase.getString("role", "");

        return role;

    }
    public String getEmail() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String email = userLocalDatabase.getString("email", "");

        return email;
    }
    public String getName() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String fname = userLocalDatabase.getString("fname", "");
        String lname = userLocalDatabase.getString("lname", "");
        String name = fname+" "+lname;

        return name;
    }
}