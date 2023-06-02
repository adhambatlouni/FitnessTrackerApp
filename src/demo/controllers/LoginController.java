package demo.controllers;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.ResourceBundle;

import demo.AnchorPaneUtils;
import demo.Main;
import demo.connection.ClassConnection;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
public class LoginController extends AnchorPane implements Initializable {

    public TextField usernamefield;

    public PasswordField passwordfield;
    public Label forgetpassLabel;
    public Label registerLabel;
    private Main application;
    @FXML
    private VBox childVbox;
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private Button loginBtn;
    @FXML
    private AnchorPane childAnchorPane;

    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane,childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);

        AnchorPaneUtils.btnHover(loginBtn);
        AnchorPaneUtils.labelHover(forgetpassLabel);
        AnchorPaneUtils.labelHover(registerLabel);
    }

    public void goToRegister(MouseEvent mouseEvent) throws Exception {
        application.replaceSceneContent("fxml/SignUp.fxml");
    }

    public void logIn(ActionEvent actionEvent) throws Exception {
        TranslateTransition shakeTransition1 = new TranslateTransition(Duration.millis(100), usernamefield);
        TranslateTransition shakeTransition2 = new TranslateTransition(Duration.millis(100), passwordfield);
        shakeTransition1.setCycleCount(4);
        shakeTransition2.setCycleCount(4);

        shakeTransition1.setByX(10);
        shakeTransition1.setAutoReverse(true);

        shakeTransition2.setByX(10);
        shakeTransition2.setAutoReverse(true);

        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if(username.isEmpty() || password.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            return;
        }
        if (usernameExists(username) && passwordExists(password)) {
            application.replaceSceneContent("fxml/Homepage.fxml");
        }
        if(!usernameExists(username)) {
            shakeTransition1.playFromStart();
        }
        else if(!passwordExists(username)) {
            shakeTransition2.playFromStart();
        }
    }

    private boolean usernameExists(String username) {
        try {
            String checkUser = "SELECT user_id, user_name FROM user WHERE user_name = ?";
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(checkUser);
            preparedStatement.setString(1, username);

            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                int userId = resultset.getInt("user_id");
                SignUpController.setUserId(userId);
                return true;
            }

            preparedStatement.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return false;
    }

    private boolean passwordExists(String password) {
        try {
            String checkPass = "Select * FROM user where user_password = ?";
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            PreparedStatement preparedStatement = connection.prepareStatement(checkPass);
            preparedStatement.setString(1, Base64.getEncoder().encodeToString(passwordHash));

            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                return true;
            }

            preparedStatement.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
