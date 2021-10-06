package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Long id;
    private String name;
    private String text;
    private Channel channel;
    private User user;
    private MessageFile messageFile;
    private User userDestiny;
    

}