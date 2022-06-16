package pl.edu.pwsztar.domain.dto;

import io.jsonwebtoken.Jwt;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import javax.persistence.Column;

public class UserRegistrationDto {
	private String firstName;
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String firstName, String password) {


		this.firstName = firstName;
		this.password = password;
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
}
