package com.alexandrecasrtilho.websweep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.alexandrecasrtilho.websweep.domain.Cliente;
import com.alexandrecasrtilho.websweep.domain.RequisitionSweep;
import com.alexandrecasrtilho.websweep.repositories.RequisitionSweepRepository;
import com.alexandrecasrtilho.websweep.security.UserSS;
import com.alexandrecasrtilho.websweep.services.exceptions.AuthorizationException;
import com.alexandrecasrtilho.websweep.services.exceptions.DataIntegrityException;
import com.alexandrecasrtilho.websweep.services.exceptions.MyFileNotFoundException;
import com.alexandrecasrtilho.websweep.services.exceptions.ObjectNotFoundException;

@Service
public class RequisitionSweepService {

	@Autowired
	private RequisitionSweepRepository repo;

	@Autowired
	private ClienteService clienteService;
	
		

	public RequisitionSweep getFile(String fileId) {
		return repo.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}

	@Query("SELECT t.title FROM Todo t where t.id = :id")
	public List<RequisitionSweep> findRequisitionByIdFile(@Param("fileId") String fileId) {
		return repo.findAll();
	}

	public RequisitionSweep find(String id) {
		Optional<RequisitionSweep> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + RequisitionSweep.class.getName()));
	}
	
	public Page<RequisitionSweep> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente =  clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}
	
	public void delete(String id) {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete because there are related requests");
		}
	}

}
