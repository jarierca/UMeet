
package com.umeet.umeet.services;

import com.umeet.umeet.dtos.UserValidacionDto;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
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
            List<GrantedAuthority> lista=new ArrayList<GrantedAuthority>();
            lista.add(new SimpleGrantedAuthority("ROLE_Usuario"));
            user.setRoles(lista);
             
            return user;
        }else{
            throw new UsernameNotFoundException("Usuario/Password incorrecto");
        }
    }
    
}
