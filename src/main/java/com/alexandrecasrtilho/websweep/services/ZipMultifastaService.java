package com.alexandrecasrtilho.websweep.services;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alexandrecasrtilho.websweep.domain.UploadZipMultifasta;
import com.alexandrecasrtilho.websweep.repositories.ZipMultifastaRepository;
import com.alexandrecasrtilho.websweep.services.exceptions.FileStorageException;
import com.alexandrecasrtilho.websweep.services.exceptions.MyFileNotFoundException;

@Service
public class ZipMultifastaService {

	@Autowired
	private ZipMultifastaRepository dbFileRepository;

	public UploadZipMultifasta storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			
			

			UploadZipMultifasta dbFile = new UploadZipMultifasta(fileName, file.getContentType(), file.getBytes());

			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public UploadZipMultifasta getFile(String fileId) {
		return dbFileRepository.findById(fileId)
				.orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}
	
	


}
