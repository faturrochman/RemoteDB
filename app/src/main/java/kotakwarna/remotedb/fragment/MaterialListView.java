package kotakwarna.remotedb.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotakwarna.remotedb.R;
import kotakwarna.remotedb.adapter.CustomListViewMaterialAdapter;
import kotakwarna.remotedb.beans.MaterialBean;
import kotakwarna.remotedb.conf.API;
import kotakwarna.remotedb.helper.DialogHelper;
import kotakwarna.remotedb.network.KotakwarnaHttpRequest;
import kotakwarna.remotedb.parser.MaterialParser;

/**
 * Created by Fajar on 6/26/2014.
 */
public class MaterialListView extends Fragment {

    private View viewHierarchy;
    private ArrayList<MaterialBean> materialBeans;
    private ListView myList;
    private DialogHelper dialogHelper;
    private ProgressDialog progressDialog;
    private Button b_add_material;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHierarchy = inflater.inflate(R.layout.f_material_listview, container, false);
        myList = (ListView) viewHierarchy.findViewById(R.id.list);
        dialogHelper = new DialogHelper();
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");


        b_add_material = (Button) viewHierarchy.findViewById(R.id.b_add_material);

        Drawable drawable = getActivity().getResources().getDrawable(R.drawable.ic_add);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, 20, 20);
        b_add_material.setCompoundDrawables(sd.getDrawable(), null, null, null);

        new DownloadTextTask().execute();
        return viewHierarchy;
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        String jSonResponse;
        protected String doInBackground(String... urls) {
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(API.MATERIAL_ALL);
            jSonResponse = httpRequest.requestGet();
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
            materialBeans = materialParser.parse(jSonResponse);


            String[] listContent = new String[materialBeans.size()];
            int i = 0;
            final Map x = new HashMap();

            for(MaterialBean mb:materialBeans){

                x.put(i, mb);
                listContent[i] = mb.getMaterial_name();
                Log.i("MATERIAL", "Name : " + mb.getMaterial_name());
                i++;
            }

            CustomListViewMaterialAdapter adapter = new CustomListViewMaterialAdapter(getActivity(), materialBeans);

            myList.setAdapter(adapter);

        }
    }
}
