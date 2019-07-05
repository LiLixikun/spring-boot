package com.example.demo1.repository;

import com.example.demo1.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo("123456", "皮蛋瘦肉粥", new BigDecimal(1.2), 100, "很好喝的，又便宜", "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3300305952,1328708913&fm=27&gp=0.jpg", 1, 1);
        ProductInfo restlt = productInfoRepository.save(productInfo);
        System.out.println(restlt);
        Assert.assertNotNull(restlt);
    }

    @Test
    public void findByProductStauts() {
        List<ProductInfo> productInfos = productInfoRepository.findByProductStatus(0);
        Assert.assertEquals(0, productInfos.size());
    }
}