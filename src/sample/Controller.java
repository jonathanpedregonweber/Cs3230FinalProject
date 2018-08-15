package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller
{
    public GridPane BattleshipGridPane;
    public Button StartButton;
    private String[] RowLetters = {"A","B","C","D","E","F","G","H","I","J"};
    private Button[][] PlayerButtons = new Button[10][10];
    private Button[][] OpponentButtons = new Button[10][10];

    public void StartGame()
    {
        AddLabels();
        for (int column = 0; column < 10; column++)
        {
            for(int row = 1; row < 11; row++)
            {
                StartButton.setVisible(false);
                Button buttonToAdd = new Button();
                String buttonText = RowLetters[row - 1]+ (column +1);
                buttonToAdd.setText(buttonText);
                buttonToAdd.setDisable(true);
                buttonToAdd.autosize();
                BattleshipGridPane.add(new Button(),column,row);
                PlayerButtons[row -1][column] = buttonToAdd;
            }
        }

        for (int column = 11; column < 21; column++)
        {
            for(int row = 1; row < 11; row++)
            {
                Button buttonToAdd = new Button();
                String buttonText = RowLetters[row -1]+ (column -9);
                buttonToAdd.setText(buttonText);
                buttonToAdd.setDisable(true);
                buttonToAdd.autosize();
                BattleshipGridPane.add(new Button(),column,row);
                OpponentButtons[row -1][column-11] = buttonToAdd;
            }
        }
    }

    private void AddLabels()
    {
        Label playerLabel = new Label();
        playerLabel.setText("Player Board");
        Label opponentLabel = new Label();
        opponentLabel.setText("Opponent Board");
        BattleshipGridPane.add(playerLabel,0,0,10,1);
        BattleshipGridPane.add(opponentLabel,11,0,10,1);
    }
}

