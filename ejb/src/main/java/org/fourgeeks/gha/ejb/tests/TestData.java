package org.fourgeeks.gha.ejb.tests;

import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
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
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;

@Startup
@Singleton
public class TestData {

	private final static Logger logger = Logger.getLogger(TestData.class
			.getName());

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BrandSErvice")
	BrandServiceRemote brandService;

	@EJB(name = "gmh.ManufacturerService")
	ManufacturerServiceRemote manufacturerService;

	@EJB(name = "gmh.EiaTypeService")
	EiaTypeServiceRemote eiaTypeServ;

	@EJB(name = "gmh.EiaService")
	EiaServiceRemote eiaServ;

	@EJB(name = "gmh.EiaTypeComponentService")
	EiaTypeComponentServiceRemote etcService;

	@EJB(name = "gmh.EiaComponentService")
	EiaComponentServiceRemote ecService;

	@Resource(mappedName = "java:/jdbc/gha")
	DataSource dataSource;

	@PostConstruct
	public void loadTestData() {
		userTestData();
		brandTestData();
		manufacturerTestData();
		eiaTypeTestData();
		eiaTestData();
	}

	/**
	 * 
	 */
	private void manufacturerTestData() {

		try {
			String query = "SELECT e from Manufacturer e WHERE id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				logger.log(Level.INFO, "creating test manufacturers");
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setName("AMD");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Caribe");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Sidor");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Alpes");
				em.persist(manufacturer);

				manufacturer = new Manufacturer();
				manufacturer.setName("Nipon");
				em.persist(manufacturer);

				em.flush();
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "error creating test manufacturers", e);
		}

	}

	/**
	 * 
	 */
	private void brandTestData() {
		try {
			String query = "SELECT e from Brand e WHERE id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				logger.log(Level.INFO, "creating test brands");
				Brand brand = new Brand();
				brand.setName("HP");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Epson");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Compaq");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Dell");
				em.persist(brand);

				brand = new Brand();
				brand.setName("Canon");
				em.persist(brand);

				em.flush();
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "error creating test brands", e);
		}

	}

	private void eiaTypeTestData() {
		String query = "SELECT t from EiaType t WHERE id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test eiaType");
				EiaType eiaType = new EiaType(brandService.find(1),
						manufacturerService.find(1), "Impresora Tinta",
						EiaMobilityEnum.FIXED, EiaTypeEnum.EQUIPMENT,
						EiaSubTypeEnum.IT_SYSTEM, "Stylus", "90001");
				em.persist(eiaType);

				eiaType = new EiaType(brandService.find(2),
						manufacturerService.find(2), "Impresora Laser",
						EiaMobilityEnum.FIXED, EiaTypeEnum.EQUIPMENT,
						EiaSubTypeEnum.IT_SYSTEM, "Deskjet", "90002");
				em.persist(eiaType);

				eiaType = new EiaType(brandService.find(3),
						manufacturerService.find(3), "Cartucho Tricolor",
						EiaMobilityEnum.FIXED, EiaTypeEnum.PART,
						EiaSubTypeEnum.IT_SYSTEM, "EP60", "90003");
				em.persist(eiaType);

				eiaType = new EiaType(brandService.find(4),
						manufacturerService.find(4), "Toner Laser",
						EiaMobilityEnum.FIXED, EiaTypeEnum.PART,
						EiaSubTypeEnum.IT_SYSTEM, "HP60", "90004");
				em.persist(eiaType);

				eiaType = new EiaType(brandService.find(5),
						manufacturerService.find(5), "Cartucho Negro",
						EiaMobilityEnum.FIXED, EiaTypeEnum.PART,
						EiaSubTypeEnum.IT_SYSTEM, "EPN60", "90005");
				em.persist(eiaType);

				em.flush();

			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test eiatype", e);
			}
		}
	}

	private void eiaTestData() {
		String query = "SELECT t from Eia t WHERE id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				logger.info("creating test eia");

				Bpi bpi = new Bpi();
				em.persist(bpi);
				em.flush();

				BuildingLocation buildingLocation = new BuildingLocation(bpi,
						"Building001", LocationLevelEnum.AREA_HALL, 2);
				em.persist(buildingLocation);
				em.flush();

				Facility facility = new Facility();
				facility.setBuildingLocation(buildingLocation);
				em.persist(facility);
				em.flush();

				Eia eia = new Eia(facility, eiaTypeServ.find(1),
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);
				eia.setCode("Stylus-001");
				eia.setSerialNumber("001");
				em.persist(eia);

				Eia eia2 = new Eia(facility, eiaTypeServ.find(2),
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.YEARS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				Eia eia3 = new Eia(facility, eiaTypeServ.find(3),
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.YEARS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				Eia eia4 = new Eia(facility, eiaTypeServ.find(4),
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				Eia eia5 = new Eia(facility, eiaTypeServ.find(5),
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				em.persist(eia2);
				em.persist(eia3);
				em.persist(eia4);
				em.persist(eia5);

			} catch (Exception e1) {
				logger.log(Level.INFO, "error creating test eia", e);
			}
		}
	}

	private void userTestData() {
		try {
			String query = "SELECT t from LegalEntity t WHERE id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				logger.info("creating test users");
				LegalEntity legalEntity = new LegalEntity();
				em.persist(legalEntity);
				SingleSignOnUser signOnUser = new SingleSignOnUser();
				signOnUser.setLegalEntity(legalEntity);
				signOnUser.setPassword("admin");
				signOnUser.setUserName("admin");
				em.persist(signOnUser);

				legalEntity = new LegalEntity();
				em.persist(legalEntity);
				signOnUser = new SingleSignOnUser();
				signOnUser.setLegalEntity(legalEntity);
				signOnUser.setPassword("asanchez");
				signOnUser.setUserName("asanchez");
				em.persist(signOnUser);

				em.flush();
				logger.info("done creating test users");
			}
		} catch (Exception e) {
			logger.log(Level.INFO, "error creating test users", e);
		}
	}

}