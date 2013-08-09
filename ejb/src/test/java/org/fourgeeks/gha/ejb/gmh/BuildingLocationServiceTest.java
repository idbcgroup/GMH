package org.fourgeeks.gha.ejb.gmh;

import java.util.List;

import javax.naming.Context;

import junit.framework.TestCase;

import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.ejb.ContextDeployment;

/**
 * @author vivi.torressg
 * 
 */
public class BuildingLocationServiceTest extends TestCase {

	private BuildingLocationServiceRemote ejbService;

	@Override
	protected void setUp() throws Exception {
		Context context = ContextDeployment.getContext();
		ejbService = (BuildingLocationServiceRemote) context
				.lookup("java:global/ejb/gmh.BuildingLocationService");
	}

	public void testNotNull() throws Exception {
		assertNotNull(ejbService);
	}

	public void testGetAll() throws Exception {
		List<BuildingLocation> buildingLocations = ejbService.getAll();
		System.out.println(buildingLocations);
		assertNotNull(buildingLocations);
	}

	public void testSave() throws Exception {
		/*
		 * BuildingLocation buildingLocation = ejbService.getAll().get(0); Bpi
		 * tempBpi = buildingLocation.getBpi(); System.out.println("BPI " +
		 * tempBpi); buildingLocation = null; buildingLocation = new
		 * BuildingLocation(); buildingLocation.setBpi(tempBpi);
		 * buildingLocation.setCode("123456");
		 * buildingLocation.setDescription("Description Test");
		 * buildingLocation.setLocationLevel(LocationLevelEnum.AREA_HALL);
		 * buildingLocation.setName("Name Test"); buildingLocation.setUnits(0);
		 * ejbService.save(buildingLocation);
		 */
	}

	public void testFind() throws Exception {
		BuildingLocation buildingLocation = ejbService.find("Building 000");
		assertNotNull(buildingLocation);
	}
}
