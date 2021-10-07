
package com.umeet.umeet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
    
public class UserDto {
    private Long id;
    private String nickName;
    private String username;
    private String email;
    private String pass;
    private String avatar;
    private String status;
    private String codigo;
    private String oauth2;
}
