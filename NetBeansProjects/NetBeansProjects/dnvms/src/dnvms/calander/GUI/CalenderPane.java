/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.calander.GUI;
import dnvms.calander.dataObjects.Day;
import dnvms.calander.dataObjects.Month;
import dnvms.calander.dataObjects.Day.DayType;
import dnvms.calander.dataObjects.Month.MonthType;
import dnvms.calander.dataObjects.Year;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Mathew Keegan
 */
public class CalenderPane extends JLayeredPane implements MouseListener{
    
    private JLabel[] dayLabels;
    private final int labelWidth = 7,labelHeight = 6;
    private Year year;
    private JPanel daysPanel;
    private JLabel currentMonthLabel;
    private MonthType currentMonth = Month.MonthType.JANUARY;
    private JLabel previousMonth,nextMonth;
    private Month theMonth;
    
    public CalenderPane(){
        setup();
        
        year = new Year(2012,DayType.SUNDAY);
        theMonth = year.getMonth(currentMonth);        
        initiateLabels();
    }
    
    private void setup(){
        currentMonthLabel = new JLabel(Month.getMonthString(currentMonth),SwingConstants.CENTER);
        currentMonthLabel.setBounds(230,40,140,50);
        this.add(currentMonthLabel);
        
        previousMonth = new JLabel(new ImageIcon("prevMonth.jpg"));
        previousMonth.addMouseListener(this);
        previousMonth.setBounds(150,20,80,80);
        this.add(previousMonth);
        
        nextMonth = new JLabel(new ImageIcon("nextMonth.jpg"));
        nextMonth.addMouseListener(this);
        nextMonth.setBounds(370,20,80,80);
        this.add(nextMonth);
        
        
        
        JLabel mondayLabel = new JLabel("MON",SwingConstants.CENTER);
        JLabel tuesdayLabel = new JLabel("TUE",SwingConstants.CENTER);
        JLabel wednesdayLabel = new JLabel("WED",SwingConstants.CENTER);
        JLabel thursdayLabel = new JLabel("THURS",SwingConstants.CENTER);
        JLabel fridayLabel = new JLabel("FRI",SwingConstants.CENTER);
        JLabel saturdayLabel = new JLabel("SAT",SwingConstants.CENTER);
        JLabel sundayLabel = new JLabel("SUN",SwingConstants.CENTER);
        
        mondayLabel.setBounds(10,140,80,20);
        tuesdayLabel.setBounds(90,140,80,20);
        wednesdayLabel.setBounds(170,140,80,20);
        thursdayLabel.setBounds(250,140,80,20);
        fridayLabel.setBounds(330,140,80,20);
        saturdayLabel.setBounds(410,140,80,20);
        sundayLabel.setBounds(490,140,80,20);
        
        this.add(mondayLabel);
        this.add(tuesdayLabel);
        this.add(wednesdayLabel);
        this.add(thursdayLabel);
        this.add(fridayLabel);
        this.add(saturdayLabel);
        this.add(sundayLabel);
    }
    
    private void initiateLabels(){
        daysPanel = new JPanel(new GridLayout(6, 7));
        daysPanel.setBounds(10,160,560,400);
        this.add(daysPanel);
        dayLabels = null;
        dayLabels = new JLabel[42];
        int i = 0;
        int theDay = 1;
        switch(theMonth.getFirstDay()){
            case MONDAY:
                break;
            case TUESDAY:
                i = 1;
                break;
            case WEDNESDAY:
                i = 2;
                break;
            case THURDAY:
                i = 3;
                break;
            case FRIDAY:
                i = 4;
                break;
            case SATURDAY:
                i = 5;
                break;
            case SUNDAY:
                i = 6;
                break;            
        }
        int startAt = i;
        i = 0;
        for(;i<42;i++){
            dayLabels[i] = new JLabel("",SwingConstants.CENTER);
            dayLabels[i].setBorder(new BevelBorder(1));
            if(theDay <= theMonth.getNumberOfDaysInMonth()&&i>=startAt){
                dayLabels[i].setText(""+(theDay++));
            }
            daysPanel.add(dayLabels[i]);
            
        }
    }
    
    
    public void advanceMonth(){
        switch(currentMonth){
            case JANUARY:
                currentMonth = MonthType.FEBRUARY;
                break;
            case FEBRUARY:
                currentMonth = MonthType.MARCH;
                break;
            case MARCH:
                currentMonth = MonthType.APRIL;
                break;
            case APRIL:
                currentMonth = MonthType.MAY;
                break;
            case MAY:
                currentMonth = MonthType.JUNE;
                break;
            case JUNE:
                currentMonth = MonthType.JULY;
                break;
            case JULY:
                currentMonth = MonthType.AUGUST;
                break;
            case AUGUST:
                currentMonth = MonthType.SEPTEMBER;
                break;
            case SEPTEMBER:
                currentMonth = MonthType.OCTOBER;
                break;
            case OCTOBER:
                currentMonth = MonthType.NOVEMBER;
                break;
            case NOVEMBER:
                currentMonth = MonthType.DECEMBER;
                break;
            case DECEMBER:
                currentMonth = MonthType.JANUARY;
                year.setYear(year.getYear()+1);
                break;
        }
    }
    
    public void backAMonth(){
        switch(currentMonth){
            case JANUARY:
                DayType lastDayOfPreviousYear = year.getLastDay();
                year = new Year(year.getYear()-1,lastDayOfPreviousYear);                
                currentMonth = MonthType.DECEMBER;
                break;
            case FEBRUARY:
                currentMonth = MonthType.JANUARY;
                break;
            case MARCH:
                currentMonth = MonthType.FEBRUARY;
                break;
            case APRIL:
                currentMonth = MonthType.MARCH;
                break;
            case MAY:
                currentMonth = MonthType.APRIL;
                break;
            case JUNE:
                currentMonth = MonthType.MAY;
                break;
            case JULY:
                currentMonth = MonthType.JUNE;
                break;
            case AUGUST:
                currentMonth = MonthType.JULY;
                break;
            case SEPTEMBER:
                currentMonth = MonthType.AUGUST;
                break;
            case OCTOBER:
                currentMonth = MonthType.SEPTEMBER;
                break;
            case NOVEMBER:
                currentMonth = MonthType.OCTOBER;
                break;
            case DECEMBER:
                currentMonth = MonthType.NOVEMBER;
                break;
        }
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==previousMonth){
            backAMonth();
            currentMonthLabel.setText(Month.getMonthString(currentMonth));
        }else if(e.getSource()==nextMonth){
            advanceMonth();
            currentMonthLabel.setText(Month.getMonthString(currentMonth));
        }
        theMonth = year.getMonth(currentMonth);
        initiateLabels();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
