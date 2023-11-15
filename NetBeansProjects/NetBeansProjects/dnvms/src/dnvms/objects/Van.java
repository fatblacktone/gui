/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.objects;

/**
 *
 * @author Mathew Keegan
 */
class Van {
    
    private String reg = "";
    private String make = "";
    private int serviceMilage = 0;

    public Van() {
        
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public int getServiceMilage() {
        return serviceMilage;
    }

    public void setServiceMilage(int serviceMilage) {
        this.serviceMilage = serviceMilage;
    }
    
    
    
    
    
}
