import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//--------------------------------------------------------------------------------------
//Min bil, version 1.0
//--------------------------------------------------------------------------------------
public class MinBilV2 extends JPanel implements Runnable,MouseListener,
        MouseMotionListener{
    //--------------------------------------------------------------------------------------
//Deklarationer
//--------------------------------------------------------------------------------------
    int x = 900, y = 280,
            hastighet = 25, vx = 5;
    int bgrund = 0;
    Thread t = new Thread(this);

    int a = 100;
    int b = 100;
    //--------------------------------------------------------------------------------------
//Konstruktor
//--------------------------------------------------------------------------------------
    public MinBilV2(){
        addMouseListener(this);
        this.addMouseMotionListener(this );
        start();
        t.start();
    }//end konstruktor
    //--------------------------------------------------------------------------------------
//Ritaningar till figurerna
//--------------------------------------------------------------------------------------
    public void paint(Graphics g){
//System.out.println("paint()");
//Bakgrund
        g.setColor(Color.gray);
        g.fillRect(0,0,1200,400);
        g.setColor(Color.darkGray);
        g.fillRect(0,400,1200,200);
        g.setColor(Color.blue);
        g.fillRect(0,0,1200,250);
//Sol
        g.setColor(Color.yellow);
        g.fillOval(800,20,100,100);



        g.setColor(Color.white);
        g.fillOval(100, 100, 120, 80);
        g.setColor(Color.white);
        g.fillOval(300, 100, 120, 80);

        g.setColor(Color.black);
        g.fillOval(a, b, 30, 30);
        g.setColor(Color.black);
        g.fillOval(a+200, b, 30, 30);

//nattläge
// 		if(vx >= 25 && vx <= 40){
// 		g.setColor(Color.darkGray);
// 		g.fillRect(0,0,1200,400);
// 		g.setColor(Color.gray);
// 		g.fillRect(0,400,1200,200);
// 		g.setColor(Color.black);
// 		g.fillRect(0,0,1200,250);
//mån
// 		g.setColor(Color.white);
// 		g.fillOval(800,20,100,100);
// 	 }//end if
//Träd1
        g.setColor(Color.orange);
        g.fillRect(675,145,40,105);
        g.setColor(Color.green);
        g.fillOval(670,110,50,50);
        g.setColor(Color.green);
        g.fillOval(700,105,50,50);
        g.setColor(Color.green);
        g.fillOval(640,105,50,50);
        g.setColor(Color.green);
        g.fillOval(660,80,70,70);
//Träd2
// 		g.setColor(Color.orange);
// 		g.fillRect(375,145,40,105);
// 		g.setColor(Color.green);
// 		g.fillOval(370,110,50,50);
// 		g.setColor(Color.green);
// 		g.fillOval(400,105,50,50);
// 		g.setColor(Color.green);
// 		g.fillOval(340,105,50,50);
// 		g.setColor(Color.green);
// 		g.fillOval(360,80,70,70);
//Träd3
        g.setColor(Color.orange);
        g.fillRect(975,145,40,105);
        g.setColor(Color.green);
        g.fillOval(970,110,50,50);
        g.setColor(Color.green);
        g.fillOval(1000,105,50,50);
        g.setColor(Color.green);
        g.fillOval(940,105,50,50);
        g.setColor(Color.green);
        g.fillOval(960,80,70,70);
//skylt
        g.setColor(Color.gray);
        g.fillRect(500,160,8,90);
        g.setColor(Color.red);
        g.fillOval(478,150,50,50);
        g.setColor(Color.yellow);
        g.fillOval(483,155,40,40);
        g.setFont(new Font("Times New Roman",Font.BOLD,24));
        g.setColor(Color.black);
        g.drawString("25",492,183);
//vit linje i mitten
        for(int i = 0; i < 1200; i = i + 100){
            g.setColor(Color.white);
            g.drawLine(i,325,i+50,325);
        }//end for
//Andra bakgrunden
 		if(bgrund >= 1){
 			g.clearRect(0,0,1200,600);
 			g.setColor(Color.gray);
 			g.fillRect(0,0,1200,400);
 			g.setColor(Color.darkGray);
 			g.fillRect(0,400,1200,200);
 			g.setColor(Color.blue);
 			g.fillRect(0,0,1200,250);
 	}//end if
//Karossen
        g.setColor(Color.orange);
        g.fillRect(x,y,150,50);
        g.setColor(Color.orange);
        g.fillRect(x+60,y-40,50,50);
        g.setColor(Color.darkGray);
        g.fillRect(x+10,y-50,10,50);
        g.setColor(Color.cyan);
        g.fillRect(x+65,y-38,40,38);
//Hjul
        g.setColor(Color.black);
        g.fillOval(x+10,y+20,40,40);
        g.setColor(Color.black);
        g.fillOval(x+100,y+20,40,40);
//Rök
        g.setColor(Color.black);
        g.fillOval(x+12,y-60,20,20);
        g.setColor(Color.black);
        g.fillOval(x+14,y-60,20,20);
        g.setColor(Color.black);
        g.fillOval(x+18,y-63,20,20);
        g.setColor(Color.black);
        g.fillOval(x+23,y-66,20,20);
        g.setColor(Color.black);
        g.fillOval(x+30,y-70,20,20);
        g.setColor(Color.black);
        g.fillOval(x+47,y-73,20,20);
        g.setColor(Color.black);
        g.fillOval(x+55,y-78,20,20);
        g.setColor(Color.black);
        g.fillOval(x+70,y-74,20,20);
//Acceleratation och retardation (gas&broms)
        g.setColor(Color.red);
        g.fillRect(750,450,40,80);
        g.setColor(Color.red);
        g.fillRect(800,450,40,80);
        g.setFont(new Font("Times New Roman",Font.BOLD,30));
        g.setColor(Color.white);
        g.drawString("You drive "+vx+"km/h",500,490);
        g.setFont(new Font("Times New Roman",Font.BOLD,50));
        g.setColor(Color.black);
        g.drawString("-",762,500);
        g.setFont(new Font("Times New Roman",Font.BOLD,38));
        g.setColor(Color.black);
        g.drawString("+",810,500);
//Polis bil
        if(vx >= 26 && vx <=100){
            g.setColor(Color.white);
            g.fillRect(x+500,y,150,50);
            g.setColor(Color.white);
            g.fillRect(x+560,y-40,50,50);
            g.setColor(Color.gray);
            g.fillRect(x+650,y+40,20,5);
            g.setColor(Color.black);
            g.fillOval(x+510,y+20,40,40);
            g.setColor(Color.black);
            g.fillOval(x+600,y+20,40,40);
            g.setFont(new Font("Times New Roman",Font.BOLD,16));
            g.setColor(Color.blue);
            g.drawString("Polis",x+550,y+20);}
    }//end paint()
    //--------------------------------------------------------------------------------------
//Run
//--------------------------------------------------------------------------------------
    public void run(){
//System.out.println("run()");
        requestFocus();
        while(true){
            try{Thread.sleep(10);}
            catch(InterruptedException ie){}
            start();
            repaint();
            backGround();
        }//end while()
    }//end run()
    //--------------------------------------------------------------------------------------
//Ändra plats (var bilen ska befinna sig varje gång...)
//--------------------------------------------------------------------------------------
    public void start(){
//System.out.println("start()");
        x = (int)(x-vx);
        y = (int)(y);

        if(hastighet >= 130){
            if (x < -650){
                x = 1200;
            }//end if
        }//end if
        else if (x < -150) {
            x = 1200;
        }//end if
//--------------------------------------------------------------------------------------
// Ögonens rörelse
        a = x;
        b = y;

        if(a <= 100){
            a = 100;
        }//end if
        if(a >= 190){
            a = 190;
        }//end if

        if(b <= 100){
            b = 100;
        }//end if
        if(b >=140){
            b = 140;
        }//end if
    }//end start()
    //--------------------------------------------------------------------------------------
//Mouseclicked
//--------------------------------------------------------------------------------------
    public void mouseClicked(MouseEvent e){
//System.out.println("mouseClicked()");{
//broms
        if (e.getX() >= 750	 &&
                e.getX() <= 790   	 &&
                e.getY() >= 450	     &&
                e.getY() <= 530){
            hastighet = (hastighet - 5);
            vx = (vx - 1);
        }// end if
//gas
        else if (e.getX() >= 800 &&
                e.getX() <= 840   &&
                e.getY() >= 450 	 &&
                e.getY() <= 530){
            vx = (vx + 1);

//hastighet: hur mkt den ska hoppa per vx (6vx)
            hastighet = (hastighet + 5);
            if(vx >= 500){
                hastighet = (5);
                vx = (20);
            }//end if
            else if(hastighet >= 800){
                vx = (50);
                hastighet = 300;
            }//end else
        }//end else if

//bilen ska ej köra bakåt
        if(hastighet <= 0){
            vx = (0);
            hastighet = 0;
        }//end if
    }//end mouseClicked()
    //--------------------------------------------------------------------------------
// mouseDragged
//--------------------------------------------------------------------------------
    public void mouseDragged(MouseEvent e){
//System.out.println("mouseDragged()");
        a = x;
        b = y;

        if(a <= 100){
            a = 100;
        }//end if
        if(a >= 190){
            a = 190;
        }//end if

        if(b <= 100){
            b = 100;
        }//end if
        if(b >=140){
            b = 140;
        }//end if
        repaint();
    }//end mouseDragged
    //--------------------------------------------------------------------------------
// mouseMoved
//--------------------------------------------------------------------------------
    public void mouseMoved(MouseEvent e){
//System.out.println("mouseMoved()");
        a = x;
        b = y;

        if(a <= 100){
            a = 100;
        }//end if
        if(a >= 190){
            a = 190;
        }//end if

        if(b <= 100){
            b = 100;
        }//end if
        if(b >=140){
            b = 140;
        }//end if
        repaint();
    }//end mouseMoved
    //--------------------------------------------------------------------------------------
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    //--------------------------------------------------------------------------------------
//Ändra bakgrunden
//--------------------------------------------------------------------------------------
    public void backGround(){
//System.out.println("bakgrund");
        if(bgrund <= 0 && x <= 0){
            bgrund = 1;
        }//end if
        else if(x <= 0 && bgrund >= 1){
            bgrund = 0;
        }//end else
    }//end backGround
    //--------------------------------------------------------------------------------------
//Drar igång programmet
//--------------------------------------------------------------------------------------
    public static void main(String[]args){
        JFrame f = new JFrame();
        f.setSize(1200,600);
        f.setLocation(700,70);
        f.setTitle("MinBilv2");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MinBilV2 p = new MinBilV2();
        f.add(p);
        f.setVisible(true);
    }//end main
}//end class
//--------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------
