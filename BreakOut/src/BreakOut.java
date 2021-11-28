import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//------------------------------------------------------------------------------------------------
//Skriv in vad programmet g�r
//------------------------------------------------------------------------------------------------
public class BreakOut extends JPanel implements ActionListener, Runnable, KeyListener{
    //------------------------------------------------------------------------------------------------
//Deklarationer
//------------------------------------------------------------------------------------------------
    int playerX = 315, playerY = 700;
    int ballX = 330, ballY = 380;
    int score = 0;
    int vy = 1;
    int vx = 0;
    int life = 3;
    int minusOne = 1;
    int breakOutVY = 1;
    int breakOutY = 100;

    double random = 0;

    boolean leftDown = false;
    boolean rightDown = false;
    boolean ball = false;
    boolean map = false;
    boolean soundBoolean = true;
    boolean gameOver = false;
    boolean winGame = false;
    boolean instructions = false;
    boolean cheats = false;
    boolean wallHack = false;
    boolean brokeBlock = false;

    JButton startButton;
    JButton muteButton;
    JButton instructionButton;
    JButton cheatButton;

    String cheat = "";

    Thread t = new Thread(this);

    ImageIcon mute = new ImageIcon("muteIcon.png");
    ImageIcon backgroundGame = new ImageIcon("backgroundGame.gif");
    ImageIcon backgroundMenu = new ImageIcon("backgroundMenu.gif");

    GameSound backgroundMusic = new GameSound();

    Block[][] block = new Block[7][7];

