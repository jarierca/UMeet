/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.feign;

import com.umeet.umeet.dtos.CategoryDto;
import com.umeet.umeet.dtos.CategoryParamDto;
import com.umeet.umeet.entities.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@FeignClient(contextId = "feignCategorias", name = "UMeetBack", url= "http://localhost:8082")
@RequestMapping("/b/category")
public interface CategoryFeign {
    
     @PostMapping("/form")
    public CategoryDto viewCategoryCreation(@RequestParam Long idCategory, @RequestParam Long idServer);
    
//    @PostMapping("/addCategory")
//    public CategoryDto addCategory(@RequestParam Long id, @RequestParam String name, @RequestParam Long idServer);

    @PostMapping("/addCategory")
    public CategoryDto addCategory(@SpringQueryMap CategoryParamDto categoryDto);

    @GetMapping("{idCategory}")
    public CategoryDto getCategory(@PathVariable Long idCategory);

    @GetMapping("/deleteCategory")
    public Long deleteCategory(@RequestParam Long idCategory);
    
    
}
