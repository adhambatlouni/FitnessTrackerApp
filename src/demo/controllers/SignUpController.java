package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import demo.connection.ClassConnection;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
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

public class SignUpController extends AnchorPane implements Initializable {
    public TextField usernamefield;
    public TextField emailfield;
    public PasswordField passwordfield;
    public Button signUpBtn;

    public Label loginLabel;
    private Main application;
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private VBox childVbox;
    @FXML
    private AnchorPane childAnchorPane;

    private static String user;
    private static String email;
    private static int userId;

    public void setApp(Main application) {this.application = application;}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane,childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);

        AnchorPaneUtils.btnHover(signUpBtn);
        AnchorPaneUtils.labelHover(loginLabel);
    }

    public void goToLogin(MouseEvent mouseEvent) throws Exception {
        application.replaceSceneContent("fxml/Login.fxml");
    }

    public void signUp() throws Exception {
        TranslateTransition shakeTransition1 = new TranslateTransition(Duration.millis(100), usernamefield);
        TranslateTransition shakeTransition2 = new TranslateTransition(Duration.millis(100), emailfield);
        TranslateTransition shakeTransition3 = new TranslateTransition(Duration.millis(100), passwordfield);
        shakeTransition1.setCycleCount(4);
        shakeTransition2.setCycleCount(4);
        shakeTransition3.setCycleCount(4);

        shakeTransition1.setByX(10);
        shakeTransition1.setAutoReverse(true);

        shakeTransition2.setByX(10);
        shakeTransition2.setAutoReverse(true);

        shakeTransition3.setByX(10);
        shakeTransition3.setAutoReverse(true);

        String username = usernamefield.getText();
        String email = emailfield.getText();
        String password = passwordfield.getText();

        setUsername(username);
        setEmail(email);

        if(username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            shakeTransition3.playFromStart();
            return;
        }

        if(usernameExists(username)) {
            shakeTransition1.playFromStart();
            return;
        }

        if(emailExists(email)) {
            shakeTransition2.playFromStart();
            return;
        }
        addUser(username, email, password);
        usernamefield.setText("");
        emailfield.setText("");
        passwordfield.setText("");
    }

    public void setUsername(String username) {
        this.user = usernamefield.getText();
    }
    public static String getUsername() { return user;}
    public void setEmail(String email) {
        this.email = emailfield.getText();
    }
    public static String getEmail() {
        return email;
    }
    public static void setUserId(int user_id) {
        userId = user_id;
    }
    public static int getUserId() {
        return userId;
    }

    private boolean usernameExists(String username) {
        try {
            String checkUser = "Select * FROM user where user_name = ?";
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(checkUser);
            preparedStatement.setString(1, username);

            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                return true;
            }

            preparedStatement.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return false;
    }

    private boolean emailExists(String email) {
        try {
            String checkEmail = "Select * FROM user where user_email = ?";
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(checkEmail);
            preparedStatement.setString(1, email);

            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                return true;
            }

            preparedStatement.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return false;
    }

    private void addUser(String username, String email, String password) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] passwordHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            String insertUser = "INSERT INTO user (user_name, user_email, user_password) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertUser, PreparedStatement.RETURN_GENERATED_KEYS);

            insertStatement.setString(1, username);
            insertStatement.setString(2, email);
            insertStatement.setString(3, Base64.getEncoder().encodeToString(passwordHash));

            int result = insertStatement.executeUpdate();

            if (result > 0) {
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                application.replaceSceneContent("fxml/Info.fxml");
                if (generatedKeys.next()) {
                    int user_id = generatedKeys.getInt(1);
                    setUserId(user_id);
                }
                insertStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
