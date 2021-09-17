/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Usuario
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ServerDto {
    private Long id;
    private String name;
    private String description;
    private String avatar;
}
