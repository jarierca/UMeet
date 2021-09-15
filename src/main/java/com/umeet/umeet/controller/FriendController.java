package com.umeet.umeet.controller;

import com.umeet.umeet.services.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friends")

public class FriendController {

//@Autowired
private IFriendService service;

//List friends
@GetMapping("")
public String listFriends (Model m){
    m.addAttribute("friends", service.getFriendList());
    return "friends";
}

//Filter friends


//Invite friends (este igual va en el users??)
    
}
