package com.hoau.crm.module.common.server.wsdl.obh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>registerUserResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="registerUserResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.server.crm.module.obh.hoau.com/}registerUserResModel" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registerUserResponse", propOrder = {
    "_return"
})
public class RegisterUserResponse {

    @XmlElement(name = "return")
    protected RegisterUserResModel _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RegisterUserResModel }
     *     
     */
    public RegisterUserResModel getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterUserResModel }
     *     
     */
    public void setReturn(RegisterUserResModel value) {
        this._return = value;
    }

}
