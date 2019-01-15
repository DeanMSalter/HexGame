
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Menu extends Application{
	
	//Main menu variables
	Color background1 = new Color(34,177,76);
	int errornum = 1;
	
	
	String fname = "";
	String lname = "";
	String cname = "";
	int score = 0;
	
	Setters set = new Setters();
	
	//Reading data variables
	Stage window;
	String[] args = null;
	TableView<Data> table;
    Scanner input;
    
    //Password variables
    int passattempt = 0;
    char[] password;
    char [] password2;
    String passwordstr;
    String passwordstr2;
    int encrypted[] = new int[100];
    int key;
    int encryptedtotal;
    boolean access = false;
    boolean generate = false;
    JLabel incorrectpass;
    
    public static void main(String[] args) {
				new Menu();
	}
	public void data2(String[] args){
		launch(args);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Menu() {
		//ensures that when the data table is launched the main menu does not open again
		if(set.getview()){
			JFrame main = new JFrame("Hex Invaders");
			main.setLayout(null);
			main.setDefaultCloseOperation(3);
			main.setResizable(false);
			main.setSize(500,300);
			main.getContentPane().setBackground(background1);
			Image frameImg = Toolkit.getDefaultToolkit().getImage("LogoSm.jpg");
			main.setIconImage(frameImg);
			main.setLocationRelativeTo(null);
			
			JLabel FNamelab = new JLabel("First name");
			FNamelab.setBounds(10, 30, 72, 20);
			
			JTextField FNameInp = new JTextField();
			FNameInp.setBorder(null);
			FNameInp.setToolTipText("What is your first name?");
			FNameInp.setBounds(72,30, 72, 20);
			
			JLabel LNamelab = new JLabel("Last name");
			LNamelab.setBounds(10, 52, 72, 20);
			
			JTextField LNameInp = new JTextField();
			LNameInp.setBorder(null);
			LNameInp.setToolTipText("What is your first name?");
			LNameInp.setBounds(72,52, 72, 20);
			
			JLabel CNamelab = new JLabel("Class");
			CNamelab.setBounds(10, 74, 72, 20);
			
			JLabel img = new JLabel(new ImageIcon("Logo.png"));
			img.setBounds(40,100,80,40);
			
			JComboBox ClassBox = new JComboBox<>();
			ClassBox.addItem("12PPM");
			ClassBox.addItem("12APM");
			ClassBox.addItem("12PCM");
			ClassBox.setBounds(72, 74, 72, 20);
			
			JLabel aliennumlabel = new JLabel("Number of aliens:");
			aliennumlabel.setBounds(290,100, 100, 20);
			
			JComboBox aliennumbox = new JComboBox<>();
			aliennumbox.addItem(30);
			aliennumbox.addItem(6);
			aliennumbox.addItem(12);
			aliennumbox.addItem(18);
			aliennumbox.addItem(24);
			aliennumbox.addItem(36);
			aliennumbox.addItem(42);
			aliennumbox.addItem(48);
			aliennumbox.setBounds(390, 100, 100, 20);
			
			JLabel Error = new JLabel();
			Error.setBounds(45,120, 250, 100);
			Error.setForeground(Color.red);
			
		    JButton alienstoggle = new JButton("Aliens on");
		    alienstoggle.setBackground(Color.red);
		    alienstoggle.setForeground(Color.white);
		    alienstoggle.setBounds(390, 70, 100, 20);
		    alienstoggle.addActionListener(new ActionListener() { 
				  @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) { 
					  if(alienstoggle.getLabel().equals("Aliens on")){
						  	alienstoggle.setBackground(Color.green);
						  	alienstoggle.setLabel("Aliens off");
					  }else{
						  	alienstoggle.setBackground(Color.red);
						  	alienstoggle.setLabel("Aliens on");
					  }

					  
					}	  	  
				  }
			);
			   
			JButton confirmbut = new JButton("confirm");
			confirmbut.setBounds(45, 120, 82, 20);
			confirmbut.addActionListener(new ActionListener() { 
				  @SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) { 
					  fname = FNameInp.getText();
					  lname = LNameInp.getText();
					  cname = (String)ClassBox.getSelectedItem();
					  if(fname.matches("[a-zA-Z]+") && lname.matches("[a-zA-Z]+")){
						  System.out.println("FName: " + fname + "\n" +  "LName: " + lname + "\n" +  "CName: " + cname);
						  set.setfname(fname);
						  set.setlname(lname);
						  set.setcname(cname);
						  set.setaliennum((int) aliennumbox.getSelectedItem());
						  if(alienstoggle.getLabel().equals("Aliens on")){
							  set.setaliens(true);
						  }else{
							  set.setaliens(false);
						  }
						  new LaunchHex();
						  main.dispose();
					  }else{
						  Error.setText("Invalid data formats, please try again." + "("+errornum+")");
						  errornum++;
					  }
				  } 
				} );
			
			JLabel passwordlab = new JLabel("Password:");
			passwordlab.setBounds(15, 210, 82, 20);
			
			JPasswordField passwordInp = new JPasswordField();
			passwordInp.setBounds(85, 210, 82, 20);
			passwordInp.setBorder(null);
			
			incorrectpass = new JLabel();
			incorrectpass.setBounds(10, 150, 500, 20);
			incorrectpass.setForeground(Color.red);
			
			JLabel changepasslabold = new JLabel("old password:");
			changepasslabold.setBounds(320, 170, 120, 20);
			
			JPasswordField changepasswordInp = new JPasswordField();
			changepasswordInp.setBounds(410, 170, 82, 20);
			changepasswordInp.setBorder(null);
			
			JLabel changepasslabnew = new JLabel("new password:");
			changepasslabnew.setBounds(320, 200, 120, 20);
			
			JPasswordField changepasswordInpnew = new JPasswordField();
			changepasswordInpnew.setBounds(410, 200, 82, 20);
			changepasswordInpnew.setBorder(null);
			
			JButton changeconfirm = new JButton("Change password");
			changeconfirm.setBounds(320, 240,160, 20);
			changeconfirm.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  password = changepasswordInp.getPassword();
					  password2 = changepasswordInpnew.getPassword();
					  passwordstr = String.copyValueOf(password);
					  passwordstr2 = String.copyValueOf(password2);
					  //ensures that the password is 4 characters long or more, had a capital and lowercass letter and a number
					  if(passwordstr.matches("^.*(?=.{4,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$") 
							  && passwordstr2.matches("^.*(?=.{4,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")){
						  if (passwordCheck(password)){
							  generate = true;
							  password = changepasswordInpnew.getPassword();
							  passwordCheck(password);
						  }else{
							  incorrectpass.setText("Incorrect pass!" + "(" + passattempt +")");
							  passattempt++;
						  }

					  }else{
						  incorrectpass.setText("Pass must contain a capital,a lowercass,a number and be atleast 4 characters." + "("+errornum+")");
						  errornum++;
					  }
				  } 
				} );
			
			JButton databut = new JButton("Scores");
			databut.setBackground(Color.RED);
			databut.setForeground(Color.white);
			databut.setBounds(15, 240, 82, 20);
			databut.addActionListener(new ActionListener() { 
				  public void actionPerformed(ActionEvent e) { 
					  password = passwordInp.getPassword();
					  passwordstr = String.copyValueOf(password);

						  
						  if(passwordstr.matches("^.*(?=.{4,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")){					  
								  if (passwordCheck(password)){
									main.setVisible(false);
									set.setview(false);
									data2(args);
								  }else{
								  }
							}else{
							  incorrectpass.setText("Pass must contain a capital,a lowercass,a number and be atleast 4 characters." + "("+errornum+")");
							  errornum++;
					}	  	  
				  }
				} );
			
		   
		   
		   String path = "logo6.jpg";
		   File file = new File(path);
		   BufferedImage image = null;
		   try {
			image = ImageIO.read(file);
		   } catch (IOException e1) {
			e1.printStackTrace();
		   }
		   JLabel label = new JLabel(new ImageIcon(image));
		   label.setBounds(265,0, 250, 70);
			
		        
		    main.add(img);
			main.add(FNameInp);
			main.add(FNamelab);
			main.add(LNameInp);
			main.add(LNamelab);
			main.add(ClassBox);
			main.add(CNamelab);
			main.add(confirmbut);
			main.add(passwordlab);
			main.add(passwordInp);
			main.add(databut);
			main.add(Error);
			main.add(incorrectpass);
			main.add(changepasslabold);
			main.add(changepasswordInp);
			main.add(changepasslabnew);
			main.add(changepasswordInpnew);
			main.add(changeconfirm);
			//main.add(label);
			main.add(alienstoggle);
			main.add(aliennumbox);
			main.add(aliennumlabel);
			main.setVisible(true);
		}
	}
    
	//Checks the users password to see if its correct, it also generates a new password if there is not one. 
	public boolean passwordCheck(char[] password){
        String fileName = "password.txt";
        String line = null;
        encryptedtotal = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            if((line = bufferedReader.readLine()) == null) {
            	generate = true;
            }
            bufferedReader.close();         
        }
        catch(Exception e) {
        }

		for(int x = 0;x<password.length;x++){
			encrypted[x] = password[x];
			encryptedtotal += encrypted[x];
		}
		try{
		encryptedtotal = (encryptedtotal/password.length) + password[0];
		}catch(Exception e){
			
		}
        String encryptedstr = Integer.toString(encryptedtotal);
        
		if(generate){
    		try{
    			FileWriter fstream = new FileWriter("password.txt");
    			BufferedWriter out = new BufferedWriter(fstream);
    			out.write(encryptedstr);
    			out.close();
    			generate = false;
    		}catch(Exception e){
    			System.out.println(e);
    		}
		}
		
		if(!generate){
	        try {
	            FileReader fileReader = new FileReader(fileName);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	
	            while((line = bufferedReader.readLine()) != null) {
	                if(line.equals(encryptedstr)){
	                	access = true;
	                }else{
						  incorrectpass.setText("Incorrect pass!" + "(" + passattempt +")");
						  passattempt++;
	                }
	            }
	            bufferedReader.close();         
	        }
	        catch(Exception e) {
	        }
		}
		return access;
	}
	
	//launches the data view table.
	@SuppressWarnings("unchecked")
	public void start(Stage primaryStage){
       // These set up the columns and the data they contain, they are all exactly the same so.
    	window = primaryStage;
        window.setTitle("Data View Thing");
        
        
        // sets up the column and its display name.
        //"Data" is the class that it extends,String is the data type
        //("Name") is the name to use for the column
        TableColumn<Data, String> nameColumn = new TableColumn<>("Name");
        // the minimum width the column will always be
        // it can be expanded but not be any smaller than this
        nameColumn.setMinWidth(100);
        //The variable to use in this column
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Data, String> lastnameColumn = new TableColumn<>("Last Name");
        lastnameColumn.setMinWidth(100);
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        
        TableColumn<Data, Double> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setMinWidth(20);
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        

        TableColumn<Data, String> classColumn = new TableColumn<>("Class");
        classColumn.setMinWidth(20);
        classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        
        
        
        // creates a table
        table = new TableView<>();
        // gets the stuff to put in the table
        table.setItems(getData());
        // adds all the columns in
        table.getColumns().addAll(nameColumn,lastnameColumn,classColumn,scoreColumn);
        // sets out the layout of the window- VBox sets out everything in one column 
        //and since we are adding a table in this is the best one to use
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        // creates a scene with the given layout
        Scene scene = new Scene(vBox);
        // gives the window the scene
        window.setScene(scene);
        // makes the window visible
        window.show();
        
       
    }
    //Get all the data and puts it into an array (ObservableList<Data> is the return type, meaning it returns an array type thing)
    public ObservableList<Data> getData(){
    	// makes an array 
    	ObservableList<Data> data = FXCollections.observableArrayList();
		try{
			// sets the file as the input of the scanner
			input = new Scanner(new File("Test.txt"));
		}catch(Exception e){
			System.out.println("NOOOO PLZ");
		}
		// while there is something in the file
        while(input.hasNext()){
        	// sets the variables as the next thing in the file
        	fname = input.next();
        	lname = input.next();
        	score = input.nextInt();
        	cname = input.next();
        	// adds data to the array by calling the Data class which then goes into the constructor in that class which sorts 
        	// out all these variables so they can be used by the columns.
	        data.add(new Data(fname,lname, score , cname));
        }
        // closes the input/file
        input.close();
        //returns the array to the table so it actually has stuff.
        return data;
    }
	public class LaunchHex extends JFrame{
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int score;

    	// is the constructor of the class so when it is called this is run
    	public LaunchHex(){
    		// adds the main class to the screen , which itself adds everything else
            add(new Hex2());        
            // sets the size of the window
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int)dim.getWidth();
            set.setwidth(width);
            int height = (int)dim.getHeight();
            set.setheight(height);
            setSize(width,height);
           setUndecorated(true);
            //sets the title of the window
            setTitle("Hex invaders");
			Image frameImg = Toolkit.getDefaultToolkit().getImage("LogoSm.jpg");
			setIconImage(frameImg);
            // this stops users being able to close the game without pressing the exit button.
            setDefaultCloseOperation(0);
            // centres the window on the monitor
            setLocationRelativeTo(null);
            //sets it to visible
            setVisible(true);
            // makes the window not re sizable
            setResizable(false);
            addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
    				try{
    					System.out.println(set.getscore());
    					int score = set.getscore();
    					dispose();
    		    		FileWriter fstream = new FileWriter("Test.txt", true);
    		    		BufferedWriter out = new BufferedWriter(fstream);
    		    		out.write(fname + " " + lname + " " + score + " " + cname);
    		    		out.newLine();
    		    		out.close();
    				}catch(Exception ee){
    				}
                    System.out.println("Closed");
                    //e.getWindow().dispose();
                }
            });
        }
    
    }
}
