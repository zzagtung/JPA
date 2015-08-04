package net.g1project.jpatest.repository;

import net.g1project.jpatest.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long>, QueryDslPredicateExecutor<Category>{
    
    @Query("SELECT c FROM Category c JOIN FETCH c.productList WHERE c.id = (:id)")
    Category findByIdWithProductList(@Param("id") Long id);
    
}
