
package com.hoau.crm.module.job.server.wsdl.oa;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hoau.crm.module.job.server.wsdl.oa package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hoau.crm.module.job.server.wsdl.oa
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetJob }
     * 
     */
    public GetJob createGetJob() {
        return new GetJob();
    }

    /**
     * Create an instance of {@link GetOrgResponse }
     * 
     */
    public GetOrgResponse createGetOrgResponse() {
        return new GetOrgResponse();
    }

    /**
     * Create an instance of {@link GetJobResponse }
     * 
     */
    public GetJobResponse createGetJobResponse() {
        return new GetJobResponse();
    }

    /**
     * Create an instance of {@link GetEmpResponse }
     * 
     */
    public GetEmpResponse createGetEmpResponse() {
        return new GetEmpResponse();
    }

    /**
     * Create an instance of {@link GetEmp }
     * 
     */
    public GetEmp createGetEmp() {
        return new GetEmp();
    }

    /**
     * Create an instance of {@link GetOrg }
     * 
     */
    public GetOrg createGetOrg() {
        return new GetOrg();
    }

}
