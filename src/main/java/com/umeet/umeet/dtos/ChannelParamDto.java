package com.umeet.umeet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelParamDto {
    private Long id;
    private String name;
    private Long idCategory;
}
