
package com.hoau.crm.module.job.server.wsdl.oa;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2015-06-05T14:42:49.074+08:00
 * Generated source version: 2.7.11
 * 
 */
 
public class OrgEmpServicePortType_OrgEmpServiceHttpPort_Server{

    protected OrgEmpServicePortType_OrgEmpServiceHttpPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new OrgEmpServicePortTypeImpl();
        String address = "http://10.39.251.142/services/OrgEmpService";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new OrgEmpServicePortType_OrgEmpServiceHttpPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
