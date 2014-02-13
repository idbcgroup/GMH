package org.fourgeeks.gha.webclient.client.UI;

import java.util.List;

import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.bpi.BpiModel;
import org.fourgeeks.gha.webclient.client.bpu.BpuModel;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;
import org.fourgeeks.gha.webclient.client.buildinglocation.BuildingLocationModel;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeModel;
import org.fourgeeks.gha.webclient.client.externalprovider.ExternalProviderModel;
import org.fourgeeks.gha.webclient.client.facility.FacilityModel;
import org.fourgeeks.gha.webclient.client.manufacturer.ManufacturerModel;
import org.fourgeeks.gha.webclient.client.obu.BspModel;
import org.fourgeeks.gha.webclient.client.obu.ObuModel;
import org.fourgeeks.gha.webclient.client.rolebase.RoleModel;
import org.fourgeeks.gha.webclient.client.workingarea.WorkingAreaModel;

import com.google.gwt.user.client.Timer;

/**
 * @author alacret
 * 
 */
public enum GHACache {
	/**
	 * 
	 */
	INSTANCE; // Single value

	/*
	 * The cache expiration time
	 */
	private static final int CACHE_TIME = 50000;
	private List<Brand> brands;
	private List<Manufacturer> manufacturers;
	private List<BuildingLocation> buildingLocations;
	private List<Obu> obus;
	private List<Role> roles;
	private List<ExternalProvider> externalProviders;
	private List<EiaType> eiaTypes;
	private List<Eia> eias;
	private List<WorkingArea> workingAreas;
	private List<Facility> facilities;
	private List<Bpi> bpis;
	private List<Bpu> bpus;
	private List<Bsp> bsps;

	{
		// Inititalization of the invalidation policy
		final Timer t = new Timer() {
			@Override
			public void run() {
				invalidateCache();
			}
		};
		t.scheduleRepeating(CACHE_TIME);
	}

	private void invalidateCache() {
		brands = null;
		manufacturers = null;
		buildingLocations = null;
		obus = null;
		roles = null;
		externalProviders = null;
		eiaTypes = null;
		workingAreas = null;
		facilities = null;
		bpis = null;
	}

	public void getFacilities(GHAAsyncCallback<List<Facility>> callback) {
		if (facilities == null)
			getFacilitiesFromServer(callback);
		else
			callback.onSuccess(facilities);

	}

	private void getFacilitiesFromServer(
			final GHAAsyncCallback<List<Facility>> callback) {
		FacilityModel.getAll(new GHAAsyncCallback<List<Facility>>() {

			@Override
			public void onSuccess(List<Facility> result) {
				facilities = result;
				callback.onSuccess(result);
			}
		});
	}

	public void getWorkingAreas(GHAAsyncCallback<List<WorkingArea>> callback) {
		if (workingAreas == null)
			getWorkingAreasFromServer(callback);
		else
			callback.onSuccess(workingAreas);

	}

	private void getWorkingAreasFromServer(
			final GHAAsyncCallback<List<WorkingArea>> callback) {
		WorkingAreaModel.getAll(new GHAAsyncCallback<List<WorkingArea>>() {

			@Override
			public void onSuccess(List<WorkingArea> result) {
				workingAreas = result;
				callback.onSuccess(result);
			}
		});
	}

	public void getEias(GHAAsyncCallback<List<Eia>> callback) {
		if (eias == null)
			getEiasFromServer(callback);
		else
			callback.onSuccess(eias);
	}

