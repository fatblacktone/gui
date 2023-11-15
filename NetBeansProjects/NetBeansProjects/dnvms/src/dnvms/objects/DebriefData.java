/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mathew Keegan
 */
public class DebriefData {
    
    private int numberOfrecords;
    private ArrayList<Date> date;
    private ArrayList<String> run;
    private ArrayList<String> reg;
    private ArrayList<Integer> miles;
    private ArrayList<Integer> hours;
    private ArrayList<Integer> col;
    private ArrayList<Integer> del;
    
    private ArrayList<Downtime> downtime;
    
    private ArrayList<Integer> delivered;
    private ArrayList<Integer> deliveredCarded;
    private ArrayList<Integer> deliveredRejected;
    private ArrayList<Integer> deliveredBadWeather;
    private ArrayList<Integer> deliveredCFA;
    private ArrayList<Integer> deliveredPCA;
    
    private ArrayList<Integer> collectedScheduled;
    private ArrayList<Integer> collectedUnscheduled;
    private ArrayList<Integer> collectedNTC;
    private ArrayList<Integer> collectedCarded;
    private ArrayList<Integer> collectedBadWeather;
    private ArrayList<Integer> collectedCFA;
    private ArrayList<Integer> collectedPCA;
    
    private boolean isCreated = false;

    public DebriefData(int numberOfrecords, ArrayList<String> run, ArrayList<String> reg, ArrayList<Integer> miles, ArrayList<Integer> hours, ArrayList<Integer> col, ArrayList<Integer> del, ArrayList<Downtime> downtime, ArrayList<Integer> delivered, ArrayList<Integer> deliveredCarded, ArrayList<Integer> deliveredRejected, ArrayList<Integer> deliveredBadWeather, ArrayList<Integer> deliveredCFA, ArrayList<Integer> deliveredPCA, ArrayList<Integer> collectedScheduled, ArrayList<Integer> collectedUnscheduled, ArrayList<Integer> collectedNTC, ArrayList<Integer> collectedCarded, ArrayList<Integer> collectedBadWeather, ArrayList<Integer> collectedCFA, ArrayList<Integer> collectedPCA) {
        this.numberOfrecords = numberOfrecords;
        this.run = run;
        this.reg = reg;
        this.miles = miles;
        this.hours = hours;
        this.col = col;
        this.del = del;
        this.downtime = downtime;
        this.delivered = delivered;
        this.deliveredCarded = deliveredCarded;
        this.deliveredRejected = deliveredRejected;
        this.deliveredBadWeather = deliveredBadWeather;
        this.deliveredCFA = deliveredCFA;
        this.deliveredPCA = deliveredPCA;
        this.collectedScheduled = collectedScheduled;
        this.collectedUnscheduled = collectedUnscheduled;
        this.collectedNTC = collectedNTC;
        this.collectedCarded = collectedCarded;
        this.collectedBadWeather = collectedBadWeather;
        this.collectedCFA = collectedCFA;
        this.collectedPCA = collectedPCA;
    }
    
    public DebriefData(){
        
    }
    
    
    
    public void createNewDebrief(String run,String reg,int miles,int hours,
                                 int col,int del,Downtime downtime,int delivered,
                                 int deliveredCarded,int deliveredRejected,
                                 int deliveredBadWeather,int deliveredCFA,
                                 int deilveredPCA,int collectedScheduled,
                                 int collectedUnscheduled,int collectedNTC,
                                 int collectedCarded,int collectedBadWeather,
                                 int collectedCFA,int collectedPCA){
        if(!isCreated){
            this.initiate();
            isCreated = true;
        }
        numberOfrecords++;
        this.date.add(new Date());
        this.run.add(run);
        this.reg.add(reg);
        this.miles.add(miles);
        this.hours.add(hours);
        this.col.add(col);
        this.del.add(del);
        this.downtime.add(downtime);
        this.delivered.add(delivered);
        this.deliveredCarded.add(deliveredCarded);
        this.deliveredRejected.add(deliveredRejected);
        this.deliveredBadWeather.add(deliveredBadWeather);
        this.deliveredCFA.add(deliveredCFA);
        this.deliveredPCA.add(deilveredPCA);
        this.collectedScheduled.add(collectedScheduled);
        this.collectedUnscheduled.add(collectedUnscheduled);
        this.collectedNTC.add(collectedNTC);
        this.collectedCarded.add(collectedCarded);
        this.collectedCFA.add(collectedCFA);
        this.collectedPCA.add(collectedPCA);
        
        
        
    }

    private void initiate(){
        this.numberOfrecords = 0;
        this.date = new ArrayList<>();
        this.run = new ArrayList<>();
        this.reg = new ArrayList<>();
        this.miles = new ArrayList<>();
        this.hours = new ArrayList<>();
        this.col = new ArrayList<>();
        this.del = new ArrayList<>();
        this.downtime = new ArrayList<>();
        this.delivered = new ArrayList<>();
        this.deliveredCarded = new ArrayList<>();
        this.deliveredRejected = new ArrayList<>();
        this.deliveredBadWeather = new ArrayList<>();
        this.deliveredCFA = new ArrayList<>();
        this.deliveredPCA = new ArrayList<>();
        this.collectedScheduled = new ArrayList<>();
        this.collectedUnscheduled = new ArrayList<>();
        this.collectedNTC = new ArrayList<>();
        this.collectedCarded = new ArrayList<>();
        this.collectedBadWeather = new ArrayList<>();
        this.collectedCFA = new ArrayList<>();
        this.collectedPCA = new ArrayList<>();
    }
    
    

