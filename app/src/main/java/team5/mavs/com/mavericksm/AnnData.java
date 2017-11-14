package team5.mavs.com.mavericksm;

/**
 * Created by Avinash on 10/25/2016.
 */
public class AnnData {

    private String message_sender,message_date,message_title,message_body;

    public AnnData(String message_sender,String message_date,String message_title,String message_body)
    {
        this.setMessage_body(message_body);
        this.setMessage_date(message_date);
        this.setMessage_sender(message_sender);
        this.setMessage_title(message_title);

    }

    public String getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(String message_sender) {
        this.message_sender = message_sender;
    }

    public String getMessage_date() {
        return message_date;
    }

    public void setMessage_date(String message_date) {
        this.message_date = message_date;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }
}
