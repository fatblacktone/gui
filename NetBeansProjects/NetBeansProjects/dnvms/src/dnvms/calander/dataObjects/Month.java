/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.calander.dataObjects;

import dnvms.calander.dataObjects.Day.DayType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Mathew Keegan
 */
public class Month {
    
    private int numberOfDaysInMonth;
    private DayType firstDay,lastDay; 
    private ArrayList<Day> days;
    private MonthType thisMonth;
    
    public static enum MonthType{
        JANUARY,FEBRUARY,MARCH,APRIL,MAY,JUNE,JULY,AUGUST,SEPTEMBER,OCTOBER,NOVEMBER,DECEMBER
    }

    public Month(DayType firstDay,Month.MonthType thisMonth) {        
        this.firstDay = firstDay;
        this.thisMonth = thisMonth;
        numberOfDaysInMonth = getNumberOfDays(thisMonth);
        setLastDay();
    } 
    
    private void setLastDay(){
        LinkedList<Day.DayType> theWeek = Day.getDays();
        Day.DayType tempDay = DayType.MONDAY;
        for(int i = 0;i<7;i++){
            tempDay = theWeek.poll();
            theWeek.add(tempDay);
            if(tempDay == firstDay)break;
        }
        for(int i = 0;i<(numberOfDaysInMonth-1);i++){
            tempDay = theWeek.poll();
            theWeek.add(tempDay);            
        }
        lastDay = tempDay;
        System.out.println(thisMonth+"::::::"+lastDay);
    }
    
    public static String getMonthString(Month.MonthType month){
        String returnValue = "";
        switch(month){
            case JANUARY:
                returnValue = "January";
                break;
            case FEBRUARY:
                returnValue = "February";
                break;
            case MARCH:
                returnValue = "March";
                break;
            case APRIL:
                returnValue = "April";
                break;
            case MAY:
                returnValue = "May";
                break;
            case JUNE:
                returnValue = "June";
                break;
            case JULY:
                returnValue = "July";
                break;
            case AUGUST:
                returnValue = "August";
                break;
            case SEPTEMBER:
                returnValue = "September";
                break;
            case OCTOBER:
                returnValue = "October";
                break;
            case NOVEMBER:
                returnValue = "November";
                break;
            case DECEMBER:
                returnValue = "December";
                break;
        }
        return returnValue;
    }
    
    public static int getNumberOfDays(MonthType month){
        int returnValue = 0;
        switch(month){
            case JANUARY:
                returnValue = 31;
                break;
            case FEBRUARY:
                returnValue = 29;
                break;
            case MARCH:
                returnValue = 31;
                break;
            case APRIL:
                returnValue = 30;
                break;
            case MAY:
                returnValue = 31;
                break;
            case JUNE:
                returnValue = 30;
                break;
            case JULY:
                returnValue = 31;
                break;
            case AUGUST:
                returnValue = 31;
                break;
            case SEPTEMBER:
                returnValue = 30;
                break;
            case OCTOBER:
                returnValue = 31;
                break;
            case NOVEMBER:
                returnValue = 30;
                break;
            case DECEMBER:
                returnValue = 31;
                break;
        }
        return returnValue;
    }
    

    public DayType getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(DayType firstDay) {
        this.firstDay = firstDay;
    }

    public DayType getLastDay() {
        return lastDay;
    }

    public void setLastDay(DayType lastDay) {
        this.lastDay = lastDay;
    }

    public int getNumberOfDaysInMonth() {
        return numberOfDaysInMonth;
    }

    public void setNumberOfDaysInMonth(int numberOfDaysInMonth) {
        this.numberOfDaysInMonth = numberOfDaysInMonth;
    }

    public ArrayList<Day> getDays() {
        return days;
    }

    public void setDays(ArrayList<Day> days) {
        this.days = days;
    }

    public MonthType getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(MonthType thisMonth) {
        this.thisMonth = thisMonth;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Month other = (Month) obj;
        if (this.numberOfDaysInMonth != other.numberOfDaysInMonth) {
            return false;
        }
        if (this.firstDay != other.firstDay) {
            return false;
        }
        if (this.lastDay != other.lastDay) {
            return false;
        }
        if (!Objects.equals(this.days, other.days)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.numberOfDaysInMonth;
        hash = 17 * hash + (this.firstDay != null ? this.firstDay.hashCode() : 0);
        hash = 17 * hash + (this.lastDay != null ? this.lastDay.hashCode() : 0);
        hash = 17 * hash + Objects.hashCode(this.days);
        return hash;
    }
    
    

    
}
