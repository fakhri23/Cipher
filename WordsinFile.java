
import edu.duke.*;;
import java.util.*;
import java.io.*;
/**
 * Décrivez votre classe WordsinFile ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class WordsinFile {
    private HashMap<String, ArrayList<String>> map;
    public WordsinFile(){
        map = new HashMap();
    }
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String word: fr.words()){
            if(map.containsKey(word)){
                if (!map.get(word).contains(f.getName())){
                    map.get(word).add(f.getName());
                }
            }else{
                ArrayList<String> arr = new ArrayList();
                arr.add(f.getName());
                map.put(word, arr);
            }
        }
        
    }
    public void buildWordFileMap(){
        this.map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            this.addWordsFromFile(f);
        }
    }
    public int maxNumber(){
        int max = 0 ;
        for(String word: map.keySet()){
            int value = map.get(word).size();
            if (value > max){
                max = value;
            }
        }
        return max;
    }
    public ArrayList<String> wordsNumFile(int num){
        ArrayList<String> arr = new ArrayList();
        for(String word: map.keySet()){
            int value = map.get(word).size();
            if (value == num){
                arr.add(word);
            }
        }
        return arr;
    }
    public void printFilesIn(String word){
        System.out.println("Names of Files in which the word '"+word+"' occurs are :");
        System.out.println(this.map.get(word));
    }
    public void teser(){
        WordsinFile wf = new WordsinFile();
        wf.buildWordFileMap();
        System.out.println("The maximum number of Files: "+wf.maxNumber()); 
        ArrayList<String> arr = wf.wordsNumFile(7);
       
        System.out.println(arr.size());
         arr = wf.wordsNumFile(4);
         System.out.println(arr.size());
        System.out.println(wf.map.get("sea"));
        System.out.println(wf.map.get("tree"));
        /**for( String name : arr){
            printFilesIn(name);
        }
        for(String word : wf.map.keySet()){
            System.out.println(word+'\t'+wf.map.get(word));
        }*/
    }
}
