
package com.hoau.crm.module.customer.server.si.dc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>deleteCustomer complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="deleteCustomer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customer" type="{http://www.hoau.net/services/CRMServices}crmCustomer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "deleteCustomer", propOrder = {
    "customer"
})
public class DeleteCustomer {

    protected CrmCustomer customer;

    /**
     * 获取customer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link CrmCustomer }
     *     
     */
    public CrmCustomer getCustomer() {
        return customer;
    }

    /**
     * 设置customer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustomer }
     *     
     */
    public void setCustomer(CrmCustomer value) {
        this.customer = value;
    }

}
