package kotakwarna.remotedb.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import kotakwarna.remotedb.Main;
import kotakwarna.remotedb.R;
import kotakwarna.remotedb.adapter.CustomDrawerAdapter;
import kotakwarna.remotedb.adapter.CustomDrawerAdapterItem;
import kotakwarna.remotedb.beans.MaterialBean;
import kotakwarna.remotedb.factory.FragmentTag;
import kotakwarna.remotedb.fragment.MaterialDetailEdit;
import kotakwarna.remotedb.fragment.MaterialDetailItem;
import kotakwarna.remotedb.fragment.MaterialListView;

/**
 * Created by Fajar on 6/30/2014.
 */
public class DrawerLayout_LeftMenu_Impl extends ActionBarActivity implements Main{

    private DrawerLayout drawerLayout;
    private ListView lv_drawer;

    private ActionBarDrawerToggle drawerToggle;

    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_drawerlayout_leftmenu);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        getActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_leftmenu);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.open_drawer,
                R.string.close_drawer
        ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setDrawerShadow(R.drawable.ic_drawer_shadow, GravityCompat.START);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        String[] drawerList = getResources().getStringArray(R.array.drawer_menu);
        TypedArray imgs = getResources().obtainTypedArray(R.array.drawer_menu_icon);
        ArrayList<CustomDrawerAdapterItem> drawerItems = new ArrayList<CustomDrawerAdapterItem>();
        int i = 0;
        for(String title:drawerList){
            CustomDrawerAdapterItem customDrawerItem = new CustomDrawerAdapterItem();
            customDrawerItem.setTitle(title);
            customDrawerItem.setIcon(imgs.getResourceId(i, -1));
            drawerItems.add(customDrawerItem);
            i++;
        }
        lv_drawer = (ListView) findViewById(R.id.lv_drawer);
        int width = ((getResources().getDisplayMetrics().widthPixels)*1)/2;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) lv_drawer.getLayoutParams();
        params.width = width;
        lv_drawer.setLayoutParams(params);
        lv_drawer.setAdapter(new CustomDrawerAdapter(getApplicationContext(), drawerItems));

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        MaterialListView materialListView = new MaterialListView();
        ft.remove(materialListView);
        ft.add(R.id.content_frame, materialListView, FragmentTag.MATERIAL_LIST_VIEW);
        ft.commit();

    }


    @Override
    public void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }

    @Override
    public void onChangeFragment(String fragmentTag) {

    }

    @Override
    public void onChangeFragmentToMaterialDetailItem(MaterialBean materialBean) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        MaterialDetailItem materialDetailItem = new MaterialDetailItem();
        Bundle args = new Bundle();
        args.putSerializable("mb",materialBean);
        materialDetailItem.setArguments(args);
        if(!materialDetailItem.isInLayout()){
            ft.replace(R.id.content_frame, materialDetailItem, FragmentTag.MATERIAL_DETAIL_ITEM);
            ft.setCustomAnimations(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onChangeFragmentToMaterialDetailEdit(MaterialBean materialBean) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        MaterialDetailEdit materialDetailEdit = new MaterialDetailEdit();
        Bundle args = new Bundle();
        args.putSerializable("mb",materialBean);
        materialDetailEdit.setArguments(args);
        if(!materialDetailEdit.isInLayout()){
            ft.replace(R.id.content_frame, materialDetailEdit, FragmentTag.MATERIAL_DETAIL_EDIT);
            ft.setCustomAnimations(R.anim.anim_slide_in_left,
                    R.anim.anim_slide_out_left);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onChangeFragmentBackToFragmentBeforeEdit() {
        onBackPressed();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
