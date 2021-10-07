package com.umeet.umeet.services;

import com.umeet.umeet.dtos.UserDto;
import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.feign.ProfileFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidacionService implements UserDetailsService{

    @Autowired
    ProfileFeign profileFeign;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String valores[] = username.split("##_##");
        String sUsername = valores[0];
        String sOAuth = "user";
        if (valores.length > 1) {
            sOAuth = valores[1];
        }
        UserDto user = profileFeign.getUserByUsernameAndOAuth2(sUsername, sOAuth);
//        UserDto user = profileFeign.getUserByUsername(username);
        UserDto user2 = user;
        if (user.getId()!=null){
            
            UserValidacionDto userValidation = new UserValidacionDto();
            userValidation.setId(user.getId());
            userValidation.setOauth(sOAuth);
            userValidation.setUsername(user.getUsername());
            userValidation.setPassword(user.getPass());
            List<GrantedAuthority> lista=new ArrayList<GrantedAuthority>();
            lista.add(new SimpleGrantedAuthority("ROLE_Usuario"));
            userValidation.setRoles(lista);
             
            return userValidation;
        }else{
            throw new UsernameNotFoundException("Usuario/Password incorrecto");
        }
    }
    
}