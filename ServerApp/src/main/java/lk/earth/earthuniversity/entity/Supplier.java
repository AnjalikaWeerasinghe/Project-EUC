package lk.earth.earthuniversity.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

@Entity
public class Supplier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "regid")
    private String regid;
    @Basic
    @Column(name = "contactperson")
    private String contactperson;
    @Basic
    @Column(name = "photo")
    private byte[] photo;
    @Basic
    @Column(name = "contactmobile")
    private String contactmobile;
    @Basic
    @Column(name = "contactland")
    private String contactland;
    @Basic
    @Column(name = "doregistered")
    private Date doregistered;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "suppliertype_id", referencedColumnName = "id", nullable = false)
    private Suppliertype suppliertype;
    @ManyToOne
    @JoinColumn(name = "supplierstatus_id", referencedColumnName = "id", nullable = false)
    private Supplierstatus supplierstatus;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Supcountry supcountry;

    public Supplier(){}

    public Supplier(Integer id, String contactperson){
        this.id = id;
        this.contactperson = contactperson;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getContactmobile() {
        return contactmobile;
    }

    public void setContactmobile(String contactmobile) {
        this.contactmobile = contactmobile;
    }

    public String getContactland() {
        return contactland;
    }

    public void setContactland(String contactland) {
        this.contactland = contactland;
    }

    public Date getDoregistered() {
        return doregistered;
    }

    public void setDoregistered(Date doregistered) {
        this.doregistered = doregistered;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (id != null ? !id.equals(supplier.id) : supplier.id != null) return false;
        if (name != null ? !name.equals(supplier.name) : supplier.name != null) return false;
        if (regid != null ? !regid.equals(supplier.regid) : supplier.regid != null) return false;
        if (contactperson != null ? !contactperson.equals(supplier.contactperson) : supplier.contactperson != null)
            return false;
        if (!Arrays.equals(photo, supplier.photo)) return false;
        if (contactmobile != null ? !contactmobile.equals(supplier.contactmobile) : supplier.contactmobile != null)
            return false;
        if (contactland != null ? !contactland.equals(supplier.contactland) : supplier.contactland != null)
            return false;
        if (doregistered != null ? !doregistered.equals(supplier.doregistered) : supplier.doregistered != null)
            return false;
        if (address != null ? !address.equals(supplier.address) : supplier.address != null) return false;
        if (email != null ? !email.equals(supplier.email) : supplier.email != null) return false;
        if (description != null ? !description.equals(supplier.description) : supplier.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (regid != null ? regid.hashCode() : 0);
        result = 31 * result + (contactperson != null ? contactperson.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (contactmobile != null ? contactmobile.hashCode() : 0);
        result = 31 * result + (contactland != null ? contactland.hashCode() : 0);
        result = 31 * result + (doregistered != null ? doregistered.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Suppliertype getSuppliertype() {
        return suppliertype;
    }

    public void setSuppliertype(Suppliertype suppliertype) {
        this.suppliertype = suppliertype;
    }

    public Supplierstatus getSupplierstatus() {
        return supplierstatus;
    }

    public void setSupplierstatus(Supplierstatus supplierstatus) {
        this.supplierstatus = supplierstatus;
    }

    public Supcountry getSupcountry() {
        return supcountry;
    }

    public void setSupcountry(Supcountry supcountry) {
        this.supcountry = supcountry;
    }
}
