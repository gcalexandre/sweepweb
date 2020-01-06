package com.alexandrecasrtilho.websweep.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexandrecasrtilho.websweep.domain.Cliente;
import com.alexandrecasrtilho.websweep.domain.enums.Perfil;
import com.alexandrecasrtilho.websweep.dto.ClienteDTO;
import com.alexandrecasrtilho.websweep.dto.ClienteNewDTO;
import com.alexandrecasrtilho.websweep.repositories.ClienteRepository;
import com.alexandrecasrtilho.websweep.security.UserSS;
import com.alexandrecasrtilho.websweep.services.exceptions.AuthorizationException;
import com.alexandrecasrtilho.websweep.services.exceptions.DataIntegrityException;
import com.alexandrecasrtilho.websweep.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;

	public Cliente find(String id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access denied");
		}

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(String id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete because there are related requests");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente findByEmail(String email) {
		/*
		 * UserSS user = UserService.authenticated(); if (user == null ||
		 * !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) { throw new
		 * AuthorizationException("Access denied"); }
		 */
		Cliente obj = repo.findByEmail(email);
		if (obj == null) {
			// throw new ObjectNotFoundException(
			// "Object not found! Id: " + user.getId() + ", Tipo: " +
			// Cliente.class.getName());
		}
		return obj;
	}

	

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null);
	}

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), pe.encode(objDto.getSenha()));

		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());

	}

}
