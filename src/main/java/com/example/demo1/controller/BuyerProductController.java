package com.example.demo1.controller;

import com.example.demo1.VO.ProductInfoVo;
import com.example.demo1.VO.ProductVo;
import com.example.demo1.VO.ResultVo;
import com.example.demo1.dataobject.ProductCategory;
import com.example.demo1.dataobject.ProductInfo;
import com.example.demo1.repository.ProductCategoryRepository;
import com.example.demo1.service.CategoryService;
import com.example.demo1.service.ProductInfoService;
import com.example.demo1.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ResultVo list() {
        ResultVo resultVo = new ResultVo();

        //查询所有上架产品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //查询类目
//        List<Integer> categoryTypes=new ArrayList<>();
//        //传统方法
//        for (ProductInfo info:productInfoList){
//            categoryTypes.add(info.getCategoryType());
//        }

        //精简方法
        List<Integer> categoryTypes = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        //查询所有在售类别
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypes);

        //数据拼装
        List<ProductVo> productVoList = new ArrayList<>();

        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                //判断在售商品和类别是否相同
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }

            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }

        return ResultVoUtils.success(productVoList);
    }
}
