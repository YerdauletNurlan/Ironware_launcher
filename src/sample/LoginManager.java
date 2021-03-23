package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import com.chilkatsoft.*;


import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager {



    //////////////


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
    public void addSQL(String str) {
    }

    private Scene scene;

    public LoginManager(Scene scene) {
        this.scene = scene;
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample/Login.fxml")
            );
            scene.setRoot((Parent) loader.load());
            LoginController controller = loader.<LoginController>getController();
            controller.initManager(this);
        }
        catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void authenticated(String sessionID) {
        showMainView(sessionID);
    }


    public void showMainView(String sessionID) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample/app.fxml")
            );
            scene.setRoot((Parent) loader.load());
            MainController controller =
                    loader.<MainController>getController();
            controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showRegistrationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample/Registration.fxml")
            );
            scene.setRoot((Parent) loader.load());

            RegistrationController controller = loader.<RegistrationController>getController();
            controller.initManager(this);
        }
        catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showShopScreen(String sessionID) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample/shop.fxml")
            );
            scene.setRoot((Parent) loader.load());
            ShopController controller =
                    loader.<ShopController>getController();
            controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showBuyScreen(String sessionID, double cost){
        double balance=0;
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample/buy cancel.fxml")
            );
            scene.setRoot((Parent) loader.load());
            Buy_CancelController controller =
                    loader.<Buy_CancelController>getController();
            controller.initSessionID(this, sessionID, cost);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void showBalanceScreen(String sessionID) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample/balance.fxml")
            );
            scene.setRoot((Parent) loader.load());
            BalanceController controller =
                    loader.<BalanceController>getController();
            controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showOfferScreen(String sessionID) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/sample/special_offer.fxml")
            );
            scene.setRoot((Parent) loader.load());
            OfferController controller =
                    loader.<OfferController>getController();
            controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void logout(){
        showLoginScreen();
    }
}
