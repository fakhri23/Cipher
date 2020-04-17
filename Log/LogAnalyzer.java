package Log;


/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import java.io.*;
public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource();
         for(String line : fr.lines()){
            records.add(WebLogParser.parseEntry(line));
            
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs(){
         List<String> uniqueIPs = new ArrayList();
         for( LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if(! uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
                }
            }
            return uniqueIPs.size();
     }
     public void printAllHigherThanNum(int num){
         for( LogEntry le: records){
             if (le.getStatusCode()>num){
                System.out.println(le);
                }
            }
        }
     public void uniqueIPVisitsOnDay(String someday){
         List<String> uniqueIPs = new ArrayList();
         for( LogEntry le: records){
             String ipAddr = le.getIpAddress();
             String Date = le.getAccessTime().toString();
             if((!uniqueIPs.contains(ipAddr))&&Date.contains(someday)){
                 uniqueIPs.add(ipAddr);
                }
            }System.out.println(uniqueIPs.size());
        }
     public int countUniqueIPsInRange(int low, int high){
         List<String> uniqueIPs = new ArrayList();
         int count = 0;
         for( LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if(! uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
                 if(le.getStatusCode()>=low && le.getStatusCode()<=high){count++;}
                }
            }
            return count;
        }
        
        public HashMap<String , Integer> countVisitsperIp(){
            HashMap<String, Integer> counts = new HashMap(); 
            for (LogEntry le : records){
                String ip = le.getIpAddress();
                if(counts.containsKey(ip)){
                    int value = counts.get(ip);
                    counts.replace(ip, value+1);
                }else{
                    counts.put(ip,1);
                }
            }
            return counts;
        }
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         String maxind = null; 
         for (String ip : counts.keySet()){
             if ((maxind==null) || (counts.get(maxind)<counts.get(ip))){
                maxind = ip;
                }
            }
            return counts.get(maxind);
        }
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> arr = new ArrayList();
         int max = mostNumberVisitsByIP(counts);
         for (String ip: counts.keySet()){
             if (counts.get(ip)==(max)){ arr.add(ip);}
            }
            
            return arr;
        }
        public HashMap<String, ArrayList<String>> iPsForDays(){
            HashMap<String, ArrayList<String>> map = new HashMap();
            
            for (LogEntry le : records){
                String Date = le.getAccessTime().toString().substring(4,10);
                if(map.containsKey(Date)){
                    map.get(Date).add(le.getIpAddress());
                }else {
                    ArrayList<String> arr = new ArrayList();
                    arr.add(le.getIpAddress());
                    map.put(Date, arr);
                }
                
            }
            return map;
        }
        public String dayWithMostVisits(HashMap<String, ArrayList<String>> map){
            String max = null;
            for (String date : map.keySet()){
                if ((max==null) || (map.get(max).size()<map.get(date).size())){
                    max = date;
                }
            }
            return max;
        }
        private HashMap<String, Integer> reshape(ArrayList<String> arr){
            HashMap<String, Integer>  map = new HashMap();
            System.out.println(arr);
            for (String s : arr){
                if (map.containsKey(s)){
                    map.replace(s, map.get(s)+1);
                }else{
                    map.put(s,1);
                }
            } return map;
        }
        public ArrayList<String> iPsWithMostVisits(HashMap<String, ArrayList<String>> map , String date){
            HashMap<String, Integer> mapi = reshape(map.get(date));
         
            String max = null ;
            for (String s : mapi.keySet()){
                if((max ==null)||(mapi.get(max)<mapi.get(s))){
                    max = s;
                }
            }
            int maxvalue = mapi.get(max);
            ArrayList<String> arr = new ArrayList();
            for(String s : mapi.keySet()){
                if (mapi.get(s) == maxvalue){
                    arr.add(s);
                }
            }
            return arr; 
            
        }
        
}