    //------------------------------------------------------------------------------------------------
//Konstruktor
//------------------------------------------------------------------------------------------------
    public BreakOut(){
        this.setLayout(null);
        addKeyListener(this);
        backgroundMusic.playBgMusic("MainMenuMusic.wav");
        start();
        for(int row=0; row<7; row= row+1){
            for(int col=0; col<7;col=col+1){
                block[row][col] = new Block(55 + (85*row),50 + (15*col),80,10);
            }//end for col
        }//end for row
    }//end konstruktor
    //------------------------------------------------------------------------------------------------
//Start
//------------------------------------------------------------------------------------------------
    public void start(){
        //Start button
        startButton = new JButton("Start");
        startButton.setSize(200,50);
        startButton.setLocation(250,275);
        startButton.setFont(new Font("Arial",Font.BOLD,30));
        startButton.setFocusPainted(false);
        startButton.setForeground(new Color(121, 252, 61));
        startButton.setBackground(new Color(242, 53, 179));
        startButton.addActionListener(this);
        startButton.setVisible(true);
        this.add(startButton);

        //Mute button
        muteButton = new JButton("Mute");
        muteButton.setSize(200,50);
        muteButton.setLocation(250,385);
        muteButton.setFont(new Font("Arial",Font.BOLD,30));
        muteButton.setFocusPainted(false);
        muteButton.setForeground(new Color(121, 252, 61));
        muteButton.setBackground(new Color(242, 53, 179));
        muteButton.addActionListener(this);
        muteButton.setVisible(true);
        this.add(muteButton);

        //Instruction button
        instructionButton = new JButton("Instructions");
        instructionButton.setSize(200,50);
        instructionButton.setLocation(250,330);
        instructionButton.setFont(new Font("Arial",Font.BOLD,28));
        instructionButton.setFocusPainted(false);
        instructionButton.setForeground(new Color(121, 252, 61));
        instructionButton.setBackground(new Color(242, 53, 179));
        instructionButton.addActionListener(this);
        instructionButton.setVisible(true);
        this.add(instructionButton);

        //Cheat button
        cheatButton = new JButton("Cheats");
        cheatButton.setSize(200,50);
        cheatButton.setLocation(250,440);
        cheatButton.setFont(new Font("Arial",Font.BOLD,30));
        cheatButton.setFocusPainted(false);
        cheatButton.setForeground(new Color(121, 252, 61));
        cheatButton.setBackground(new Color(242, 53, 179));
        cheatButton.addActionListener(this);
        cheatButton.setVisible(true);
        this.add(cheatButton);
    }//end start()
    //------------------------------------------------------------------------------------------------
//Paint
//------------------------------------------------------------------------------------------------
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.clearRect(0,0,700,800);
        if(map || gameOver || winGame){
            backgroundGame.paintIcon(this,g,0,0);
            backgroundGame.paintIcon(this,g,570,0);
            backgroundGame.paintIcon(this,g,0,363);
            backgroundGame.paintIcon(this,g,570,363);
            backgroundGame.paintIcon(this,g,570,726);
            backgroundGame.paintIcon(this,g,0,726);
            repaint();
        }//end if
        else{
            backgroundMenu.paintIcon(this,g,0,0);
            backgroundMenu.paintIcon(this,g,500,0);
            g.setColor(new Color(10,26,27));
            g.fillRect(0,708,700,300);
            repaint();
        }//end else
        //BREAKOUT
        if(map==false && gameOver==false && winGame==false){
            g.setColor(new Color(242, 53, 179));
            g.setFont(new Font("Dialog", Font.BOLD, 100));
            g.drawString("BreakOut", 130, breakOutY);

            try{Thread.sleep(10);}catch(Exception ex){}
            breakOutY = breakOutY + breakOutVY;
            if(breakOutY >= 105){
                breakOutVY = -1;
            }//end if
            if(breakOutY <= 95){
                breakOutVY = 1;
            }//end if
            repaint();
        }//end if
        //Instruction Panel
        if(map==false && winGame==false && gameOver==false){
            if(instructions == true){
                g.setColor(new Color(242, 53, 179));
                g.fillRect(100,500,500,165);
                g.setColor(new Color(121, 252, 61));
                g.setFont(new Font("Dialog", Font.BOLD, 20));
                g.drawString("Use the arrows left and right to hit the ball.", 145, 560);
                g.drawString("Hitting the green blocks will destroy them.", 145, 590);
                g.drawString("Once all blocks are gone you win, ENJOY!", 145, 620);
            }//end if instructions
        }//end if map
        //Cheat Panel
        if(map==false){
            if(cheats == true){
                g.setColor(new Color(242, 53, 179));
                g.fillRect(100,500,500,165);
                g.setColor(new Color(121, 252, 61));
                g.setFont(new Font("Dialog", Font.BOLD, 20));
            }//end if instructions
        }//end if map
        //Life
        if(map){
            g.setColor(new Color(242, 53, 179));
            g.setFont(new Font("Dialog", Font.BOLD, 80));
            g.drawString(""+life, 80, 680);
        }//end if map
        //Ball
        if(ball){
            g.setColor(new Color(121, 252, 61));
            g.fillOval((int)ballX,(int)ballY,20,20);
        }//end if ball
        //Player
        if(map){
            g.setColor(Color.red);
            g.fillRect(playerX,playerY,70,10);
        }//end if map
        //Walls
        if(wallHack==false){
            if(map){
                g.setColor(new Color(242, 53, 179));
                g.fillRect(20,20,20,780);
                g.fillRect(660,20,20,780);
                g.fillRect(20,20,660,20);
            }//end if map
        }//end if wallHack
        //Blocks
        if(map){
            for(int row=0; row<7; row= row+1){
                for(int col=0; col<7;col=col+1){
                    block[row][col].paint(g);
                }//end for col
            }//end for row
        }//end if map
        //Game over screen
        if(gameOver && winGame==false){
            g.setColor(Color.red);
            g.setFont(new Font("Dialog", Font.BOLD, 100));
            g.drawString("Game Over", 80, 300);
        }//end if gameOver
        //Win game screen
        if(winGame && gameOver==false){
            map = false;
            ball = false;
            g.setColor(Color.green);
            g.setFont(new Font("Dialog", Font.BOLD, 100));
            g.drawString("You Win", 145, 300);
        }//end if gameOver
        //Mute button
        if(soundBoolean==false){
            mute.paintIcon(this,g,600,750);
        }//end soundBoolean
        //Score
        if(map){
            g.setColor(Color.white);
            g.setFont(new Font("Dialog", Font.BOLD, 20));
            g.drawString("Score = "+score, 50,750);
        }//end if map
        //Made by
        if(map==false && winGame==false && gameOver==false){
            g.setColor(new Color(242, 53, 179));
            g.setFont(new Font("Dialog", Font.BOLD, 20));
            g.drawString("by Morteza", 320, 130);
        }//end if map
    }//end paint()
    //------------------------------------------------------------------------------------------------
