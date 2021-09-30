package com.umeet.umeet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewServerDto {
    private ServerDto server;
    private List<CategoryViewDto> categories;
    private UserServerRolDto usr;
    private List<UserServerRolDto> userServerRoles;
}