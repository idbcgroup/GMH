package org.fourgeeks.gha.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;

@Startup
@Singleton
public class TestData {

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BrandSErvice")
	BrandServiceRemote bServiceRemote;
	
	@EJB(name = "gmh.ManufacturerService")
	ManufacturerServiceRemote mServiceRemote;

	@Resource(mappedName = "java:/jdbc/gha")
	DataSource dataSource;

	@PostConstruct
	public void inicializar() {
		userTestData();
		createIndexs();
		eiaTypeTestData();
		//brandTestData();
		//manufacturerTestData();
	}

	/**
	 * 
	 */
	private void manufacturerTestData() {
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setName("TEST Manufacturer 1");
		mServiceRemote.save(manufacturer);
		
		manufacturer.setName("TEST Manufacturer 2");
		mServiceRemote.save(manufacturer);
		
		System.out.println("MANUFACTURER: testing find");
		manufacturer.setName("TEST");
		List<Manufacturer> findResult = mServiceRemote.find(manufacturer);
		for(Manufacturer man : findResult){
			System.out.println("manufacturer id="+Long.toString(man.getId())
					+ " name="+man.getName());
		}
		
//		System.out.println("MANUFACTURER: testing delete");
//		mServiceRemote.delete(1L);
		
		System.out.println("MANUFACTURER: testing getAll");
		List <Manufacturer> getAllResult = mServiceRemote.getAll();
		for(Manufacturer man : getAllResult){
			System.out.println("manufacturer id="+Long.toString(man.getId())
					+ " name="+man.getName());
		}
	}

	/**
	 * 
	 */
	private void brandTestData() {
		Brand brand = new Brand();
		brand.setName("TEST Brand 1");
		bServiceRemote.save(brand);
		
		brand.setName("TEST Brand 2");
		bServiceRemote.save(brand);
		
		System.out.println("BRAND: testing find");
		brand.setName("TEST");
		List<Brand> findResult = bServiceRemote.find(brand);
		for(Brand br : findResult){
			System.out.println("brand id="+Long.toString(br.getId())
					+ " name="+br.getName());
		}
		
//		System.out.println("BRAND: testing delete");
//		bServiceRemote.delete(1L);
		
		System.out.println("BRAND: testing getAll");
		List <Brand> getAllResult = bServiceRemote.getAll();
		for(Brand br : getAllResult){
			System.out.println("manufacturer id="+Long.toString(br.getId())
					+ " name="+br.getName());
		}
		
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
			ps = con.prepareStatement("CREATE INDEX eiaType_index ON eiatype (type)");
			ps.execute();
			con.close();
		} catch (SQLException e1) {
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

			Bpi bpi = new Bpi();
			em.persist(bpi);

			BuildingLocation buildingLocation = new BuildingLocation(bpi,
					"Building001", LocationLevelEnum.AREA_HALL, 2);
			em.persist(buildingLocation);

			Facility facility = new Facility();
			facility.setBuildingLocation(buildingLocation);
			em.persist(facility);

			Eia eia = new Eia(facility, eiaType, WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS, EiaStateEnum.TEST, WarrantyStateEnum.VALID);
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