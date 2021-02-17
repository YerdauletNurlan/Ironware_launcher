package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainController {
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
    @FXML
    private Button app_Login_button;
    @FXML
    private Button app_Registration_button;
    //App.fxml page
    @FXML
    private Button app_Shop_button;
    @FXML
    private Button app_Library_button;
    @FXML
    private Button app_Info_button;
    @FXML
    private Button app_Logout_button;
    @FXML
    private Label Registred_email;



    public void initialize(){}

    public void initSessionID(final LoginManager loginManager, String sessionID) {
        if (sessionID!="") {
            Registred_email.setText(sessionID);
            app_Login_button.setVisible(false);
            app_Registration_button.setVisible(false);
            Registred_email.setVisible(true);
        }
        else {
            app_Logout_button.setVisible(false);
        }
        app_Logout_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.logout();
            }
        });
        app_Registration_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.showRegistrationScreen();
            }
        });
        app_Login_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.showLoginScreen();
            }
        });
    }
/*
    public void openHomePage() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/app.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 700, 450));
        //Registred_email.setText("registred_user");
        stage.showAndWait();
        //app_Registration_button.setVisible(buttonsVisible);
        //app_Login_button.setVisible(buttonsVisible);

    }
*/
}
