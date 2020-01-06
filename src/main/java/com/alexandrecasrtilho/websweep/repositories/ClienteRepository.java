package com.alexandrecasrtilho.websweep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alexandrecasrtilho.websweep.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

	
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
	
	
}
