package org.codetechn.auth.repository;

import org.codetechn.auth.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Integer> {

}
