package demo.controllers;

import demo.AnchorPaneUtils;
import demo.connection.ClassConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class FavoritesPageController extends AnchorPane implements Initializable {

    public AnchorPane parentAnchorPane;
    public AnchorPane childAnchorPane;
    public HBox childHbox;

    public VBox svg1Nav;
    public VBox svg2Nav;
    public VBox svg3Nav;
    public VBox svg4Nav;
    public VBox svg5Nav;

    public TextField searchTextField1;
    public TextField searchTextField2;
    public AnchorPane tableAnchorPane;
    @FXML
    private TableView<MealSearchModel> favoriteMealsTableView;
    @FXML
    private TableColumn<MealSearchModel, String> usernameTable1Column;
    @FXML
    private TableColumn<MealSearchModel, String> useremailTable1Column;
    @FXML
    private TableColumn<MealSearchModel, String> mealCategoryTableColumn;
    @FXML
    private TableColumn<MealSearchModel, String> mealDurationTableColumn;
    @FXML
    private TableColumn<MealSearchModel, String> mealTimesperweekTableColumn;
    @FXML
    private TableColumn<MealSearchModel, String> mealDifficultyTableColumn;
    @FXML
    private TableColumn<MealSearchModel, Image> mealImageTableColumn;

    @FXML
    private TableView<WorkoutSearchModel>favoriteWorkoutsTableView;
    @FXML
    private TableColumn<WorkoutSearchModel, String> usernameTable2Column;
    @FXML
    private TableColumn<WorkoutSearchModel, String> useremailTable2Column;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutCategoryTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutDurationTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutTimesperweekTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, String> workoutDifficultyTableColumn;
    @FXML
    private TableColumn<WorkoutSearchModel, Image> workoutImageTableColumn;

    ObservableList<MealSearchModel> mealSearchModelObservableList = FXCollections.observableArrayList();
    ObservableList<WorkoutSearchModel> workoutSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPaneUtils.intializeAnchorPane(parentAnchorPane, childAnchorPane);
        AnchorPaneUtils.intializeHbox(childAnchorPane, childHbox);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(tableAnchorPane);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        childHbox.getChildren().add(scrollPane);

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

        ClassConnection classConnection = new ClassConnection();
        Connection connection = classConnection.getConnection();

        String query1 = "SELECT user.user_name, user.user_email, meal.meal_category, meal.meal_duration, " +
                "meal.meal_times_per_week, meal.meal_difficulty, meal.meal_image " +
                "FROM user_favorite_meals JOIN user ON user_favorite_meals.user_id = user.user_id " +
                "JOIN meal ON user_favorite_meals.meal_id = meal.meal_id WHERE user.user_id = ?";

        String query2 = "SELECT user.user_name, user.user_email, workout.workout_category, workout.workout_duration, " +
                "workout.workout_times_per_week, workout.workout_difficulty, workout.workout_image " +
                "FROM user_favorite_workouts JOIN user ON user_favorite_workouts.user_id = user.user_id " +
                "JOIN workout ON user_favorite_workouts.workout_id = workout.workout_id WHERE user.user_id = ?";

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setInt(1, SignUpController.getUserId());
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement2.setInt(1, SignUpController.getUserId());
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while(resultSet1.next()) {
                String user_name = resultSet1.getString("user.user_name");
                String user_email = resultSet1.getString("user.user_email");
                String meal_category = resultSet1.getString("meal.meal_category");
                String meal_duration = resultSet1.getString("meal.meal_duration");
                String meal_timesperweek = resultSet1.getString("meal.meal_times_per_week");
                String meal_difficulty = resultSet1.getString("meal.meal_difficulty");
                Blob meal_image_blob = resultSet1.getBlob("meal.meal_image");
                byte[] meal_image = meal_image_blob.getBytes(1, (int)meal_image_blob.length());


                Image mealImage = null;
                if (meal_image != null) {
                    mealImage = new Image(new ByteArrayInputStream(meal_image));
                }

                mealSearchModelObservableList.add(new MealSearchModel(user_name, user_email, meal_category,
                        meal_duration, meal_timesperweek, meal_difficulty, mealImage));
            }

            usernameTable1Column.setCellValueFactory(new PropertyValueFactory<>("userName"));
            useremailTable1Column.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
            mealCategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealCategory"));
            mealDurationTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealDuration"));
            mealTimesperweekTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealTimesperweek"));
            mealDifficultyTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealDifficulty"));
            mealImageTableColumn.setCellValueFactory(new PropertyValueFactory<>("mealImage"));
            mealImageTableColumn.setCellFactory(tc -> new TableCell<MealSearchModel, Image>() {
                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        ImageView imageView = new ImageView(item);
                        imageView.setFitHeight(125);
                        imageView.setFitWidth(125);
                        setGraphic(imageView);
                    }
                }
            });

            favoriteMealsTableView.setItems(mealSearchModelObservableList);

            FilteredList<MealSearchModel> filteredData1 = new FilteredList<>(mealSearchModelObservableList, b -> true);

            searchTextField1.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData1.setPredicate(MealSearchModel -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }

                    String searchKeyword = newValue.toLowerCase();
                    if(MealSearchModel.getMealCategory().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(MealSearchModel.getMealDuration().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(MealSearchModel.getMealTimesperweek().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(MealSearchModel.getMealDifficulty().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(MealSearchModel.getUserName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(MealSearchModel.getUserEmail().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });

            SortedList<MealSearchModel> sortedData1 = new SortedList<>(filteredData1);

            sortedData1.comparatorProperty().bind(favoriteMealsTableView.comparatorProperty());

            favoriteMealsTableView.setItems(sortedData1);

            while(resultSet2.next()) {
                String user_name = resultSet2.getString("user.user_name");
                String user_email = resultSet2.getString("user.user_email");
                String workout_category = resultSet2.getString("workout.workout_category");
                String workout_duration = resultSet2.getString("workout.workout_duration");
                String workout_timesperweek = resultSet2.getString("workout.workout_times_per_week");
                String workout_difficulty = resultSet2.getString("workout.workout_difficulty");
                Blob workout_image_blob = resultSet2.getBlob("workout.workout_image");
                byte[] workout_image = workout_image_blob.getBytes(1, (int)workout_image_blob.length());

                Image workoutImage = null;
                if (workout_image != null) {
                    workoutImage = new Image(new ByteArrayInputStream(workout_image));
                }

                workoutSearchModelObservableList.add(new WorkoutSearchModel(user_name, user_email,workout_category,
                        workout_duration, workout_timesperweek, workout_difficulty, workoutImage));
            }
            usernameTable2Column.setCellValueFactory(new PropertyValueFactory<>("userName"));
            useremailTable2Column.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
            workoutCategoryTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutCategory"));
            workoutDurationTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutDuration"));
            workoutTimesperweekTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutTimesperweek"));
            workoutDifficultyTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutDifficulty"));
            workoutImageTableColumn.setCellValueFactory(new PropertyValueFactory<>("workoutImage"));
            workoutImageTableColumn.setCellFactory(tc -> new TableCell<WorkoutSearchModel, Image>() {

                @Override
                protected void updateItem(Image item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        ImageView imageView = new ImageView(item);
                        imageView.setFitHeight(125);
                        imageView.setFitWidth(125);
                        setGraphic(imageView);
                    }
                }
            });

            favoriteWorkoutsTableView.setItems(workoutSearchModelObservableList);

            FilteredList<WorkoutSearchModel> filteredData2 = new FilteredList<>(workoutSearchModelObservableList, b -> true);

            searchTextField2.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData2.setPredicate(WorkoutSearchModel -> {
                    if(newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                        return true;
                    }
                    String searchKeyword = newValue.toLowerCase();
                    if(WorkoutSearchModel.getWorkoutCategory().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(WorkoutSearchModel.getWorkoutDuration().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(WorkoutSearchModel.getWorkoutTimesperweek().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(WorkoutSearchModel.getWorkoutDifficulty().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(WorkoutSearchModel.getUserName().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else if(WorkoutSearchModel.getUserEmail().toLowerCase().indexOf(searchKeyword) > -1) {
                        return true;
                    }
                    else {
                        return false;
                    }
                });
            });

            SortedList<WorkoutSearchModel> sortedData2 = new SortedList<>(filteredData2);

            sortedData2.comparatorProperty().bind(favoriteWorkoutsTableView.comparatorProperty());

            favoriteWorkoutsTableView.setItems(sortedData2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
