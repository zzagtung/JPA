package jelly.jpatest.service;

import javax.persistence.EntityManager;

import jelly.jpatest.entity.Category;
import jelly.jpatest.entity.Product;
import jelly.jpatest.entity.QCategory;
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
        
        //save를 호출할 필요 없음
        //save는 update가 아님
        //productRepository.save(product);
        
        // transaction이 commit()되는 시점에서 
        // entity의 변화를 확인하고 자동으로 update가 실행됨
        
    }
    
    /**
     * Category내부의 productList가 FetchType.LAZY로 동작하는지 확인
     */
    public Category getCategory(Long categoryId){
    	
    	Category category = categoryRepository.findOne(categoryId);
    	
    	// FetchType.LAZY 동작
    	category.getProductList().size();
    	
    	return category;
    }
    
    /**
     * Category 조회 시 Category에 속한 Product의 목록도 함께 가져오기
     */
    public Category getCategoryProduct(Long categoryId){
        
        // QueryDSL 사용
        
        QCategory c = QCategory.category;
        //QProduct p = QProduct.product;
        
        JPAQuery query = new JPAQuery(entityManager);
        
        //return query.from(c).join(c.productList, p).fetch().where(c.id.eq(categoryId)).singleResult(c);
        
        return query.from(c).join(c.productList).fetch().where(c.id.eq(categoryId)).singleResult(c);
    }
    
    /**
     * Category 조회 시 Category에 속한 Product의 목록도 함께 가져오기
     */
    public Category getCategoryProduct2(Long categoryId){
        // jpql 사용
        // @Query("SELECT c FROM Category c JOIN FETCH c.productList WHERE c.id = (:id)")
        return categoryRepository.findByIdWithProductList(categoryId);
        
    }
    
    /**
     * 동일한 key로 select된 두 객체의 비교
     */
    public void compareProduct(Long productId){
        
        Product product1 = productRepository.findOne(productId);
        Product product2 = productRepository.findOne(productId);
        
        System.out.println("레알? " + (product1 == product2));
        
        // 아래의 조건문을 == 으로 대체 가능
        if(product1 != null && product2 != null 
        		&& product1.getId() != null 
        		&& product1.getId().equals(product2.getId())){
        	
        }
        
    }
    
}
