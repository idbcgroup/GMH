package org.fourgeeks.gha.domain.mix;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.CredentialTypeEnum;
import org.fourgeeks.gha.domain.ess.LogonLog;
import org.fourgeeks.gha.domain.ess.SingleSignOnUser;

@Entity
public class LegalEntityCredential extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private CredentialTypeEnum primaryRoleCode;
	
	@ManyToOne
	@JoinColumn(name = "singleSignOnUserFk")
	private SingleSignOnUser singleSignOnUser;
	
	@ManyToOne
	@JoinColumn(name = "systemInstanceFk")
	private SystemInstance systemInstance;
	
	@OneToMany(mappedBy = "legalEntityCredential")
	private Collection <LogonLog> loginAttempts;
	
	/**Attributes*/
	
	@Enumerated(EnumType.STRING)
	private CredentialTypeEnum primaryRoleCodea; /** CREDENTIAL TYPE PRIMARIA para esta credencial length =20 */
	
	private String primaryIdentifier; /** (HME) No de CREDENCIAL para el PRIMARY_ROLE_CODEA (CREDENTIAL TYPE PRIMARIA) = HME length =255 */
	private String secondaryIdentifier; /** No de CREDENCIAL s-ecundario para el SECONDARY_ROLE_CODEA (CREDENTIAL TYPE SECUNDARIA ) length =255 */
	
//	private Long secondaryRole; /** NO SE USA length =10 */
	private Timestamp createdDate; /** Fecha de creación de la credencial length = */
	private Boolean userHasSingleSignOn; /** Indica si el Usuario tiiene SSO. Si es TRUE (1) utilizar la tabla SINGLE_SIGN_ON_USER para determinar acceso, en caso contraio tomar user y psw de esta tabla para el sistema length =1 */
	
	@Enumerated(EnumType.STRING)
	private CredentialTypeEnum secondaryRoleCodea; /** CREDENTIAL TYPE SECUNDARIA para la CREDENTIAL TYPE PRIMARIA length =20 */

	/**
	 * 
	 */
	public LegalEntityCredential() {
		super();
		// TODO Auto-generated constructor stub
	}
}
