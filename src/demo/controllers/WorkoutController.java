package demo.controllers;

import demo.AnchorPaneUtils;
import demo.connection.ClassConnection;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import java.io.*;
import java.net.URL;
import java.sql.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import java.util.ResourceBundle;

import static javafx.scene.paint.Color.GOLD;

public class WorkoutController extends AnchorPane implements Initializable {

    public AnchorPane parentAnchorPane;

    public AnchorPane childAnchorPane;

    public VBox childVbox;

    public static boolean isFavorite1;
    public static boolean isFavorite2;
    public static boolean isFavorite3;
    public static boolean isFavorite4;
    public static boolean isFavorite5;
    public static boolean isFavorite6;
    public static boolean isFavorite7;
    public static boolean isFavorite8;
    public static boolean isFavorite9;
    public Label workout1Category;
    public Label workout1duration;
    public Label workout1times;
    public Label workout1difficulty;
    public Label workout2Category;
    public Label workout2duration;
    public Label workout2times;
    public Label workout2difficulty;
    public Label workout3Category;
    public Label workout3duration;
    public Label workout3times;
    public Label workout3difficulty;
    public Label workout4Category;
    public Label workout4duration;
    public Label workout4times;
    public Label workout4difficulty;
    public Label workout5Category;
    public Label workout5duration;
    public Label workout5times;
    public Label workout5difficulty;
    public Label workout6Category;
    public Label workout6duration;
    public Label workout6times;
    public Label workout6difficulty;
    public Label workout7Category;
    public Label workout7duration;
    public Label workout7times;
    public Label workout7difficulty;
    public Label workout8Category;
    public Label workout8duration;
    public Label workout8times;
    public Label workout8difficulty;
    public Label workout9Category;
    public Label workout9duration;
    public Label workout9times;
    public Label workout9difficulty;
    public ImageView workout9ImageView;
    public ImageView workout8ImageView;
    public ImageView workout7ImageView;
    public ImageView workout6ImageView;
    public ImageView workout5ImageView;
    public ImageView workout4ImageView;
    public ImageView workout3ImageView;
    public ImageView workout2ImageView;
    public ImageView workout1ImageView;

