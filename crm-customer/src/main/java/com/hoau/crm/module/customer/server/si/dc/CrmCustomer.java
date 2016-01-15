
package com.hoau.crm.module.customer.server.si.dc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>crmCustomer complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="crmCustomer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crm_khbh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crm_khzh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmguid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cwlxdh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cwlxr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fhfx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gs_yb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gsbh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="khbs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="khdj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="khhylx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="khlb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="khmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="khxz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="khzt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lxdh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lxr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobilphone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qhxxdz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qhyb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SFXSJLKH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sfyx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sjgsbh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ssjtgs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XSJLGH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XSJLXM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="xxdz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="yjgsdm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ywkssj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zkl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zybzfs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zysx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmCustomer", propOrder = {
    "bz",
    "cpmc",
    "crmKhbh",
    "crmKhzh",
    "crmguid",
    "cwlxdh",
    "cwlxr",
    "email",
    "fhfx",
    "gsYb",
    "gsbh",
    "khbs",
    "khdj",
    "khhylx",
    "khlb",
    "khmc",
    "khxz",
    "khzt",
    "lxdh",
    "lxr",
    "mobilphone",
    "qhxxdz",
    "qhyb",
    "sfxsjlkh",
    "sfyx",
    "sjgsbh",
    "ssjtgs",
    "xsjlgh",
    "xsjlxm",
    "xxdz",
    "yjgsdm",
    "ywkssj",
    "zkl",
    "zybzfs",
    "zysx"
})
public class CrmCustomer {

    protected String bz;
    protected String cpmc;
    @XmlElement(name = "crm_khbh")
    protected String crmKhbh;
    @XmlElement(name = "crm_khzh")
    protected String crmKhzh;
    protected String crmguid;
    protected String cwlxdh;
    protected String cwlxr;
    protected String email;
    protected String fhfx;
    @XmlElement(name = "gs_yb")
    protected String gsYb;
    protected String gsbh;
    protected String khbs;
    protected String khdj;
    protected String khhylx;
    protected String khlb;
    protected String khmc;
    protected String khxz;
    protected String khzt;
    protected String lxdh;
    protected String lxr;
    protected String mobilphone;
    protected String qhxxdz;
    protected String qhyb;
    @XmlElement(name = "SFXSJLKH")
    protected String sfxsjlkh;
    protected String sfyx;
    protected String sjgsbh;
    protected String ssjtgs;
    @XmlElement(name = "XSJLGH")
    protected String xsjlgh;
    @XmlElement(name = "XSJLXM")
    protected String xsjlxm;
    protected String xxdz;
    protected String yjgsdm;
    protected String ywkssj;
    protected String zkl;
    protected String zybzfs;
    protected String zysx;

