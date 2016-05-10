package com.sms.core.documents;

public class Document {
	
	private Long id;
	private Long documentOwnerId;
	private String typeOfDoc;
	private int seq;
	private String cmisObjectId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDocumentOwnerId() {
		return documentOwnerId;
	}
	public void setDocumentOwnerId(Long documentOwnerId) {
		this.documentOwnerId = documentOwnerId;
	}
	public String getTypeOfDoc() {
		return typeOfDoc;
	}
	public void setTypeOfDoc(String typeOfDoc) {
		this.typeOfDoc = typeOfDoc;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCmisObjectId() {
		return cmisObjectId;
	}
	public void setCmisObjectId(String cmisObjectId) {
		this.cmisObjectId = cmisObjectId;
	}

}
