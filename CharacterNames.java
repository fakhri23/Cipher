
import java.util.*;
import edu.duke.*;
/**
 * Décrivez votre classe CharacterNames ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CharacterNames {
    private ArrayList<String> Names;
    private ArrayList<Integer> Freq;
    public CharacterNames(){
        Names = new ArrayList();
        Freq = new ArrayList(); 
    }
    public void update(String person){
        if(! Names.contains(person)){
            Names.add(person);
            Freq.add(1);
        }else{
            int ind = Names.indexOf(person);
            Freq.set(ind, Freq.get(ind)+1);
        }
    }
    public void findAllCharacters(){
        Names.clear();
        Freq.clear();
        FileResource fr = new FileResource();
        for ( String line : fr.lines()){
            if (line.contains(".")&&line.length()>1){
                String periode = line.substring(0,line.indexOf('.'));
                update(periode);
            }
        }
    }
    public void CharacterWithNumParts(int num1, int num2){
        for ( int k = 0; k < Names.size(); k++){
            if( Freq.get(k) <=num2 && Freq.get(k) >= num1){
                System.out.println(Freq.get(k)+"\t"+Names.get(k));
            }
        }
    }
    public void tester(){
        findAllCharacters();
        /**for ( int k = 0; k < Names.size(); k++){
            System.out.println(Freq.get(k)+"\t"+Names.get(k));
        }*/
        CharacterWithNumParts(10,100);
    }

}
