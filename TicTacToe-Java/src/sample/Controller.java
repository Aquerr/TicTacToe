package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private String currentPlayer = "X";
    private Boolean gameEnded = false;

    @FXML public Button btn1;
    @FXML public Button btn2;
    @FXML public Button btn3;

    @FXML public Button btn4;
    @FXML public Button btn5;
    @FXML public Button btn6;

    @FXML public Button btn7;
    @FXML public Button btn8;
    @FXML public Button btn9;

    private Map<Integer, Button> buttonMap;

    @FXML protected void initialize()
    {
        buttonMap = new HashMap<>();
        buttonMap.put(1, btn1);
        buttonMap.put(2, btn2);
        buttonMap.put(3, btn3);
        buttonMap.put(4, btn4);
        buttonMap.put(5, btn5);
        buttonMap.put(6, btn6);
        buttonMap.put(7, btn7);
        buttonMap.put(8, btn8);
        buttonMap.put(9, btn9);
    }

    @FXML public void onButtonPressed(ActionEvent event)
    {
        if (!gameEnded)
        {
            Button button = (Button)event.getSource();

            if (button.getText().equals(""))
            {
                button.setText(currentPlayer);
            }

            if (didWin(currentPlayer))
            {
                gameEnded = true;
                showFinish();
            }

            switchSide();
        }
        else
        {
            showFinish();
        }
    }

    private void showFinish() {
        Stage stage = new Stage();
        VBox pane = new VBox(5);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(new Label("Game has been finished!"));

        Button okButton = new Button("Ok");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });

        pane.getChildren().add(okButton);

        Scene scene = new Scene(pane, 150, 50);
        stage.setScene(scene);
        stage.show();
    }

    private boolean didWin(String player) {
//            GridPane root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            int[][] expectedResults = Main.getExpectedResults();

            for (int i = 0; i < expectedResults.length ; i++) {
                int[] expectedResult = expectedResults[i];

                Button field1 = buttonMap.get(expectedResult[0]);
                Button field2 = buttonMap.get(expectedResult[1]);
                Button field3 = buttonMap.get(expectedResult[2]);

                if (field1.getText() != null && field2.getText() != null && field3.getText() != null)
                {
                    if (field1.getText().equals(currentPlayer) && field2.getText().equals(currentPlayer) && field3.getText().equals(currentPlayer))
                    {
                        return true;
                    }
                }
            }

        return false;
    }

    private void switchSide()
    {
        if (currentPlayer.equals("X"))
            currentPlayer = "O";
        else currentPlayer = "X";
    }
}
