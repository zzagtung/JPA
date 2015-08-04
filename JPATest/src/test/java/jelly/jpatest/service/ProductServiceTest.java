package jelly.jpatest.service;

import jelly.jpatest.config.JpaConfiguration;
import jelly.jpatest.entity.Category;
import jelly.jpatest.entity.Product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes ={JpaConfiguration.class})
public class ProductServiceTest {
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void testAddCategory() throws Exception {
        
        Category category = new Category();
        category.setName("category 1");
        
        productService.addCategory(category);
        
    }

    @Test
    public void testAddProduct() throws Exception {
        
        Category category = new Category();
        category.setName("category 1");
        
        Product product = new Product();
        product.setName("product name 1");
        //product.setCategory(category);
        //영속성 Context에서 관리하고 있는 객체가 아님.
        
        productService.addProduct(product);
        
    }

    @Test
    public void testAddProductProductLong() throws Exception {
        
        Category category = new Category();
        category.setName("category 1");
        
        productService.addCategory(category);
        
        Product product = new Product();
        product.setName("product name 1");
        productService.addProduct(product, category.getId());
        
    }

    @Test
    public void testAddMapping() throws Exception {
        
        Category category = new Category();
        category.setName("category 1");
        
        productService.addCategory(category);
        
        Product product = new Product();
        product.setName("product name 1");
        
        productService.addProduct(product);
        
        productService.addMapping(product.getId(), category.getId());
        
    }

	@Test
	public void testGetCategory() throws Exception {
        
        Category category = new Category();
        category.setName("category 1");
        productService.addCategory(category);
        
        Product product = new Product();
        product.setName("product name 1");
        productService.addProduct(product, category.getId());
        
        Product product2 = new Product();
        product2.setName("product name 2");
        productService.addProduct(product2, category.getId());
        
        Category categoryProduct = productService.getCategory(category.getId());
        
        try{
        	Assert.assertNotNull(categoryProduct);
            Assert.assertNotNull(categoryProduct.getProductList());
            Assert.assertEquals(2, categoryProduct.getProductList().size());
        } catch (Exception e){
        	e.printStackTrace();
        }
		
	}

    @Test
    public void testGetCategoryProduct() throws Exception {
        
        Category category = new Category();
        category.setName("category 1");
        productService.addCategory(category);
        
        Product product = new Product();
        product.setName("product name 1");
        productService.addProduct(product, category.getId());
        
        Product product2 = new Product();
        product2.setName("product name 2");
        productService.addProduct(product2, category.getId());
        
        Category categoryProduct = productService.getCategoryProduct(category.getId());
        
        Assert.assertNotNull(categoryProduct);
        Assert.assertNotNull(categoryProduct.getProductList());
        Assert.assertEquals(2, categoryProduct.getProductList().size());
        
    }

    @Test
    public void testGetCategoryProduct2() throws Exception {
        
        Category category = new Category();
        category.setName("category 1");
        productService.addCategory(category);
        
        Product product = new Product();
        product.setName("product name 1");
        productService.addProduct(product, category.getId());
        
        Product product2 = new Product();
        product2.setName("product name 2");
        productService.addProduct(product2, category.getId());
        
        Category categoryProduct = productService.getCategoryProduct2(category.getId());
        
        Assert.assertNotNull(categoryProduct);
        Assert.assertNotNull(categoryProduct.getProductList());
        Assert.assertEquals(2, categoryProduct.getProductList().size());
        
    }

    @Test
    public void testCompareProduct() throws Exception {
        
        Product product = new Product();
        product.setName("product name 1");
        productService.addProduct(product);
        
        productService.compareProduct(product.getId());
        
    }

}
