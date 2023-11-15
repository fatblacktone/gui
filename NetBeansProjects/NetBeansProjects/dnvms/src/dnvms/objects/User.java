/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dnvms.objects;

/**
 *
 * @author Mathew Keegan
 */
public class User {
    
    private String userName;
    private String password;

    
    public static enum UserType {
        USER,MANAGER,ADMIN,SUPER
    } ;
    
    private UserType clearance;
    
    public User(){
        
    }
    
    public User(String userName,String password,int secLevel){
        this.userName = userName;
        this.password = password;
        switch(secLevel){
            case 1:
                clearance = UserType.SUPER;
                break;
            case 2:
                clearance = UserType.ADMIN;
                break;
            case 3:
                clearance = UserType.USER;
                break;
            case 4:
                clearance = UserType.MANAGER;
                break;
        }
    }
    
    public String getClearenceString() {
        String output = "";
        switch(clearance){
            case ADMIN:
                output = "ADMIN";
                break;
            case USER:
                output = "USER";
                break;
            case MANAGER:
                output = "MANAGER";
                break;                
        }
        return output;
    }

    public UserType getClearance() {
        return clearance;
    }

    public void setClearance(UserType clearance) {
        this.clearance = clearance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
