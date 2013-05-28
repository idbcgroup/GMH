package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class Bpo extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;
	
	
	/**Attributes*/
	
//	private String bpoNumber; /** length =255 */	
//	private String bpoType; /** length =255 */
//	private Date legalEntityCredentialDate; /** length = */
//	private String legalEntityCredential; /** length =255 */
//	private Date createdDate; /** length =8 */
//	private String bpoFirstname; /** length =255 */
//	private String bpoLastname; /** length =255 */
//	private String bpoSecondName; /** length =255 */
//	private String bpoSecondLastName; /** length =255 */
//	private String gender; /** length =20 */
//	private Date birthDate; /** length =8 */
//	private Long citizenId; /** length =19 */
	
	/**
	 * 
	 */
	public Bpo() {
		super();
		// TODO Auto-generated constructor stub
	}	
}
