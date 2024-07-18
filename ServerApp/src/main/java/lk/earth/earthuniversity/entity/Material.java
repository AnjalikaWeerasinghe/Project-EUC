package lk.earth.earthuniversity.entity;

import lk.earth.earthuniversity.util.RegexPattern;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;

@Entity
public class Material {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    @Pattern(regexp = "^[A-Z][a-z]+\\s(?:[A-Z][a-z]+\\s)?(?:[A-Z][a-z]+\\s)?$", message = "Invalid material name.")
    private String name;
    @Basic
    @Column(name = "itemcode")
    @Pattern(regexp = "^[RM]\\d{4}$", message = "Invalid Item code.")
    private String itemcode;
    @Basic
    @Column(name = "photo")
    private byte[] photo;
    @Basic
    @Column(name = "quantity")
    @RegexPattern(reg = "^\\d{2,4]$", msg = "Invalid Quantity")
    private Integer quantity;
    @Basic
    @Column(name = "purchasedate")
    @RegexPattern(reg = "^\\d{2}-\\d{2}-\\d{2}$", msg = "Invalid Date Format")
    private Date purchasedate;
    @Basic
    @Column(name = "unitprice")
    @RegexPattern(reg = "^\\d{2,4}(?:[.]\\d{2})?$", msg = "Invalid Unit Price")
    private BigDecimal unitprice;
    @Basic
    @Column(name = "rop")
    @RegexPattern(reg = "^\\d{2}$", msg = "Invalid ROP")
    private Integer rop;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id", nullable = false)
    private Subcategory subcategory;
    @ManyToOne
    @JoinColumn(name = "materialstatus_id", referencedColumnName = "id", nullable = false)
    private Materialstatus materialstatus;
    @ManyToOne
    @JoinColumn(name = "materialtype_id", referencedColumnName = "id", nullable = false)
    private Materialtype materialtype;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", nullable = false)
    private Warehouse warehouse;

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

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(Date purchasedate) {
        this.purchasedate = purchasedate;
    }

    public BigDecimal getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getRop() {
        return rop;
    }

    public void setRop(Integer rop) {
        this.rop = rop;
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

        Material material = (Material) o;

        if (id != null ? !id.equals(material.id) : material.id != null) return false;
        if (name != null ? !name.equals(material.name) : material.name != null) return false;
        if (itemcode != null ? !itemcode.equals(material.itemcode) : material.itemcode != null) return false;
        if (!Arrays.equals(photo, material.photo)) return false;
        if (quantity != null ? !quantity.equals(material.quantity) : material.quantity != null) return false;
        if (purchasedate != null ? !purchasedate.equals(material.purchasedate) : material.purchasedate != null) return false;
        if (unitprice != null ? !unitprice.equals(material.unitprice) : material.unitprice != null) return false;
        if (rop != null ? !rop.equals(material.rop) : material.rop != null) return false;
        if (description != null ? !description.equals(material.description) : material.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (itemcode != null ? itemcode.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(photo);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (purchasedate != null ? purchasedate.hashCode() : 0);
        result = 31 * result + (unitprice != null ? unitprice.hashCode() : 0);
        result = 31 * result + (rop != null ? rop.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public Materialstatus getMaterialstatus() {
        return materialstatus;
    }

    public void setMaterialstatus(Materialstatus materialstatus) {
        this.materialstatus = materialstatus;
    }

    public Materialtype getMaterialtype() {
        return materialtype;
    }

    public void setMaterialtype(Materialtype materialtype) {
        this.materialtype = materialtype;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Warehouse getWarehouseId() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
