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

import com.alexandrecasrtilho.websweep.domain.UploadFastaSweep;
import com.alexandrecasrtilho.websweep.domain.UploadFileResponse;
import com.alexandrecasrtilho.websweep.services.FastaSweepService;

@RestController
public class FastaSweepController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FastaSweepController.class);

	@Autowired
	private FastaSweepService DBFileStorageService;

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		UploadFastaSweep dbFile = DBFileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(dbFile.getId()).toUriString();

		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// Load file from database
		UploadFastaSweep dbFile = DBFileStorageService.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));

	}

	@GetMapping(value = "reqdeletefasta/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		DBFileStorageService.delete(id);
		return ResponseEntity.noContent().build();
	}

}