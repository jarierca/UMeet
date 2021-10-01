package com.umeet.umeet.controller;

import com.umeet.umeet.dtos.CategoryDto;
import com.umeet.umeet.dtos.ServerDto;
import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Server;
import com.umeet.umeet.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/b/category")
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

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{idCategory}")
    public CategoryDto getCategory(@PathVariable Long idCategory){
        Category category = categoryRepository.findById(idCategory).get();
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @PostMapping("/addCategory")
    public CategoryDto addCategory(CategoryDto categoryDto, Long idServer) {
        Category category = mapper.map(categoryDto, Category.class);
        Server server = serverRepository.findById(idServer).get();
        category.setServer(server);
        category = categoryRepository.save(category);
        categoryDto = mapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @GetMapping("/deleteCategory")
    public Long deleteCategory(Long idCategory) {
        long idServer = categoryRepository.findById(idCategory).get().getServer().getId();
        categoryRepository.deleteById(idCategory);
        return idServer;
    }
}
