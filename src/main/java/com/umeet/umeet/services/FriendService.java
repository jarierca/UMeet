package com.umeet.umeet.services;

import com.umeet.umeet.interfaces.IFriendService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FriendService implements IFriendService {

    private List<String> nombres = new ArrayList<>();

    @Override
    public List<String> getFriendList() {
        //Devuelve una lista inmutable.
        return nombres;
    }

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
}
