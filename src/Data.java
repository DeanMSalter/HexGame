
public class Data {

	// the variables to use
    String name;
    String lastname;
    int score;
    String className;
    // this constructor is called whenever the class is called.
    // it accepts all these variables and then stores them, this means that they can be returned with the
    // below methods, this is useful for the table as it needs to be done for each row.
    public Data(String name, String lastname, int score,String className){
        this.name = name;
        this.lastname = lastname;
        this.className = className;
        this.score = score;
    }
    
    // these all do pretty much the same thing and return the variables that are wanted.
    public String getLastname() {
        return lastname;
    }    
    public String getName() {
        return name;
    }
    public String getClassName() {
        return className;
    }
    public int getScore() {
        return score;
    }

}