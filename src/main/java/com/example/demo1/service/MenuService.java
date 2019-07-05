package com.example.demo1.service;

import com.example.demo1.dataobject.Menu;

import java.util.List;

public interface MenuService {

    void addMenu(Menu menu);

    void deleteMenu(Integer id);

    Menu findMenuById(Integer id);

    List<Menu> getMenus();

    /**
     * 查询最顶级
     *
     * @return
     */
    List<Menu> findByPidMenu();
}
