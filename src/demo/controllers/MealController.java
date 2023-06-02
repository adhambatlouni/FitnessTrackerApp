package demo.controllers;

import demo.AnchorPaneUtils;
import demo.connection.ClassConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.GOLD;

public class MealController extends AnchorPane implements Initializable {
    public static boolean isFavorite1;
    public static boolean isFavorite2;
    public static boolean isFavorite3;
    public static boolean isFavorite4;
    public static boolean isFavorite5;
    public static boolean isFavorite6;
    public static boolean isFavorite7;
    public static boolean isFavorite8;
    public static boolean isFavorite9;

    public AnchorPane parentAnchorPane;

    public AnchorPane childAnchorPane;

    public VBox childVbox;
    public Label meal1duration;
    public Label meal2duration;
    public Label meal3duration;
    public Label meal4duration;
    public Label meal5duration;
    public Label meal6duration;
    public Label meal7duration;
    public Label meal1times;
    public Label meal2times;
    public Label meal3times;
    public Label meal4times;
    public Label meal5times;
    public Label meal6times;
    public Label meal7times;
    public Label meal1difficulty;
    public Label meal2difficulty;
    public Label meal3difficulty;
    public Label meal4difficulty;
    public Label meal5difficulty;
    public Label meal6difficulty;
    public Label meal7difficulty;
    public Label meal8Category;
    public Label meal8duration;
    public Label meal8times;
    public Label meal8difficulty;
    public Label meal9Category;
    public Label meal9duration;
    public Label meal9times;
    public Label meal9difficulty;
    public ImageView meal1ImageView;
    public ImageView meal2ImageView;
    public ImageView meal3ImageView;
    public ImageView meal4ImageView;
    public ImageView meal5ImageView;
    public ImageView meal6ImageView;
    public ImageView meal7ImageView;
    public ImageView meal8ImageView;
    public ImageView meal9ImageView;

