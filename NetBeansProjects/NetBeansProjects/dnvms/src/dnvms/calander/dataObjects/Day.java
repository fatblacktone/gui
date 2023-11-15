/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.calander.dataObjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Objects;
import sun.misc.Queue;

/**
 *
 * @author Mathew Keegan
 */
public class Day {
    
    private Date date;
    private ArrayList<ScheduledEvent> events;
    public static enum DayType{
        MONDAY,TUESDAY,WEDNESDAY,THURDAY,FRIDAY,SATURDAY,SUNDAY
    };    
    private DayType thisDay;

    public Day(Date date, DayType thisDay) {
        this.date = date;
        this.thisDay = thisDay;
        events = new ArrayList<>();
        
    }
    
    public static LinkedList<DayType> getDays(){
        LinkedList<DayType> theWeek = new LinkedList<>();
        theWeek.add(DayType.MONDAY);
        theWeek.add(DayType.TUESDAY);
        theWeek.add(DayType.WEDNESDAY);
        theWeek.add(DayType.THURDAY);
        theWeek.add(DayType.FRIDAY);
        theWeek.add(DayType.SATURDAY);
        theWeek.add(DayType.SUNDAY);
        return theWeek;
    }
    
    public static DayType getNextDay(DayType currentDay){
        LinkedList<DayType> theDays = getDays();
        DayType tempDay = DayType.MONDAY;
        boolean currentDayFound = false;
        for(DayType day:theDays){
            tempDay = day;
            if(tempDay == currentDay){
                currentDayFound = true;
              
            }else if(currentDayFound){
                break;
            }
            
        }
        
        return tempDay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<ScheduledEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<ScheduledEvent> events) {
        this.events = events;
    }

    public DayType getThisDay() {
        return thisDay;
    }

    public void setThisDay(DayType thisDay) {
        this.thisDay = thisDay;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Day other = (Day) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.events, other.events)) {
            return false;
        }
        if (this.thisDay != other.thisDay) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.events);
        hash = 79 * hash + (this.thisDay != null ? this.thisDay.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Day{" + "date=" + date + ", events=" + events + ", thisDay=" + thisDay + '}';
    }
    
    
    
    
    
}
