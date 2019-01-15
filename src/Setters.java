
public class Setters {
	static String FName;
	static String LName;
	static String CName;
	static int Score;
	static boolean view = true;
	static int width;
	static int height;
	static boolean aliens;
	static int aliennum;
	public void setfname(String FName){
		Setters.FName = FName;
	}
	public String getfname(){
		return FName;
	}
	
	public void setlname(String LName){
		Setters.LName = LName;
	}
	public String getlname(){
		return LName;
	}
	public void setcname(String CName){
		Setters.CName = CName;
	}
	public String getcname(){
		return CName;
	}
	public void setscore(int Score){
		Setters.Score = Score;
	}
	public int getscore(){
		return Score;
	}
	public void setview(boolean view){
		Setters.view = view;
	}
	public boolean getview(){
		return view;
	}
	public void setwidth(int width) {
		Setters.width = width;
	}
	public int getwidth() {
		return width;
	}
	public void setheight(int height) {
		Setters.height = height;
	}
	public int getheight() {
		return height;
	}
	public void setaliens(boolean aliens) {
		Setters.aliens = aliens;
	}
	public boolean getaliens() {
		return aliens;
	}
	public void setaliennum(int aliennum) {
		Setters.aliennum = aliennum;
	}
	public int getaliennum() {
		return aliennum;
	}
}
