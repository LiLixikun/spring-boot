package com.example.demo1.controller;

import com.example.demo1.VO.ResultVo;
import com.example.demo1.dataobject.Menu;
import com.example.demo1.dataobject.mapper.MenuMapper;
import com.example.demo1.service.MenuService;
import com.example.demo1.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    //mybatis 用法
    @Autowired
    private MenuMapper menuMapper;

    @GetMapping("/list")
    public ResultVo getMenuList() {
        return ResultVoUtils.success(menuMapper.selectTreeMenu(0));
    }


    @PostMapping("/save")
    public ResultVo save(@RequestBody Menu menu) {
        //menuService.addMenu(menu);
        menuMapper.addMenu(menu);
        return ResultVoUtils.success();
    }

    @DeleteMapping("/delete/{id}")
    public ResultVo delete(@PathVariable("id") Integer id) {
        menuMapper.deleteMenu(id);
        return ResultVoUtils.success();
    }

    @PostMapping(value = "/update")
    public ResultVo update(@RequestBody Menu menu) {
        menuMapper.updateMenu(menu);
        return ResultVoUtils.success();
    }
}
