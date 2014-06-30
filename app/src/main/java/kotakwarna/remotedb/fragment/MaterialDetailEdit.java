package kotakwarna.remotedb.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kotakwarna.remotedb.Main;
import kotakwarna.remotedb.R;
import kotakwarna.remotedb.beans.MaterialBean;
import kotakwarna.remotedb.conf.API;
import kotakwarna.remotedb.helper.DialogHelper;
import kotakwarna.remotedb.network.KotakWarnaParam;
import kotakwarna.remotedb.network.KotakwarnaHttpRequest;

/**
 * Created by Fajar on 6/26/2014.
 */
public class MaterialDetailEdit extends Fragment {

    private View viewHierarchy;
    private LinearLayout ll_detail_edit;
    private MaterialBean materialBean;

    private Button b_submit_update_material;
    private DialogHelper dialogHelper;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHierarchy = inflater.inflate(R.layout.f_material_detail_edit, container, false);

        ll_detail_edit = (LinearLayout) viewHierarchy.findViewById(R.id.ll_detail_edit);
        b_submit_update_material = (Button) viewHierarchy.findViewById(R.id.b_submit_update_material);
        b_submit_update_material.setOnClickListener(b_submit_onClick);
        materialBean = (MaterialBean) getArguments().getSerializable("mb");
        dialogHelper = new DialogHelper();
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");

        List<MaterialEditable> materialEditables = setMaterialEditable();

        for(MaterialEditable mE:materialEditables){
            EditText et_materialEdit = new EditText(getActivity().getApplicationContext());
            et_materialEdit.setText(mE.value);
            et_materialEdit.setHint(mE.label);
            et_materialEdit.setTag(mE.tag);
            et_materialEdit.setTextColor(Color.BLACK);
            et_materialEdit.setBackgroundDrawable(getResources().getDrawable(R.drawable.edittext_rectangle));
            et_materialEdit.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)et_materialEdit.getLayoutParams();
            params.setMargins(0, 10, 0, 0); //substitute parameters for left, top, right, bottom
            et_materialEdit.setLayoutParams(params);

            ll_detail_edit.addView(et_materialEdit);
        }
        return viewHierarchy;
    }

    View.OnClickListener b_submit_onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new UpdateMaterial().execute();
        }
    };

    private class MaterialEditable{
        String tag;
        String label;
        String value;
    }

    private List<MaterialEditable> setMaterialEditable(){
        MaterialEditable materialEditable;
        List<MaterialEditable> materialEditables = new ArrayList<MaterialEditable>();

        /*Material Code*/
        materialEditable = new MaterialEditable();
        materialEditable.tag = MaterialBean.MATERIAL_CODE;
        materialEditable.label = "Material Code";
        materialEditable.value = materialBean.getMaterial_code();
        materialEditables.add(materialEditable);
        /*Material Name*/
        materialEditable = new MaterialEditable();
        materialEditable.tag = MaterialBean.MATERIAL_NAME;
        materialEditable.label = "Material Name";
        materialEditable.value = materialBean.getMaterial_name();
        materialEditables.add(materialEditable);
        /*Material Remark*/
        materialEditable = new MaterialEditable();
        materialEditable.tag = MaterialBean.MATERIAL_REMARK;
        materialEditable.label = "Material Remark";
        materialEditable.value = materialBean.getMaterial_remark();
        materialEditables.add(materialEditable);

       return materialEditables;

    }


    private class UpdateMaterial extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(API.MATERIAL_UPDATE);

            EditText et;
            KotakWarnaParam param;
            List<KotakWarnaParam> params = new ArrayList<KotakWarnaParam>();
            /*Material ID*/
            param = new KotakWarnaParam();
            et = (EditText) viewHierarchy.findViewWithTag(MaterialBean.MATERIAL_ID);
            param.setKey(MaterialBean.MATERIAL_ID);
            param.setValue(materialBean.getMaterial_id());
            params.add(param);
            /*Material Code*/
            param = new KotakWarnaParam();
            et = (EditText) viewHierarchy.findViewWithTag(MaterialBean.MATERIAL_CODE);
            param.setKey(MaterialBean.MATERIAL_CODE);
            param.setValue(et.getText().toString());
            params.add(param);
            /*Material Name*/
            param = new KotakWarnaParam();
            et = (EditText) viewHierarchy.findViewWithTag(MaterialBean.MATERIAL_NAME);
            param.setKey(MaterialBean.MATERIAL_NAME);
            param.setValue(et.getText().toString());
            params.add(param);
            /*Material Remark*/
            param = new KotakWarnaParam();
            et = (EditText) viewHierarchy.findViewWithTag(MaterialBean.MATERIAL_REMARK);
            param.setKey(MaterialBean.MATERIAL_REMARK);
            param.setValue(et.getText().toString());
            params.add(param);

            String response = httpRequest.requestPost(params);
            return response;

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();

            Log.i("Response HTTP Post : ", result);
            Toast.makeText(getActivity().getApplicationContext(), "Update Data to Server Success", Toast.LENGTH_LONG).show();
            Main main = (Main) getActivity();
            main.onChangeFragmentBackToFragmentBeforeEdit();

        }
    }
}
