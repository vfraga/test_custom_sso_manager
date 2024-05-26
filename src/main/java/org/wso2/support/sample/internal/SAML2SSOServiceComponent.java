package org.wso2.support.sample.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.http.HttpService;
import org.wso2.carbon.base.api.ServerConfigurationService;
import org.wso2.carbon.identity.sso.saml.util.SAMLSSOUtil;
import org.wso2.carbon.user.core.service.RealmService;

@Component(
        name = "ac.identity.application.authenticator.samlsso.component",
        service = SAML2SSOServiceComponent.class,
        immediate = true)
public class SAML2SSOServiceComponent {
    private static final Log log = LogFactory.getLog(SAML2SSOServiceComponent.class);

    @Activate
    protected void activate(ComponentContext ctxt) {
        log.info("Started Custom SAML SSO Manager.");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {
        log.info("Stopped Custom SAML SSO Manager.");
    }

    @Reference(
            name = "RealmService",
            service = org.wso2.carbon.user.core.service.RealmService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            bind = "setRealmService",
            unbind = "unsetRealmService")
    protected void setRealmService(RealmService realmService) {
        log.debug("RealmService is set in the AC SAML2 SSO Authenticator bundle");
        SAML2SSODataHolder.getInstance().setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {
        log.debug("RealmService is unset in the AC SAML2 SSO Authenticator bundle");
        SAML2SSODataHolder.getInstance().setRealmService(null);
    }

    @Reference(
            name = "ServerConfigurationService",
            service = org.wso2.carbon.base.api.ServerConfigurationService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            bind = "setServerConfigurationService",
            unbind = "unsetServerConfigurationService")
    protected void setServerConfigurationService(ServerConfigurationService serverConfigurationService) {
        log.debug("ServerConfigurationService is set in the AC SAML2 SSO Authenticator bundle");
        SAML2SSODataHolder.getInstance().setServerConfigurationService(serverConfigurationService);
    }

    protected void unsetServerConfigurationService(ServerConfigurationService serverConfigurationService) {
        log.debug("ServerConfigurationService is unset in the AC SAML2 SSO Authenticator bundle");
        SAML2SSODataHolder.getInstance().setServerConfigurationService(null);
    }

    @Reference(
            name = "osgi.httpservice",
            service = org.osgi.service.http.HttpService.class,
            cardinality = ReferenceCardinality.MANDATORY,
            policy = ReferencePolicy.DYNAMIC,
            bind = "setHttpService",
            unbind = "unsetHttpService")
    protected void setHttpService(HttpService httpService) {
        log.debug("HTTP Service is set in the SAML SSO bundle");
        SAMLSSOUtil.setHttpService(httpService);
    }

    protected void unsetHttpService(HttpService httpService) {
        log.debug("HTTP Service is unset in the SAML SSO bundle");
        SAMLSSOUtil.setHttpService(null);
    }

}