//Break
//------------------------------------------------------------------------------------------------
    public void breakBlock(){
        for(int row=0; row<7; row= row+1){
            for(int col=0; col<7;col=col+1){
                if(ballY <= (block[row][col].posY +10)  &&
                        (ballY + 20) >= block[row][col].posY &&
                        (ballX + 20) >= block[row][col].posX &&
                        ballX <= (block[row][col].posX + 80) &&
                        brokeBlock==false					 &&
                        block[row][col].visible==true){
                    block[row][col].Break();
                    brokeBlock = true;
                    vy = -vy;
                    score = score + 100;
                    if(soundBoolean && winGame==false && gameOver==false){
                        backgroundMusic.playBreak();
                    }//end if
                }//end if
                if(ballY >= 300){
                    brokeBlock = false;
                }//end if
                if(ballY <= (block[row][col].posY +10)  &&
                        (ballY + 20) >= block[row][col].posY &&
                        (ballX + 20) >= block[row][col].posX &&
                        ballX <= (block[row][col].posX + 80) &&
                        block[row][col].visible==true){
                    vx = -vx;
                }//end if
            }//end for col
        }//end for row
    }//end breakBlock();
    //------------------------------------------------------------------------------------------------
//Keylistener
//------------------------------------------------------------------------------------------------
    public void keyPressed(KeyEvent ke){
        if(ke.getKeyCode() == KeyEvent.VK_LEFT){
            leftDown = true;
        }//end if
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            rightDown = true;
        }//end if
    }//end keyPressed()
    //------------------------------------------------------------------------------------------------
    public void keyReleased(KeyEvent ke){
        if(ke.getKeyCode() == KeyEvent.VK_LEFT){
            leftDown = false;
        }//end if
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            rightDown = false;
        }//end if
    }//end keyReleased
    //------------------------------------------------------------------------------------------------
    public void keyTyped(KeyEvent ke){}
    //------------------------------------------------------------------------------------------------
//ActionPerformed
//------------------------------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==startButton){
            startButton.removeActionListener(this);
            muteButton.removeActionListener(this);
            instructionButton.removeActionListener(this);
            cheatButton.removeActionListener(this);
            startButton.setVisible(false);
            muteButton.setVisible(false);
            instructionButton.setVisible(false);
            cheatButton.setVisible(false);
            ball = true;
            map = true;
            t.start();
            if(soundBoolean){
                backgroundMusic.gameMusic.stop();
                backgroundMusic.playBgMusic("BackgroundmusicV3.wav");
            }//end if
        }//end if getSource

        else if(e.getSource()==instructionButton){
            if(instructions==false){
                instructions = true;
                repaint();
            }//end if
            else{
                instructions = false;
                repaint();
            }//end else
        }//end else if

        else if(e.getSource()==cheatButton){
            cheat = JOptionPane.showInputDialog("Input cheat code");
            if(cheat.equals("Godmode")){
                JOptionPane.showMessageDialog(null,"Godmode activated");
                minusOne = 0;
            }//end if
            if(cheat.equals("Wallhack")){
                JOptionPane.showMessageDialog(null,"Walls are no more");
                wallHack = true;
            }//end if
        }//end else if

        else if(e.getSource()==muteButton){
            if(soundBoolean==false){
                soundBoolean = true;
                muteButton.setText("Mute");
                backgroundMusic.playBgMusic("MainMenuMusic.wav");
                repaint();
            }//end soundBoolean
            else{
                soundBoolean = false;
                muteButton.setText("Unmute");
                backgroundMusic.gameMusic.stop();
                repaint();
            }//end else
        }//end else
    }//end actionPerformed()
    //------------------------------------------------------------------------------------------------
