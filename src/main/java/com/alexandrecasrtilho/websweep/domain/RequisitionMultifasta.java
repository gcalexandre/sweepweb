package com.alexandrecasrtilho.websweep.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "requisition_multifasta")
public class RequisitionMultifasta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date instant;

	private String typeList;
	
	private String jobName;

	
	@ManyToOne
	@JoinColumn(name = "file_id")
	private UploadZipMultifasta dbfile;

	public RequisitionMultifasta() {

	}

	public RequisitionMultifasta(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public RequisitionMultifasta(String fileName, String fileType, byte[] data, Date instant, String typeList,
		 UploadZipMultifasta dbfile,String jobName) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.instant = instant;
		this.typeList = typeList;
		this.dbfile = dbfile;
		this.jobName = jobName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public UploadZipMultifasta getDBFile() {
		return dbfile;
	}

	public void setDBFile(UploadZipMultifasta dbfile) {
		this.dbfile = dbfile;
	}
	
	public String getTypeList() {
		return typeList;
	}

	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}
	
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequisitionMultifasta other = (RequisitionMultifasta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}

	


