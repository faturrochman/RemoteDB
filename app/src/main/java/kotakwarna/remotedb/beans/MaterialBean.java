package kotakwarna.remotedb.beans;

import java.io.Serializable;

/**
 * Created by Fajar on 6/25/2014.
 */
public class MaterialBean implements Serializable{

    public static final String MATERIAL_ID = "material_id";
    public static final String MATERIAL_CODE = "material_code";
    public static final String MATERIAL_NAME = "material_name";
    public static final String MATERIAL_UNIT = "material_unit";
    public static final String MATERIAL_CATEGORY = "material_category";
    public static final String MATERIAL_REMARK = "material_remark";
    public static final String DELETED = "deleted";
    public static final String CREATED_BY = "created_by";
    public static final String MODIFIED_BY = "modified_by";
    public static final String CREATED_ON = "created_on";
    public static final String MODIFIED_ON = "modified_on";
    public static final String MATERIAL_CATEGORY_ID = "material_category_id";
    public static final String MATERIAL_CATEGORY_NAME = "material_category_name";
    public static final String MATERIAL_CATEGORY_REMARK = "material_category_remark";

    private String material_id;
    private String material_code;
    private String material_name;
    private String material_unit;
    private String material_category;
    private String material_remark;
    private String deleted;
    private String created_by;
    private String modified_by;
    private String created_on;
    private String modified_on;
    private String material_category_id;
    private String material_category_name;
    private String material_category_remark;

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_code() {
        return material_code;
    }

    public void setMaterial_code(String material_code) {
        this.material_code = material_code;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_unit() {
        return material_unit;
    }

    public void setMaterial_unit(String material_unit) {
        this.material_unit = material_unit;
    }

    public String getMaterial_category() {
        return material_category;
    }

    public void setMaterial_category(String material_category) {
        this.material_category = material_category;
    }

    public String getMaterial_remark() {
        return material_remark;
    }

    public void setMaterial_remark(String material_remark) {
        this.material_remark = material_remark;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public String getCreated_on() {
        return created_on;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    public String getModified_on() {
        return modified_on;
    }

    public void setModified_on(String modified_on) {
        this.modified_on = modified_on;
    }

    public String getMaterial_category_id() {
        return material_category_id;
    }

    public void setMaterial_category_id(String material_category_id) {
        this.material_category_id = material_category_id;
    }

    public String getMaterial_category_name() {
        return material_category_name;
    }

    public void setMaterial_category_name(String material_category_name) {
        this.material_category_name = material_category_name;
    }

    public String getMaterial_category_remark() {
        return material_category_remark;
    }

    public void setMaterial_category_remark(String material_category_remark) {
        this.material_category_remark = material_category_remark;
    }
}
