/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author skudo
 */
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

    @OneToMany(mappedBy = "user")
    private List<UserServerRole> userServerRole;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @OneToMany(mappedBy = "user1")
    private List<Friend> friends1;

    @OneToMany(mappedBy = "user2")
    private List<Friend> friends2;

    @OneToMany(mappedBy = "userDestiny")
    private List<Message> message;
}
