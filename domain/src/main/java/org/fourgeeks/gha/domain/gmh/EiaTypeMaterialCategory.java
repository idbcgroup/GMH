/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.glm.MaterialCategory;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaTypeFk",
		"materialCategoryFk" }))
@NamedQueries(value = { @NamedQuery(name = "EiaTypeMaterialCategory.findByEiaType", query = "SELECT etm FROM EiaTypeMaterialCategory etm WHERE etm.eiaType = :eiaType ORDER BY etm.id") })
public class EiaTypeMaterialCategory extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaTypeFk", nullable = false)
	// , columnDefinition =
	// "varchar(255) REFERENCES eiatype(code) ON UPDATE CASCADE ON DELETE CASCADE")
	private EiaType eiaType;

	@ManyToOne
	@JoinColumn(name = "materialCategoryFk", nullable = false)
	// , columnDefinition =
	// "varchar(255) REFERENCES materialcategory(code) ON UPDATE CASCADE ON DELETE CASCADE")
	private MaterialCategory materialCategory;

	private int amount = 0;

	public EiaType getEiaType() {
		return eiaType;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public MaterialCategory getMaterialCategory() {
		return materialCategory;
	}

	public void setMaterialCategory(MaterialCategory materialCategory) {
		this.materialCategory = materialCategory;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

}