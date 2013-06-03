/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class EiaType extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "brandFk")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "manufacturerFk")
	private  Manufacturer manufacturer;
	
	private String name;
	private String code;
	private String model;
	
	/**
	 * 
	 */
	public EiaType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
	public String getModel() {
		return model;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCode(String code) {
		this.code = code;
	}	
	public void setModel(String model) {
		this.model = model;
	}
	public Brand getBrand() {
		return brand;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Map<String, Object> getAttributes(){
		Map <String, Object> res = new TreeMap<String, Object>();
		res.put("name", this.name);
		res.put("code", this.code);
		res.put("model", this.model);
		res.put("manufacturer", this.manufacturer);
		res.put("brand", this.brand);
		return res;
	}
}
