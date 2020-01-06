package com.alexandrecasrtilho.websweep.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alexandrecasrtilho.websweep.domain.UploadFastaSweep;
import com.alexandrecasrtilho.websweep.repositories.FastaSweepRepository;
import com.alexandrecasrtilho.websweep.services.exceptions.DataIntegrityException;
import com.alexandrecasrtilho.websweep.services.exceptions.FileStorageException;
import com.alexandrecasrtilho.websweep.services.exceptions.MyFileNotFoundException;
import com.alexandrecasrtilho.websweep.services.exceptions.ObjectNotFoundException;

@Service
public class FastaSweepService {

	@Autowired
	private FastaSweepRepository dbFileRepository;

	public UploadFastaSweep storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			UploadFastaSweep dbFile = new UploadFastaSweep(fileName, file.getContentType(), file.getBytes());

			return dbFileRepository.save(dbFile);
			
			
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public UploadFastaSweep getFile(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}
	
	public List<UploadFastaSweep> findAll() {
		return dbFileRepository.findAll();
	}
	
	public UploadFastaSweep find(String id) {
		Optional<UploadFastaSweep> obj = dbFileRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + UploadFastaSweep.class.getName()));
	}
	
	public void delete(String id) {
		find(id);
		try {
			dbFileRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete because there are related requests");
		}
	}

}