package org.wso2.support.sample.internal;

import org.wso2.carbon.base.api.ServerConfigurationService;
import org.wso2.carbon.user.core.service.RealmService;

public class SAML2SSODataHolder {
    private static final SAML2SSODataHolder INSTANCE = new SAML2SSODataHolder();

    private RealmService realmService;
    private ServerConfigurationService serverConfigurationService;

    private SAML2SSODataHolder() {
    }

    public static SAML2SSODataHolder getInstance() {
        return INSTANCE;
    }

    public RealmService getRealmService() {
        if (realmService == null) {
            throw new RuntimeException("RealmService is null.");
        }
        return realmService;
    }

    public void setRealmService(RealmService realmService) {
        this.realmService = realmService;
    }

    public ServerConfigurationService getServerConfigurationService() {
        return serverConfigurationService;
    }

    public void setServerConfigurationService(ServerConfigurationService serverConfigurationService) {
        this.serverConfigurationService = serverConfigurationService;
    }

}
