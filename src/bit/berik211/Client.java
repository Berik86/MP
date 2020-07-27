package bit.berik211;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        Server server = new Server();
        server.connectSql();

        try {
            Socket socket = new Socket("127.0.0.1", 2016);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                System.out.println("PRESS [1] TO ADD STUDENT");
                System.out.println("PRESS [2] LIST STUDENTS");
                System.out.println("PRESS [3] EXIT");
                int choice = in.nextInt();

                if (choice == 1) {
                    System.out.println("Insert name");
                    String name = in.next();
                    System.out.println("Insert surname");
                    String surname = in.next();
                    System.out.println("Insert age");
                    int age = in.nextInt();
                    Student st = new Student(null, name, surname, age);

                    PackageData pd = new PackageData();
                    server.addStudentSql(st);

                } else if (choice == 2) {
                    PackageData pd = new PackageData();
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    if ((pd = (PackageData) inputStream.readObject()) != null) {
                        server.getStudents();
                        /*students = pd.getStudents();
                        for (Student s: students){
                            System.out.println(s);*/
                    }
                } else if (choice == 3) {
                    break;
                }
            }
        }catch(Exception e){
                e.printStackTrace();
        }
    }
}


