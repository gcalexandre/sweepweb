package com.alexandrecasrtilho.websweep.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Table(name = "requisition_sweep")
public class RequisitionSweep implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date instante;
	
	private long instante2;

	private String maskListParameter;

	private String projectionListParameter;

	private String typeList;

	private String jobName;

	private String generateTree;

	private String distanceTree;

	@ManyToOne
	@JoinColumn(name = "file_id")
	private UploadFastaSweep dbfile;

	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;

	private boolean isPublic;

	public RequisitionSweep() {

	}

	public RequisitionSweep(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public RequisitionSweep(String fileName, String fileType, byte[] data, Date instante, String maskListParameter,
			String projectionListParameter, String typeList, UploadFastaSweep dbfile, String jobName,
			String generateTree, String distanceTree, boolean isPublic, Cliente cliente, long instante2) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.instante = instante;
		this.maskListParameter = maskListParameter;
		this.projectionListParameter = projectionListParameter;
		this.typeList = typeList;
		this.dbfile = dbfile;
		this.jobName = jobName;
		this.generateTree = generateTree;
		this.distanceTree = distanceTree;
		this.isPublic = isPublic;
		this.cliente = cliente;
		this.instante2 = instante2;

	}

	public Date getInstant() {
		return instante;
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

	public UploadFastaSweep getDBFile() {
		return dbfile;
	}

	public void setDBFile(UploadFastaSweep dbfile) {
		this.dbfile = dbfile;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getProjectionListParameter() {
		return projectionListParameter;
	}

	public void setProjectionListParameter(String projectionListParameter) {
		this.projectionListParameter = projectionListParameter;
	}

	public String getMaskListParameter() {
		return maskListParameter;
	}

	public void setMaskListParameter(String maskListParameter) {
		this.maskListParameter = maskListParameter;
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

	public String getGenerateTree() {
		return generateTree;
	}

	public void setGenerateTree(String generateTree) {
		this.generateTree = generateTree;
	}

	public String getDistanceTree() {
		return distanceTree;
	}

	public void setDistanceTree(String distanceTree) {
		this.distanceTree = distanceTree;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public long getInstante2() {
		return instante2;
	}

	public void setInstante2(long instante2) {
		this.instante2 = instante2;
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
		RequisitionSweep other = (RequisitionSweep) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Requisition: ");
		builder.append(getId());
		builder.append(", Instante: ");
		builder.append(sdf.format(getInstant()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", MAsk: ");
		builder.append(getMaskListParameter());
		builder.append("\nDetalhes:\n");
				
		return builder.toString();
	}

	

}
