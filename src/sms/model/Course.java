package sms.model;

public class Course {
    private int courseId;
    private String 	course_name;
    private int course_hour;
    private int gradeId;
    private int roomId;
    private String empNo;

    public Course(int courseId, String course_name, int course_hour, int gradeId, int roomId, String empNo) {
        this.courseId = courseId;
        this.course_name = course_name;
        this.course_hour = course_hour;
        this.gradeId = gradeId;
        this.roomId = roomId;
        this.empNo = empNo;
    }

    public Course(int courseId, String course_name) {
        this.courseId = courseId;
        this.course_name = course_name;
    }

    public Course(int courseId, String course_name, int course_hour) {
        this.courseId = courseId;
        this.course_name = course_name;
        this.course_hour = course_hour;
    }



    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_hour() {
        return course_hour;
    }

    public void setCourse_hour(int course_hour) {
        this.course_hour = course_hour;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
}
