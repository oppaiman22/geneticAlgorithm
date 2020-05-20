package geneticalgorithm.Model;
public class Target {
    private final String targetString;

    public Target(String targetString) {
        this.targetString = targetString;
    }

    public String getTargetString() {
        return targetString;
    }
    
    public char[] getTargetStringtoChar() {
        return targetString.toCharArray();
    }
    
    public int getLength(){
        return targetString.length();
    }    
}
