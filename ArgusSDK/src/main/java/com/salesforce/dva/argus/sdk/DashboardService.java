/*
 * Copyright (c) 2016, Salesforce.com, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of Salesforce.com nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.salesforce.dva.argus.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.salesforce.dva.argus.sdk.ArgusHttpClient.ArgusResponse;
import com.salesforce.dva.argus.sdk.ArgusService.EndpointService;
import com.salesforce.dva.argus.sdk.entity.Dashboard;
import com.salesforce.dva.argus.sdk.excpetions.TokenExpiredException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Provides methods to manipulate dashboards.
 *
 * @author  Tom Valine (tvaline@salesforce.com)
 */
public class DashboardService extends EndpointService {

    //~ Static fields/initializers *******************************************************************************************************************

    private static final String RESOURCE = "/dashboards";

    //~ Constructors *********************************************************************************************************************************

    /**
     * Creates a new DashboardService object.
     *
     * @param  client  The HTTP client for use by the service.
     */
    DashboardService(ArgusHttpClient client) {
        super(client);
    }

    //~ Methods **************************************************************************************************************************************

    /**
     * Creates a new dashboard.
     *
     * @param   dashboard  The dashboard to create.
     *
     * @return  The persisted dashboard.
     *
     * @throws  IOException  If the server cannot be reached.
     * @throws  TokenExpiredException   If the token sent along with the request has expired
     */
    public Dashboard createDashboard(Dashboard dashboard) throws IOException, TokenExpiredException {
        String requestUrl = RESOURCE;
        ArgusResponse response = getClient().executeHttpRequest(ArgusHttpClient.RequestType.POST, requestUrl, dashboard);

        assertValidResponse(response, requestUrl);
        return fromJson(response.getResult(), Dashboard.class);
    }

    /**
     * Creates a new dashboard.
     *
     * @param   dashboardId  The ID of the dashboard to update.
     * @param   dashboard    The dashboard to create.
     *
     * @return  The persisted dashboard.
     *
     * @throws  IOException  If the server cannot be reached.
     * @throws  TokenExpiredException   If the token sent along with the request has expired
     */
    public Dashboard updateDashboard(BigInteger dashboardId, Dashboard dashboard) throws IOException, TokenExpiredException {
        String requestUrl = RESOURCE + "/" + dashboardId.toString();
        ArgusResponse response = getClient().executeHttpRequest(ArgusHttpClient.RequestType.PUT, requestUrl, dashboard);

        assertValidResponse(response, requestUrl);
        return fromJson(response.getResult(), Dashboard.class);
    }

    /**
     * Deletes a dashboard.
     *
     * @param   dashboardId  The ID of the dashboard to delete.
     *
     * @throws  IOException  If the server cannot be reached.
     * @throws  TokenExpiredException   If the token sent along with the request has expired
     */
    public void deleteDashboard(BigInteger dashboardId) throws IOException, TokenExpiredException {
        String requestUrl = RESOURCE + "/" + dashboardId.toString();
        ArgusResponse response = getClient().executeHttpRequest(ArgusHttpClient.RequestType.DELETE, requestUrl, null);

        assertValidResponse(response, requestUrl);
    }

    /**
     * Returns the batch for the given ID.
     *
     * @param   dashboardId  The ID of the dashboard to retrieve.
     *
     * @return  The dashboard.
     *
     * @throws  IOException  If the server cannot be reached.
     * @throws  TokenExpiredException   If the token sent along with the request has expired
     */
    public Dashboard getDashboard(BigInteger dashboardId) throws IOException, TokenExpiredException {
        String requestUrl = RESOURCE + "/" + dashboardId.toString();
        ArgusResponse response = getClient().executeHttpRequest(ArgusHttpClient.RequestType.GET, requestUrl, null);

        assertValidResponse(response, requestUrl);
        return fromJson(response.getResult(), Dashboard.class);
    }

    /**
     * Returns the list of dashboards owned by the user.
     *
     * @return  The list of dashboards owned by the user.
     *
     * @throws  IOException  If the server cannot be reached.
     * @throws  TokenExpiredException   If the token sent along with the request has expired
     */
    public List<Dashboard> getDashboards() throws IOException, TokenExpiredException {
        String requestUrl = RESOURCE;
        ArgusResponse response = getClient().executeHttpRequest(ArgusHttpClient.RequestType.GET, requestUrl, null);

        assertValidResponse(response, requestUrl);
        return fromJson(response.getResult(), new TypeReference<List<Dashboard>>() { });
    }
}
/* Copyright (c) 2016, Salesforce.com, Inc.  All rights reserved. */
