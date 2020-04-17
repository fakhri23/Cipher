
import java.util.*;
import edu.duke.*;
import java.io.*;
/**
 * Décrivez votre classe WordFrequencies ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WordFrequencies {
    ArrayList<String> myWords;
    ArrayList<Integer> myFreq;
    public WordFrequencies(){
        myWords = new ArrayList();
        myFreq = new ArrayList();
    }
    public void findUnique(){
        myWords.clear();
        myFreq.clear();
            FileResource fr = new FileResource();
            for(String word: fr.words()){
                word = word.toLowerCase();
                if(!myWords.contains(word)){
                    myWords.add(word);
                    myFreq.add(1);
                }else{
                    int index = myWords.indexOf(word);
                    myFreq.set(index, myFreq.get(index)+1);
            }
        }
    }
    public int findIndexOfMax(){
        int indMax = 0;
        for(int k =0; k < myWords.size(); k++ ){
            if (myFreq.get(k)>myFreq.get(indMax)){
                indMax = k;
            }
        }
        return indMax;
    }
    public void tester(){
        WordFrequencies o = new WordFrequencies();
        o.findUnique();
        System.out.println("Number of unique Words: "+o.myWords.size());
        /**for(int i = 0; i < o.myWords.size(); i++){
            System.out.println(o.myFreq.get(i)+"\t"+o.myWords.get(i));
        }*/
        System.out.println("Most occring word: "+o.myWords.get(findIndexOfMax())+" " + o.myFreq.get(findIndexOfMax()));
        

    }
}
