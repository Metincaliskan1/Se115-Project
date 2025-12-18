// Main.java — Students version

import java.io.*;
import java.nio.file.Paths;
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

            for (int m = 0; m <MONTHS; m++){
                try{
                    Scanner reader = new Scanner(Paths.get("Data_Files/" +months[m]+".txt"));
                    while (reader.hasNextLine()){
                        String line= reader.nextLine();
                        String[] parts=line.split(",");
                        int day=Integer.parseInt(parts[0].trim());//trim boşluk varsa silmek için
                        String CommName= parts[1].trim();
                        int profit=Integer.parseInt(parts[2].trim());
                        int commIndex = -1;
                        for(int i=0;i<COMMS;i++){
                            if(commodities[i].equals(CommName)){
                                commIndex=i;
                                break;
                            }
                        }
                        if(commIndex != -1) {
                            profitData[m][day-1][commIndex] = profit;
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Something went wrong....");

            }
     }
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
        int comm1Index=-1;
        int comm2Index=-1;
        for (int i=0;i<COMMS;i++){
            if (c1.equals(commodities[i])){
                comm1Index=i; //String olarak girilen commodatiesi int değerine çevirdik
            }
            if (c2.equals(commodities[i])){
                comm2Index=i;
            }
        }
        if(comm1Index==-1||comm2Index==-1){
            return "INVALID_COMMODITY";
        }

        int c1Profit = commodityProfitInRange(c1,1,28);
        int c2Profit = commodityProfitInRange(c2,1,28);
        int diff=0;


        String result;

        if (c1Profit>c2Profit){
            diff=c1Profit-c2Profit;
            result=c1+" is better by "+diff;
        }else if (c1Profit==c2Profit){
             result="Equal";
        }else {
            diff=c2Profit-c1Profit;
            result=c2+" is better by "+diff;
        }

        return result;
    }
    
    public static String bestWeekOfMonth(int month) {
        if(month>11||month<0){
            return "INVALID_MONTH";
        }
        int profitWeek1=0;
        int profitWeek2=0;
        int profitWeek3=0;
        int profitWeek4=0;

        for (int d=1;d<=7;d++){
            profitWeek1=profitWeek1+totalProfitOnDay(month,d);
        }
        for (int d=8;d<=14;d++){
            profitWeek2=profitWeek2+totalProfitOnDay(month,d);
        }
        for (int d=15;d<=21;d++){
            profitWeek3=profitWeek3+totalProfitOnDay(month,d);
        }
        for (int d=22;d<=28;d++){
            profitWeek4= profitWeek4+totalProfitOnDay(month,d);
        }

        int maxProfit = profitWeek1;
        String result="Week 1";


        if (profitWeek2 > maxProfit){
            maxProfit=profitWeek2;
            result="Week 2";

        }
        if (profitWeek3 > maxProfit){
            maxProfit=profitWeek3;
            result="Week 3";

        }
        if (profitWeek4 > maxProfit){
            maxProfit=profitWeek4;
            result="Week 4";

        }

        return result;
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }

}