package org.fourgeeks.gha.domain.mix;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class CitizenPicture extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private byte[] imageData;
	private Date imageDate;
	private int imageOrder;
	private boolean current;
	
	@ManyToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;
	
	/**
	 * 
	 */
	public CitizenPicture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public Date getImageDate() {
		return imageDate;
	}
	public int getImageOrder() {
		return imageOrder;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	public void setImageDate(Date imageDate) {
		this.imageDate = imageDate;
	}
	public void setImageOrder(int imageOrder) {
		this.imageOrder = imageOrder;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
}
