package com.umeet.umeet.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryViewDto {
    private Long id;
    private String name;
    private List<ChannelViewDto> channels;
}
