// Main.java — Students version

import java.io.*;
import java.util.Scanner;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};

    public static int[][][] profitData = new int[12][28][5];
                                            //[month][day][comm]
    

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        //temporary data
        profitData[0][0][0] = 100;
        profitData[0][0][1] = 200;
        profitData[0][1][0] = 500;

        System.out.println("Dummy data loaded for testing.");
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        int maxProfit=-999999999;
        int bestCommodityIndex=-1;
        if(month>11||month<0){
            return "INVALID_MONTH";
        }
        else {
            for(int c=0;c<COMMS;c++){
                int sum = 0;
                for (int d=0;d<DAYS;d++){
                    sum=sum+profitData[month][d][c];

                }
                if(sum>maxProfit){
                    maxProfit=sum;
                    bestCommodityIndex=c;
                }


            }
        }
        return commodities[bestCommodityIndex]+" "+maxProfit;
    }

    public static int totalProfitOnDay(int month, int day) {
        int sum=0;
        if(month>11||month<0||day>28||day<1){
            return -99999;
        }

        for (int c=0;c<COMMS;c++){
            sum=sum+profitData[month][day -1][c];
        }

        return sum;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        return 1234;
    }

    public static int bestDayOfMonth(int month) { 
        return 1234; 
    }
    
    public static String bestMonthForCommodity(String comm) { 
        return "DUMMY"; 
    }

    public static int consecutiveLossDays(String comm) { 
        return 1234; 
    }
    
    public static int daysAboveThreshold(String comm, int threshold) { 
        return 1234; 
    }

    public static int biggestDailySwing(int month) { 
        return 1234; 
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
        return "DUMMY is better by 1234"; 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}