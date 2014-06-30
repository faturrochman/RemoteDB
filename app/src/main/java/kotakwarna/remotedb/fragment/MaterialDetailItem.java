package kotakwarna.remotedb.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kotakwarna.remotedb.Main;
import kotakwarna.remotedb.R;
import kotakwarna.remotedb.beans.MaterialBean;
import kotakwarna.remotedb.conf.API;
import kotakwarna.remotedb.helper.DialogHelper;
import kotakwarna.remotedb.network.KotakwarnaHttpRequest;
import kotakwarna.remotedb.parser.MaterialParser;

/**
 * Created by Fajar on 6/26/2014.
 */
public class MaterialDetailItem extends Fragment{

    private View viewHierarchy;
    private LinearLayout ll_detail_item;
    private Button b_edit_detail_item;
    private MaterialBean materialBean;
    private ArrayList<MaterialBean> materialBeans;

    private DialogHelper dialogHelper;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewHierarchy = inflater.inflate(R.layout.f_material_detail_item, container, false);

        ll_detail_item = (LinearLayout) viewHierarchy.findViewById(R.id.ll_detail_item);
        b_edit_detail_item = (Button) viewHierarchy.findViewById(R.id.b_edit_detail_item);
        b_edit_detail_item.setOnClickListener(b_edit_onClick);

        materialBean = (MaterialBean) getArguments().getSerializable("mb");
        dialogHelper = new DialogHelper();
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");

        new DownloadTextTask().execute();
        return viewHierarchy;
    }

    View.OnClickListener b_edit_onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Main main = (Main) getActivity();
            main.onChangeFragmentToMaterialDetailEdit(materialBean);
        }
    };

    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls) {
            String url = API.MATERIAL_VIEW+materialBean.getMaterial_id();
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(url);
            String jSonResponse = httpRequest.requestGet();
            return jSonResponse;

        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog.show();

        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();
            MaterialParser materialParser = new MaterialParser();
            materialBeans = materialParser.parse(result);
            materialBean = materialBeans.get(0);

            ArrayList<String> materialDetails = new ArrayList<String>();
            materialDetails.add("Material ID = " + materialBean.getMaterial_id());
            materialDetails.add("Material Code = " + materialBean.getMaterial_code());
            materialDetails.add("Material Name = " + materialBean.getMaterial_name());
            materialDetails.add("Material Unit = " + materialBean.getMaterial_unit());
            materialDetails.add("Material Category = " + materialBean.getMaterial_category());
            materialDetails.add("Material Remark = " + materialBean.getMaterial_remark());
            materialDetails.add("Deleted = " + materialBean.getDeleted());
            materialDetails.add("Created By = " + materialBean.getCreated_by());
            materialDetails.add("Modified By = " + materialBean.getModified_by());
            materialDetails.add("Created On = " + materialBean.getCreated_on());
            materialDetails.add("Modified On = " + materialBean.getModified_on());
            materialDetails.add("Material Category ID = " + materialBean.getMaterial_category_id());
            materialDetails.add("Material Category Name = " + materialBean.getMaterial_category_name());
            materialDetails.add("Material Category Remark = " + materialBean.getMaterial_category_remark());

            for(String detail:materialDetails){
                TextView tv_materialDetail = new TextView(getActivity().getApplicationContext());
                tv_materialDetail.setText(detail);
                tv_materialDetail.setTextColor(Color.BLACK);
                tv_materialDetail.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                ll_detail_item.addView(tv_materialDetail);

            }

            b_edit_detail_item.setVisibility(View.VISIBLE);

        }
    }
}
