package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Category;
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
        Category category = catFeign.viewCategoryCreation(idCategory, idServer);

        model.addAttribute("category", category);
        return "formCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(Category category, Long idServer) {

        Category cat = catFeign.addCategory(category.getId(), category.getName(),idServer);

        return "redirect:server/one?idServer=" + cat.getServer().getId();
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(Long idCategory) {

        Long idServer = catFeign.deleteCategory(idCategory);

        return "redirect:server/one?idServer=" + idServer;
    }

}
