package kotakwarna.remotedb.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;
import kotakwarna.remotedb.beans.MaterialBean;

/**
 * Created by Fajar on 6/25/2014.
 */
public class MaterialParser {

    private ArrayList<MaterialBean> materialBeans;

    public ArrayList<MaterialBean> parse(String responseJson) {

        materialBeans = new ArrayList<MaterialBean>();
        JSONArray jArr;
        JSONObject jObj;


        try {

            MaterialBean materialBean;
            Object json = new JSONTokener(responseJson).nextValue();
            if(json instanceof JSONObject){
                jObj = (JSONObject) json;
                materialBean = getItem(jObj);
                materialBeans.add(materialBean);
            }else if(json instanceof JSONArray){
                jArr = (JSONArray) json;
                for(int i=0; i<jArr.length(); i++){
                    jObj = jArr.getJSONObject(i);
                    materialBean = getItem(jObj);
                    materialBeans.add(materialBean);
                }
            }else{
                materialBeans.add(new MaterialBean());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return materialBeans;
    }

    private MaterialBean getItem(JSONObject jObj){

        MaterialBean materialBean = new MaterialBean();

        try {
            materialBean.setMaterial_id(jObj.isNull(MaterialBean.MATERIAL_ID) ? null : jObj.getString(MaterialBean.MATERIAL_ID));
            materialBean.setMaterial_code(jObj.isNull(MaterialBean.MATERIAL_CODE) ? null : jObj.getString(MaterialBean.MATERIAL_CODE));
            materialBean.setMaterial_name(jObj.isNull(MaterialBean.MATERIAL_NAME) ? null : jObj.getString(MaterialBean.MATERIAL_NAME));
            materialBean.setMaterial_unit(jObj.isNull(MaterialBean.MATERIAL_UNIT) ? null : jObj.getString(MaterialBean.MATERIAL_UNIT));
            materialBean.setMaterial_category(jObj.isNull(MaterialBean.MATERIAL_CATEGORY) ? null : jObj.getString(MaterialBean.MATERIAL_CATEGORY));
            materialBean.setMaterial_remark(jObj.isNull(MaterialBean.MATERIAL_REMARK) ? null : jObj.getString(MaterialBean.MATERIAL_REMARK));
            materialBean.setDeleted(jObj.isNull(MaterialBean.DELETED) ? null : jObj.getString(MaterialBean.DELETED));
            materialBean.setCreated_by(jObj.isNull(MaterialBean.CREATED_BY) ? null : jObj.getString(MaterialBean.CREATED_BY));
            materialBean.setModified_by(jObj.isNull(MaterialBean.MODIFIED_BY) ? null : jObj.getString(MaterialBean.MODIFIED_BY));
            materialBean.setCreated_on(jObj.isNull(MaterialBean.CREATED_ON) ? null : jObj.getString(MaterialBean.CREATED_ON));
            materialBean.setModified_on(jObj.isNull(MaterialBean.MODIFIED_ON) ? null : jObj.getString(MaterialBean.MODIFIED_ON));
            materialBean.setMaterial_category_id(jObj.isNull(MaterialBean.MATERIAL_CATEGORY_ID) ? null : jObj.getString(MaterialBean.MATERIAL_CATEGORY_ID));
            materialBean.setMaterial_category_name(jObj.isNull(MaterialBean.MATERIAL_CATEGORY_NAME) ? null : jObj.getString(MaterialBean.MATERIAL_CATEGORY_NAME));
            materialBean.setMaterial_category_remark(jObj.isNull(MaterialBean.MATERIAL_CATEGORY_REMARK) ? null : jObj.getString(MaterialBean.MATERIAL_CATEGORY_REMARK));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return materialBean;
    }

}
