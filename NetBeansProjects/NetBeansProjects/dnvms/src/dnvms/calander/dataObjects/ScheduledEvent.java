/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.calander.dataObjects;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Mathew Keegan
 */
public class ScheduledEvent {
    
    private Date date;
    private String title;
    private String notes;

    public ScheduledEvent(Date date, String title, String notes) {
        this.date = date;
        this.title = title;
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ScheduledEvent other = (ScheduledEvent) obj;
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.date);
        hash = 73 * hash + Objects.hashCode(this.title);
        hash = 73 * hash + Objects.hashCode(this.notes);
        return hash;
    }

    @Override
    public String toString() {
        return "ScheduledEvent{" + "date=" + date + ", title=" + title + ", notes=" + notes + '}';
    }
    
    
    
}
