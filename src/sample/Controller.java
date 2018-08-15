package sample;

import Main.Handlers.ServerHandler;
import Main.Models.Message;
import Main.Models.MessageFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller
{
    private String[] RowLetters = {"A","B","C","D","E","F","G","H","I","J"};
    private Button[][] PlayerButtons = new Button[10][10];
    private Button[][] OpponentButtons = new Button[10][10];

    private String ServerUrl = "ec2-18-207-150-67.compute-1.amazonaws.com";
    private int ServerPort = 8989;

    public GridPane BattleshipGridPane;
    public Button StartButton;
    public TextArea ChatTextArea = new TextArea();
    public TextField ChatTextField = new TextField();
    public Button ChatSendButton = new Button();

    public void StartGame()
    {
        AddLabels();
        AddChat();
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
        SetUpConnection();
    }

    private void AddChat()
    {
        ChatTextArea.setDisable(true);
        ChatSendButton.setText("Send Chat");
//        ChatSendButton.setOnAction(SendChat());
        BattleshipGridPane.add(ChatTextArea,0,11,21,4);
        BattleshipGridPane.add(ChatTextField, 0, 15,21,2);
        BattleshipGridPane.add(ChatSendButton, 18,17,4,1);
    }

//    private EventHandler<ActionEvent> SendChat()
//    {
//        String chat = ChatTextField.getText();
//
//    }

    private void AddLabels()
    {
        Label playerLabel = new Label();
        playerLabel.setText("Player Board");
        Label opponentLabel = new Label();
        opponentLabel.setText("Opponent Board");
        BattleshipGridPane.add(playerLabel,0,0,10,1);
        BattleshipGridPane.add(opponentLabel,11,0,10,1);
    }

    private void SetUpConnection()
    {
        String userName = "Johnny";
        try (Socket conn = new Socket(ServerUrl, ServerPort);
             BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream())))
        {

            System.out.println("Just connected to " + conn.getRemoteSocketAddress());

            ServerHandler sh = new ServerHandler(conn);

            sh.SendLoginMessage(userName);

            String input = read.readLine();

            int loop = 0;

            while (input != null)
            {
                System.out.println(input);
                Message message = MessageFactory.parse(input);

                System.out.println(message.type);

                if (loop == 0) {
                    sh.SendHitMessage(true);
                } else if (loop == 1) {
                    sh.SendMoveMessage(2, 2);
                } else if (loop == 2) {
                    sh.SendStartMessage();
                }

                input = read.readLine();
                loop++;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

