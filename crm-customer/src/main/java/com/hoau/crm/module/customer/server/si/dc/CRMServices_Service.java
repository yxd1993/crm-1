package com.hoau.crm.module.customer.server.si.dc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.14
 * 2016-01-07T10:47:14.423+08:00
 * Generated source version: 2.7.14
 * 
 */
@WebServiceClient(name = "CRMServices", 
                  wsdlLocation = "http://10.39.109.29:8080/services/CRMServices?wsdl",
                  targetNamespace = "http://www.hoau.net/services/CRMServices") 
public class CRMServices_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.hoau.net/services/CRMServices", "CRMServices");
    public final static QName CRMServicesPort = new QName("http://www.hoau.net/services/CRMServices", "CRMServicesPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.39.109.29:8080/services/CRMServices?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CRMServices_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.39.109.29:8080/services/CRMServices?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CRMServices_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CRMServices_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CRMServices_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns CRMServices
     */
    @WebEndpoint(name = "CRMServicesPort")
    public CRMServices getCRMServicesPort() {
        return super.getPort(CRMServicesPort, CRMServices.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CRMServices
     */
    @WebEndpoint(name = "CRMServicesPort")
    public CRMServices getCRMServicesPort(WebServiceFeature... features) {
        return super.getPort(CRMServicesPort, CRMServices.class, features);
    }

}
