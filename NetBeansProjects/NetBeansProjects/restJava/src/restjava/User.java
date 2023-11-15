/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package restjava;

/**
 *
 * @author Fatblack
 */
public class User {
    
    private String forname,
                   surname,
                   phone,
                   gradYear,
                   email,
                   login,
                   password;
    private int id;

    public User(String forname, String surname, String phone, String gradYear, String email, String login, String password) {
        this.forname = forname;
        this.surname = surname;
        this.phone = phone;
        this.gradYear = gradYear;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "forname=" + forname + ", surname=" + surname + ", phone=" + phone + ", gradYear=" + gradYear + ", email=" + email + ", login=" + login + ", password=" + password + ", id=" + id + '}';
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getForname() {
        return forname;
    }

    public void setForname(String forname) {
        this.forname = forname;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
