package com.ae.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.springboot.model.AeEntity;

/**
 * 
 * @author Kamlesh.Singh
 *
 */
@Repository
public interface AeRepository extends JpaRepository<AeEntity, Long> {

}
