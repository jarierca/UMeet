package com.umeet.umeet.interfaces;

import com.umeet.umeet.entities.User;
import java.util.List;


public interface IFriendService {
    /**
     * Devuelve una lista con los nombres.
     */
    public List<String> getFriendList();
    public List<User> sendFriendList(Long idUser);
    
}

