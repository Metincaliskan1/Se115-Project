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
        if(from < 1 || to < 1 || from > to || to > 28){
            return -99999;
        }
        int comm=-1;
        int sum=0;
        for(int i=0;i<commodities.length;i++){
            if (commodities[i].equals(commodity)){
                comm=i;
            }
        }
        if (comm == -1) {
            return -99999; //eğer değer bulunumaz ise
        }
        for(int m=0;m<months.length;m++){


            for (int i=from;i<=to;i++){
                sum= sum+profitData[m][i-1][comm];

            }

        }

        return sum;
    }

    public static int bestDayOfMonth(int month) {
        if(month>11||month<0){
            return -1;
        }

        int bestdaytotal=-999999999;
        int bestday=0;
        for (int d=0;d<28;d++){
            int sum=0;
            for (int c=0;c<COMMS;c++){
                sum=sum+profitData[month][d][c];
            }
            if(sum>bestdaytotal){
                bestdaytotal=sum;
                bestday=d+1;

            }
        }

        return bestday;
    }
    
    public static String bestMonthForCommodity(String comm) {

        int commIndex=-1;
        for (int i=0;i<COMMS;i++){
            if (comm.equals(commodities[i])){
                commIndex=i; //String olarak girilen commodatiesi int değerine çevirdik
            }
        }
        if(commIndex==-1){
            return "INVALID_COMMODITY";
        }
        int bestMonth=-1;
        int bestMonthTotal=Integer.MIN_VALUE;
        for(int m=0;m<months.length;m++){
            int sum=0;
            for(int d=0;d<DAYS;d++){
                sum=sum+profitData[m][d][commIndex];
            }
            if (sum>bestMonthTotal){
                bestMonthTotal=sum;
                bestMonth=m;
            }
        }


        return months[bestMonth];
    }

    public static int consecutiveLossDays(String comm) {
        int commIndex=-1;
        for (int i=0;i<COMMS;i++){
            if (comm.equals(commodities[i])){
                commIndex=i; //String olarak girilen commodatiesi int değerine çevirdik
            }
        }
        if(commIndex==-1){
            return -1;
        }
        int currentStreak=0;
        int maxStreak=0;

        for(int m=0;m<MONTHS;m++){
            for (int d=0;d<DAYS;d++){
                if(profitData[m][d][commIndex]<0){
                    currentStreak++;
                }
                else {
                    currentStreak=0;
                }
                if (currentStreak>maxStreak){
                    maxStreak=currentStreak;
                }
            }
        }

        return maxStreak;
    }
    
    public static int daysAboveThreshold(String comm, int threshold) {
        int commIndex=-1;
        for (int i=0;i<COMMS;i++){
            if (comm.equals(commodities[i])){
                commIndex=i;
            }
        }
        if(commIndex==-1){
            return -1;
        }
        int dayCount=0;
        for (int m=0;m<MONTHS;m++){
            for (int d=0;d<DAYS;d++){
                if (profitData[m][d][commIndex]>threshold){
                    dayCount++;
                }
            }
        }


        return dayCount;
    }

    public static int biggestDailySwing(int month) {
        if(month>11||month<0){
            return -99999;
        }
        int swing=0;
        int maxSwing=0;


        for (int d=2;d<=DAYS;d++){
            //totalProfitOnDay metodu 1-28 arası o yüzden 2den başladım

            int today=totalProfitOnDay(month,d);
            int yesterday=totalProfitOnDay(month,d-1);

            swing=today-yesterday;
            if (swing < 0) {
                swing = swing*-1;
            }
            if (swing>maxSwing){
                maxSwing=swing;
            }
        }


        return maxSwing;
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