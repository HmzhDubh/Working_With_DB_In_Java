package org.example;

import java.sql.*;

public class Task {
    private String name;
    private boolean isComplete;
    private String owner;

    public Task(String name, boolean isComplete, String owner){
        this.name = name;
        this.isComplete = isComplete;
        this.owner = owner;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
    public String getOwner(){
        return owner;
    }
    public void setOwner(String owner){
        this.owner = owner;
    }
    // Insert Query
    public void insertTask(){
        try{

            Connection dbConnection = DBConnection.getInstance().getConnection();

            Statement stmt = dbConnection.createStatement();
            PreparedStatement insertStmt = dbConnection.prepareStatement("INSERT INTO todo (task, done, owner)  VALUES (?, ?, ?);");
            insertStmt.setString(1, this.name);
            insertStmt.setBoolean(2, this.isComplete);
            insertStmt.setString(3, this.owner);
            int rows = insertStmt.executeUpdate();

            System.out.println("Rows Inserted: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve query
    public void retrieveTasks(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();
            Statement stmt = dbConnection.createStatement();
            String query = "SELECT id, task, done, owner FROM todo";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                // print table Values
                String row = "ID: " + rs.getInt("id") +
                        " Task: " + rs.getString("task") +
                        " Done: " + rs.getBoolean("done") +
                        " Owner: " + rs.getString("owner") + "\n";
                System.out.print(row);
            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
    }


    // Update query
    // todo
    public void updateTask(){
        try {
            Connection dbConnection = DBConnection.getInstance().getConnection();

            // First Update
            PreparedStatement updateStmt = dbConnection.prepareStatement("UPDATE todo SET done = ? WHERE id = ?;");
            updateStmt.setBoolean(1, true);
            updateStmt.setInt(2, 13);
            int rows = updateStmt.executeUpdate();
            System.out.println("Rows Updated: " + rows);
            // Another Update
            PreparedStatement updateStmt2 = dbConnection.prepareStatement("UPDATE todo SET owner = ? WHERE id = ?;");
            updateStmt2.setString(1, "Hamza Helal Dubh");
            updateStmt2.setInt(2, 13);
            rows = updateStmt2.executeUpdate();

            System.out.println("Rows Updated: " + rows);

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String toString(){
        return "Task: " + this.name + "\nDone: " + (this.isComplete ? "1": "0");
    }




}
