package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    private Long id;
    private String status;
    private User user1;
    private User user2;
}
