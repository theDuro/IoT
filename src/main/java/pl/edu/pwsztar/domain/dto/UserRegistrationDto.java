package pl.edu.pwsztar.domain.dto;

import io.jsonwebtoken.Jwt;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import javax.persistence.Column;
import java.io.Serializable;

public class UserRegistrationDto implements Serializable {
	private String firstName;
	private String password;
	private String role;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String firstName, String password, String role) {


		this.firstName = firstName;
		this.password = password;
		this.role = role;
	}



	@Override
	public String toString() {
		return "UserRegistrationDto{" +
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
