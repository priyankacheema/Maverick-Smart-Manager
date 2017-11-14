package team5.mavs.com.mavericksm;

/**
 * Created by Avinash on 11/20/2016.
 */
public class DeleteCData {

    private String course_id;

    public DeleteCData(String course_id)
    {
        this.setCourse_id(course_id);

    }
    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
