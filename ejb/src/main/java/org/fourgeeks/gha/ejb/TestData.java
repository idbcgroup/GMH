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

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.enu.WarrantyStateEnum;
import org.fourgeeks.gha.domain.ess.SingleSignOnUser;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
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
		eiaTypeTestData();
		// eiaTestData();
	}

	/**
	 * 
	 */
	private void eiaTestData() {
		// TODO: probar los servicios ejb para el eia

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

	private void eiaTypeTestData() {

		String query = "SELECT t from EiaType t WHERE id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setName("Epson");
			em.persist(manufacturer);

			Brand brand = new Brand();
			brand.setName("Stylus");
			em.persist(brand);

			EiaType eiaType = new EiaType(brand, manufacturer, "Epson",
					EiaMobilityEnum.FIXED, EiaTypeEnum.EQUIPMENT,
					EiaSubTypeEnum.IT_SYSTEM);
			em.persist(eiaType);

			BuildingLocation buildingLocation = new BuildingLocation();
			em.persist(buildingLocation);

			Facility facility = new Facility();
			em.persist(facility);

			Eia eia = new Eia(facility, eiaType, buildingLocation,
					WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
					EiaStateEnum.TEST, WarrantyStateEnum.VALID);
			eia.setCode("TESTCODE");
			eia.setEiatype(eiaType);
			eia.setSerialNumber("SERIALNUMBER");

			em.persist(eia);
		}
	}

	private void userTestData() {
		try {
			String query = "SELECT t from LegalEntity t WHERE id = 1 ";
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