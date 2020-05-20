package geneticalgorithm.Model;

import java.util.Random;

public class Genes {
    private final String genesString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String getGenesString() {
        return genesString;
    }
    
    public char[] getGenesStringtoChar() {
        return genesString.toCharArray();
    }
    
    public char mutatedGenChar(){
        int lenght = genesString.length();
        Random random = new Random();
        int randomInt = random.nextInt(lenght);
        return genesString.toCharArray()[randomInt];
    }
  
    public String createGnome(int lenght){
        String gnome = "";
        for (int i = 0; i < lenght; i++) {
            gnome += mutatedGenChar();
        }
        return gnome;
    }
    
    public int getIndex(char input){
        return genesString .indexOf(input);
    }
    
}
