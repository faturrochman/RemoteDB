package kotakwarna.remotedb.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kotakwarna.remotedb.Main;
import kotakwarna.remotedb.MainImpl;
import kotakwarna.remotedb.R;
import kotakwarna.remotedb.beans.MaterialBean;
import kotakwarna.remotedb.conf.API;
import kotakwarna.remotedb.factory.FragmentTag;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewHierarchy = inflater.inflate(R.layout.f_material_listview, container, false);
        myList = (ListView) viewHierarchy.findViewById(R.id.list);
        dialogHelper = new DialogHelper();
        progressDialog = dialogHelper.buildProgressDialog(getActivity(), "Loading...");

        new DownloadTextTask().execute();
        return viewHierarchy;
    }

    private class DownloadTextTask extends AsyncTask<String, Void, String> {

        String jSonResponse;
        protected String doInBackground(String... urls) {
            KotakwarnaHttpRequest httpRequest = new KotakwarnaHttpRequest(API.MATERIAL_ALL);
            jSonResponse = httpRequest.getResponse();
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

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.listview_item, listContent);
            myList.setAdapter(adapter);
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Main main = (Main) getActivity();
                    main.onChangeFragmentToMaterialDetailItem((MaterialBean) x.get(position));
                }
            });

        }
    }
}
