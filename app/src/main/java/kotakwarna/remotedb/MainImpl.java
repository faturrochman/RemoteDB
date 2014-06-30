package kotakwarna.remotedb;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import kotakwarna.remotedb.beans.MaterialBean;
import kotakwarna.remotedb.factory.FragmentTag;
import kotakwarna.remotedb.fragment.MaterialDetailEdit;
import kotakwarna.remotedb.fragment.MaterialDetailItem;
import kotakwarna.remotedb.fragment.MaterialListView;

public class MainImpl extends Activity implements Main{

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        MaterialListView materialListView = new MaterialListView();
        if(!materialListView.isInLayout()) {
            ft.add(R.id.rl_main_layout, materialListView, FragmentTag.MATERIAL_LIST_VIEW);
            ft.commit();
        }
    }

    @Override
    public void onChangeFragment(String fragmentTag) {

    }

    @Override
    public void onChangeFragmentToMaterialDetailItem(MaterialBean materialBean) {

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        MaterialDetailItem materialDetailItem = new MaterialDetailItem();
        Bundle args = new Bundle();
        args.putSerializable("mb",materialBean);
        materialDetailItem.setArguments(args);
        if(!materialDetailItem.isInLayout()){
            ft.replace(R.id.rl_main_layout, materialDetailItem, FragmentTag.MATERIAL_DETAIL_ITEM);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onChangeFragmentToMaterialDetailEdit(MaterialBean materialBean) {
        fm = getFragmentManager();
        ft = fm.beginTransaction();

        MaterialDetailEdit materialDetailEdit = new MaterialDetailEdit();
        Bundle args = new Bundle();
        args.putSerializable("mb",materialBean);
        materialDetailEdit.setArguments(args);
        if(!materialDetailEdit.isInLayout()){
            ft.replace(R.id.rl_main_layout, materialDetailEdit, FragmentTag.MATERIAL_DETAIL_EDIT);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onChangeFragmentBackToFragmentBeforeEdit() {
        onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
