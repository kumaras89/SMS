package com.sms.core.documents;

public class DocType {
	
	private Long id;
	private String docCategory;
	private String typeOfDoc;
	private int maxDocCopy;
	private String desc;
	
	
	
	public int getMaxDocCopy() {
		return maxDocCopy;
	}
	public void setMaxDocCopy(int maxDocCopy) {
		this.maxDocCopy = maxDocCopy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDocCategory() {
		return docCategory;
	}
	public void setDocCategory(String docCategory) {
		this.docCategory = docCategory;
	}
	public String getTypeOfDoc() {
		return typeOfDoc;
	}
	public void setTypeOfDoc(String typeOfDoc) {
		this.typeOfDoc = typeOfDoc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
