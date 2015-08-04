package net.g1project.jpatest.repository;


import net.g1project.jpatest.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryDslPredicateExecutor<Product>{
    
}
