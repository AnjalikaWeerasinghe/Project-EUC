package lk.earth.earthuniversity.report.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MaterialCountByCategory {

    private Integer id;
    private String categoryname;
    private Long count;

    public MaterialCountByCategory() {}

    public MaterialCountByCategory(String categoryname, Long count){
        this.categoryname = categoryname;
        this.count = count;
    }

    @Id
    public Integer getId() { return id; }
    public void setId(Integer id) {this.id = id; }

    public String getCategoryname() { return categoryname; }
    public void setCategoryname(String categoryname) {this.categoryname = categoryname; }

    public Long getCount() { return count; }
    public void setCount(Long count) {this.count = count; }

}
