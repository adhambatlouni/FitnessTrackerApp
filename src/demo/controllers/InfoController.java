package demo.controllers;

import demo.AnchorPaneUtils;
import demo.Main;
import demo.User;
import demo.connection.ClassConnection;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class InfoController extends AnchorPane implements Initializable {
    public TextField userheight;
    public TextField userweight;
    public TextField userage;
    public Button nextBtn;
    @FXML
    private AnchorPane parentAnchorPane;
    @FXML
    private AnchorPane childAnchorPane;
    @FXML
    private ComboBox<String> myComboBox1;
    @FXML
    private ComboBox<String> myComboBox2;
    public ComboBox<String> myComboBox3;
    @FXML
    private VBox childVbox;
    private Main application;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);
        List<String> countryNames = fetchCountryNames();
        myComboBox2.getItems().addAll(countryNames);

        AnchorPaneUtils.btnHover(nextBtn);
    }

    public void setApp(Main application) {
        this.application = application;
    }

    private List<String> fetchCountryNames() {
        List<String> countryNames = new ArrayList<>();

        try {
            URL url = new URL("http://www.geognos.com/api/en/countries/info/all.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String response = in.readLine();

            JSONObject jsonObj = new JSONObject(response);
            JSONObject resultsObj = jsonObj.getJSONObject("Results");

            for (String countryCode : resultsObj.keySet()) {
                String countryName = resultsObj.getJSONObject(countryCode).getString("Name");
                countryNames.add(countryName);
            }
            in.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryNames;
    }

    public void addInfo(ActionEvent actionEvent) throws Exception {
        String gender = myComboBox1.getValue();
        String country = myComboBox2.getValue();
        String goal = myComboBox3.getValue();

        String height = userheight.getText();
        String weight = userweight.getText();
        String age = userage.getText();

        TranslateTransition shakeTransition1 = new TranslateTransition(Duration.millis(100), myComboBox1);
        TranslateTransition shakeTransition2 = new TranslateTransition(Duration.millis(100), myComboBox2);
        TranslateTransition shakeTransition3 = new TranslateTransition(Duration.millis(100), userheight);
        TranslateTransition shakeTransition4 = new TranslateTransition(Duration.millis(100), userweight);
        TranslateTransition shakeTransition5 = new TranslateTransition(Duration.millis(100), userage);
        TranslateTransition shakeTransition6 = new TranslateTransition(Duration.millis(100), myComboBox3);
        shakeTransition1.setCycleCount(4);
        shakeTransition2.setCycleCount(4);
        shakeTransition3.setCycleCount(4);
        shakeTransition4.setCycleCount(4);
        shakeTransition5.setCycleCount(4);
        shakeTransition6.setCycleCount(4);

        shakeTransition1.setByX(10);
        shakeTransition1.setAutoReverse(true);

        shakeTransition2.setByX(10);
        shakeTransition2.setAutoReverse(true);

        shakeTransition3.setByX(10);
        shakeTransition3.setAutoReverse(true);

        shakeTransition4.setByX(10);
        shakeTransition4.setAutoReverse(true);

        shakeTransition5.setByX(10);
        shakeTransition5.setAutoReverse(true);

        shakeTransition6.setByX(10);
        shakeTransition6.setAutoReverse(true);

        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        String query = "INSERT INTO fitbit_user_info (user_gender, user_country, user_height, user_weight, user_age," +
                " user_goal, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, gender);
        statement.setString(2, country);
        statement.setString(3, height);
        statement.setString(4, weight);
        statement.setString(5, age);
        statement.setString(6, goal);
        statement.setInt(7, SignUpController.getUserId());

        if(gender.isEmpty() || country.isEmpty() || height.isEmpty() || weight.isEmpty() || age.isEmpty() || goal.isEmpty()) {
            shakeTransition1.playFromStart();
            shakeTransition2.playFromStart();
            shakeTransition3.playFromStart();
            shakeTransition4.playFromStart();
            shakeTransition5.playFromStart();
            return;
        }

        int rowsAffected = statement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("User info added successfully!");
        } else {
            System.out.println("Failed to add user info.");
        }
        myComboBox1.setValue("");
        myComboBox2.setValue("");
        myComboBox3.setValue("");
        userheight.setText("");
        userweight.setText("");
        userage.setText("");
        application.replaceSceneContent("fxml/Homepage.fxml");
    }
}

