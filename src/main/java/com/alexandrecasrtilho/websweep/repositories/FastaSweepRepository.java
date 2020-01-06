package com.alexandrecasrtilho.websweep.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexandrecasrtilho.websweep.domain.UploadFastaSweep;

@Repository
public interface FastaSweepRepository extends JpaRepository<UploadFastaSweep, String> {
	
	

}
