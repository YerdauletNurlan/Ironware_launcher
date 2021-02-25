package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ShopController {
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
    private Button Home_button;
    @FXML
    private Button Library_button;
    @FXML
    private Button Info_button;
    @FXML
    private Label Registred_email;
    @FXML
    private Button month1;
    @FXML
    private Button month3;
    @FXML
    private Button month12;

    int price;


    public void initialize(){}

    public void initSessionID(final LoginManager loginManager, String sessionID) {
        if (sessionID!="") {
            Registred_email.setText(sessionID);
            Registred_email.setVisible(true);
        }
        else {
            System.out.println("NOPE");
        }
        Home_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                loginManager.showMainView(sessionID);
            }
        });

        //create xml buy page
        month1.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event) {
                loginManager.showBuyScreen(sessionID, 1.99);
            }
        });
        month3.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event) {
                loginManager.showBuyScreen(sessionID, 4.99);
            }
        });
        month12.setOnAction(new EventHandler<ActionEvent>(){
            @Override public void handle(ActionEvent event) {
                loginManager.showBuyScreen(sessionID, 9.99);
            }
        });
    }


    }
