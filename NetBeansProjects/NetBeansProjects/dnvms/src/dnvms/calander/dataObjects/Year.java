/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.calander.dataObjects;

import dnvms.calander.dataObjects.Day.DayType;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Mathew Keegan
 */
public class Year {
    
    private int year = 2012;
    private Day firstDay;
    private Day.DayType lastDay;
    
    private ArrayList<Month> months;

    public Year(int theYear,Day.DayType firstDayType) {
        firstDay = new Day(new Date(theYear,1,1), firstDayType);
        months = new ArrayList<>();
        setupYear();
        
        setLastDay();
    }
    
    public final void setupYear(){
        months.clear();
        Month january = new Month(firstDay.getThisDay(),Month.MonthType.JANUARY);
        Month february = new Month(Day.getNextDay(january.getLastDay()), Month.MonthType.FEBRUARY);
        Month march = new Month(Day.getNextDay(february.getLastDay()), Month.MonthType.MARCH);
        Month april = new Month(Day.getNextDay(march.getLastDay()), Month.MonthType.APRIL);
        Month may = new Month(Day.getNextDay(april.getLastDay()), Month.MonthType.MAY);
        Month june = new Month(Day.getNextDay(may.getLastDay()), Month.MonthType.JUNE);
        Month july = new Month(Day.getNextDay(june.getLastDay()), Month.MonthType.JULY);
        Month august = new Month(Day.getNextDay(july.getLastDay()), Month.MonthType.AUGUST);
        Month september = new Month(Day.getNextDay(august.getLastDay()), Month.MonthType.SEPTEMBER);
        Month october = new Month(Day.getNextDay(september.getLastDay()), Month.MonthType.OCTOBER);
        Month november = new Month(Day.getNextDay(october.getLastDay()), Month.MonthType.NOVEMBER);
        Month december = new Month(Day.getNextDay(november.getLastDay()), Month.MonthType.DECEMBER);
        
        months.add(january);
        months.add(february);
        months.add(march);
        months.add(april);
        months.add(may);
        months.add(june);
        months.add(july);
        months.add(august);
        months.add(september);
        months.add(october);
        months.add(november);
        months.add(december);
    }
    
    private void setLastDay(){
        LinkedList<Day.DayType> theWeek = Day.getDays();
        Day.DayType tempDay = Day.DayType.MONDAY;
        for(int i = 0;i<7;i++){
            tempDay = theWeek.poll();
            theWeek.add(tempDay);
            if(tempDay == firstDay.getThisDay())break;
        }
        for(int i = 0;i<364;i++){
            tempDay = theWeek.poll();
            theWeek.add(tempDay);            
        }
        lastDay = tempDay;        
    }
    
    public Month getMonth(Month.MonthType currntMonth){
        Month returnMonth = null;
        switch(currntMonth){
            case JANUARY:
                returnMonth = months.get(0);
                break;
            case FEBRUARY:
                returnMonth = months.get(1);
                break;
            case MARCH:
                returnMonth = months.get(2);
                break;
            case APRIL:
                returnMonth = months.get(3);
                break;
            case MAY:
                returnMonth = months.get(4);
                break;
            case JUNE:
                returnMonth = months.get(5);
                break;
            case JULY:
                returnMonth = months.get(6);
                break;
            case AUGUST:
                returnMonth = months.get(7);
                break;
            case SEPTEMBER:
                returnMonth = months.get(8);
                break;
            case OCTOBER:
                returnMonth = months.get(9);
                break;
            case NOVEMBER:
                returnMonth = months.get(10);
                break;
            case DECEMBER:
                returnMonth = months.get(11);
                break;
        }
        return returnMonth;
    }

    public Day getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(Day firstDay) {
        this.firstDay = firstDay;
    }

    public DayType getLastDay() {
        return lastDay;
    }

    public void setLastDay(DayType lastDay) {
        this.lastDay = lastDay;
    }
    
    

    public ArrayList<Month> getMonths() {
        return months;
    }

    public void setMonths(ArrayList<Month> months) {
        this.months = months;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Year other = (Year) obj;
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.months, other.months)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.year;
        hash = 19 * hash + Objects.hashCode(this.months);
        return hash;
    }

    @Override
    public String toString() {
        return "Year{" + "year=" + year + ", months=" + months + '}';
    }
    
    
    
}
