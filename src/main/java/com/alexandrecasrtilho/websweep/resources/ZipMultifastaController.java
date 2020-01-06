package com.alexandrecasrtilho.websweep.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alexandrecasrtilho.websweep.domain.UploadFileResponse;
import com.alexandrecasrtilho.websweep.domain.UploadZipMultifasta;
import com.alexandrecasrtilho.websweep.services.ZipMultifastaService;

@RestController
public class ZipMultifastaController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ZipMultifastaController.class);

	@Autowired
	private ZipMultifastaService DBFileStorageService;

	@PostMapping("/uploadFileMultifasta")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		UploadZipMultifasta dbFile = DBFileStorageService.storeFile(file);
		
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFileMultifasta/")
				.path(dbFile.getId()).toUriString();
		
		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFileMultifasta/{fileId}")
	public ResponseEntity<Resource> downloadFileMultifasta(@PathVariable String fileId) {
		// Load file from database
		UploadZipMultifasta dbFile = DBFileStorageService.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));

	}

}