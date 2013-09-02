/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;

/**
 * @author emiliot
 *
 */

@Entity
public class MaterialCategory extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "El tipo de material no puede ser nulo")
	@Column(nullable = false)
	private MaterialTypeEnum type;
	
	@ManyToOne
	@JoinColumn(name = "serviceResourceCategoryFk")
	private ServiceResourceCategory sRCategory;
	
	private String description;
	private String name;
	private String code;
	private String extCode;
	private String model;
	
	
	/**
	 * 
	 */
	public MaterialCategory() {
		// TODO Auto-generated constructor stub
	}
	public MaterialTypeEnum getType() {
		return type;
	}
	public void setType(MaterialTypeEnum type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getExtCode() {
		return extCode;
	}
	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}
