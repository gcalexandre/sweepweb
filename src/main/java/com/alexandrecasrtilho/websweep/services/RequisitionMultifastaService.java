package com.alexandrecasrtilho.websweep.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.alexandrecasrtilho.websweep.domain.RequisitionMultifasta;
import com.alexandrecasrtilho.websweep.repositories.RequisitionMultifastaRepository;
import com.alexandrecasrtilho.websweep.services.exceptions.MyFileNotFoundException;

@Service
public class RequisitionMultifastaService {

	@Autowired
	private RequisitionMultifastaRepository repo;

	public RequisitionMultifasta getFile(String fileId){
		return repo.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}
	
	@Query ("SELECT t.title FROM Todo t where t.id = :id")
	public List<RequisitionMultifasta> findRequisitionByIdFile(@Param("fileId") String fileId) {
		return repo.findAll();
	}

	public Page<RequisitionMultifasta > findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);

	}
}