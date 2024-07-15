import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {

    int board_Width =550;
    int board_Height =600;
    
    JFrame frame = new JFrame("Tic_Tac_Toe");
    JLabel text_label = new JLabel();
    JPanel text_Panel = new JPanel();
    JPanel Board_panel = new JPanel();

    JButton[] [] board = new JButton[3][3];
    String playerX = "X";
    String playerO ="O";
    String current_player = playerX;

    boolean game_over = false;

    int turns=0;


    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(board_Width, board_Height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        text_label.setBackground(Color.cyan);
        text_label.setForeground(Color.white);
        text_label.setFont(new Font("Arial", Font.BOLD, 50));
        text_label.setHorizontalAlignment(JLabel.CENTER);
        text_label.setText("Tic-Tac-Toe");
        text_label.setOpaque(true);

        text_Panel.setLayout(new BorderLayout());
        text_Panel.add(text_label);
        frame.add(text_Panel, BorderLayout.NORTH);

        Board_panel.setLayout(new GridLayout(3,3));
        Board_panel.setBackground(Color.cyan);
        frame.add(Board_panel);

        for (int row=0; row<3; row++) {
            for (int column = 0; column<3; column++){
                JButton tile = new JButton();
                board[row] [column] = tile;
                Board_panel.add(tile);

                tile.setBackground(Color.white);
                tile.setForeground(Color.cyan);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                     tile.addActionListener(new ActionListener() {
                         public void actionPerformed(ActionEvent e) {
                         if(game_over) return;
                            JButton tile = (JButton) e.getSource();

                        if(tile.getText()==""){
                        tile.setText(current_player);

                        turns++;
                        check_winner();
                        if (!game_over){
                            current_player = current_player == playerX ? playerO :playerX;
                            text_label.setText(current_player + " 'S turn");
                         }
                    }      
                  }  
                });
            }
        }  
    }

     void check_winner(){
            //rows
          for (int row=0; row<3;row++){
               if(board[row][0].getText() =="") continue;
        
               if(board[row][0].getText()==board[row][1].getText() &&
                board[row][1].getText()==board[row][2].getText()){
                    for (int i=0; i<3; i++){
                        setWinner(board[row][i]);
                    }
                 game_over =true;
                 return;
        
                }
            }

            //column
            for (int column=0; column<3;column++){
                if(board[0][column].getText() =="") continue;
         
                if(board[0][column].getText()==board[1][column].getText() &&
                 board[1][column].getText()==board[2][column].getText()){
                     for (int i=0; i<3; i++){
                         setWinner(board[i][column]);
                     }
                  game_over =true;
                  return;
                 }
             }

             //left to right corner
             if(
                board[0][0].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][2].getText() &&
                board[0][0].getText() != ""){
                    for(int i=0; i<3; i++){
                        setWinner(board[i][i]);
                    }
                    game_over = true;
                    return;
                }


                //right to left corner
                if(
                board[0][2].getText() == board[1][1].getText() &&
                board[1][1].getText() == board[2][0].getText() &&
                board[0][2].getText() != ""){
                    
                        setWinner(board[0][2]);
                        setWinner(board[1][1]);
                        setWinner(board[2][0]);
                        
                    game_over = true;
                    return;
                }

                if(turns == 9){
                    for(int row=0; row<3; row++){
                        for(int column=0; column<3; column++){
                            Set_tie(board[row][column]);
                        }
                    }
                    game_over = true;
                }
       }


       void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.cyan);
        text_label.setText(current_player + " is the Winner");
       } 
       
       void Set_tie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.cyan);
        text_label.setText("Tie!");
       }    
}