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
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(contextId = "feignCategorias", name = "UMeetBack", url= "http://localhost:8082")
@RequestMapping("/b/category")
public interface CategoryFeign {
    
     @PostMapping("/form")
    public Category viewCategoryCreation(@RequestParam Long idCategory, @RequestParam Long idServer);
    
    @PostMapping("/addCategory")
    public Category addCategory(@RequestParam Long id, @RequestParam String name, @RequestParam Long idServer);
    
    @GetMapping("/deleteCategory")
    public Long deleteCategory(@RequestParam Long idCategory);
    
    
}
