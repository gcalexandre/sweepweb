package com.alexandrecasrtilho.websweep.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alexandrecasrtilho.websweep.domain.Cliente;
import com.alexandrecasrtilho.websweep.domain.RequisitionSweep;
import com.alexandrecasrtilho.websweep.domain.UploadFastaSweep;
import com.alexandrecasrtilho.websweep.repositories.RequisitionSweepRepository;
import com.alexandrecasrtilho.websweep.services.ClienteService;
import com.alexandrecasrtilho.websweep.services.FastaSweepService;
import com.alexandrecasrtilho.websweep.services.RequisitionSweepService;
import com.alexandrecasrtilho.websweep.util.HashMapOrthMat;
import com.mathworks.engine.MatlabEngine;

@RestController
public class RequisitionSweepController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FastaSweepController.class);

	@Autowired
	private RequisitionSweepRepository repo;

	@Autowired
	private FastaSweepService DBFileStorageService;

	@Autowired
	private RequisitionSweepService requisitionservice;

	@Autowired
	private ClienteService dbcliente;

	@GetMapping("/process")
	public String process(String fileId, String mask, String projection, String type, String jobName,
			String generateTree, String generateHDV, String distanceTree, boolean isPublic, String dbemail) {

		if (dbemail == null) {
			dbemail = "email@anonimo";
		}

		Cliente cli = dbcliente.findByEmail(dbemail);

		
		Random rand = new Random();
		Long rnd = rand.nextLong();
		if (rnd < 0) {
			rnd = -rnd;
		}

		UploadFastaSweep dbFile = DBFileStorageService.getFile(fileId);
		String filePach = "/tmp/matlabFasta" + System.currentTimeMillis() + "_" + rnd;

		boolean varGenerateHDV = Boolean.parseBoolean(generateHDV);
		boolean varGenerateTree = Boolean.parseBoolean(generateTree);

		try {
			FileOutputStream stream = new FileOutputStream(new File(filePach));
			stream.write(dbFile.getData());
			stream.close();

			// MapKey orth_mat

			Map<String, String> listMapOrth = HashMapOrthMat.getHashMap();
			String mapOrth = mask.concat(projection);
			String valueMapOrth = listMapOrth.get(mapOrth);
			Date instant;
			long instant2;
			String p = filePach;
			char[] gfg = p.toCharArray();
			String params1[] = mask.split(",");
			double[] maskParam = new double[params1.length];
			for (int i = 0; i < params1.length; i++) {
				maskParam[i] = Double.parseDouble(params1[i]);
			}
			String typeVar = type;
			char[] typeParam = typeVar.toCharArray();
			char[] pathProjec = valueMapOrth.toCharArray();
			double hdvDiscard = 0;
			double hdvComposition = 0;
			
			// Headers download
			
			MatlabEngine eng = MatlabEngine.startMatlab();

			
			String[] w = eng.feval("web_h_fas2sweep", gfg, typeParam, hdvComposition, pathProjec, hdvDiscard,
					maskParam);

			StringBuilder builder2 = new StringBuilder();
			for (int i = 0; i < w.length; i++) {
				builder2.append(w[i] + "");// append to the output string
				if (i < w.length - 1)// if this is not the last row element
					builder2.append(" ");// then add comma (if you don't like commas you can use spaces)

				builder2.append("\n");// append new line at the end of the row
			}
			byte[] dataMatlab2 = builder2.toString().getBytes("UTF-8");
			String nameOut2 = "SWeeP-Headers.txt";
			RequisitionSweep FileOut2 = new RequisitionSweep(nameOut2, "plain/text", dataMatlab2, instant = new Date(), mask,
					projection, type, dbFile, jobName, generateTree, distanceTree, isPublic, cli, instant2 = new Date().getTime());

			repo.save(FileOut2);
			
			
			

			// LHDV Download

			

			double[][] t = eng.feval("web_lhdv_fas2sweep", gfg, typeParam, hdvComposition, pathProjec, hdvDiscard,
					maskParam);
			try {
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < t.length; i++)// for each row
				{
					for (int j = 0; j < t[0].length; j++)// for each column
					{
						builder.append(t[i][j] + "");// append to the output string
						if (j < t[0].length - 1)// if this is not the last row element
							builder.append(",");// then add comma (if you don't like commas you can use spaces)
					}
					builder.append("\n");// append new line at the end of the row
					
				}
				byte[] dataMatlab = builder.toString().getBytes("UTF-8");
				String nameOut = "SWeeP-LHDV.csv";
				RequisitionSweep FileOut = new RequisitionSweep(nameOut, "plain/text", dataMatlab, instant = new Date(), mask,
						projection, type, dbFile, jobName, generateTree, distanceTree, isPublic, cli, instant2 = new Date().getTime());

				repo.save(FileOut);
				
				

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/downloadFileProcess/").path(FileOut.getId().toString()).toUriString();


				
			

				String fileDownloadUri2 = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/downloadFileProcess/").path(FileOut2.getId().toString()).toUriString();

				if (varGenerateHDV == false && varGenerateTree == false) {

					eng.close();

					return "{\"jobName\":\"" + jobName + "\" ,\"mask\":\"" + mask + "\" ,\"projection\":\"" + projection
							+ "\" ,\"type\":\"" + type + "\" ,\"fileDownloadURI\":\"" + fileDownloadUri
							+ "\" ,\"fileDownloadURI2\":\"" + fileDownloadUri2 + "\" }";

				}

				if (varGenerateHDV == false && varGenerateTree == true) {

					try {

						// Download phylomat

						char[][] c = new char[w.length][1];
						for (int i = 0; i < w.length; i++) {
							String wi = w[i];
							char[] vet = wi.toCharArray();
							c[i] = vet;

						}

						String distanceVar = distanceTree;
						char[] distanceParam = distanceVar.toCharArray();

						String pathPhylomat = eng.feval("web_phylomat", t, c, distanceParam);

						InputStream readData = new FileInputStream(pathPhylomat);
						File phy = new File(pathPhylomat);

						byte[] dataMatlab4 = new byte[(int) phy.length()];
						readData.read(dataMatlab4);

						String nameOut4 = "SWeeP-Phylomat.tree";
						RequisitionSweep FileOut4 = new RequisitionSweep(nameOut4, "plain/text", dataMatlab4, instant = new Date(),
								mask, projection, type, dbFile, jobName, generateTree, distanceTree, isPublic, cli, instant2 = new Date().getTime());

						repo.save(FileOut4); 
						

						String fileDownloadUri4 = ServletUriComponentsBuilder.fromCurrentContextPath()
								.path("/downloadFileProcess/").path(FileOut4.getId().toString()).toUriString();

						readData.close();
						eng.close();
						return "{\"jobName\":\"" + jobName + "\" ,\"mask\":\"" + mask + "\" ,\"projection\":\""
								+ projection + "\" ,\"type\":\"" + type + "\" ,\"fileDownloadURI\":\"" + fileDownloadUri
								+ "\" ,\"fileDownloadURI2\":\"" + fileDownloadUri2 + "\" ,\"fileDownloadURI4\":\""
								+ fileDownloadUri4 + "\" }";

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@GetMapping("/downloadFileProcess/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		// Load file from database
		RequisitionSweep dbFile = requisitionservice.getFile(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));

	}

	@RequestMapping(value = "/requisitionlist", method = RequestMethod.GET)
	public ResponseEntity<Page<RequisitionSweep>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "30") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instante2") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<RequisitionSweep> list = requisitionservice.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
	
    @GetMapping(value = "reqdelete/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		requisitionservice.delete(id);
		return ResponseEntity.noContent().build();
	}

}