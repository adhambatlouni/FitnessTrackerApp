package demo.controllers;

import demo.AnchorPaneUtils;
import demo.connection.ClassConnection;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ProfileController extends AnchorPane implements Initializable {

    public boolean profileImageUpload;
    public Label uploadLabel;
    public TextField usernametextfield;
    public TextField emailtextfield;
    public Button updateBtn;
    public AnchorPane parentAnchorpane;

    public VBox childVbox;
    public ImageView userImageView;
    public SVGPath profileIcon;
    private Dialog<Void> dialog;

    public void setDialog(Dialog<Void> dialog) {
        this.dialog = dialog;
    }

    private void closeProfile() {
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeVbox(parentAnchorpane, childVbox);
        AnchorPaneUtils.btnHover2(updateBtn);
        AnchorPaneUtils.labelHover2(uploadLabel);

        try {
            userInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void userInfo() throws SQLException {
        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        String username = usernametextfield.getText();
        String useremail = emailtextfield.getText();
        int userId = SignUpController.getUserId();
        byte[] imageData = null;

        String userInfo = "SELECT user_name, user_email, user_profile_image FROM user WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(userInfo);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                 username = resultSet.getString("user_name");
                 useremail = resultSet.getString("user_email");
                imageData = resultSet.getBytes("user_profile_image");

                usernametextfield.setText(username);
                emailtextfield.setText(useremail);

                if (imageData != null) {
                    profileIcon.setVisible(false);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (userImageView != null) {
                        userImageView.setImage(image);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void editUser() throws SQLException {
        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        String username = usernametextfield.getText();
        String useremail = emailtextfield.getText();
        int userId = SignUpController.getUserId();

        TranslateTransition shakeTransition1 = new TranslateTransition(Duration.millis(100), usernametextfield);
        TranslateTransition shakeTransition2 = new TranslateTransition(Duration.millis(100), emailtextfield);
        shakeTransition1.setCycleCount(4);
        shakeTransition2.setCycleCount(4);

        shakeTransition1.setByX(10);
        shakeTransition1.setAutoReverse(true);

        shakeTransition2.setByX(10);
        shakeTransition2.setAutoReverse(true);

        if(username.isEmpty() && useremail.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            return;
        }
        else if(username.isEmpty()) {
            shakeTransition1.playFromStart();
            return;
        }
        else if(useremail.isEmpty()) {
            shakeTransition2.playFromStart();
            return;
        }
        else if(usernameExists(username) && emailExists(useremail)) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            return;
        }
        else if(usernameExists(username)) {
            shakeTransition1.playFromStart();
            return;
        }
        else if(emailExists(useremail)) {
            shakeTransition2.playFromStart();
            return;
        }

        String updateUser = "UPDATE user SET user_name = ?, user_email = ? WHERE user_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(updateUser);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, useremail);
        preparedStatement.setInt(3, userId);

        int rowsAffected = preparedStatement.executeUpdate();

        System.out.println(rowsAffected + " rows updated.");

        preparedStatement.close();
        connection.close();

        usernametextfield.setText("");
        emailtextfield.setText("");
    }

    public void updateUserImage() throws SQLException, IOException, FileNotFoundException {
        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        int userId = SignUpController.getUserId();
        InputStream imageStream = null;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        File imageFile = fileChooser.showOpenDialog(null);

        if (imageFile != null) {
            imageStream = new FileInputStream(imageFile);
        }

        String updateUserImage = "UPDATE user SET user_profile_image = ? WHERE user_id = ?";
        String getUserImage = "SELECT user_profile_image FROM user WHERE user_id = ?";

        PreparedStatement updateStatement = connection.prepareStatement(updateUserImage);
        PreparedStatement selectStatement = connection.prepareStatement(getUserImage);

        if (imageStream != null) {
            updateStatement.setBinaryStream(1, imageStream);
            updateStatement.setInt(2, userId);
            int rowsAffected = updateStatement.executeUpdate();
            System.out.println(rowsAffected + " rows updated.");
        }

        selectStatement.setInt(1, userId);
        ResultSet resultSet = selectStatement.executeQuery();
        if (resultSet.next()) {
            profileIcon.setVisible(false);
            Blob blob = resultSet.getBlob("user_profile_image");
            if (blob != null) {
                InputStream blobStream = blob.getBinaryStream();
                Image userImage = new Image(blobStream);
                userImageView.setImage(userImage);
                profileImageUpload = true;
            }
        }
        updateStatement.close();
        selectStatement.close();
        connection.close();
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

    private boolean emailExists(String useremail) {
        try {
            String checkEmail = "Select * FROM user where user_email = ?";
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(checkEmail);
            preparedStatement.setString(1, useremail);

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

    public void updateUser(ActionEvent actionEvent) throws SQLException {
        editUser();
    }

    public void uploadPhoto(MouseEvent event) throws SQLException, IOException {
        updateUserImage();
    }
}
