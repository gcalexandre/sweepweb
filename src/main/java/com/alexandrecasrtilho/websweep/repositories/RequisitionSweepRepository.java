package com.alexandrecasrtilho.websweep.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alexandrecasrtilho.websweep.domain.Cliente;
import com.alexandrecasrtilho.websweep.domain.RequisitionSweep;

@Repository
public interface RequisitionSweepRepository extends JpaRepository<RequisitionSweep, String> {
	
	@Transactional(readOnly=true)
	Page<RequisitionSweep> findByCliente(Cliente cliente, Pageable pageRequest);

	
}
