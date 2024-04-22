package com.ohgiraffers.crud.menu.controller;

import com.ohgiraffers.crud.menu.model.dto.MenuDTO;
import com.ohgiraffers.crud.menu.model.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

    @GetMapping("/list")
    public String findMenuList(Model model) {

       List<MenuDTO> menuList = menuService.findAllMenu();

       for (MenuDTO menus : menuList) {
           System.out.println("menus = " + menus);
       }

       model.addAttribute("menuList", menuList);

       return "/list";
    }
}
