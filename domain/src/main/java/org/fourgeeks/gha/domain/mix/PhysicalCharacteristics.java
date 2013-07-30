package org.fourgeeks.gha.domain.mix;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.AgeGroupEnum;
import org.fourgeeks.gha.domain.enu.BodyContextureEnum;
import org.fourgeeks.gha.domain.enu.HairColorEnum;
import org.fourgeeks.gha.domain.enu.SkinColorEnum;

@Entity
public class PhysicalCharacteristics extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "physicalCharacteristics")
	private Collection<PhysicalCharacteristicsCode> physicalCharacteristicsCodes;

	/** Attributes */

	private AgeGroupEnum ageGroup;
	/** length =60 */

	private HairColorEnum hairColor;
	/** length =60 */

	private BodyContextureEnum bodyContexture;
	/** length =60 */

	private SkinColorEnum skinColor;
	/** length =60 */

	private String otherSigns;
	/** length =255 */
	private Date dateCreated;
	/** length =12 */

}
