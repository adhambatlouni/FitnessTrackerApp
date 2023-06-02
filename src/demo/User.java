package demo;

import demo.connection.ClassConnection;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.*;

public class User {
    @FXML
    private ComboBox<String> myComboBox1;
    private static int userId;
    private static String username;

    public static TextField usernamefield;
    private String email;
    private String gender;

    private static String user;
    private String password;

    public User() {this(0, "", "", "", "");}

    public User(int userId, String username, String email, String password, String gender) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
    }

    public User(String username, String email, String password, String gender) {
    }

    // Getters
    public int getUserId() throws SQLException {
        return userId;
    }

    public void setUserId(int userId) throws SQLException {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUsername(String username) {
        this.user = usernamefield.getText();
    }
    public static String getUsername() {
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User getUserById(int userId, String gender) throws SQLException {
        User user = null;
        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            userId = rs.getInt("user_id");
            String username = rs.getString("user_name");
            String email = rs.getString("user_email");
            String password = rs.getString("user_password");
            gender = rs.getString("user_gender");
            user = new User( username, email, password, gender);
        }
        return user;
    }
}