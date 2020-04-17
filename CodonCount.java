
import java.util.*;
import java.io.*;
import edu.duke.*;

/**
 * Décrivez votre classe CodonCount ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class CodonCount {
    private HashMap<String, Integer> map;
    public CodonCount(){
        map = new HashMap();
    }
    public void buildCodonMap(String dna, int start){
        map.clear();
        int index = start; 
        while(index+3 <= dna.length()){
            String codon = dna.substring(index, index+3);
            index +=3;
            if( map.containsKey(codon)){
                map.replace(codon, map.get(codon)+1);
            }else{
                map.put(codon, 1);
            }
        }
    }
    public String getMostCommonCodon(){
        String maxCodon = "";
        for (String codon : map.keySet()){
            if(maxCodon.equals("") ||(map.get(maxCodon)<map.get(codon))){
                maxCodon = codon;
            }
        }
        return maxCodon;
    }public void printCodoncounts(int start, int end){
        for(String codon: map.keySet()){
            if(map.get(codon)>=start && map.get(codon)<=end){
                System.out.println(map.get(codon)+"\t"+codon);
            }
        }
    }
    public void tester(){
     
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        int start = 0;
        dna = dna.toUpperCase();
        CodonCount cc = new CodonCount();
        cc.buildCodonMap(dna, start);
        System.out.println("Total Number of unique codon: "+cc.map.size());
        System.out.println("The most Common Codon: "+cc.getMostCommonCodon());
        cc.printCodoncounts(0,11000);
    }
    
}
