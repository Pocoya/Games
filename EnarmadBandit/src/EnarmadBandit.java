import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//-------------------------------------------------------------------------------------
//Enarmad Bandit Version 1.0
//-------------------------------------------------------------------------------------
public class EnarmadBandit extends JPanel implements ActionListener {
    //-------------------------------------------------------------------------------------
//Deklarationer
//-------------------------------------------------------------------------------------
    JButton[] knapp = new JButton[3];
    int nr = 1;
    JButton spin;
    JLabel vinnare;
    int pott = 100;
    ImageIcon bakgrund = new ImageIcon("bg.jpeg");
    //-------------------------------------------------------------------------------------
//Konstruktor
//-------------------------------------------------------------------------------------
    public EnarmadBandit(){
        this.setLayout(null);

        for(int i=0; i<3; i=i+1){
            knapp[i] = new JButton();
            knapp[i].setFont(new Font("Algerian", Font.BOLD,50));
            knapp[i].setSize(100,100);
            knapp[i].setLocation(100+i*100,155);
            knapp[i].setBackground(Color.blue);
            this.add(knapp[i]);
        }//end for;
//spin
        spin = new JButton();
        spin.setText("Spin");
        spin.setSize(100,70);
        spin.setLocation(450,170);
        spin.setFont(new Font("Times New Roman", Font.BOLD,30));
        spin.setForeground(Color.black);
        spin.setOpaque(true);
        spin.addActionListener(this);
        this.add(spin);
//visa vinnare
        vinnare = new JLabel();
        vinnare.setSize(250,70);
        vinnare.setLocation(125,300);
        vinnare.setFont(new Font("Times New Roman", Font.BOLD,30));
        vinnare.setHorizontalAlignment(SwingConstants.CENTER);
        vinnare.setBackground(Color.gray);
        vinnare.setForeground(Color.red);
        vinnare.setText("Pott: " + pott + " $");
        vinnare.setOpaque(true);
        this.add(vinnare);

    }//end knstruktor
    //----------------------------------------------------------------------------------------
//paint 
//----------------------------------------------------------------------------------------
    public void paintComponent(Graphics g){
//System.out.println("snurraRandom()");
        super.paintComponent(g);
        bakgrund.paintIcon(this,g,0,0);
    }// end paint
    //-------------------------------------------------------------------------------------
//Actionsperformed
//-------------------------------------------------------------------------------------		
    public void actionPerformed(ActionEvent e){
        if (pott >= 10){
            if(e.getSource().equals(spin)){
                snurraRandom();
                pott = pott - 10;
                vinnare.setText("Pott: " + pott + " $");
            }//end if
            else {
                nyttSpel();
            }//end if
        }// end if
    }//end actionPerformed
    //----------------------------------------------------------------------------------------
//nyttSpel
//----------------------------------------------------------------------------------------
    public void nyttSpel(){
//System.out.println("nyttSpel()");
        for(int i=0; i<3; i=i+1){
            knapp[i].setText("");
            knapp[i].setBackground(Color.blue);
        }// end for
    }//end nyttSpel
    //----------------------------------------------------------------------------------------
//Spinna
//----------------------------------------------------------------------------------------
    public void snurraRandom(){
//System.out.println("snurraRandom()");	
        for(int i=0; i<3; i=i+1){
            nr = (int)(Math.random()*10);
            knapp[i].setText(""+nr);
            kollaVinnare();
        }//end for
    }//end snurraRandom
    //----------------------------------------------------------------------------------------
//visaVinnare
//----------------------------------------------------------------------------------------
    public void kollaVinnare(){
//System.out.println("snurraRandom()");	
        if (knapp[0].getText().equals(knapp[1].getText())&&
                knapp[0].getText().equals(knapp[2].getText())){
            pott = pott + 10000;
        }//end if
        else if (knapp[0].getText().equals(knapp[1].getText())||
                knapp[0].getText().equals(knapp[2].getText())||
                knapp[1].getText().equals(knapp[2].getText())){
            pott = pott + 50;
        }//end elseif
    }//end kollaVinnare
    //--------------------------------------------------------------------------------------
//Drar igång programmet
//--------------------------------------------------------------------------------------
    public static void main(String[]args){
        JFrame f = new JFrame();
        f.setSize(600,500);
        f.setLocation(1060,60);
        f.setTitle("EnarmadBandit");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        EnarmadBandit p = new EnarmadBandit();
        f.add(p);
        f.setVisible(true);
    }//end main
}//end class 
//--------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------