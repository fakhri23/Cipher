package Cryptography;

import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder sb = new StringBuilder();
        for (int i = whichSlice; i< message.length() ; i += totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cracker = new CaesarCracker(mostCommon);
        //WRITE YOUR CODE HERE
        for(int i = 0 ; i< klength; i++){
            String message = sliceString(encrypted, i , klength);
            int k = cracker.getKey(message);
            key[i] = k;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource();
        String message = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> map = new HashMap();
        for(File f: dr.selectedFiles()){
            HashSet<String> dictionary =  readDictionary(new FileResource(f));
            map.put(f.getName(), dictionary);
        }
            
        int[] arr = tryKeyLength(message, breakForAllLangs(message, map), 'e');
        System.out.println(arr.length);
        for (int i : arr){
            System.out.print(i+" , ");
        }
        VigenereCipher vg = new VigenereCipher(arr);
        String decrypted = vg.decrypt(message);
        System.out.println(decrypted.substring(0,200));
    }
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> set = new HashSet();
        for( String word : fr.words()){
            set.add(word.toLowerCase());
        }
        return set;
    }
    public int countWords(String message , HashSet<String> dictionary){
        int total = 0;
        for (String word : message.split("\\W")){
            if (dictionary.contains(word.toLowerCase())){ total++;}
        }
        return total;
    }
    public int breakForLanguage(String encrypted, HashSet<String> dictionary){
        int key = 0;
        int value = 0;
        for(int i =1; i<101;i++){
            int[] arr = tryKeyLength(encrypted,i, mostCommonCharIn(dictionary));
            VigenereCipher vg = new VigenereCipher(arr);
            String decrypted = vg.decrypt(encrypted);
            int counts = countWords(decrypted, dictionary);
            if (counts > value){
                key = i;
                value = counts;
            }
        }
        return key;
    }
    public int getValue(String encrypted, HashSet<String> dictionary){
            int key = breakForLanguage(encrypted, dictionary);
            int[] arr = tryKeyLength(encrypted,key, 'e');
            VigenereCipher vg = new VigenereCipher(arr);
            int counts = countWords(encrypted, dictionary);
            return counts;
            
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> occ = new HashMap();
        for(String word: dictionary){
            for(int i =0 ; i< word.length(); i++){
                char c = word.toLowerCase().charAt(i);
                if(occ.containsKey(c)){
                    occ.replace(c, occ.get(c)+1);
                }else {
                    occ.put(c,1);
                }
            }
        }
        int max = 0; 
        char toreturn='e';
        for(char c : occ.keySet()){
            if(max< occ.get(c)){
                toreturn = c ;
                max = occ.get(c);
            }
        }
        return toreturn; 
    }
    public int breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        String language = null;
        int max = 0;
        for(String lang : languages.keySet()){
            int value = getValue(encrypted, languages.get(lang));
            if (language == null || max < value){
                System.out.println(lang);
                language = lang; 
                max = value;
            }
        }
        System.out.println(language);
        return breakForLanguage(encrypted, languages.get(language));
    }
}
