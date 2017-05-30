package org.bp.lab.simpleblog.repository;

import org.bp.lab.simpleblog.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Category findByName(String name);

}
