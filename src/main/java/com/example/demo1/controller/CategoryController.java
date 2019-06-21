package com.example.demo1.controller;

import com.example.demo1.VO.ResultVo;
import com.example.demo1.dataobject.ProductCategory;
import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.SellException;
import com.example.demo1.service.CategoryService;
import com.example.demo1.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResultVo getProductCategory() {
        List<ProductCategory> productCategoryList = categoryService.findAll();
        return ResultVoUtils.success(productCategoryList);
    }

    @GetMapping("/category/{categoryId}")
    public ResultVo findById(@PathVariable("categoryId") Integer categoryId){
        return ResultVoUtils.success(categoryService.findById(categoryId));
    }

    @PostMapping("/category")
    public ResultVo add(@RequestBody ProductCategory productCategory) {
        ProductCategory productCategory1 = new ProductCategory(productCategory.getCategoryName(), productCategory.getCategoryType());
        categoryService.save(productCategory1);
        return ResultVoUtils.success();
    }

    @PutMapping("/category/{categoryId}")
    public ResultVo updata(@PathVariable("categoryId") Integer categoryId,@RequestBody ProductCategory productCategory) throws SellException {
        ProductCategory category = categoryService.findById(categoryId);
        if (category == null) {
            throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
        }
        BeanUtils.copyProperties(productCategory, category);
        categoryService.save(category);
        return ResultVoUtils.success();
    }

    @DeleteMapping("/category/{categoryId}")
    public ResultVo delete(@PathVariable("categoryId") Integer categoryId) throws SellException {
        ProductCategory productCategory = categoryService.findById(categoryId);
        log.info("查询出来的数据是={}", productCategory);
        if (productCategory == null) {
            throw new SellException(ResultEnum.PRODUCT_NO_EXIT);
        }
        categoryService.deleteById(categoryId);
        return ResultVoUtils.success();
    }
}