	private void getEiasFromServer(final GHAAsyncCallback<List<Eia>> callback) {
		EIAModel.getAll(new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				eias = result;
				callback.onSuccess(eias);
			}
		});
	}

	/**
	 * @param callback
	 * @param forceFromServer
	 */
	public void getEiaTypes(GHAAsyncCallback<List<EiaType>> callback,
			boolean forceFromServer) {
		if (forceFromServer || eiaTypes == null)
			getEiaTypesFromServer(callback);
		else
			callback.onSuccess(eiaTypes);
	}

	private void getEiaTypesFromServer(
			final GHAAsyncCallback<List<EiaType>> callback) {
		EIATypeModel.getAll(new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> result) {
				eiaTypes = result;
				callback.onSuccess(eiaTypes);
			}
		});
	}

	/**
	 * @param callback
	 */
	public void getExternalProviders(
			GHAAsyncCallback<List<ExternalProvider>> callback) {
		// Avoiding synchronization problems
		if (externalProviders == null)
			getExternalProvidersFromServer(callback);
		else
			callback.onSuccess(externalProviders);

	}

	private void getExternalProvidersFromServer(
			final GHAAsyncCallback<List<ExternalProvider>> callback) {
		ExternalProviderModel
				.getAll(new GHAAsyncCallback<List<ExternalProvider>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<ExternalProvider> result) {
						externalProviders = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getBaseRoles(GHAAsyncCallback<List<Role>> callback) {
		// Avoiding synchronization problems
		if (roles == null)
			getBaseRolesFromServer(callback);
		else
			callback.onSuccess(roles);

	}

	private void getBaseRolesFromServer(
			final GHAAsyncCallback<List<Role>> callback) {
		RoleModel.getAll(new GHAAsyncCallback<List<Role>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<Role> result) {
						roles = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getBsps(GHAAsyncCallback<List<Bsp>> callback) {
		// Avoiding synchronization problems
		if (bsps == null)
			getBspsServer(callback);
		else
			callback.onSuccess(bsps);

	}

	private void getBspsServer(final GHAAsyncCallback<List<Bsp>> callback) {
		BspModel.getAll(new GHAAsyncCallback<List<Bsp>>() {

			@Override
			public void onSuccess(List<Bsp> result) {
				bsps = result;
				callback.onSuccess(result);
			}
		});
	}

	/**
	 * @param callback
	 */
	public void getObus(GHAAsyncCallback<List<Obu>> callback) {
		// Avoiding synchronization problems
		if (obus == null)
			getObusFromServer(callback);
		else
			callback.onSuccess(obus);

	}

	private void getObusFromServer(final GHAAsyncCallback<List<Obu>> callback) {
		ObuModel.getAll(new GHAAsyncCallback<List<Obu>>() { // AyncCallback
			// subclass

			@Override
			public void onSuccess(List<Obu> result) {
				obus = result;
				// Avoiding synchronization problems
				callback.onSuccess(result);
			}
		});
	}

	/**
	 * @param callback
	 */
	public void getBuildingLocations(
			GHAAsyncCallback<List<BuildingLocation>> callback) {
		// Avoiding synchronization problems
		if (buildingLocations == null)
			getBuildingLocationsFromServer(callback);
		else
			callback.onSuccess(buildingLocations);

	}

	private void getBuildingLocationsFromServer(
			final GHAAsyncCallback<List<BuildingLocation>> callback) {
		BuildingLocationModel
				.getAll(new GHAAsyncCallback<List<BuildingLocation>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<BuildingLocation> result) {
						buildingLocations = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 * @param forceFromServer
	 */
	public void getBrands(GHAAsyncCallback<List<Brand>> callback,
			boolean forceFromServer) {
		if (forceFromServer || brands == null)
			getBrandsFromServer(callback);
		else
			callback.onSuccess(brands);
	}

	private void getBrandsFromServer(
			final GHAAsyncCallback<List<Brand>> callback) {
		BrandModel.getAll(new GHAAsyncCallback<List<Brand>>() { // AyncCallback
																// subclass

					@Override
					public void onSuccess(List<Brand> result) {
						brands = result;
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getManufacturesrs(
			GHAAsyncCallback<List<Manufacturer>> callback,
			boolean forceFromServer) {
		if (forceFromServer || manufacturers == null)
			getManufacturersFromServer(callback);
		else {
			callback.onSuccess(manufacturers);
		}
	}

	private void getManufacturersFromServer(
			final GHAAsyncCallback<List<Manufacturer>> callback) {
		ManufacturerModel.getAll(new GHAAsyncCallback<List<Manufacturer>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<Manufacturer> result) {
						manufacturers = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getBpis(GHAAsyncCallback<List<Bpi>> callback,
			boolean forceFromServer) {
		if (forceFromServer || bpis == null)
			getBpisFromServer(callback);
		else {
			callback.onSuccess(bpis);
		}
	}

	private void getBpisFromServer(final GHAAsyncCallback<List<Bpi>> callback) {
		BpiModel.getAll(new GHAAsyncCallback<List<Bpi>>() { // AsyncCallback
			// subclass

			@Override
			public void onSuccess(List<Bpi> result) {
				bpis = result;
				// Avoiding synchronization problems
				callback.onSuccess(result);
			}
		});
	}

	/**
	 * @param callback
	 * @param forceFromServer
	 */
	public void getBpus(GHAAsyncCallback<List<Bpu>> callback,
			boolean forceFromServer) {
		if (forceFromServer || bpus == null)
			getBpusFromServer(callback);
		else {
			callback.onSuccess(bpus);
		}
	}

	private void getBpusFromServer(final GHAAsyncCallback<List<Bpu>> callback) {
		BpuModel.getAll(new GHAAsyncCallback<List<Bpu>>() {
			@Override
			public void onSuccess(List<Bpu> result) {
				bpus = result;
				// Avoiding synchronization problems
				callback.onSuccess(result);
			}
		});
	}

}