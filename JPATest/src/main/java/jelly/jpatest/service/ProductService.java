package jelly.jpatest.service;

import javax.persistence.EntityManager;

import jelly.jpatest.entity.Category;
import jelly.jpatest.entity.Product;
import jelly.jpatest.entity.QCategory;
import jelly.jpatest.entity.QProduct;
import jelly.jpatest.repository.CategoryRepository;
import jelly.jpatest.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.impl.JPAQuery;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    /**
     * 등록
     */
    public void addCategory(Category category){
        
        categoryRepository.save(category);
        
    }
    
    /**
     * 등록
     */
    public void addProduct(Product product){
        
        productRepository.save(product);
        
    }
    
    /**
     * Product와 Category의 관계 설정하여 product 등록
     */
    public void addProduct(Product product, Long categoryId){
        
        Category category = categoryRepository.findOne(categoryId);
        
        product.setCategory(category);
        
        productRepository.save(product);
        
    }
    
    /**
     * 이미 등록된 Product와 Category의 관계 설정하여 Product 업데이트
     */
    public void addMapping(Long productId, Long categoryId){
        
        Category category = categoryRepository.findOne(categoryId);
        
        Product product = productRepository.findOne(productId);
        
        product.setCategory(category);
        
        productRepository.save(product);
        
    }
    
    /**
     * Category 조회 시 Category에 속한 Product의 목록도 함께 가져오기
     */
    public Category getCategoryProduct(Long categoryId){
        
        // QueryDSL 사용
        
        QCategory c = QCategory.category;
        QProduct p = QProduct.product;
        
        JPAQuery query = new JPAQuery(entityManager);
        
        return query.from(c).join(c.productList, p).fetch().where(c.id.eq(categoryId)).singleResult(c);
    }
    
    /**
     * Category 조회 시 Category에 속한 Product의 목록도 함께 가져오기
     */
    public Category getCategoryProduct2(Long categoryId){
        // jpql 사용
        // @Query("SELECT c FROM Category c JOIN FETCH c.productList WHERE c.id = (:id)")
        return categoryRepository.findByIdWithProductList(categoryId);
        
    }
    
    public void getProductTest(Long productId){
        
        Long id = productId;
        
        Product product1 = productRepository.findOne(id);
        Product product2 = productRepository.findOne(id);
        
        System.out.println("레알? " + (product1 == product2));
        
    }
    
}
