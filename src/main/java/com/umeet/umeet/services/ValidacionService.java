
package com.umeet.umeet.services;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class ValidacionService implements UserDetailsService{
    
    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usuario = userRepository.findByUsername(username);
        
        if (usuario.isPresent()){
            
            UserValidacionDto user = new UserValidacionDto();
            user.setUsername(usuario.get().getUsername());
            user.setPass(usuario.get().getPass());
             
            return user;
        }else{
            throw new UsernameNotFoundException("Usuario/Password incorrecto");
        }
    }
    
}
