package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String text;

    @ManyToOne
    @JoinColumn(name = "id_channel")
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToOne(mappedBy = "message")
    private MessageFile messageFile;

    @ManyToOne
    @JoinColumn(name = "id_user_destiny")
    private User userDestiny;
    

}
