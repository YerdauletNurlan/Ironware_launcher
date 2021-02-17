package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginManager {
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


    public void logout(){
        showLoginScreen();
    }
}
