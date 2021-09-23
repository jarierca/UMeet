package com.umeet.umeet.controller;

import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Channel;
import com.umeet.umeet.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageFileRepository messageFileRepository;

    @GetMapping("/form")
    public String viewCategoryCreation(Model model, Long idCategory, Long idServer){
        Category category = new Category();
        if(idCategory==null){
            category.setServer(serverRepository.findById(idServer).get());
        } else {
            category = categoryRepository.findById(idCategory).get();
        }
        model.addAttribute("category", category);
        return "formCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(Category category, Long idServer){
        category.setServer(serverRepository.findById(idServer).get());
        if(category.getName()==null || ("").equals(category.getName())){
            return "redirect:/server/one?idServer="+category.getServer().getId();
        }
        categoryRepository.save(category);
        return "redirect:/server/one?idServer="+category.getServer().getId();
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(Long idCategory){
        long idServer = categoryRepository.findById(idCategory).get().getServer().getId();
        categoryRepository.deleteById(idCategory);
        return "redirect:/server/one?idServer="+idServer;
    }

}