    @FXML
    private SVGPath svg1;
    public  SVGPath svg2;
    public SVGPath svg3;
    public SVGPath svg4;
    public SVGPath svg5;
    public SVGPath svg6;
    public SVGPath svg7;
    public SVGPath svg8;
    public SVGPath svg9;
    @FXML
    private Label meal1Category;
    public Label meal2Category;
    public Label meal3Category;
    public Label meal4Category;
    public Label meal5Category;
    public Label meal6Category;
    public Label meal7Category;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);

        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM meal";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            String category = null;
            String duration = null;
            String times = null;
            String difficulty = null;
            byte[] imageData = null;
            if (resultSet.next()) {
                category = resultSet.getString("meal_category");
                duration = resultSet.getString("meal_duration");
                times = resultSet.getString("meal_times_per_week");
                difficulty = resultSet.getString("meal_difficulty");
                imageData = resultSet.getBytes("meal_image");
                if (meal1Category != null) {
                    meal1Category.setText(category);
                    meal1duration.setText(duration);
                    meal1times.setText(times);
                    meal1difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (meal1ImageView != null) {
                        meal1ImageView.setImage(image);
                        if(isFavorite1)
                            svg1.setFill(Color.GOLD);
                        else
                            svg1.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("meal_category");
                duration = resultSet.getString("meal_duration");
                times = resultSet.getString("meal_times_per_week");
                difficulty = resultSet.getString("meal_difficulty");
                imageData = resultSet.getBytes("meal_image");
                if (meal2Category != null) {
                    meal2Category.setText(category);
                    meal2duration.setText(duration);
                    meal2times.setText(times);
                    meal2difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (meal2ImageView != null) {
                        meal2ImageView.setImage(image);
                        if(isFavorite2)
                            svg2.setFill(Color.GOLD);
                        else
                            svg2.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("meal_category");
                duration = resultSet.getString("meal_duration");
                times = resultSet.getString("meal_times_per_week");
                difficulty = resultSet.getString("meal_difficulty");
                imageData = resultSet.getBytes("meal_image");
                if (meal3Category != null) {
                    meal3Category.setText(category);
                    meal3duration.setText(duration);
                    meal3times.setText(times);
                    meal3difficulty.setText(difficulty);

                    if (imageData != null) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        Image image = new Image(bis);
                        if (meal3ImageView != null) {
                            meal3ImageView.setImage(image);
                            if(isFavorite3)
                                svg3.setFill(Color.GOLD);
                            else
                                svg3.setFill(Color.valueOf("#515f66"));
                        }
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("meal_category");
                duration = resultSet.getString("meal_duration");
                times = resultSet.getString("meal_times_per_week");
                difficulty = resultSet.getString("meal_difficulty");
                imageData = resultSet.getBytes("meal_image");
                if (meal4Category != null) {
                    meal4Category.setText(category);
                    meal4duration.setText(duration);
                    meal4times.setText(times);
                    meal4difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (meal4ImageView != null) {
                        meal4ImageView.setImage(image);
                        if(isFavorite4)
                            svg4.setFill(Color.GOLD);
                        else
                            svg4.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("meal_category");
                duration = resultSet.getString("meal_duration");
                times = resultSet.getString("meal_times_per_week");
                difficulty = resultSet.getString("meal_difficulty");
                imageData = resultSet.getBytes("meal_image");
                if (meal5Category != null) {
                    meal5Category.setText(category);
                    meal5duration.setText(duration);
                    meal5times.setText(times);
                    meal5difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (meal5ImageView != null) {
                        meal5ImageView.setImage(image);
                        if(isFavorite5)
                            svg5.setFill(Color.GOLD);
                        else
                            svg5.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("meal_category");
                duration = resultSet.getString("meal_duration");
                times = resultSet.getString("meal_times_per_week");
                difficulty = resultSet.getString("meal_difficulty");
                imageData = resultSet.getBytes("meal_image");
                if (meal6Category != null) {
                    meal6Category.setText(category);
                    meal6duration.setText(duration);
                    meal6times.setText(times);
                    meal6difficulty.setText(difficulty);
                    if (imageData != null) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        Image image = new Image(bis);
                        if (meal6ImageView != null) {
                            meal6ImageView.setImage(image);
                            if(isFavorite6)
                                svg6.setFill(Color.GOLD);
                            else
                                svg6.setFill(Color.valueOf("#515f66"));;
                        }
                    }
                }
            }
                if (resultSet.next()) {
                    category = resultSet.getString("meal_category");
                    duration = resultSet.getString("meal_duration");
                    times = resultSet.getString("meal_times_per_week");
                    difficulty = resultSet.getString("meal_difficulty");
                    imageData = resultSet.getBytes("meal_image");
                    if (meal7Category != null) {
                        meal7Category.setText(category);
                        meal7duration.setText(duration);
                        meal7times.setText(times);
                        meal7difficulty.setText(difficulty);
                    }
                    if (imageData != null) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        Image image = new Image(bis);
                        if (meal7ImageView != null) {
                            meal7ImageView.setImage(image);
                            if(isFavorite7)
                                svg7.setFill(Color.GOLD);
                            else
                                svg7.setFill(Color.valueOf("#515f66"));
                        }
                    }
                }

                if (resultSet.next()) {
                    category = resultSet.getString("meal_category");
                    duration = resultSet.getString("meal_duration");
                    times = resultSet.getString("meal_times_per_week");
                    difficulty = resultSet.getString("meal_difficulty");
                    imageData = resultSet.getBytes("meal_image");
                    if (meal8Category != null) {
                        meal8Category.setText(category);
                        meal8duration.setText(duration);
                        meal8times.setText(times);
                        meal8difficulty.setText(difficulty);
                    }
                    if (imageData != null) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        Image image = new Image(bis);
                        if (meal8ImageView != null) {
                            meal8ImageView.setImage(image);
                            if(isFavorite8)
                                svg8.setFill(Color.GOLD);
                            else
                                svg8.setFill(Color.valueOf("#515f66"));
                        }
                    }
                }

                if (resultSet.next()) {
                    category = resultSet.getString("meal_category");
                    duration = resultSet.getString("meal_duration");
                    times = resultSet.getString("meal_times_per_week");
                    difficulty = resultSet.getString("meal_difficulty");
                    imageData = resultSet.getBytes("meal_image");
                    if (meal9Category != null) {
                        meal9Category.setText(category);
                        meal9duration.setText(duration);
                        meal9times.setText(times);
                        meal9difficulty.setText(difficulty);
                    }
                    if (imageData != null) {
                        ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                        Image image = new Image(bis);
                        if (meal9ImageView != null) {
                            meal9ImageView.setImage(image);
                            if(isFavorite9)
                                svg9.setFill(Color.GOLD);
                            else
                                svg9.setFill(Color.valueOf("#515f66"));
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    @FXML
    public void addMeal1(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 1;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite1 = false;
                svg1.setFill(Color.valueOf("#515f66"));

            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();
                isFavorite1 = true;
                svg1.setFill(Color.GOLD);
            }
            SVGPath svg = svg1;
            setSvg1(svg);
            System.out.println(getSvg1());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setSvg1(SVGPath svg1) {
        this.svg1 = svg1;
    }
    public SVGPath getSvg1() {
        return svg1;
    }

    public void addMeal2(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 2;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite2 = false;
                svg2.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();
                isFavorite2 = true;
                svg2.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addMeal3(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 3;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite3 = false;
                svg3.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();

                isFavorite3 = true;
                svg3.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMeal4(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 4;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite4 = false;
                svg4.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();

                isFavorite4 = true;
                svg4.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMeal5(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 5;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite5 = false;
                svg5.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();

                isFavorite5 = true;
                svg5.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMeal6(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 6;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite6 = false;
                svg6.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();

                isFavorite6 = true;
                svg6.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addMeal7(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 7;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite7 = false;
                svg7.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();

                isFavorite7 = true;
                svg7.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMeal8(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 8;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite8 = false;
                svg8.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();

                isFavorite8 = true;
                svg8.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addMeal9(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int mealId = 9;

            String query = "SELECT * FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, mealId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_meals WHERE user_id = ? AND meal_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, mealId);
                deleteStatement.execute();

                isFavorite9 = false;
                svg9.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_meals (user_id, meal_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, mealId);
                statement.execute();

                isFavorite9 = true;
                svg9.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

