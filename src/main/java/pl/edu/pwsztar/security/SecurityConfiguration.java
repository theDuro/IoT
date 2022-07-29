package pl.edu.pwsztar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.pwsztar.domain.dto.UserRegistrationDto;
import pl.edu.pwsztar.service.serviceImpl.UserService;

import javax.annotation.PostConstruct;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userDetailsService;

    public SecurityConfiguration(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    public void initUsers() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto("dawid", "password");
        userDetailsService.addNewUser(userRegistrationDto);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/getNickFromId/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/comands").permitAll()
                .antMatchers(HttpMethod.GET,"/gui/commands").permitAll()
                .antMatchers(HttpMethod.GET,"/gui/commands/add").permitAll()
                .antMatchers(HttpMethod.GET,"/gui/commands/{commandId}/edit").permitAll()
                .antMatchers(HttpMethod.GET,"/gui/commands/{categoryId}/delete").permitAll()
                .antMatchers(HttpMethod.GET,"/comandForIot").permitAll()
                .antMatchers(HttpMethod.POST,"/gui/commands/save").permitAll()
                .antMatchers(HttpMethod.POST,"/comands").permitAll()
                .antMatchers(HttpMethod.POST,"/comands/{expire}").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET,"/redis").permitAll()
                .antMatchers(HttpMethod.POST, "/api/registration").permitAll()
                .antMatchers(HttpMethod.POST, "/api/registration").permitAll()
                .antMatchers("/h2-console").permitAll()//todo spawdz
                .antMatchers("/console").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager()))
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
