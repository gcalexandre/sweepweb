package com.alexandrecasrtilho.websweep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexandrecasrtilho.websweep.domain.RequisitionMultifasta;

@Repository
public interface RequisitionMultifastaRepository extends JpaRepository<RequisitionMultifasta, String> {
	
	
	
}
