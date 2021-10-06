
package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String nickName;
    private String email;
    private String pass;
    private String avatar;
    private String status;
    private String codigo;
    private List<UserServerRole> userServerRole;
    private List<Message> messages;
    private List<Friend> friends1;
    private List<Friend> friends2;
    private List<Message> message;

    @Override
    public int hashCode() {
        return 424242424; 
    }
    
    @Override
    public boolean equals(Object obj){
        User u=(User)obj;
        return id.equals(u.id);
    }
}