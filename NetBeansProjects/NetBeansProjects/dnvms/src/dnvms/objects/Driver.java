/*
 * This class is used to store driver information.  
 */
package dnvms.objects;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author fatblacktone
 */
public class Driver {
   
    //Instance variables
    private String name = "";
    private String address = "";
    private String dob = "";
    private String licenseNumber = "";
    private String driverNumber = "";
    private String run = "";
    private File driverPhoto;
    private DebriefData debriefData;

    
    private Van van;
    
    /**
     * This Constructor creates a blank driver instance
     */
    
    public Driver(){
        
    }
    
    public Driver(String name,String address,String dob,String licenseNumber,String driverNumber,String run,String photoPath){
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.licenseNumber = licenseNumber;
        this.driverNumber = driverNumber;
        this.run = run;
        this.driverPhoto = new File(photoPath);
        debriefData = new DebriefData();
    }

    public File getDriverPhoto() {
        return driverPhoto;
    }

    public void setDriverPhoto(File driverPhoto) {
        this.driverPhoto = driverPhoto;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Van getVan() {
        return van;
    }

    public void setVan(Van van) {
        this.van = van;
    }
    
    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public DebriefData getDebriefData() {
        return debriefData;
    }

    public void setDebriefData(DebriefData debriefData) {
        this.debriefData = debriefData;
    }
    
    
    
    
}
