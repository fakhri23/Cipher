import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private String[] Categories = {"adjective", "noun","color","country","name", "animal", "timeframe", "verb", "fruit"};
    private boolean[] used = new boolean[9]; 
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";

    public GladLibMap(){
         myMap = new HashMap();
        initializeFromSource("data");
        myRandom = new Random();
       
    }

    public GladLibMap(String source){
        myMap = new HashMap();
     initializeFromSource(source);
     myRandom = new Random();
     
     }

    private void initializeFromSource(String source) {
        for (String word : Categories){
            ArrayList<String> arr = readIt(source+"/"+word+".txt");
            myMap.put(word, arr);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }else if (Arrays.asList(Categories).contains(label)) {
            used[Arrays.asList(Categories).indexOf(label)] = true;
            return randomFrom(myMap.get(label));
           
        }
        return "**UNKNOWN**";
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(new File(source));
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    public int totalWordsInMap(){
        int total =0 ;
        for(String word : myMap.keySet()){
            total += myMap.get(word).size();
        }return total;
    }
    public int totalWordsConsidered(){
        int total = 0;
        for(String word : myMap.keySet()){
            if(used[Arrays.asList(Categories).indexOf(word)] = true){
                total += myMap.get(word).size();
            }
            
        }   return total;
    }
    public  void  main(){
        GladLibMap glp = new GladLibMap("data");
        System.out.println("\n");
        String story = glp.fromTemplate("data/madtemplate.txt");
        glp.printOut(story, 60);
        System.out.println();
        System.out.println("total Num of Words: "+glp.totalWordsInMap());
        System.out.println("total Num of Words Considered: "+glp.totalWordsConsidered());
    }



}