    public SVGPath svg1;
    public SVGPath svg2;
    public SVGPath svg3;
    public SVGPath svg4;
    public SVGPath svg5;
    public SVGPath svg6;
    public SVGPath svg7;
    public SVGPath svg8;
    public SVGPath svg9;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeVbox(childAnchorPane, childVbox);

        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        try {
            String query1 = "SELECT * FROM workout";

            PreparedStatement statement = connection.prepareStatement(query1);
            ResultSet resultSet = statement.executeQuery();
            String category = null;
            String duration = null;
            String times = null;
            String difficulty = null;
            byte[] imageData = null;
            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout1Category != null) {
                    workout1Category.setText(category);
                    workout1duration.setText(duration);
                    workout1times.setText(times);
                    workout1difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout1ImageView != null) {
                        workout1ImageView.setImage(image);
                        if(isFavorite1)
                            svg1.setFill(Color.GOLD);
                        else
                            svg1.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout2Category != null) {
                    workout2Category.setText(category);
                    workout2duration.setText(duration);
                    workout2times.setText(times);
                    workout2difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout2ImageView != null) {
                        workout2ImageView.setImage(image);
                        if(isFavorite2)
                            svg2.setFill(Color.GOLD);
                        else
                            svg2.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout3Category != null) {
                    workout3Category.setText(category);
                    workout3duration.setText(duration);
                    workout3times.setText(times);
                    workout3difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout3ImageView != null) {
                        workout3ImageView.setImage(image);
                        if(isFavorite3)
                            svg3.setFill(Color.GOLD);
                        else
                            svg3.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout4Category != null) {
                    workout4Category.setText(category);
                    workout4duration.setText(duration);
                    workout4times.setText(times);
                    workout4difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout4ImageView != null) {
                        workout4ImageView.setImage(image);
                        if(isFavorite4)
                            svg4.setFill(Color.GOLD);
                        else
                            svg4.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout5Category != null) {
                    workout5Category.setText(category);
                    workout5duration.setText(duration);
                    workout5times.setText(times);
                    workout5difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout5ImageView != null) {
                        workout5ImageView.setImage(image);
                        if(isFavorite5)
                            svg5.setFill(Color.GOLD);
                        else
                            svg5.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout6Category != null) {
                    workout6Category.setText(category);
                    workout6duration.setText(duration);
                    workout6times.setText(times);
                    workout6difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout6ImageView != null) {
                        workout6ImageView.setImage(image);
                        if(isFavorite6)
                            svg6.setFill(Color.GOLD);
                        else
                            svg6.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout7Category != null) {
                    workout7Category.setText(category);
                    workout7duration.setText(duration);
                    workout7times.setText(times);
                    workout7difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout7ImageView != null) {
                        workout7ImageView.setImage(image);
                        if(isFavorite7)
                            svg7.setFill(Color.GOLD);
                        else
                            svg7.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout8Category != null) {
                    workout8Category.setText(category);
                    workout8duration.setText(duration);
                    workout8times.setText(times);
                    workout8difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout8ImageView != null) {
                        workout8ImageView.setImage(image);
                        if(isFavorite8)
                            svg8.setFill(Color.GOLD);
                        else
                            svg8.setFill(Color.valueOf("#515f66"));
                    }
                }
            }

            if (resultSet.next()) {
                category = resultSet.getString("workout_category");
                duration = resultSet.getString("workout_duration");
                times = resultSet.getString("workout_times_per_week");
                difficulty = resultSet.getString("workout_difficulty");
                imageData = resultSet.getBytes("workout_image");
                if (workout9Category != null) {
                    workout9Category.setText(category);
                    workout9duration.setText(duration);
                    workout9times.setText(times);
                    workout9difficulty.setText(difficulty);
                }
                if (imageData != null) {
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                    Image image = new Image(bis);
                    if (workout9ImageView != null) {
                        workout9ImageView.setImage(image);
                        if(isFavorite9)
                            svg9.setFill(Color.GOLD);
                        else
                            svg9.setFill(Color.valueOf("#515f66"));
                    }
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addWorkout1(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 1;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite1 = false;
                svg1.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite1 = true;
                svg1.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkout2(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 2;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite2 = false;
                svg2.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite2 = true;
                svg2.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkout3(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 3;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite3 = false;
                svg3.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite3 = true;
                svg3.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkout4(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 4;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite4 = false;
                svg4.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite4 = true;
                svg4.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkout5(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 5;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite5 = false;
                svg5.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite5 = true;
                svg5.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkout6(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 6;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite6 = false;
                svg6.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite6 = true;
                svg6.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addWorkout7(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 7;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite7 = false;
                svg7.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite7 = true;
                svg7.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkout8(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 8;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite8 = false;
                svg8.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite8 = true;
                svg8.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkout9(MouseEvent mouseEvent) {
        try {
            ClassConnection classConnection = new ClassConnection();
            Connection connection = classConnection.getConnection();

            int userId = SignUpController.getUserId();
            int workoutId = 9;

            String query = "SELECT * FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, userId);
            checkStatement.setInt(2, workoutId);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                String deleteQuery = "DELETE FROM user_favorite_workouts WHERE user_id = ? AND workout_id = ?";
                PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, userId);
                deleteStatement.setInt(2, workoutId);
                deleteStatement.execute();

                isFavorite9 = false;
                svg9.setFill(Paint.valueOf("#515f66"));
            } else {
                String query1 ="INSERT INTO user_favorite_workouts (user_id, workout_id) VALUES (?, ?)";

                PreparedStatement statement = connection.prepareStatement(query1);

                statement.setInt(1, userId);
                statement.setInt(2, workoutId);
                statement.execute();

                isFavorite9 = true;
                svg9.setFill(GOLD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
