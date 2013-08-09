package org.fourgeeks.gha.webclient.server.brand;

import org.fourgeeks.gha.webclient.client.brand.GWTBrandService;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface GWTBrandServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see org.fourgeeks.gha.webclient.client.brand.GWTBrandService
     */
    void getAll( AsyncCallback<java.util.List<org.fourgeeks.gha.domain.gmh.Brand>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see org.fourgeeks.gha.webclient.client.brand.GWTBrandService
     */
    void getAll( int offset, int size, AsyncCallback<java.util.List<org.fourgeeks.gha.domain.gmh.Brand>> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static GWTBrandServiceAsync instance;

        public static final GWTBrandServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (GWTBrandServiceAsync) GWT.create( GWTBrandService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
