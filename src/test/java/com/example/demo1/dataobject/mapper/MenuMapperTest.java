package com.example.demo1.dataobject.mapper;

import com.example.demo1.dataobject.Menu;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void selectMenus() {
        List<Menu> menus = menuMapper.selectMenus();
        log.info("查询出来的数据是={}", menus);
        Assert.assertNotEquals(0, menus.size());
    }

    @Test
    public void addMenu() {

    }

    @Test
    public void deleteMenu() {
        menuMapper.deleteMenu(1);
    }

    @Test
    public void selectTreeMenu() {
        List<Menu> menus = menuMapper.selectTreeMenu(0);
        log.info("查询出来的数据是={}", menus);
        Assert.assertNotEquals(0, menus.size());
    }
}