    /**
     * 获取bz属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBz() {
        return bz;
    }

    /**
     * 设置bz属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBz(String value) {
        this.bz = value;
    }

    /**
     * 获取cpmc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpmc() {
        return cpmc;
    }

    /**
     * 设置cpmc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpmc(String value) {
        this.cpmc = value;
    }

    /**
     * 获取crmKhbh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmKhbh() {
        return crmKhbh;
    }

    /**
     * 设置crmKhbh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmKhbh(String value) {
        this.crmKhbh = value;
    }

    /**
     * 获取crmKhzh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmKhzh() {
        return crmKhzh;
    }

    /**
     * 设置crmKhzh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmKhzh(String value) {
        this.crmKhzh = value;
    }

    /**
     * 获取crmguid属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmguid() {
        return crmguid;
    }

    /**
     * 设置crmguid属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmguid(String value) {
        this.crmguid = value;
    }

    /**
     * 获取cwlxdh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCwlxdh() {
        return cwlxdh;
    }

    /**
     * 设置cwlxdh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCwlxdh(String value) {
        this.cwlxdh = value;
    }

    /**
     * 获取cwlxr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCwlxr() {
        return cwlxr;
    }

    /**
     * 设置cwlxr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCwlxr(String value) {
        this.cwlxr = value;
    }

    /**
     * 获取email属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * 获取fhfx属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFhfx() {
        return fhfx;
    }

    /**
     * 设置fhfx属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFhfx(String value) {
        this.fhfx = value;
    }

    /**
     * 获取gsYb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGsYb() {
        return gsYb;
    }

    /**
     * 设置gsYb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGsYb(String value) {
        this.gsYb = value;
    }

    /**
     * 获取gsbh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGsbh() {
        return gsbh;
    }

    /**
     * 设置gsbh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGsbh(String value) {
        this.gsbh = value;
    }

    /**
     * 获取khbs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKhbs() {
        return khbs;
    }

    /**
     * 设置khbs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKhbs(String value) {
        this.khbs = value;
    }

    /**
     * 获取khdj属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKhdj() {
        return khdj;
    }

    /**
     * 设置khdj属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKhdj(String value) {
        this.khdj = value;
    }

    /**
     * 获取khhylx属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKhhylx() {
        return khhylx;
    }

    /**
     * 设置khhylx属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKhhylx(String value) {
        this.khhylx = value;
    }

    /**
     * 获取khlb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKhlb() {
        return khlb;
    }

    /**
     * 设置khlb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKhlb(String value) {
        this.khlb = value;
    }

    /**
     * 获取khmc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKhmc() {
        return khmc;
    }

    /**
     * 设置khmc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKhmc(String value) {
        this.khmc = value;
    }

    /**
     * 获取khxz属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKhxz() {
        return khxz;
    }

    /**
     * 设置khxz属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKhxz(String value) {
        this.khxz = value;
    }

    /**
     * 获取khzt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKhzt() {
        return khzt;
    }

    /**
     * 设置khzt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKhzt(String value) {
        this.khzt = value;
    }

    /**
     * 获取lxdh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLxdh() {
        return lxdh;
    }

    /**
     * 设置lxdh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLxdh(String value) {
        this.lxdh = value;
    }

    /**
     * 获取lxr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLxr() {
        return lxr;
    }

    /**
     * 设置lxr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLxr(String value) {
        this.lxr = value;
    }

    /**
     * 获取mobilphone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilphone() {
        return mobilphone;
    }

    /**
     * 设置mobilphone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilphone(String value) {
        this.mobilphone = value;
    }

    /**
     * 获取qhxxdz属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQhxxdz() {
        return qhxxdz;
    }

    /**
     * 设置qhxxdz属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQhxxdz(String value) {
        this.qhxxdz = value;
    }

    /**
     * 获取qhyb属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQhyb() {
        return qhyb;
    }

    /**
     * 设置qhyb属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQhyb(String value) {
        this.qhyb = value;
    }

    /**
     * 获取sfxsjlkh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSFXSJLKH() {
        return sfxsjlkh;
    }

    /**
     * 设置sfxsjlkh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSFXSJLKH(String value) {
        this.sfxsjlkh = value;
    }

    /**
     * 获取sfyx属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSfyx() {
        return sfyx;
    }

    /**
     * 设置sfyx属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSfyx(String value) {
        this.sfyx = value;
    }

    /**
     * 获取sjgsbh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSjgsbh() {
        return sjgsbh;
    }

    /**
     * 设置sjgsbh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSjgsbh(String value) {
        this.sjgsbh = value;
    }

    /**
     * 获取ssjtgs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSsjtgs() {
        return ssjtgs;
    }

    /**
     * 设置ssjtgs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSsjtgs(String value) {
        this.ssjtgs = value;
    }

    /**
     * 获取xsjlgh属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSJLGH() {
        return xsjlgh;
    }

    /**
     * 设置xsjlgh属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSJLGH(String value) {
        this.xsjlgh = value;
    }

    /**
     * 获取xsjlxm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXSJLXM() {
        return xsjlxm;
    }

    /**
     * 设置xsjlxm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXSJLXM(String value) {
        this.xsjlxm = value;
    }

    /**
     * 获取xxdz属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXxdz() {
        return xxdz;
    }

    /**
     * 设置xxdz属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXxdz(String value) {
        this.xxdz = value;
    }

    /**
     * 获取yjgsdm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYjgsdm() {
        return yjgsdm;
    }

    /**
     * 设置yjgsdm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYjgsdm(String value) {
        this.yjgsdm = value;
    }

    /**
     * 获取ywkssj属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYwkssj() {
        return ywkssj;
    }

    /**
     * 设置ywkssj属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYwkssj(String value) {
        this.ywkssj = value;
    }

    /**
     * 获取zkl属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZkl() {
        return zkl;
    }

    /**
     * 设置zkl属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZkl(String value) {
        this.zkl = value;
    }

    /**
     * 获取zybzfs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZybzfs() {
        return zybzfs;
    }

    /**
     * 设置zybzfs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZybzfs(String value) {
        this.zybzfs = value;
    }

    /**
     * 获取zysx属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZysx() {
        return zysx;
    }

    /**
     * 设置zysx属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZysx(String value) {
        this.zysx = value;
    }

}