    public ArrayList<Integer> getCol() {
        return col;
    }

    public void setCol(ArrayList<Integer> col) {
        this.col = col;
    }

    public ArrayList<Integer> getCollectedBadWeather() {
        return collectedBadWeather;
    }

    public void setCollectedBadWeather(ArrayList<Integer> collectedBadWeather) {
        this.collectedBadWeather = collectedBadWeather;
    }

    public ArrayList<Integer> getCollectedCFA() {
        return collectedCFA;
    }

    public void setCollectedCFA(ArrayList<Integer> collectedCFA) {
        this.collectedCFA = collectedCFA;
    }

    public ArrayList<Integer> getCollectedCarded() {
        return collectedCarded;
    }

    public void setCollectedCarded(ArrayList<Integer> collectedCarded) {
        this.collectedCarded = collectedCarded;
    }

    public ArrayList<Integer> getCollectedNTC() {
        return collectedNTC;
    }

    public void setCollectedNTC(ArrayList<Integer> collectedNTC) {
        this.collectedNTC = collectedNTC;
    }

    public ArrayList<Integer> getCollectedPCA() {
        return collectedPCA;
    }

    public void setCollectedPCA(ArrayList<Integer> collectedPCA) {
        this.collectedPCA = collectedPCA;
    }

    public ArrayList<Integer> getCollectedScheduled() {
        return collectedScheduled;
    }

    public void setCollectedScheduled(ArrayList<Integer> collectedScheduled) {
        this.collectedScheduled = collectedScheduled;
    }

    public ArrayList<Integer> getCollectedUnscheduled() {
        return collectedUnscheduled;
    }

    public void setCollectedUnscheduled(ArrayList<Integer> collectedUnscheduled) {
        this.collectedUnscheduled = collectedUnscheduled;
    }

    public ArrayList<Integer> getDel() {
        return del;
    }

    public void setDel(ArrayList<Integer> del) {
        this.del = del;
    }

    public ArrayList<Integer> getDelivered() {
        return delivered;
    }

    public void setDelivered(ArrayList<Integer> delivered) {
        this.delivered = delivered;
    }

    public ArrayList<Integer> getDeliveredBadWeather() {
        return deliveredBadWeather;
    }

    public void setDeliveredBadWeather(ArrayList<Integer> deliveredBadWeather) {
        this.deliveredBadWeather = deliveredBadWeather;
    }

    public ArrayList<Integer> getDeliveredCFA() {
        return deliveredCFA;
    }

    public void setDeliveredCFA(ArrayList<Integer> deliveredCFA) {
        this.deliveredCFA = deliveredCFA;
    }

    public ArrayList<Integer> getDeliveredCarded() {
        return deliveredCarded;
    }

    public void setDeliveredCarded(ArrayList<Integer> deliveredCarded) {
        this.deliveredCarded = deliveredCarded;
    }

    public ArrayList<Integer> getDeliveredPCA() {
        return deliveredPCA;
    }

    public void setDeliveredPCA(ArrayList<Integer> deliveredPCA) {
        this.deliveredPCA = deliveredPCA;
    }

    public ArrayList<Integer> getDeliveredRejected() {
        return deliveredRejected;
    }

    public void setDeliveredRejected(ArrayList<Integer> deliveredRejected) {
        this.deliveredRejected = deliveredRejected;
    }

    public ArrayList<Downtime> getDowntime() {
        return downtime;
    }

    public void setDowntime(ArrayList<Downtime> downtime) {
        this.downtime = downtime;
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }

    public void setHours(ArrayList<Integer> hours) {
        this.hours = hours;
    }

    public ArrayList<Integer> getMiles() {
        return miles;
    }

    public void setMiles(ArrayList<Integer> miles) {
        this.miles = miles;
    }

    public int getNumberOfrecords() {
        return numberOfrecords;
    }

    public void setNumberOfrecords(int numberOfrecords) {
        this.numberOfrecords = numberOfrecords;
    }

    public ArrayList<String> getReg() {
        return reg;
    }

    public void setReg(ArrayList<String> reg) {
        this.reg = reg;
    }

    public ArrayList<String> getRun() {
        return run;
    }

    public void setRun(ArrayList<String> run) {
        this.run = run;
    }

    @Override
    public String toString() {
        return "DebriefData{" + "numberOfrecords=" + numberOfrecords + ", run=" + run + ", reg=" + reg + ", miles=" + miles + ", hours=" + hours + ", col=" + col + ", del=" + del + ", downtime=" + downtime + ", delivered=" + delivered + ", deliveredCarded=" + deliveredCarded + ", deliveredRejected=" + deliveredRejected + ", deliveredBadWeather=" + deliveredBadWeather + ", deliveredCFA=" + deliveredCFA + ", deliveredPCA=" + deliveredPCA + ", collectedScheduled=" + collectedScheduled + ", collectedUnscheduled=" + collectedUnscheduled + ", collectedNTC=" + collectedNTC + ", collectedCarded=" + collectedCarded + ", collectedBadWeather=" + collectedBadWeather + ", collectedCFA=" + collectedCFA + ", collectedPCA=" + collectedPCA + '}';
    }
    
    
}
