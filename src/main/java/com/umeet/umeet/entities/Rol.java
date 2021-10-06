package com.umeet.umeet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rol {

    private Long id;
    private String rol;
    private String privileges;
    private List<UserServerRole> userServerRoles;
}
