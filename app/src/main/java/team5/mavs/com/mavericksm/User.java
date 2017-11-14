package team5.mavs.com.mavericksm;

/**
 * Created by Avinash on 10/24/2016.
 */
public class User {

    String etFName,etLName,etUTAID,etEmail,etPhone,etPassword,etNetID,etRole;

    public User(String etFName,String etLName,String etUTAID,String etEmail,String etPhone,String etPassword,String etNetID,String etRole)
    {
        this.etFName = etFName;
        this.etLName = etLName;
        this.etUTAID = etUTAID;
        this.etEmail = etEmail;
        this.etPhone = etPhone;
        this.etPassword = etPassword;
        this.etNetID = etNetID;
        this.etRole = etRole;

    }
    public User(String etEmail,String etPassword)
    {
        this.etFName = "";
        this.etLName = "";
        this.etUTAID = "";
        this.etEmail = etEmail;
        this.etPhone = "";
        this.etPassword = etPassword;
        this.etNetID = "";
        this.etRole = "";

    }

}
