package com.example.demo1.dataobject.mapper;

import com.example.demo1.dataobject.Menu;
import java.util.List;

public interface MenuMapper {

    List<Menu> selectMenus();

    /**
     * 添加菜单
     * @param menu
     */
    void addMenu(Menu menu);

    /**
     * 删除菜单和子菜单
     * @param id
     */
    void deleteMenu(Integer id);


    /**
     * 根据id修改
     * @param id
     * @param menu
     */
    void updateMenu(Integer id,Menu menu);


    /**
     * 查询树形数据 默认从0 开始
     */
    List<Menu> selectTreeMenu(Integer id);
}
