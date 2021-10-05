package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.CategoryDto;
import com.umeet.umeet.dtos.CategoryParamDto;
import com.umeet.umeet.feign.CategoryFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryFeign catFeign;

    @GetMapping("/form")
    public String viewCategoryCreation(Model model, Long idCategory, Long idServer) {
        CategoryDto categoryDto = new CategoryDto();
        if(idCategory != null){
            categoryDto = catFeign.getCategory(idCategory);
        }
        model.addAttribute("category", categoryDto);
        model.addAttribute("idServer", idServer);
        return "formCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(CategoryParamDto category) {
        if(category.getName()==null || ("").equals(category.getName())){
            return "redirect:/server/one?idServer=" + category.getIdServer();
        }
        CategoryDto category2 = catFeign.addCategory(category);
        return "redirect:/server/one?idServer=" + category2.getServer().getId();
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(Long idCategory) {
        Long idServer = catFeign.deleteCategory(idCategory);
        return "redirect:/server/one?idServer=" + idServer;
    }
}
