package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;

public class UserLoginDto implements Serializable {
    private String firstName;
    private String password;

    public UserLoginDto(){

    }

    public UserLoginDto(String firstName, String password) {


        this.firstName = firstName;
        this.password = password;

    }



    @Override
    public String toString() {
        return "UserLoginDto{" +
                ",first_name='" + firstName + '\'' +
                ", password='" + password+
                '}';
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}



