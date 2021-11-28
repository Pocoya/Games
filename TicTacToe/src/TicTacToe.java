import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//----------------------------------------------------------------------------------------
//TicTacToe Version 1.0
//----------------------------------------------------------------------------------------
public class TicTacToe extends JPanel implements ActionListener {
    //----------------------------------------------------------------------------------------
//Deklarationer
//----------------------------------------------------------------------------------------
    JButton[][] spelplan = new JButton[3][3];
    Color[] farg = {Color.white};
    JButton tryck;
    String spelare="X";
    JLabel winner;
    boolean gameOver = false;
    ImageIcon bakgrund = new ImageIcon("bg.png");
    //----------------------------------------------------------------------------------------
//konstruktor
//----------------------------------------------------------------------------------------
    public TicTacToe(){
        this.setLayout(null);

        for(int rad=0; rad<3; rad=rad+1){
            for(int kol=0; kol<3; kol=kol+1){
                spelplan[rad][kol] = new JButton();
                spelplan[rad][kol].setSize(100,100);
                spelplan[rad][kol].addActionListener(this);
                spelplan[rad][kol].setFont(new Font("Times New Roman", Font.BOLD,50));
                spelplan[rad][kol].setLocation(100+kol*100,130+rad*100);
                spelplan[rad][kol].setBackground(Color.red);
                this.add(spelplan[rad][kol]);
            }//end for kol;
        }//end for rad;
//Tryck
        tryck = new JButton();
        tryck.setText("Nytt Spel");
        tryck.setSize(200,70);
        tryck.setLocation(140,20);
        tryck.setFont(new Font("Times New Roman", Font.BOLD,30));
        tryck.setForeground(Color.black);
        tryck.setOpaque(true);
        tryck.addActionListener(this);
        this.add(tryck);
//visavinnare(label)
        winner = new JLabel();
        winner.setSize(190,70);
        winner.setLocation(160,450);
        winner.setFont(new Font("Times New Roman", Font.BOLD,30));
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        winner.setForeground(Color.blue);
        winner.setOpaque(true);
        this.add(winner);
        System.out.println("Vinnare()");
    }//end konstruktor
    //----------------------------------------------------------------------------------------
//paint
//----------------------------------------------------------------------------------------
    public void paintComponent(Graphics g){
//System.out.println("paintComponent()");
        super.paintComponent(g);
        bakgrund.paintIcon(this,g,0,0);
    }// end paint
    //----------------------------------------------------------------------------------------
//actionperformed -motor
//----------------------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(tryck)){
            TicTacToe();
        }//end if
        else{
            markeraVal(e);
            kollaRad();
            kollaKolumn();
            kollaDiagonal();
            if (gameOver) {
                visaVinnare();
            }//end if
            bytSpelare();
        }//end else
    }//end actionPerformed
    //----------------------------------------------------------------------------------------
//markeraVal
//----------------------------------------------------------------------------------------
    public void markeraVal(ActionEvent e){
//System.out.println("markeraVal()");
        for(int rad=0; rad<3; rad=rad+1){
            for(int kol=0; kol<3; kol=kol+1){
                if(e.getSource().equals(spelplan[rad][kol])){
                    spelplan[rad][kol].setText(spelare);
                }//end if
            }//end for kol
        }//end for rad
    }//end markeraVal
    //----------------------------------------------------------------------------------------
//TicTacToe
//----------------------------------------------------------------------------------------
    public void TicTacToe(){
// System.out.println("TicTacToe()");
        for(int rad=0; rad<3; rad=rad+1){
            for(int kol=0; kol<3; kol=kol+1){
                spelplan[rad][kol].setText("");
                spelplan[rad][kol].setBackground(Color.red);
                gameOver=false;
                winner.setText("");
            }//end for kol
        }//end for rad
    }//end TicTacToe
    //----------------------------------------------------------------------------------------
//bytSpelare
//----------------------------------------------------------------------------------------
    public void bytSpelare(){
// System.out.println("bearbeta()");
        if(spelare == "X"){
            spelare = "O";
        }//end if
        else{
            spelare= "X";
        }//end else
    }//end bytSpelare
    //----------------------------------------------------------------------------------------
//kollaRad
//----------------------------------------------------------------------------------------
    public void kollaRad(){
// System.out.println("bearbeta()");
        for(int rad=0; rad<3; rad=rad+1){
            for(int kol=0; kol<3; kol=kol+1){
                if(spelplan[rad][0].getText().equals(""));
                else {
                    if (spelplan[rad][0].getText().equals(spelplan[rad][1].getText())&&
                            spelplan[rad][0].getText().equals(spelplan[rad][2].getText())){
                        visaVinnare();
                        spelplan[rad][kol].setBackground(Color.green);
                        gameOver=true;
                    }//end if
                }//end else
            }//for kol
        }//for rad
    }//end kollaRad
    //----------------------------------------------------------------------------------------
//kollaDiagonal
//----------------------------------------------------------------------------------------
    public void kollaDiagonal(){
// System.out.println("bearbeta()");
        for(int rad=0; rad<3; rad=rad+1){
            for(int kol=0; kol<3; kol=kol+1){
                if(spelplan[1][1].getText().equals(""));
                else{
                    if(spelplan[0][0].getText().equals(spelplan[1][1].getText())&&
                            spelplan[1][1].getText().equals(spelplan[2][2].getText())||
                            spelplan[0][2].getText().equals(spelplan[1][1].getText())&&
                                    spelplan[1][1].getText().equals(spelplan[2][0].getText())){
                        visaVinnare();
                        spelplan[rad][kol].setBackground(Color.green);
                        gameOver=true;
                    }//end if
                }//end else
            }//end for
        }//end for
    }//kollaDiagonal
    //----------------------------------------------------------------------------------------
//kollaKolumn
//----------------------------------------------------------------------------------------
    public void kollaKolumn(){
// System.out.println("bearbeta()");
        for(int rad=0; rad<3; rad=rad+1){
            for(int kol=0; kol<3; kol=kol+1){
                if(spelplan[0][kol].getText().equals(""));
                else{
                    if(spelplan[0][kol].getText().equals(spelplan[1][kol].getText())&&
                            spelplan[0][kol].getText().equals(spelplan[2][kol].getText())){
                        visaVinnare();
                        spelplan[rad][kol].setBackground(Color.green);
                        gameOver=true;
                    }//end if
                }//end else
            }//end for koll
        }//end for rad
    }//end kollaKolumn
    //----------------------------------------------------------------------------------------
//visaVinnare
//----------------------------------------------------------------------------------------
    public void visaVinnare(){
// System.out.println("bearbeta()");
        winner.setText("Vinnare: "+ spelare);
    }//end visaVinnare
    //----------------------------------------------------------------------------------------
//Drar igång programmet
//----------------------------------------------------------------------------------------
    public static void main(String[]args){
        JFrame f = new JFrame();
        f.setSize(500,600);
        f.setLocation(1000,100);
        f.setTitle("TicTacToe");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TicTacToe p = new TicTacToe();
        f.add(p);
        f.setVisible(true);
    }//end main
}//end class
//----------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------