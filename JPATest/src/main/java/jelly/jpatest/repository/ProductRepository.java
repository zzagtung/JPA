package jelly.jpatest.repository;


import jelly.jpatest.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product>{
    
}
