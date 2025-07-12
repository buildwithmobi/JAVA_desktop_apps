package sample.todoapp.Database;

import sample.todoapp.Model.Task;
import sample.todoapp.Model.User;

import java.sql.*;

public class DatabaseHandler extends Config {
    Connection dbConnection;
    public  Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mariadb://" + dbHost + ":"+
                dbPort+"/"+
                dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,dnUser,dbPass);
        return dbConnection;
    }



    public void updateTask(Timestamp datecreated, String description, String task, int taskId) throws SQLException, ClassNotFoundException {

        String query = "UPDATE tasks SET datecreated=?, description=?, task=? WHERE taskid=?";


        PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
        preparedStatement.setTimestamp(1, datecreated);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, task);
        // preparedStatement.setInt(4, userId);
        preparedStatement.setInt(4, taskId);
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }
    //Write
    public  void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_FIRSTNAME + "," +
                Const.USERS_LASTNAME + "," + Const.USERS_NAME + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_LOCATION + "," + Const.USERS_GENDER + ") VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5,user.getLocation());
            preparedStatement.setString(6,user.getGender());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resultSet = null;
        //System.out.println(user.getGender());
        if (!user.getUserName().isEmpty() && !user.getPassword().isEmpty()) {
            String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                    + Const.USERS_NAME + "=?" + " AND " + Const.USERS_PASSWORD
                    + "=?";
            try {
                PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
                preparedStatement.setString(1, user.getUserName());
                preparedStatement.setString(2, user.getPassword());
                System.out.println(user.getUserName());


                resultSet = preparedStatement.executeQuery();


            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();

            }
        } else {
            System.out.println("Please Enter  Credentials");

        }

        return resultSet;
    }

    public int getAllTasks(int userId){

        String query = "SELECT COUNT(*) FROM " + Const.TASKS_TABLE + " WHERE "
                + Const.USERS_ID + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1,userId);

            ResultSet resultSet =preparedStatement.executeQuery();
            while (resultSet.next()){

                return  resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public  void  insertTask(Task task){
        String insert = "INSERT INTO " + Const.TASKS_TABLE + " (" +
                Const.USERS_ID+ ","+ Const.TASKS_DATE + "," + Const.TASKS_DESCRIPTION + "," + Const.TASKS_TASK +
                ") VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);

            preparedStatement.setInt(1, task.getUserId());
            preparedStatement.setTimestamp(2, task.getDatecreated());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getTask());



            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    public  ResultSet getTasksByUser(int UserID){
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + Const.TASKS_TABLE + " WHERE "
                + Const.USERS_ID + "=?";


        try {
            PreparedStatement preparedStatement =getDbConnection().prepareStatement(query);

            preparedStatement.setInt(1,UserID);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    //delete task
    public void deleteTask(int userID, int taskId){
        String query = "DELETE FROM " + Const.TASKS_TABLE + " WHERE " +
                Const.USERS_ID + " = ? AND " + Const.TASKS_ID + " = ?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setInt(1,userID);
            preparedStatement.setInt(2,taskId);
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
