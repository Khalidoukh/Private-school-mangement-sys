package sms.tableModel;

/**
 * @author Safnaj on 11/3/2018
 * @project School Management System
 **/
public class StaffTableModel {

    int empNo;
    String teacherName;
    String nic;

    String gender;
    String email;

    String phone;
    String address;


    public StaffTableModel(int empNo, String teacherName, String nic, String gender, String email, String phone, String address) {
        this.empNo = empNo;
        this.teacherName = teacherName;
        this.nic = nic;

        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;

    }

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
