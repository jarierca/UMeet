/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.umeet.umeet.feign;

import com.umeet.umeet.entities.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(/*name = "UMeetBack"*/name = "categorias", url= "http://localhost:8082")
@RequestMapping("/b/category")
public interface CategoryFeign {
    
     @GetMapping("/form")
    public Category viewCategoryCreation(Long idCategory, Long idServer);
    
    @PostMapping("/addCategory")
    public Category addCategory(Category category, Long idServer);
    
    @GetMapping("/deleteCategory")
    public Long deleteCategory(Long idCategory);
    
    
}
