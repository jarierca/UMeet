package com.umeet.umeet.services;

import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.entities.User;
import com.umeet.umeet.interfaces.IFriendService;
import com.umeet.umeet.repositories.FriendRepository;
import com.umeet.umeet.repositories.ProfileRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService implements IFriendService {

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    ProfileRepository profileRepository;

    private List<String> nombres = new ArrayList<>();

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

    //El metodo se llama a si mismo y peta la memoria (Entity User)
    public void deleteFriendCascade(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public List<User> sendFriendList(Long idUser) {

        List<User> aceptados = friendRepository.findByAmigos(idUser, "Aceptado").stream()
                .distinct()
                .collect(Collectors.toList());

        return aceptados;

    }

}
