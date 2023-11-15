/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restclientv1;

/**
 *
 * @author mathewkeegan
 */
public class User {
    
    private int id;
    private String forename, surname, phone, gradYear, email, username, password;    
    private Boolean jobs;
    
    public User(String forename, String surname, String phone, String gradYear, Boolean jobs, String email, String username, String password) {
        this.forename = forename;
        this.surname = surname;
        this.phone = phone;
        this.gradYear = gradYear;
        this.jobs = jobs;
        this.email = email;
        this.username = username;
        this.password = password;        
    }
    
    public User(String forename, String surname, String phone, String gradYear, Boolean jobs, String email, String username, String password, int id) {
        this.forename = forename;
        this.surname = surname;
        this.phone = phone;
        this.gradYear = gradYear;
        this.jobs = jobs;
        this.email = email;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGradYear() {
        return gradYear;
    }

    public void setGradYear(String gradYear) {
        this.gradYear = gradYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getJobs() {
        return jobs;
    }

    public void setJobs(Boolean jobs) {
        this.jobs = jobs;
    }
             
    @Override
    public String toString() {
        return "User{" + "forname=" + forename + ", surname=" + surname + ", phone=" + phone + ", gradYear=" + gradYear + ", jobs=" + jobs + ", email=" + email + ", login=" + username + ", password=" + password + ", id=" + id + '}';
    }
}
