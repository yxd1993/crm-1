
package com.hoau.crm.module.customer.server.si.dc;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hoau.crm.module.customer.server.si.dc package. 
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

    private final static QName _AddCustomerResponse_QNAME = new QName("http://www.hoau.net/services/CRMServices", "addCustomerResponse");
    private final static QName _ModifyCustomer_QNAME = new QName("http://www.hoau.net/services/CRMServices", "modifyCustomer");
    private final static QName _DeleteCustomerResponse_QNAME = new QName("http://www.hoau.net/services/CRMServices", "deleteCustomerResponse");
    private final static QName _AddCustomer_QNAME = new QName("http://www.hoau.net/services/CRMServices", "addCustomer");
    private final static QName _ModifyCustomerResponse_QNAME = new QName("http://www.hoau.net/services/CRMServices", "modifyCustomerResponse");
    private final static QName _DeleteCustomer_QNAME = new QName("http://www.hoau.net/services/CRMServices", "deleteCustomer");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hoau.crm.module.customer.server.si.dc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModifyCustomer }
     * 
     */
    public ModifyCustomer createModifyCustomer() {
        return new ModifyCustomer();
    }

    /**
     * Create an instance of {@link AddCustomerResponse }
     * 
     */
    public AddCustomerResponse createAddCustomerResponse() {
        return new AddCustomerResponse();
    }

    /**
     * Create an instance of {@link DeleteCustomerResponse }
     * 
     */
    public DeleteCustomerResponse createDeleteCustomerResponse() {
        return new DeleteCustomerResponse();
    }

    /**
     * Create an instance of {@link ModifyCustomerResponse }
     * 
     */
    public ModifyCustomerResponse createModifyCustomerResponse() {
        return new ModifyCustomerResponse();
    }

    /**
     * Create an instance of {@link AddCustomer }
     * 
     */
    public AddCustomer createAddCustomer() {
        return new AddCustomer();
    }

    /**
     * Create an instance of {@link DeleteCustomer }
     * 
     */
    public DeleteCustomer createDeleteCustomer() {
        return new DeleteCustomer();
    }

    /**
     * Create an instance of {@link ResultMsg }
     * 
     */
    public ResultMsg createResultMsg() {
        return new ResultMsg();
    }

    /**
     * Create an instance of {@link CrmCustomer }
     * 
     */
    public CrmCustomer createCrmCustomer() {
        return new CrmCustomer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hoau.net/services/CRMServices", name = "addCustomerResponse")
    public JAXBElement<AddCustomerResponse> createAddCustomerResponse(AddCustomerResponse value) {
        return new JAXBElement<AddCustomerResponse>(_AddCustomerResponse_QNAME, AddCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hoau.net/services/CRMServices", name = "modifyCustomer")
    public JAXBElement<ModifyCustomer> createModifyCustomer(ModifyCustomer value) {
        return new JAXBElement<ModifyCustomer>(_ModifyCustomer_QNAME, ModifyCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hoau.net/services/CRMServices", name = "deleteCustomerResponse")
    public JAXBElement<DeleteCustomerResponse> createDeleteCustomerResponse(DeleteCustomerResponse value) {
        return new JAXBElement<DeleteCustomerResponse>(_DeleteCustomerResponse_QNAME, DeleteCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hoau.net/services/CRMServices", name = "addCustomer")
    public JAXBElement<AddCustomer> createAddCustomer(AddCustomer value) {
        return new JAXBElement<AddCustomer>(_AddCustomer_QNAME, AddCustomer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyCustomerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hoau.net/services/CRMServices", name = "modifyCustomerResponse")
    public JAXBElement<ModifyCustomerResponse> createModifyCustomerResponse(ModifyCustomerResponse value) {
        return new JAXBElement<ModifyCustomerResponse>(_ModifyCustomerResponse_QNAME, ModifyCustomerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCustomer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.hoau.net/services/CRMServices", name = "deleteCustomer")
    public JAXBElement<DeleteCustomer> createDeleteCustomer(DeleteCustomer value) {
        return new JAXBElement<DeleteCustomer>(_DeleteCustomer_QNAME, DeleteCustomer.class, null, value);
    }

}
