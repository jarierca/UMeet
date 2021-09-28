
package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String nickName;
    private String email;
    private String pass;
    private String avatar;
    private String status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserServerRole> userServerRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Message> messages;

    @OneToMany(mappedBy = "user1", cascade = CascadeType.REMOVE)
    private List<Friend> friends1;

    @OneToMany(mappedBy = "user2", cascade = CascadeType.REMOVE)
    private List<Friend> friends2;

    @OneToMany(mappedBy = "userDestiny", cascade = CascadeType.REMOVE)
    private List<Message> message;
}
