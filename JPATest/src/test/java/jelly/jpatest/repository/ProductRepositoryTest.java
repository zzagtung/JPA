package jelly.jpatest.repository;

import java.util.List;

import jelly.jpatest.config.JpaConfiguration;
import jelly.jpatest.entity.Product;
import jelly.jpatest.repository.ProductRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={JpaConfiguration.class})
public class ProductRepositoryTest {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Test
    public void testFindAll() throws Exception {
        
        Product product = new Product();
        product.setName("product name 1");
        productRepository.save(product);
        
        Product product2 = new Product();
        product2.setName("product name 2");
        productRepository.save(product2);
        
        List<Product> list = productRepository.findAll();
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
        
    }

    @Test
    public void testSave() throws Exception {
        Product product = new Product();
        product.setName("product name 1");
        productRepository.save(product);
    }

}
