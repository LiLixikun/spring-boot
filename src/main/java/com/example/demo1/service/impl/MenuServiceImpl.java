package com.example.demo1.service.impl;

import com.example.demo1.dataobject.Menu;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import com.example.demo1.repository.MenuRepository;
import com.example.demo1.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repository;

    @Override
    public void addMenu(Menu menu) {
       repository.save(menu);
    }

    @Override
    public void deleteMenu(Integer id) {

        //判断该菜单下面有没有子菜单 如果有都要删除
    }

    @Override
    public Menu findMenuById(Integer id) throws SellException {
        Menu menu=repository.findById(id).orElse(null);
        if(menu==null){
            throw new SellException(ResultEnum.MENU_NO_EXIT);
        }
        return menu;
    }

    @Override
    public List<Menu> getMenus() {
       //查询所有数据
        List<Menu> menus=repository.findAll();
        //查询所有的父级
        List<Menu> pidMenu=findByPidMenu();

        for (Menu menu:pidMenu){
          menu.setChildren(addChildren(menus,menu.getId()));
        }

        return pidMenu;
    }

    /**
     * 递归查询匹配添加到children
     * @param menuList
     * @param id
     * @return
     */
    public static List<Menu> addChildren(List<Menu> menuList,Integer id){
        log.info("【总数据是={},id=】",menuList,id);
        log.info("pid={}",id);
        List<Menu> list=new ArrayList<>();
        for (Menu menu:menuList){
            //如果父和子ID相同
            if(menu.getPid()==id){
                //自己调用自己直到不相同
                List<Menu> newList=addChildren(menuList,menu.getId());
                menu.setChildren(newList);
                list.add(menu);
            }
        }
        return list;
    }

    @Override
    public List<Menu> findByPidMenu() {
       List<Menu> menus=repository.findAll();
        menus=menus.stream().filter(e-> e.getPid()==0).collect(Collectors.toList());
        return menus;
    }
}
