package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import demo.connection.ClassConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController extends AnchorPane implements Initializable {
    public AnchorPane parentAnchorPane;
    public AnchorPane childAnchorPane;
    public HBox childHbox;
    public VBox svg1Nav;
    public VBox svg2Nav;
    public VBox svg3Nav;
    public VBox svg4Nav;
    public VBox svg5Nav;
    public Label welcomeText;
    public Button startBtn;
    public Label editProfileLabel;
    public Label logoutlabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeHbox(childAnchorPane, childHbox);

        AnchorPaneUtils.setNavBar(svg1Nav);
        AnchorPaneUtils.setNavBar(svg2Nav);
        AnchorPaneUtils.setNavBar(svg3Nav);
        AnchorPaneUtils.setNavBar(svg4Nav);
        AnchorPaneUtils.setNavBar(svg5Nav);

        AnchorPaneUtils.goToProfile(svg1Nav);
        AnchorPaneUtils.goToFavorites(svg2Nav);
        AnchorPaneUtils.goToMeals(svg3Nav);
        AnchorPaneUtils.goToWorkouts(svg4Nav);
        AnchorPaneUtils.goToNotification(svg5Nav);

        AnchorPaneUtils.btnHover2(startBtn);
        AnchorPaneUtils.labelHover3(editProfileLabel);
        AnchorPaneUtils.labelHover3(logoutlabel);

        try {
            welcomeUser();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void welcomeUser() throws SQLException {
        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        String query = "SELECT user_name FROM user WHERE user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, SignUpController.getUserId());

        ResultSet resultSet = preparedStatement.executeQuery();

        if(resultSet.next()) {
            String username = resultSet.getString("user_name");
            welcomeText.setText("Welcome " + username);
        }
    }

    public void goToHomepage(ActionEvent actionEvent) throws Exception {
        Main.replaceSceneContent("fxml/Mealspage.fxml");
    }

    public void editProfile(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Profile.fxml"));
        Parent root = loader.load();
        ProfileController overlayController = loader.getController();

        Dialog<Void> dialog = new Dialog<>();
        dialog.getDialogPane().setContent(root);
        ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(closeButton);
        dialog.showAndWait();
        overlayController.setDialog(dialog);
    }

    private Stage getStage() {
        return (Stage) editProfileLabel.getScene().getWindow();
    }

    public void logout(MouseEvent event) throws Exception {
        Main.replaceSceneContent("fxml/Startup.fxml");
    }
}
