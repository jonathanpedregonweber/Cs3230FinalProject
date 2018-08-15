package sample;

import javafx.scene.control.Button;
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
        for (int column = 0; column < 10; column++)
        {
            for(int row = 0; row < 10; row++)
            {
                StartButton.setVisible(false);
                Button buttonToAdd = new Button();
                String buttonText = RowLetters[row]+ (column +1);
                buttonToAdd.setText(buttonText);
                buttonToAdd.setDisable(true);
                buttonToAdd.autosize();
                BattleshipGridPane.add(new Button(),column,row);
                PlayerButtons[row][column] = buttonToAdd;
            }
        }

        for (int column = 11; column < 21; column++)
        {
            for(int row = 0; row < 10; row++)
            {
                Button buttonToAdd = new Button();
                String buttonText = RowLetters[row]+ (column -9);
                buttonToAdd.setText(buttonText);
                buttonToAdd.setDisable(true);
                buttonToAdd.autosize();
                BattleshipGridPane.add(new Button(),column,row);
                OpponentButtons[row][column-11] = buttonToAdd;
            }
        }
    }
}

