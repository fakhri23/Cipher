package Log;


/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer Loga = new LogAnalyzer();
        Loga.readFile("weblog1_log.txt");
        Loga.printAll();
    }
    public void testUnique(){
        LogAnalyzer Loga = new LogAnalyzer();
        Loga.readFile("weblog1_log.txt");
        System.out.println(Loga.countUniqueIPs());
    }
    public void testHigherThanNul(){
        LogAnalyzer Loga = new LogAnalyzer();
        Loga.readFile("weblog1_log.txt");
        Loga.printAllHigherThanNum(400);
    }public void testUniqueIPsVisitsOnDay(){
        LogAnalyzer Loga = new LogAnalyzer();
        Loga.readFile("weblog1_log.txt");
        System.out.println("Start");
        Loga.uniqueIPVisitsOnDay("Sep 27") ;
    }
    public void testcountUniqueIPsInRange(){
        LogAnalyzer Loga = new LogAnalyzer();
        Loga.readFile("weblog1_log.txt");
        System.out.println(Loga.countUniqueIPsInRange(200 ,299));
        System.out.println(Loga.countUniqueIPsInRange(400,499));
    }
    public void testcountsVisitsperIP(){
        LogAnalyzer Loga = new LogAnalyzer();
        Loga.readFile("weblog1_log.txt");
        System.out.println(Loga.countVisitsperIp());
        System.out.println(Loga.countVisitsperIp().size());
    }
    public void testdayWithMostVisits(){
        LogAnalyzer Loga = new LogAnalyzer();
        Loga.readFile("weblog1_log.txt");
        System.out.println("Day With Most Visits: "+Loga.dayWithMostVisits(Loga.iPsForDays()));
        System.out.println("Most num Visits by Ip : "+Loga.mostNumberVisitsByIP(Loga.countVisitsperIp()));
        System.out.println("Ip Most visits : "+Loga.iPsMostVisits(Loga.countVisitsperIp())); 
        System.out.println(Loga.iPsWithMostVisits(Loga.iPsForDays(),"Sep 30"));
        
    }
}
