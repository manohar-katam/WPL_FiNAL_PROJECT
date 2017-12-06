package com.wpl.gift.model;

/**
 * Author Sneha
 */
public class RegistrationBean {

    private String username;

    private String password;

    private String passwordagain;

    private String firstname;

    private String lastname;

    private String securityquestion;

    private String securityanswer;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordagain() { return passwordagain; }

    public void setPasswordagain(String passwordagain) { this.passwordagain = passwordagain; }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSecurityquestion() {
        return securityquestion;
    }

    public void setSecurityquestion(String securityquestion) {
        this.securityquestion = securityquestion;
    }

    public String getSecurityanswer() {
        return securityanswer;
    }

    public void setSecurityanswer(String securityanswer) {
        this.securityanswer = securityanswer;
    }
}
