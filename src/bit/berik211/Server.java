package bit.berik211;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Server {
    public static Connection connection;
    public static void main(String[] args) {
        connectSql();


        try{
            ServerSocket server = new ServerSocket(2016);
            System.out.println("WAITING FOR CLIENT");
            while (true){
                Socket socket = server.accept();
                System.out.println("CLIENT CONNECTED");
                ClientHandler ch = new ClientHandler(socket);
                ch.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // соединение с БД
    public static void connectSql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lab11?useUnicode=true&serverTimezone=UTC","root", ""
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Метод добавления - addStudentSql()
    public static void addStudentSql(Student student){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO students (name, surname, age) " +
                    "VALUES (?, ?, ?)"
            );
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3, student.getAge());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // Метод просмотра - getStudentsSql()
    public ArrayList<Student> getStudents(){
        ArrayList<Student> studentList = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                studentList.add(new Student(id, name, surname, age));
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return studentList;
    }
}
