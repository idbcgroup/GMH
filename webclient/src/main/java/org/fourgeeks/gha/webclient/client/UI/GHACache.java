package org.fourgeeks.gha.webclient.client.UI;

import java.util.List;

import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;
import org.fourgeeks.gha.webclient.client.buildinglocation.BuildingLocationModel;
import org.fourgeeks.gha.webclient.client.externalprovider.ExternalProviderModel;
import org.fourgeeks.gha.webclient.client.manufacturer.ManufacturerModel;
import org.fourgeeks.gha.webclient.client.obu.ObuModel;
import org.fourgeeks.gha.webclient.client.rolebase.BaseRoleModel;

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
	private List<RoleBase> roles;
	private List<ExternalProvider> externalProviders;

	{
		// Inititalization of the invalidation policy
		Timer t = new Timer() {
			@Override
			public void run() {
				invalidateCache();
			}
		};
		t.scheduleRepeating(CACHE_TIME);
	}

	protected void invalidateCache() {
		brands = null;
		manufacturers = null;
		buildingLocations = null;
		obus = null;
		roles = null;
		externalProviders = null;
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
	public void getBaseRoles(GHAAsyncCallback<List<RoleBase>> callback) {
		// Avoiding synchronization problems
		if (roles == null)
			getBaseRolesFromServer(callback);
		else
			callback.onSuccess(roles);

	}

	private void getBaseRolesFromServer(
			final GHAAsyncCallback<List<RoleBase>> callback) {
		BaseRoleModel.getAll(new GHAAsyncCallback<List<RoleBase>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<RoleBase> result) {
						roles = result;
						// Avoiding synchronization problems
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
	 */
	public void getBrands(GHAAsyncCallback<List<Brand>> callback) {
		// Avoiding synchronization problems
		if (brands == null)
			getBrandsFromServer(callback);
		else {
			callback.onSuccess(brands);
		}
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
	public void getManufacturesrs(GHAAsyncCallback<List<Manufacturer>> callback) {
		// Avoiding synchronization problems
		if (manufacturers == null)
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
}