package org.fourgeeks.gha.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.fourgeeks.gha.domain.enu.EiaMovilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.ess.SingleSignOnUser;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;

@Startup
@Singleton
public class TestData {

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.EquipmentService")
	EiaServiceRemote remote;

	@Resource(mappedName = "java:/jdbc/gha")
	DataSource dataSource;

	@PostConstruct
	public void inicializar() {
		userTestData();
		createIndexs();
		// eiaTypeTestData();
	}

	private void createIndexs() {

		System.out.println("Creating indexes...");
		Connection con;
		try {
			con = dataSource.getConnection();
		} catch (SQLException e2) {
			return;
		}
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("CREATE INDEX username_index ON singlesignonuser (username)");
			ps.execute();
		} catch (SQLException e1) {
			return;
		}

		try {
			ps = con.prepareStatement("CREATE INDEX eiaType_index ON eiatype (type)");
			ps.execute();
		} catch (SQLException e) {
			return;
		}

		System.out.println("...done creating indexes!");

	}

	/**
	 * 
	 */
	// private void eiaTestData() {
	//
	// Brand brand = new Brand();
	// Manufacturer manufacturer = new Manufacturer();
	//
	// manufacturer.setName("Epson");
	// em.persist(manufacturer);
	//
	// brand.setName("Stylus");
	// em.persist(brand);
	//
	// EiaType eiaType = new EiaType();
	// eiaType.setName("Impresora Epson multifuncional");
	// eiaType.setModel("Stylus123");
	// eiaType.setManufacturer(manufacturer);
	// eiaType.setBrand(brand);
	// eiaType.setCode("IMPHP9523");
	// em.persist(eiaType);
	//
	// Eia eia = new Eia();
	// eia.setEiatype(eiaType);
	// eia.setCode("Impresora");
	// em.persist(eia);
	//
	// Eia equipment2 = new Eia();
	// equipment2.setEiatype(eiaType);
	// equipment2.setCode("Impresora");
	// em.persist(equipment2);
	//
	// List<Eia> equipments = remote.find(eiaType);
	// if (equipments == null)
	// System.out.println("NULL");
	// else {
	// for (Eia e : equipments) {
	// System.out.println(e.getCode());
	// }
	// }
	// }

	private void eiaTypeTestData() {

		try {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setName("Epson");
			em.persist(manufacturer);

			Brand brand = new Brand();
			brand.setName("Stylus");
			em.persist(brand);

			EiaType eiaType = new EiaType();
			eiaType.setName("Impresora Epson multifuncional");
			eiaType.setModel("Deskjet 9510");
			eiaType.setManufacturer(manufacturer);
			eiaType.setBrand(brand);
			eiaType.setMovility(EiaMovilityEnum.FIXED);
			eiaType.setType(EiaTypeEnum.EQUIPMENT);
			eiaType.setSubtype(EiaSubTypeEnum.IT_SYSTEM);
			eiaType.setCode("IMPHP9523");
			em.persist(eiaType);

		} catch (Exception e) {
			System.out.println("ERROR: No se puede cargar la data de prueba:"
					+ e.getMessage());
		}
	}

	private void userTestData() {
		try {
			String query = "SELECT t from TestData t WHERE id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				LegalEntity legalEntity = new LegalEntity();
				em.persist(legalEntity);
				SingleSignOnUser signOnUser = new SingleSignOnUser();
				signOnUser.setLegalEntity(legalEntity);
				signOnUser.setPassword("admin");
				signOnUser.setUserName("admin");
				em.persist(signOnUser);

			}
		} catch (Exception e) {
			System.out.println("ERROR: No se puede cargar la data de prueba:"
					+ e.getMessage());
		}
	}
}