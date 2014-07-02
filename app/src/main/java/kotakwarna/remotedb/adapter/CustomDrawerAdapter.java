package kotakwarna.remotedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kotakwarna.remotedb.R;

/**
 * Created by Fajar on 6/30/2014.
 */
public class CustomDrawerAdapter extends BaseAdapter {

    private static ArrayList<CustomDrawerAdapterItem> drawerAdapterItems;
    private LayoutInflater layoutInflater;

    public CustomDrawerAdapter(Context context, ArrayList<CustomDrawerAdapterItem> mDrawerItems) {
        this.drawerAdapterItems = mDrawerItems;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return drawerAdapterItems.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerAdapterItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview_drawer_item, null);
            holder = new ViewHolder();
            holder.tv_menutitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.iv_menuicon = (ImageView) convertView.findViewById(R.id.iv_right_icon);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_menutitle.setText(drawerAdapterItems.get(position).getTitle());
        holder.iv_menuicon.setImageResource(drawerAdapterItems.get(position).getIcon());


        return convertView;
    }

    static class ViewHolder {
        TextView tv_menutitle;
        ImageView iv_menuicon;
    }
}
