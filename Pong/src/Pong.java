import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//-------------------------------------------------------------------------------------
//Pong Version 1.0
//-------------------------------------------------------------------------------------
public class Pong extends JPanel implements ActionListener, Runnable, KeyListener {
    //-------------------------------------------------------------------------------------
//Deklarationer 
//-------------------------------------------------------------------------------------
    int		 vRacketX = 35, vRacketY = 300;
    int 	 hRacketX = 640, hRacketY = 300;
    double   bollX = 300, bollY = 325;
    int      poangV = 0, poangH = 0;
    Thread   t = new Thread(this);
    JButton  knapp;
    double   vx = 3, vy = 0;
    boolean  upDown = false, downDown = false, aDown = false, zDown = false;
    // 	SpelLjud ljud = new SpelLjud();
//-------------------------------------------------------------------------------------
//Konstruktor
//-------------------------------------------------------------------------------------
    public Pong(){
        this.setLayout(null);
        knapp = new JButton("START");
        knapp.setSize(250,100);
        knapp.setLocation(220,300);
        knapp.setFont(new Font("Times New Roman", Font.BOLD,60));
        knapp.setForeground(Color.black);
        knapp.setOpaque(true);
        knapp.setBackground(Color.white);
        knapp.addActionListener(this);

        this.add(knapp);
        addKeyListener(this);
    }//end pong
    //-------------------------------------------------------------------------------------
//Ritningar
//-------------------------------------------------------------------------------------
    public void paint(Graphics g){
//System.out.println("paint()");
        g.setColor(Color.black);
        g.fillRect(0,0,700,700);
//Rackets
        g.setColor(Color.white);
        g.fillRect(vRacketX, vRacketY, 10, 100);
        g.fillRect(hRacketX, hRacketY, 10, 100);
//boll
        g.setColor(Color.white);
        g.fillOval((int)bollX, (int)bollY, 30, 30);
//text
        g.setFont(new Font("Times New Roman", Font.BOLD,50));
        g.setColor(Color.white);
        g.drawString(""+poangV+"",200, 50);
        g.drawString(""+poangH+"",420, 50);
//tak
        g.setColor(Color.white);
        g.fillRect(10,60,660,10);
//golv
        g.setColor(Color.white);
        g.fillRect(10,636,660,10);
//väggar bakom racketer		
        g.setColor(Color.white);
        g.fillRect(2,0,10,646);
        g.fillRect(670,0,10,646);
    }//end Graphics
    //-------------------------------------------------------------------------------------
//Actionsperformed (bearbetnings metod)
//-------------------------------------------------------------------------------------		
    public void actionPerformed(ActionEvent e){
//System.out.println("run()");
        knapp.setVisible(false);
        knapp.removeActionListener(this);
        t.start();
        slumpa();
    }//end actionPerformed
    //-------------------------------------------------------------------------------------
//run
//-------------------------------------------------------------------------------------
    public void run(){
//System.out.println("run()");
        requestFocus();
        while(true){
            try{Thread.sleep(5);}
            catch(InterruptedException ie){}
            bollY = bollY - vy;
            bollX = bollX - vx;
//bollens studs på racketer
            if ((bollX < vRacketX + 10)	&&
                    (bollY > vRacketY)		&&
                    (bollY < vRacketY + 100)	||
                    (bollX > hRacketX - 30)	&&
                            (bollY > hRacketY)		&&
                            (bollY < hRacketY + 100 )){
                vx=-vx;
                slumpa();
            }//end if
//-------------------------------------------------------------------------------------
//Bollens studs på golv och tak
            if (bollY <= 60){
                vy=-vy;
            }//end if
            if (bollY >= 620) {
                vy=-vy;
            }//end if
//-------------------------------------------------------------------------------------
//racketernas gräns mot tak och golv
            if (vRacketY <=70) {
                vRacketY=70;
            }//end if
            if (vRacketY >=570){
                vRacketY=570;
            }//end if
            if (hRacketY <=70) {
                hRacketY=70;
            }//end if
            if (hRacketY >=570){
                hRacketY=570;
            }//end if
//-------------------------------------------------------------------------------------
//Poängräknare
            if (bollX < 0) {
                bollX = 300;
                bollY = 300;
                poangH = poangH +1;
//				ljud.spela();
            }//end if

            if (bollX > 700){
                bollX = 300;
                bollY = 300;
                poangV = poangV +1;
//				ljud.spela();
            }//end if
            moveRackets();
            repaint();
        }//end while
    }//end run()
    //-------------------------------------------------------------------------------------
//flytta på racketerna
//-------------------------------------------------------------------------------------	
    public void moveRackets() {
        if (upDown)		hRacketY = hRacketY - 4;
        if (downDown)	hRacketY = hRacketY + 4;
        if (aDown)		vRacketY = vRacketY - 4;
        if (zDown) 		vRacketY = vRacketY + 4;
    }//end 	moveRackets
    //-------------------------------------------------------------------------------------
//Slumpar bollens rörelse 
//-------------------------------------------------------------------------------------	
    public void slumpa(){
//System.out.println("slumpa()");
        vy = (int)(Math.random()*5-2.5);
    }//end slumpa()
    //-------------------------------------------------------------------------------------
//Keypressed (tryckning på knappar)
//-------------------------------------------------------------------------------------			
    public void keyPressed(KeyEvent ke){
        if(ke.getKeyCode()== KeyEvent.VK_A){ aDown = true;}
        if(ke.getKeyCode()== KeyEvent.VK_Z){ zDown = true;}
        if(ke.getKeyCode()== KeyEvent.VK_UP){ upDown = true;}
        if(ke.getKeyCode()== KeyEvent.VK_DOWN){ downDown = true;}
    }// end key pressed
    public void keyReleased(KeyEvent ke){
        if(ke.getKeyCode()== KeyEvent.VK_A) aDown = false;
        if(ke.getKeyCode()== KeyEvent.VK_Z) zDown = false;
        if(ke.getKeyCode()== KeyEvent.VK_UP) upDown = false;
        if(ke.getKeyCode()== KeyEvent.VK_DOWN) downDown = false;
    }// end key released
    public void keyTyped(KeyEvent ke){}
    //-------------------------------------------------------------------------------------
//Drar igång programmet
//-------------------------------------------------------------------------------------
    public static void main(String[] args){
        JFrame f=new JFrame();
        f.setSize(700,700);
        f.setLocation(1050,40);
        f.setTitle("Pong");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pong p = new Pong();
        f.add(p);
        f.setVisible(true);
    }//end main()
}//end class
//-------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------