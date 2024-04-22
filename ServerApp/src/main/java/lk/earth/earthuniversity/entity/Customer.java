package lk.earth.earthuniversity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "regid")
    @Pattern(regexp = "^\\d{4}$", message = "Invalid Registration ID")
    private String regid;
    @Basic
    @Column(name = "contactperson")
    private String contactperson;
    @Basic
    @Column(name = "contactmobile")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid Mobile Number")
    private String contactmobile;
    @Basic
    @Column(name = "contactland")
    @Pattern(regexp = "^\\d{0,10}$", message = "Invalid Landphone Number")
    private String contactland;
    @Basic
    @Column(name = "doregistered")
    private Date doregistered;
    @Basic
    @Column(name = "address")
    @Pattern(regexp = "^([\\w\\/\\-,\\s]{2,})$", message = "Invalid Address")
    private String address;
    @Basic
    @Column(name = "email")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid Email Address")
    private String email;
    @Basic
    @Column(name = "description")
    @Pattern(regexp = "^.*$", message = "Invalid Description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "customerstatus_id", referencedColumnName = "id", nullable = false)
    private Customerstatus customerstatus;
    @ManyToOne
    @JoinColumn(name = "customertype_id", referencedColumnName = "id", nullable = false)
    private Customertype customertype;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private Country country;
    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id", nullable = false)
    private Gender gender;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Collection<User> users;

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

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (regid != null ? !regid.equals(customer.regid) : customer.regid != null) return false;
        if (contactperson != null ? !contactperson.equals(customer.contactperson) : customer.contactperson != null)
            return false;
        if (contactmobile != null ? !contactmobile.equals(customer.contactmobile) : customer.contactmobile != null)
            return false;
        if (contactland != null ? !contactland.equals(customer.contactland) : customer.contactland != null)
            return false;
        if (doregistered != null ? !doregistered.equals(customer.doregistered) : customer.doregistered != null)
            return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (description != null ? !description.equals(customer.description) : customer.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (regid != null ? regid.hashCode() : 0);
        result = 31 * result + (contactperson != null ? contactperson.hashCode() : 0);
        result = 31 * result + (contactmobile != null ? contactmobile.hashCode() : 0);
        result = 31 * result + (contactland != null ? contactland.hashCode() : 0);
        result = 31 * result + (doregistered != null ? doregistered.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Customerstatus getCustomerstatus() {
        return customerstatus;
    }

    public void setCustomerstatus(Customerstatus customerstatus) {
        this.customerstatus = customerstatus;
    }

    public Customertype getCustomertype() {
        return customertype;
    }

    public void setCustomertype(Customertype customertype) {
        this.customertype = customertype;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
