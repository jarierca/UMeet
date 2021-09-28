
package com.umeet.umeet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageChannelDto {
    private Long id;
    private String name;
    private String text;
    private ChannelDto channel;

    private UserDto user; 
    private MessageFileDto messageFile;
    private UserDto userDestiny;
    
}
