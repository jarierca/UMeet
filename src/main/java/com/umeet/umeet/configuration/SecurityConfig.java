
package com.umeet.umeet.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService validacion;    
    
    //Aquí se configura el acceso 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Solo permite el acceso a la url login y register, 
        //para acceder a cualquier otra se necesita estar autentificado
        
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/newregister").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/*.png").permitAll()
                .antMatchers("/*").hasAnyRole("Usuario")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .deleteCookies("idUser")
                .deleteCookies("idServer")
                .deleteCookies("remember-me")
                .and()
            .rememberMe().key("uniqueAndSecret");

         /*   
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/newregister").permitAll()
         .antMatchers("/register").permitAll()
         .antMatchers("/login").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .defaultSuccessUrl("/profile")
         .failureUrl("/login?error=true");*/
    }

    
    //Aquí se configura Usuario/Password
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(validacion).passwordEncoder(passwordEncoder());
    }
     
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }  
}
