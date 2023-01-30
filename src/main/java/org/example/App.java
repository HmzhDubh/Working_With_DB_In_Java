package org.example;
import java.sql.SQLException;
public class App {
    public static void main( String[] args ) {
        try{
            DBConnection db = DBConnection.getInstance();

            Task task1 = new Task("Do DB assignment", true, "Hmzh Dubh");
            Task task2 = new Task("Push the Code",false, "Hmzh Dubh");

            // Insertion
            task1.insertTask();
            task2.insertTask();

            // Update
            task2.updateTask();

            // Retrieval
            task1.retrieveTasks();
        }
        catch (SQLException e){
            e.printStackTrace();

        }
    }
}
