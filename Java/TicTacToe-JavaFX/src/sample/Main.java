package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static int[][] expectedResults = {
            {1, 2, 3},
            {1, 4, 7},
            {1, 5, 9},
            {3, 5, 7},
            {2, 5, 8},
            {4, 5, 6},
            {3, 6, 9},
            {7, 8, 9}};

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kółko i Krzyżyk");
        primaryStage.setScene(new Scene(root, 300, 275));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static int[][] getExpectedResults()
    {
        return expectedResults;
    }
}
