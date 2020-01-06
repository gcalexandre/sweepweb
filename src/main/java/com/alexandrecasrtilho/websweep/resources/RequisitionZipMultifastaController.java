package com.alexandrecasrtilho.websweep.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alexandrecasrtilho.websweep.domain.RequisitionMultifasta;
import com.alexandrecasrtilho.websweep.domain.UploadZipMultifasta;
import com.alexandrecasrtilho.websweep.repositories.RequisitionMultifastaRepository;
import com.alexandrecasrtilho.websweep.services.RequisitionMultifastaService;
import com.alexandrecasrtilho.websweep.services.ZipMultifastaService;
import com.mathworks.engine.MatlabEngine;

@RestController
public class RequisitionZipMultifastaController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ZipMultifastaController.class);

	@Autowired
	private RequisitionMultifastaRepository repo;

	@Autowired
	private ZipMultifastaService DBFileStorageService;

	@Autowired
	private RequisitionMultifastaService dbrequisitionservice;

	@GetMapping("/processMultifasta")
	public String process(String fileId, String param1, String jobName) {
		Date instant = new Date();
		Random rand = new Random();
		Long rnd = rand.nextLong();
		if (rnd < 0) {
			rnd = -rnd;
		}

		UploadZipMultifasta dbFile = DBFileStorageService.getFile(fileId);
		String filePach = "/tmp/matlabMultifasta" + System.currentTimeMillis() + "_" + rnd;
		try {
			FileOutputStream stream = new FileOutputStream(new File(filePach + ".zip"));
			stream.write(dbFile.getData());
			stream.close();
			Runtime r = Runtime.getRuntime();
			Process p = r.exec("rm -rf " + filePach);
			p.waitFor();
			Runtime r1 = Runtime.getRuntime();
			Process p1 = r.exec("mkdir " + filePach);
			p1.waitFor();
			Runtime r2 = Runtime.getRuntime();
			Process p2 = r.exec("unzip -e " + filePach + ".zip -d " + filePach);
			p2.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			MatlabEngine eng = MatlabEngine.startMatlab();
			String p = filePach;
			char[] path = p.toCharArray();
			String type = param1;
			char[] typeparam = type.toCharArray();

			String pathProcess = eng.feval("web_process_fastas", path, typeparam);

			try {
				InputStream readData = new FileInputStream(pathProcess);
				File t = new File(pathProcess);
				
				byte[] dataMatlab = new byte[(int)t.length()];
				readData.read(dataMatlab); 


				String nameOut = "_SWeeP_Multifasta.fasta";
				String nameOutComplete = jobName.concat(nameOut);				
				RequisitionMultifasta FileOut = new RequisitionMultifasta(nameOutComplete, "plain/text",
						dataMatlab, instant, param1, dbFile, jobName);

				repo.save(FileOut);

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/downloadFileProcessMultifasta/").path(FileOut.getId().toString()).toUriString();

				readData.close();
				eng.close();
				return "{\"param1\":\"" + param1 + "\" ,\"fileDownloadURI\":\"" + fileDownloadUri + "\"}";

			} catch (Exception e) {
				e.printStackTrace();
				eng.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/downloadFileProcessMultifasta/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// Load file from database
		RequisitionMultifasta dbFile = dbrequisitionservice.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));

	}

}