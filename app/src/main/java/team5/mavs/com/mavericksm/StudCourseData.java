package team5.mavs.com.mavericksm;

/**
 * Created by Avinash on 11/22/2016.
 */
public class StudCourseData {

    private String course_id,course_name,time,location,professor;

    public StudCourseData(String course_id, String course_name, String time, String location, String professor) {
        this.setCourse_id(course_id);
        this.setCourse_name(course_name);
        this.setTime(time);
        this.setLocation(location);
        this.setProfessor(professor);
        this.time = time;
        this.course_name = course_name;
        this.location = location;
        this.professor = professor;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}
