package org.bp.lab.simpleblog.repository;

import java.util.Optional;

import org.bp.lab.simpleblog.domain.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {
	
	Optional<Blogger> findByUsername(String username);

}
