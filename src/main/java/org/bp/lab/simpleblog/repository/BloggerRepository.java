package org.bp.lab.simpleblog.repository;

import org.bp.lab.simpleblog.domain.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {

}
