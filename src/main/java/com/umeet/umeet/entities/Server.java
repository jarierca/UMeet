package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {

    private Long id;
    private String name;
    private String description;
    private String avatar;
    private String status;
    private List<UserServerRole> userServerRole;
    private List<Category> categories;
}
