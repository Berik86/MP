package bit.berik211;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private ArrayList<Student> students;
    private  Student student;

    //private String name;
    //private String message;

    public PackageData(){

    }

    public PackageData(String operationType, ArrayList<Student> students, Student student){
        this.operationType = operationType;
        this.students = students;
        this.student = student;

        //this.name = name;
        //this.message = message;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "PackageData{" +
                "operationType='" + operationType + '\'' +
                ", students=" + students +
                ", student=" + student +
                '}';
    }
}
