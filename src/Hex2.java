	import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

	public class Hex2 extends JPanel implements ActionListener{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		// "this" means its using the action listener
		Timer timer = new Timer(10, this);
		//Other
		boolean gameover = false;
		Color background1 = new Color (0,0,75);
		int score = -5;
	    Font bigfont = new Font("Georgia", Font.BOLD,50);
	    Font mediumfont = new Font("Georgia",Font.BOLD,20);
	    Font buttonfont = new Font("Georgia", Font.BOLD,9);
		Setters get = new Setters();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)dim.getWidth();
        int height = (int)dim.getHeight();
		int leftborder = width/15;
		int rightborder = width-15;
		int groundheight = height -55;
        JLabel background;
        JLabel earth;        
        JLabel mars;
        JLabel neptune;
        JLabel pluto;
        int marsx = width/9;
        int neptunex = width/2;
        int plutox = width-width/6;
        int marshp = 1;
        int neptunehp =1;
        int plutohp = 1;
        ImageIcon fhi = new ImageIcon(this.getClass().getResource("FullHealth.jpg"));
        Image fullhealth = fhi.getImage();
        ImageIcon lhi = new ImageIcon(this.getClass().getResource("LowHealth.jpg"));
        Image lowhealth = lhi.getImage();
        boolean planetshot = false;
		ImageIcon exi = new ImageIcon(this.getClass().getResource("Gameover.jpg"));
		Image explodeimg = exi.getImage();
        JLabel help;
        int HelpOpened =0;
        File shotfile;
        Clip shotclip;
        File correctfile;
        Clip correctclip;
        File incorrectfile;
        Clip incorrectclip;
        
        
		// Player
		int x = 100;
		int y = groundheight -25;
		int xvel = 0;
		ImageIcon pi = new ImageIcon(this.getClass().getResource("Ship.png"));
		Image player = pi.getImage();
		String scorestr;
		
		// Missileasd	
		int missilex[] = new int[5];
		int missiley[] = new int[5];
		boolean fired = false;
		boolean onscreen = false;
		boolean collect = false;
		ImageIcon mi = new ImageIcon(this.getClass().getResource("Shot.png"));
		Image missileimg = mi.getImage();
		
		//Alien
		int amax = get.getaliennum();
		int count= 1;
		int startlevel =height/7;
		int startx = 200;
		int columlength=6;
		int aliensx[] = new int[amax];
		int aliensy[] = new int[amax];
		List<Integer> deadaliens = new ArrayList<Integer>();
		int aliensvel = -1;
		ImageIcon ai = new ImageIcon(this.getClass().getResource("Alien.png"));
		Image alienimg = ai.getImage();
		boolean dead = false;
		int speed= 10;
		
		ImageIcon ei = new ImageIcon(this.getClass().getResource("Explosion.png"));
		Image explosionimg = ei.getImage();
		
		//Bombs
		int bombsy[] = new int[amax];
		int bombs[] = new int[amax];
		List<Integer> bombspeeds = new ArrayList<Integer>();
		int bomby1 = 50;
		int bomby2 = 70;
		int bomby3 = 90;
		ImageIcon bi = new ImageIcon(this.getClass().getResource("Bomb.png"));
		Image bombimg = bi.getImage();
		
		//Questions
    	String DigitStr;
    	int Digit;
    	int QuestionNum = 1;
    	int Answer;
    	int random0,random1,random2,random3;
    	int FinalAnswer;
        int Wrong1,Wrong2,Wrong3;
        int symbol1;
        String WrongString1 = "Null";
        String WrongString2= "Null";
        String WrongString3= "Null";
        String Question1,Question2,Question3;
        String Questionfin;
        int correctbutton,correctbutton2,correctbutton3;
        boolean symboldec = false;
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        String AnswerString;
        String AnswerInp ="";
        int CorrectInARow=0;
        ImageIcon ci = new ImageIcon(this.getClass().getResource("CorrectCircle.png"));
        ImageIcon nci = new ImageIcon(this.getClass().getResource("EmptyCircle.png"));
        Image correctcircle = ci.getImage();
        Image incorrectcircle = nci.getImage();
        
        
		//Constructors
		public Hex2() {
			addKeyListener(new Keys());
			setFocusable(true);
			timer.start();
			setBackground(Color.blue);
			
	        background=new JLabel(new ImageIcon("Space.jpg"));
	        background.setBounds(0,0,width,height);
	      
	        help = new JLabel(new ImageIcon("help.jpg"));
	        help.setVisible(false);
	        help.setBounds(0,0,width,height);
	        
	        earth=new JLabel(new ImageIcon("earth.jpg"));
	        earth.setBounds(width-100,groundheight-30,128,128);
	        
	        mars=new JLabel(new ImageIcon("mars.jpg"));
	        mars.setBounds(width/9, height/45, 128,128);
	        
	        neptune=new JLabel(new ImageIcon("neptune.jpg"));
	        neptune.setBounds(width/2, height/45, 128,128);
	        
	        pluto=new JLabel(new ImageIcon("pluto.jpg"));
	        pluto.setBounds(width-width/6, height/45, 128,128);
	        
	    	button1.setFont(bigfont);
	    	button1.setBounds(width/12, 10, 200, 128);
	    	button1.setFocusable(false);
			button1.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  AnswerInp = button1.getText();
					  
				  } 
				} );
			button1.setForeground(Color.white);
			button1.setOpaque(false);
			button1.setContentAreaFilled(false);
			button1.setBorderPainted(false);
			
	    	button2.setFont(bigfont);
	    	button2.setBounds(width/2-30, 10, 200, 128);
	    	button2.setBackground(Color.red);
	    	button2.setFocusable(false);
			button2.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  AnswerInp = button2.getText();
				  } 
				} );
			button2.setForeground(Color.white);
			button2.setOpaque(false);
			button2.setContentAreaFilled(false);
			button2.setBorderPainted(false);
			
	    	button3.setFont(bigfont);
	    	button3.setBounds(width-width/5, 10, 200, 128);
	    	button3.setBackground(Color.red);
	    	button3.setFocusable(false);
			button3.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  AnswerInp = button3.getText();
				  } 
				} );
			button3.setForeground(Color.white);
			button3.setOpaque(false);
			button3.setContentAreaFilled(false);
			button3.setBorderPainted(false);
	    	  try {
		    	   shotfile = new File("sound2.wav");
		    	   shotclip = AudioSystem.getClip();
		    	   shotclip.open(AudioSystem.getAudioInputStream(shotfile));
		    	   
		    	   correctfile = new File("correct2.wav");
		    	   correctclip = AudioSystem.getClip();
		    	   correctclip.open(AudioSystem.getAudioInputStream(correctfile));
		    	   
		    	   incorrectfile = new File("incorrect2.wav");
		    	   incorrectclip = AudioSystem.getClip();
		    	   incorrectclip.open(AudioSystem.getAudioInputStream(incorrectfile));
		    } catch (Exception e) {
		    	 System.err.println(e.getMessage());
		    } 
		    	 
		
	    	StartGame();
		}
	    public void StartGame() {
	    	setBackground(background1);
	    	setFocusable(true);
	    	score+=5;
	    	CorrectInARow = 0;
	    	startx=200;
	    	for(int m = 0;m<5;m++){
	    		missiley[m] = groundheight;
	    	}
			//places all aliens on screen
	    	if(get.getaliens()){
	    		startlevel=height/7;
		    	for(int aliennum = 0;aliennum<amax;aliennum++) {
		    		aliensx[aliennum] = startx + aliennum * 60;
		    		aliensy[aliennum] = startlevel;
		    		if(count==columlength) {
		    			startlevel+=40;
		    			startx-=columlength*60;
		    			count=0;
		    		}
		    		count++;	
		    	}
		    	speed++;
		    	deadaliens.removeAll(deadaliens);
	    	}
	    	add(help);
	        add(button1);
	        add(mars); 
	    	
	    	add(button2);
	    	add(neptune);
	    	
	    	add(button3);
	    	add(pluto);
	    	
	    	//add(earth); 
	    	add(background);
	    	
	    	setLayout(null);
	    	
	    }
		
	    public void restart(){
				try{
		    		FileWriter fstream = new FileWriter("Test.txt", true);
		    		BufferedWriter out = new BufferedWriter(fstream);
		    		String FName = get.getfname();
		    		String LName = get.getlname();
		    		String CName = get.getcname();
		    		out.write(FName + " " + LName + " " + score + " " + CName);
		    		out.newLine();
		    		out.close();
				}catch(Exception ee){
				}
			QuestionNum = 1;
			CorrectInARow=0;
			bombspeeds = new ArrayList<Integer>();
			bomby1 = 50;
			bomby2 = 70;
			bomby3 = 90;
			speed= 10;
			aliensvel = -1;
			startx = 200;
			deadaliens.removeAll(deadaliens);
			count= 1;
			x = 100;
			score = -5;
			FinalAnswer = 0;
			AnswerString = "";
			Digit = 0;
			Answer = 0;
			for(int reset = 0;reset<amax;reset++){
				bombsy[reset] = 200;
				bombs[reset] = 10;
			}
			StartGame();
	    }
	    //Essential methods
	    public void actionPerformed(ActionEvent e) {
	    	repaint();
	    }
		public void paint(Graphics g) {
			gameover = gameover(g);
			if(!gameover) {
				
		    	super.paint(g);
		    	if(!help.isVisible()){
			    	player(g);
			    	aliens(g);
			    	bombing(g);
			    	g.setColor(Color.BLACK);
			    	g.fillRect(0, 0, leftborder, height);
			    	
			    	g.setColor(Color.white);
			    	scorestr = Integer.toString(score);
			    	g.setFont(mediumfont);
			    	g.drawString("Score: " +scorestr, 0,height/3 );
			    	
			    	g.drawString("A=10",0, height/2 + 30);
			    	g.drawString("B=11",0, height/2 + 50);
			    	g.drawString("C=12",0, height/2 + 70);
			    	g.drawString("D=13",0 , height/2 + 90);
			    	g.drawString("E=14",0, height/2 + 110);
			    	g.drawString("F=15",0, height/2 + 130);
			    	g.drawString("Press F1", 0, height - height/12);
			    	g.drawString("for help!", 0, height - height/16);
			    	questions(g);
			    	get.setscore(score);

			    	g.setColor(Color.white);
					
			    	if(deadaliens.size()==amax && CorrectInARow >2) {
			    		StartGame();
			    	}else if(deadaliens.size()==amax && CorrectInARow <3){
			    		g.drawString("Please answer the questions", height/2, width/2);
			    	}
			    	if(CorrectInARow >2){
			    		StartGame();
			    		CorrectInARow =0;	    	
			    	}	
		    	}
		    	HelpOpened++;
			}
	    	if(gameover){
	    		shotclip.stop();
    			g.drawImage(explodeimg,0,0,width,height,this);
    			g.setColor(Color.white);
    			g.setFont(bigfont);
    			g.drawString("Score: " + scorestr, width/2 - width/12,height/2 + height/6);
    			g.drawString("Press R to replay", width/2 - width/7,height/3);
    			g.drawString("Press ESC to exit", width/2 - width/7,height - height/10);
	    	}
	        
	    }

		//Game logic methods
	    public void player(Graphics g) {
	    	//This moves the missle aslong as its still onscreen
	    	for(int m = 0;m<5;m++){
		    	if(!(missiley[m] == groundheight)) {
		    		missiley[m] -=2;
		    		
		    	}
		    	g.drawImage(missileimg,missilex[m],missiley[m],this);
		    	if(missiley[m] < height/6 - 1){
		    		missiley[m] = groundheight;
		    		missilex[m] = 0;
		    	}
	    	}

	    	//This catches the missle when it goes off screen and resets it.

	    	//this stops the player going off screen
	    	if(x<leftborder) {
	    		x =leftborder;
	    	}
	    	if(x>rightborder) {
	    		x=rightborder;
	    	}
	    	for(int m = 0;m<5;m++){

		    	if((missilex[m] >= marsx)&&(missilex[m]<=marsx + 130)  && (missiley[m] <= height/6)){
		    		if(!planetshot && marshp >0){
		    			marshp--;
		    			System.out.println("TEST");
		    			planetshot = true;
		    		}else if(!planetshot && marshp <1){
		    			AnswerInp = button1.getText();
		    			System.out.println("TEST");
		    			marshp= 1;
		    			neptunehp =1;
		    			plutohp = 1;
		    			planetshot = true;
		    			
		    		}
		    		
		    	}
		    	if((missilex[m] >= neptunex)&&(missilex[m]<=neptunex + 130)  && (missiley[m] <= height/6)){
		    		if(!planetshot && neptunehp >0){
		    			neptunehp--;
		    			System.out.println("TEST");
		    			planetshot = true;
		    		}else if(!planetshot && neptunehp <1){
		    			AnswerInp = button2.getText();
		    			marshp= 1;
		    			neptunehp =1;
		    			plutohp = 1;
		    			planetshot = true;
		    		}
		    		
		    	}
		    	if((missilex[m] >= plutox)&&(missilex[m]<=plutox + 130)  && (missiley[m] <= height/6)){
		    		if(!planetshot && plutohp >0){
		    			plutohp--;
		    			System.out.println("TEST");
		    			planetshot = true;
		    		}else if(!planetshot && plutohp <1){
		    			AnswerInp = button3.getText();
		    			System.out.println("TEST");
		    			marshp= 1;
		    			neptunehp =1;
		    			plutohp = 1;
		    			planetshot = true;
		    			
		    		}
		    		
		    	}
		    	if(plutohp  == 0){
		    		g.drawImage(lowhealth,width-width/12, height/50 + height/25 ,this);
		    	}else{
		    		g.drawImage(fullhealth,width-width/12, height/50 + height/25 ,this);
		    	}
		    	if(neptunehp  == 0){
		    		g.drawImage(lowhealth,width- width/2 + width/12, height/50 + height/25 ,this);
		    	}else{
		    		g.drawImage(fullhealth,width- width/2 + width/12, height/50 + height/25 ,this);
		    	}
		    	if(marshp  == 0){
		    		g.drawImage(lowhealth,width/5 - width/170, height/50 + height/25 ,this);
		    	}else{
		    		g.drawImage(fullhealth,width/5 - width/170, height/50 + height/25 ,this);
		    	}
	    	}
	    	//moves the player depending on their direction
	    	x = x + xvel;
	    	//this draws the player onto the screen
	    	g.drawImage(player, x, y, this);
	    }
	    public void aliens(Graphics g) {
	    	if(get.getaliens()){
		    	for(int aliennum = 0;aliennum<amax;aliennum++) {
		    		//this draws the aliens on screen aslong as they arnt dead.
		    		if(!deadaliens.contains(aliennum)) {
			    			collision(g,aliennum);
			    			g.drawImage(alienimg,aliensx[aliennum], aliensy[aliennum],this);
			    		
			    	//this makes the aliens bounce back when they hit the right side of the screen
			    	if(aliensx[aliennum]>rightborder && !deadaliens.contains(aliennum)) {
	
		    			
			    		//This places the aliens back into the correct place when they hit the side
			    	    
			    	    // moves the aliens down and changes the velocity of them
		    			
		    			for(int aliennum2=0;aliennum2<amax;aliennum2++) {
		    				aliensy[aliennum2] += speed;
		    				aliensx[aliennum2] += aliensvel;
		    			}
		    			aliensvel = -1;
			    	}
			    	
			    	// this makes the aliens bounce back when they hit the left side of the screen
			    	if(aliensx[aliennum]<leftborder) {
			    			//stops the aliens going off screen
			    			//aliensx[aliennum] = 50;
			    			// moves the aliens down and changes the velocity of them
			    			
			    			for(int aliennum2=0;aliennum2<amax;aliennum2++) {
			    				aliensy[aliennum2] += speed;
			    				aliensx[aliennum2] += aliensvel;
			    			}
			    			aliensvel = 1;
			    	}	   
			    	
			    	//this controls the movement of the aliens
			    	aliensx[aliennum] += aliensvel;
		    	  }
		    	}
		    	//moves the bombs down
		    	for(int move = 0;move<amax;move++) {
		    		if(bombspeeds.contains(move)){
		    			bombsy[move]+=2;
		    		}else{
		    			bombsy[move]+=1;
		    			
		    		}
		    	}
		    	
		    	//Checks when the bombs go off screen and resets them at random times
		    	int random0 = (int) (Math.random()*amax-1)+1;
				int random1 = (int) (Math.random()*amax-1)+1;
				int random2 = (int) (Math.random()*amax)+1;
				if((bombsy[random0]>groundheight)&&random1<amax/4){
	    			if(!deadaliens.contains(random0)) {  
				    	bombs[random0] = aliensx[random0];
						bombsy[random0] = aliensy[random0];
						if(random2 < amax/4){
							bombspeeds.add(random0);
						}
	    			}   
				}
	    				
	    		//This draws the bombs on screen
		    	for(int check = 0;check<amax;check++) {	 
		    	    	g.drawImage(bombimg,bombs[check], bombsy[check],this);	
	    		}
	    	}
	    }
	    	
	    public void bombing(Graphics g) {
	    }
	    public void questions(Graphics g){
	    	// Creates the hex numbers
			if(QuestionNum <= 2){
				// creates a random number between 1 and 15.
				Digit = (int) (Math.random()*15)+1;
				DigitStr = Integer.toString(Digit);
				
				if(DigitStr.equals("10")){
			    	DigitStr = "A";
			    }else if(DigitStr.equals("11")){
			    	DigitStr = "B";
			    }else if(DigitStr.equals("12")){
			    	DigitStr = "C";
			    }else if(DigitStr.equals("13")){
			    	DigitStr = "D";
			    }else if(DigitStr.equals("14")){
			    	DigitStr = "E";
			    }else if(DigitStr.equals("15")){
			    	DigitStr = "F";
			    }
				
				
			    if(QuestionNum == 2){
			    	Answer = Answer + (Digit * 16);
			    }else{
			    	Answer = Answer + (Digit * 1);
			    }
			    
				random0 = (int) (Math.random()*3)+1;
				random1 = (int) (Math.random()*10)+1;
				random2 = (int) (Math.random()*10)+1;
				random3 = (int) (Math.random()*10)+1;
			    FinalAnswer = Answer;
			    if(QuestionNum == 2){
			    	Question2 = DigitStr;
			    }
			    if(QuestionNum == 1){
			    	Question1 = DigitStr;
			    }
				if(!symboldec && QuestionNum == 2) {
					System.out.println("Cheat mode: " + FinalAnswer);
					symbol1 = (int) (Math.random()*2)+1;
					for(int Symbolnum = 0;Symbolnum<3;Symbolnum++) {
						symbol1 = (int) (Math.random()*2)+1;
						if(symbol1 ==1) {
							if(Symbolnum ==0) {
								Wrong1 = FinalAnswer + random1;
							}
							else if(Symbolnum==1){
								Wrong2 = FinalAnswer + random2;
							}else {
								Wrong3 = FinalAnswer + random3;
							}
						}else {
							if(Symbolnum ==0) {
								Wrong1 = FinalAnswer - random1;
							}
							else if(Symbolnum==1){
								Wrong2 = FinalAnswer - random2;
							}else {
								Wrong3 = FinalAnswer - random3;
							}
						}
					}
					WrongString1 = Integer.toString(Wrong1);
					WrongString2 = Integer.toString(Wrong2);
					WrongString3 = Integer.toString(Wrong3);
					symboldec = true;
				}
			    QuestionNum++;
			}
			// paints the question on screen and the rectangle that its contained in
			Questionfin = Question2 + Question1;
			if(random0 == 1){
				correctbutton = 1;
			}else if(random0 == 2){
				correctbutton = 2;
			}else if(random0 == 3){
				correctbutton = 3;
			}
			
			AnswerString = Integer.toString(FinalAnswer);
			g.setColor(Color.YELLOW);
			g.setFont(bigfont);
			g.drawString(Questionfin,0,height/3 - height/16);	
			//g.drawString(AnswerString, width/2, height/2);
			if(random0 == 1){
				button1.setText(AnswerString);
			}else{
				button1.setText(WrongString1);
			}
			if(random0 == 2){
				button2.setText(AnswerString);
			}else{
				button2.setText(WrongString2);
			}
			if(random0 == 3){
				button3.setText(AnswerString);
			}else{
				button3.setText(WrongString3);
			}
			if(AnswerInp.equals(AnswerString) && !correctclip.isActive()){
				correctclip.setFramePosition(0);
				correctclip.start();
				System.out.println("Correct");
				score++;
				CorrectInARow++;
				System.out.println(CorrectInARow);
            	QuestionNum=1;
            	Answer = 0;
            	symboldec = false;
				AnswerInp = "";
			}
			if(!AnswerInp.equals(AnswerString) && !AnswerInp.equals("") && !incorrectclip.isActive()){
				incorrectclip.setFramePosition(0);
				incorrectclip.start();
				System.out.println("Incorrect");
				score--;
				CorrectInARow = 0;
            	QuestionNum=1;
            	Answer = 0;
            	symboldec = false;
				AnswerInp = "";
			}
		        g.drawImage(incorrectcircle, 0, height/50 + 10 ,null);
		        g.drawImage(incorrectcircle, 0, height/50 + 60 ,null);
		        g.drawImage(incorrectcircle, 0, height/50 + 110 ,null);
		        if(CorrectInARow == 1){
		        	g.drawImage(correctcircle, 0, height/50 + 10,null);
		        }else if(CorrectInARow == 2){
			        g.drawImage(correctcircle, 0, height/50 + 10,null);
			        g.drawImage(correctcircle, 0, height/50  + 60,null);
		        }else if(CorrectInARow == 3){
			        g.drawImage(correctcircle, 0, height/50 + 10,null);
			        g.drawImage(correctcircle, 0, height/50 + 60,null);
			        g.drawImage(correctcircle, 0, height/50 + 110,null);
		        }
		        
	   }
	      
	    //Misc Methods
	    public boolean collision(Graphics g,int aliennum) {
	    	for(int m = 0;m<5;m++){
	            if ((missilex[m] >= aliensx[aliennum]) && (missilex[m] <= aliensx[aliennum] + 43) 
	            		&& (missiley[m] >= aliensy[aliennum]) && (missiley[m] <= aliensy[aliennum]+43) ) {
	            	    g.drawImage(explosionimg,aliensx[aliennum], aliensy[aliennum],this);
	                	deadaliens.add(aliennum);
	                	dead = true;
	                	missiley[m] = groundheight;
	                	missilex[m] = 0;
	            }else {
	            	dead = false;
	            }
	    	}
	    	return dead;
	    }   
	    public boolean gameover(Graphics g) {
	    	for(int bombnum=0;bombnum<amax;bombnum++) {
	    		if ((bombs[bombnum]>= x ) && (bombs[bombnum]<= x  + 33) && 
	    		   ((bombsy[bombnum] == y) 
	    		    || (bombsy[bombnum] > y - 2) && (bombsy[bombnum] < y + 2) 
	    		    || (bombsy[bombnum] < y - 2) && (bombsy[bombnum] > y + 2)  )) {
	    			gameover = true;
	    			System.out.println("TEST");
	    			
	    			
	    			
	    		}
	    		if(!deadaliens.contains(bombnum)) {
		    		if((aliensx[bombnum] > x) && (aliensx[bombnum] < x + 20) && 
		    			(aliensy[bombnum] > y - 5 && aliensy[bombnum] < y + 10)) {
		    			gameover = true;
		    			System.out.println("TEST2");

		    		}
			    	if(aliensy[bombnum] > y) {
			    		gameover = true;
			    		System.out.println("TEST3");

			    	}
	    		}
	    	}
	    	return gameover;
	    }
	    
	    //well technically this is a class but still
	    public class Keys extends KeyAdapter {

	        public void keyReleased(KeyEvent e) {
	        	// calls the calls from the player class that handles the key releases
	        	int key = e.getKeyCode();
	            if (key == KeyEvent.VK_A){
	                xvel = 0;
	            }
	            if (key == KeyEvent.VK_D){
	            	xvel = 0;
	            }
		          if(key == KeyEvent.VK_SPACE) {
		        	  fired = false;
		          }
	        }
	        public void keyPressed(KeyEvent e) {
	          //gets the integer value for the key pressed
	          int key = e.getKeyCode();
	          if(key == KeyEvent.VK_F1 && !gameover){
	        	  
		        	  if(help.isVisible()){
		        		  help.setVisible(false);
		        		  timer.start();
		        	  }else if(HelpOpened > 40 && !(help.isVisible())){
		        		  timer.stop();
		        		  help.setVisible(true);
		        	  }
		        	  HelpOpened =0;
	        	  }
	        	  
	          
	          if (key == KeyEvent.VK_A){
	        	  xvel = -2;
	          }
	          if (key == KeyEvent.VK_D){
	        	  xvel = 2;
	          }
	          if(key == KeyEvent.VK_P){
	        	  timer.stop();
	          }
	          if(key == KeyEvent.VK_O){
	        	  timer.start();
	          }
	          if(key == KeyEvent.VK_R){
	        	  if(gameover){
	        		  restart();
	        		  gameover = false;
	        	  }
	          }
	          if(key == KeyEvent.VK_SPACE) {
	        	  for(int m = 0;m<5;m++){
	        		  if(missiley[m] == groundheight && !shotclip.isActive()){
	        			  shotclip.setFramePosition(0);
		        		  shotclip.start();
		        		  missiley[m] -= 10;
		        		  missilex[m] = x+10;
		        		  
		        		  break;
	        		  }
	        	  }
	        	  planetshot = false;
	          }
	          if(key == KeyEvent.VK_ESCAPE) {
  				try{
		    		FileWriter fstream = new FileWriter("Test.txt", true);
		    		BufferedWriter out = new BufferedWriter(fstream);
		    		String FName = get.getfname();
		    		String LName = get.getlname();
		    		String CName = get.getcname();
		    		out.write(FName + " " + LName + " " + score + " " + CName);
		    		out.newLine();
		    		out.close();
		    		System.exit(0);
				}catch(Exception ee){
				}
	          }
	          }
	       }   
	    
	}


