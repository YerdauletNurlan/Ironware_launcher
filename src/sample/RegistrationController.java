package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class RegistrationController {
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("OK");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR");
        }
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "123");
            System.out.println("OK2");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("ERROR");
        }
        return dbConnection;
    }
    public void addSQL(String str) throws SQLException{
        Connection dbConnection = getDBConnection();
        Statement statement = dbConnection.createStatement();
        System.out.println(str);
        statement.executeUpdate(str);
    }
    //Used in many places
    @FXML
    private Button app_Login_button;
    @FXML
    private Button app_home_button;
    //Registration.fxml page
    @FXML
    private TextField Reg_Email;
    @FXML
    private TextField Reg_Name;
    @FXML
    private TextField Reg_Surname;
    @FXML
    private TextField Reg_Password;
    @FXML
    private TextField Reg_Age;
    @FXML
    private Button Reg_Register_button;
    @FXML
    private Label reg_error;


    public void initialize(){}


    public void initManager(final LoginManager loginManager) {
        Reg_Register_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sessionID = null;
                try {
                    sessionID = Registration();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if (sessionID != "error") {
                    loginManager.authenticated(sessionID);
                }
                else {
                    reg_error.setText("Please change email");
                    reg_error.setVisible(true);
                }
            }
        });
        app_home_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.showMainView("");
            }
        });
        app_Login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.showLoginScreen();
            }
        });

    }
    public String Registration() throws SQLException {
        String email = Reg_Email.getText();
        String name = Reg_Name.getText();
        String surname = Reg_Surname.getText();
        String password = Reg_Password.getText();
        int age = Integer.parseInt(Reg_Age.getText());
        int id=0;
        Connection dbConnection = getDBConnection();
        Statement statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery("select id from customers order by id desc limit 1;");
        while (rs.next()) {
            id = rs.getInt("id") + 1;
        }

        if (checkEmail(email)){
            reg_error.setVisible(false);
            String str="INSERT INTO Customers (id, email, name, surname, age, password) " +
                    "VALUES(" + id + ", \'" + email + "\', \'" + name + "\', \'" + surname + "\', " + age + ", \'" + password + "\');";
            System.out.println(str);
            addSQL(str);
            return email;
        }
        else
        {
            reg_error.setText("Please change email");
            reg_error.setVisible(true);
            return "error";
        }
    }

    public boolean checkEmail(String email)throws SQLException {
        boolean check = true;
        String dbEmail;
        Connection dbConnection = getDBConnection();
        Statement statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery("select email from customers");
        System.out.println("WORKING");
        while (rs.next()) {
            dbEmail = rs.getString("email");
            System.out.println(dbEmail + "/ /"+ email);
            if (dbEmail.equals(email)) {
                System.out.println("IFWROKS");
                check = false;
            }
        }
        return check;
    }

    public boolean checkPassword(String password){
        boolean bool = true;
        if (password.length()<6)
        {
            bool =false;
        }

        return bool;
    }
}
