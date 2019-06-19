package com.example.demo1.service.impl;

import com.example.demo1.dataobject.Menu;
import com.example.demo1.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class MenuServiceImplTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void addMenu() {
        Menu menu1=new Menu();
        menu1.setId(1);
        menu1.setLink("/link");
        menu1.setMenuIcon("home");
        menu1.setMenuName("测试菜单");
        menu1.setPid(0);
        menuService.addMenu(menu1);
    }

    @Test
    public void deleteMenu() {
    }

    @Test
    public void findMenuById() {
    }

    @Test
    public void getMenus() {
        List<Menu> menus=menuService.getMenus();
        log.info("[【得到的数据是【】=",menus);
        Assert.assertEquals(0,menus.size());
    }

    @Test
    public void findByPidMenu() {
      List<Menu> menus= menuService.findByPidMenu();
      log.info("【查询出来的数据shi={}】",menus);
        Assert.assertEquals(0,menus.size());
    }
}