//Randomize vx value
//------------------------------------------------------------------------------------------------
    public void randomize(){
        random = 1;
        random = (4*Math.random())-2;
        vx = (int)Math.round(random);
        if(vx == 0){
            randomize();
        }//end if
        else if(rightDown==true){
            random = (2*Math.random());
            vx = (int)Math.round(random);
        }//end else if
        else if(leftDown==true){
            random = (2*Math.random()-2);
            vx = (int)Math.round(random);
        }//end else if
    }//end randomize();
    //------------------------------------------------------------------------------------------------
//Make ball bounce
//------------------------------------------------------------------------------------------------
    public void ballMovement(){
        if(playerX <= (ballX + 20) &&
                ballX <= (playerX + 70) &&
                ballY == 680){
            randomize();
            vy = -vy;
            if(gameOver==false && winGame==false){
                if(soundBoolean){
                    backgroundMusic.playBounce();
                }//end if
            }//end if
        }//end bounce against player
        if(ballY <= 40){
            vy = -vy;
            if(gameOver==false && winGame==false){
                if(soundBoolean){
                    backgroundMusic.playBounce();
                }//end if
            }//end if
        }//end bounce against roof
        if(ballX <= 40){
            vx = -vx;
            if(gameOver==false && winGame==false){
                if(soundBoolean){
                    backgroundMusic.playBounce();
                }//end if
            }//end if
        }//end bounce against left wall
        if((ballX + 20) >= 660){
            vx = -vx;
            if(gameOver==false && winGame==false){
                if(soundBoolean){
                    backgroundMusic.playBounce();
                }//end if
            }//end if
        }//end bounce against right wall
        if(ballY >= 839){
            life = life - minusOne;
            if(life==0){
                map = false;
                ball = false;
                gameOver = true;
                backgroundMusic.gameMusic.stop();
                if(soundBoolean){
                    backgroundMusic.playGameOver();
                }//end if soundBoolean
            }//end if life
            ballX = 330;
            ballY = 380;
            vx = 0;
            vy = 1;
        }//end gameOver
    }//end ballMovement
    //------------------------------------------------------------------------------------------------
//Win or lose
//------------------------------------------------------------------------------------------------
    public void winMethod(){
        if(winGame){
            backgroundMusic.gameMusic.stop();
            if(soundBoolean){
                backgroundMusic.playWinGame();
            }//end if soundBoolean
        }//end if winGame
    }//end winLose
    //------------------------------------------------------------------------------------------------
//Run
//------------------------------------------------------------------------------------------------
    public void run(){
        requestFocus();
        while(true){
            try{Thread.sleep(3);}
            catch(InterruptedException ie){}
            repaint();
            ballMovement();
            breakBlock();
            if(leftDown){
                playerX = playerX - 2;
                if(playerX <= 40){
                    playerX = playerX + 2;
                }//end playerX
            }//end leftDown
            if(rightDown){
                playerX = playerX + 2;
                if(playerX >= 590){
                    playerX = playerX - 2;
                }//end playerX
            }//end rightDown
            ballY = ballY + vy;
            ballX = ballX + vx;
            if(score == 4900){
                winGame = true;
                winMethod();
                score = 5000;
            }//end if
        }//end while()
    }//end run()
    //------------------------------------------------------------------------------------------------
//Drar ig�ng programmet
//------------------------------------------------------------------------------------------------
    public static void main(String[]args){
        JFrame f = new JFrame();
        f.setSize(715,839);
        f.setLocation(1000,10);
        f.setTitle("BreakOut");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BreakOut p = new BreakOut();
        f.add(p);
        f.setVisible(true);
    }//end main
}//end class 
//------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------

	