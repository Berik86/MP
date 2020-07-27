package bit.berik211;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    private PackageData pd;

    public ClientHandler(Socket socket, PackageData pd) {
        this.socket = socket;
        this.pd = pd;
    }

    public ClientHandler(Socket socket) {
    }

    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            PackageData pd = new PackageData();
            while ((pd = (PackageData) inputStream.readObject()) != null) {
                System.out.println(pd.toString());
                if (pd.getOperationType().equals("LIST")) {
                    outputStream.writeObject(pd);
                } else {
                    outputStream.writeObject(null);
                }
               /* if (pd.getOperationType().equals("ADD")){
                    ArrayList<Student> students = pd.getStudents();
                    for (Student s: students){
                        System.out.println(s);
                    }

                */
                inputStream.close();
                outputStream.close();
                socket.close();
            }
        /*catch(Exception e) {
            e.printStackTrace();
        }*/
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}