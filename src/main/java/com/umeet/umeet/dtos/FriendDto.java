package com.umeet.umeet.dtos;

import com.umeet.umeet.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendDto {
    private Long id;
    private String status;
    private UserDto user1;
    private UserDto user2;
}
