package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.MaterialBrand;

/**
 * @author emiliot
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaTypeFk",
		"materialFk" }))
@NamedQueries(value = { @NamedQuery(name = "EiaTypeMaterial.findByEiaType", query = "SELECT etm FROM EiaTypeMaterial etm WHERE etm.eiaType = :eiaType ORDER BY etm.id") })
public class EiaTypeMaterial extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk", nullable = false, columnDefinition = "varchar(255) REFERENCES eiatype(code) ON UPDATE CASCADE ON DELETE CASCADE")
	private EiaType eiaType;

	@ManyToOne
	@JoinColumn(name = "materialBrandFk")
	private MaterialBrand materialBrand;

	private int amount = 0;

	/**
	 * 
	 */
	public EiaTypeMaterial() {
	}

	public int getAmount() {
		return amount;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public MaterialBrand getMaterialBrand() {
		return materialBrand;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setMaterialBrand(MaterialBrand materialBrand) {
		this.materialBrand = materialBrand;
	}
}
