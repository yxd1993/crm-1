package com.hoau.crm.module.common.server.wsdl.obh;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hoau.crm.module.common.server.wsdl.obh package. 
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

    private final static QName _UpdateUserInfo_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "updateUserInfo");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "registerUserResponse");
    private final static QName _UpdateUserInfoResponse_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "updateUserInfoResponse");
    private final static QName _QueryNotSyncInfosResponse_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "queryNotSyncInfosResponse");
    private final static QName _QueryNotSyncInfos_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "queryNotSyncInfos");
    private final static QName _UpdateShipperInfo_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "updateShipperInfo");
    private final static QName _RegisterUser_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "registerUser");
    private final static QName _UpdateShipperInfoResponse_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "updateShipperInfoResponse");
    private final static QName _UpdateSyncSuccessInfo_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "updateSyncSuccessInfo");
    private final static QName _UpdateSyncSuccessInfoResponse_QNAME = new QName("http://service.server.crm.module.obh.hoau.com/", "updateSyncSuccessInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hoau.crm.module.common.server.wsdl.obh
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateSyncSuccessInfoResponse }
     * 
     */
    public UpdateSyncSuccessInfoResponse createUpdateSyncSuccessInfoResponse() {
        return new UpdateSyncSuccessInfoResponse();
    }

    /**
     * Create an instance of {@link UpdateSyncSuccessInfo }
     * 
     */
    public UpdateSyncSuccessInfo createUpdateSyncSuccessInfo() {
        return new UpdateSyncSuccessInfo();
    }

    /**
     * Create an instance of {@link UpdateShipperInfoResponse }
     * 
     */
    public UpdateShipperInfoResponse createUpdateShipperInfoResponse() {
        return new UpdateShipperInfoResponse();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link UpdateShipperInfo }
     * 
     */
    public UpdateShipperInfo createUpdateShipperInfo() {
        return new UpdateShipperInfo();
    }

    /**
     * Create an instance of {@link QueryNotSyncInfos }
     * 
     */
    public QueryNotSyncInfos createQueryNotSyncInfos() {
        return new QueryNotSyncInfos();
    }

    /**
     * Create an instance of {@link QueryNotSyncInfosResponse }
     * 
     */
    public QueryNotSyncInfosResponse createQueryNotSyncInfosResponse() {
        return new QueryNotSyncInfosResponse();
    }

    /**
     * Create an instance of {@link UpdateUserInfoResponse }
     * 
     */
    public UpdateUserInfoResponse createUpdateUserInfoResponse() {
        return new UpdateUserInfoResponse();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link UpdateUserInfo }
     * 
     */
    public UpdateUserInfo createUpdateUserInfo() {
        return new UpdateUserInfo();
    }

    /**
     * Create an instance of {@link RegisterUserResModel }
     * 
     */
    public RegisterUserResModel createRegisterUserResModel() {
        return new RegisterUserResModel();
    }

    /**
     * Create an instance of {@link ResResult }
     * 
     */
    public ResResult createResResult() {
        return new ResResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "updateUserInfo")
    public JAXBElement<UpdateUserInfo> createUpdateUserInfo(UpdateUserInfo value) {
        return new JAXBElement<UpdateUserInfo>(_UpdateUserInfo_QNAME, UpdateUserInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "updateUserInfoResponse")
    public JAXBElement<UpdateUserInfoResponse> createUpdateUserInfoResponse(UpdateUserInfoResponse value) {
        return new JAXBElement<UpdateUserInfoResponse>(_UpdateUserInfoResponse_QNAME, UpdateUserInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryNotSyncInfosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "queryNotSyncInfosResponse")
    public JAXBElement<QueryNotSyncInfosResponse> createQueryNotSyncInfosResponse(QueryNotSyncInfosResponse value) {
        return new JAXBElement<QueryNotSyncInfosResponse>(_QueryNotSyncInfosResponse_QNAME, QueryNotSyncInfosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryNotSyncInfos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "queryNotSyncInfos")
    public JAXBElement<QueryNotSyncInfos> createQueryNotSyncInfos(QueryNotSyncInfos value) {
        return new JAXBElement<QueryNotSyncInfos>(_QueryNotSyncInfos_QNAME, QueryNotSyncInfos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateShipperInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "updateShipperInfo")
    public JAXBElement<UpdateShipperInfo> createUpdateShipperInfo(UpdateShipperInfo value) {
        return new JAXBElement<UpdateShipperInfo>(_UpdateShipperInfo_QNAME, UpdateShipperInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateShipperInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "updateShipperInfoResponse")
    public JAXBElement<UpdateShipperInfoResponse> createUpdateShipperInfoResponse(UpdateShipperInfoResponse value) {
        return new JAXBElement<UpdateShipperInfoResponse>(_UpdateShipperInfoResponse_QNAME, UpdateShipperInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSyncSuccessInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "updateSyncSuccessInfo")
    public JAXBElement<UpdateSyncSuccessInfo> createUpdateSyncSuccessInfo(UpdateSyncSuccessInfo value) {
        return new JAXBElement<UpdateSyncSuccessInfo>(_UpdateSyncSuccessInfo_QNAME, UpdateSyncSuccessInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateSyncSuccessInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.server.crm.module.obh.hoau.com/", name = "updateSyncSuccessInfoResponse")
    public JAXBElement<UpdateSyncSuccessInfoResponse> createUpdateSyncSuccessInfoResponse(UpdateSyncSuccessInfoResponse value) {
        return new JAXBElement<UpdateSyncSuccessInfoResponse>(_UpdateSyncSuccessInfoResponse_QNAME, UpdateSyncSuccessInfoResponse.class, null, value);
    }

}
