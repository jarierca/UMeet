package com.umeet.umeet.services;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.interfaces.IFriendService;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.ProfileRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService implements IFriendService{
    private List<String> nombres=new ArrayList<>();

    @Autowired
    FriendRepository friendRepository;
    
    @Autowired
    ProfileRepository profileRepository;
    
    /*
    @Override
    public void altaNombre(String nombre) throws OperacionEnListaException {
        if (nombre==null){ //Desde el exterior se debe comprobar si vale nulo.
            throw new NullPointerException();
        }
        if (nombres.stream().noneMatch(x->x.equals(nombre))){
            nombres.add(nombre);
        }else{
            throw new OperacionEnListaException(nombre);
        }
    }

    @Override
    public void bajaNombre(String nombre) {
        nombres.remove(nombre);
    }
*/
    @Override
    public List<String> getFriendList() {
        //Devuelve una lista inmutable.
        return Collections.unmodifiableList(nombres);
    }
    
    public void deleteFriendCascade(Long id) {
        User user = profileRepository.findById(id).get();
        
        List<Friend> friends1 = user.getFriends1();
        
        friends1.stream().forEach(x -> System.out.println(x));
        //friends1.stream().forEach(x->friendRepository.deleteByUser1(id));
        
        List<Friend> friends2 = friendRepository.findFriendByUser2(profileRepository.findById(id).get());
        //friends2.stream().forEach(x -> System.out.println(x));
       // friends2.stream().forEach(x->friendRepository.deleteByUser2(id));
        
       //profileRepository.deleteById(id);
    }
}
