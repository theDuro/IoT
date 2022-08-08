package pl.edu.pwsztar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final UserService userDetailsService;
    private final DataSource dataSource;

    public SecurityConfiguration(UserService userDetailsService, DataSource dataSource) {
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void initUsers() throws Exception{
        UserRegistrationDto admin = new UserRegistrationDto("dawid", "password", "ADMIN");
        UserRegistrationDto user = new UserRegistrationDto("jas", "a", "USER");
        userDetailsService.addNewUser(admin);
        userDetailsService.addNewUser(user);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/IoT");
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
//                .inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
        .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select first_name, pasword, true from users where first_name=?")
                .authoritiesByUsernameQuery("select first_name, CONCAT('ROLE_', role) from users where first_name=?");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
