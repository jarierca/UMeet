
package com.umeet.umeet.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListasFriendDto {
    private List<UserDto> friendsAccepted;
    private List<UserDto> friendsPendingEnviado;
    private List<UserDto> friendsPendingRecibido;
}
