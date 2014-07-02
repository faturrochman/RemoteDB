package kotakwarna.remotedb.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fedorvlasov.lazylist.ImageLoader;

import java.util.ArrayList;

import kotakwarna.remotedb.Main;
import kotakwarna.remotedb.R;
import kotakwarna.remotedb.beans.MaterialBean;

/**
 * Created by Fajar on 7/1/2014.
 */
public class CustomListViewMaterialAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<MaterialBean> materialBeans;
    private static LayoutInflater inflater = null;
    public ImageLoader imageLoader;

    public CustomListViewMaterialAdapter(Activity activity, ArrayList<MaterialBean> materialBeans) {
        this.activity = activity;
        this.materialBeans = materialBeans;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return materialBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public ImageView iv;
        public TextView tv;
        public Button b1,b2,b3;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (convertView==null){
            view = inflater.inflate(R.layout.custom_listrow_material, null);
            holder = new ViewHolder();
            holder.iv = (ImageView) view.findViewById(R.id.iv_material_img);
            holder.tv = (TextView) view.findViewById(R.id.tv_text);
            holder.b1 = (Button) view.findViewById(R.id.b_view_material);
            holder.b2 = (Button) view.findViewById(R.id.b_edit_material);
            holder.b3 = (Button) view.findViewById(R.id.b_del_material);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.tv.setText(
                "Material ID = "+materialBeans.get(position).getMaterial_id()+"\n"+
                "Material Code = "+materialBeans.get(position).getMaterial_code()+"\n"+
                "Material Name = "+materialBeans.get(position).getMaterial_name()+"\n"+
                "Material Remark = "+ materialBeans.get(position).getMaterial_remark()+"");
        ImageView imageView = holder.iv;
        imageLoader.DisplayImage(materialBeans.get(position).getMaterial_code(), imageView);

        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main main = (Main) activity;
                main.onChangeFragmentToMaterialDetailItem(materialBeans.get(position));
            }
        });

        Drawable drawable = activity.getResources().getDrawable(R.drawable.ic_view);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        ScaleDrawable sd = new ScaleDrawable(drawable, 0, 20, 20);
        holder.b1.setCompoundDrawables(sd.getDrawable(), null, null, null);

        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main main = (Main) activity;
                main.onChangeFragmentToMaterialDetailEdit(materialBeans.get(position));
            }
        });

        drawable = activity.getResources().getDrawable(R.drawable.ic_edit);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        sd = new ScaleDrawable(drawable, 0, 20, 20);
        holder.b2.setCompoundDrawables(sd.getDrawable(), null, null, null);

        drawable = activity.getResources().getDrawable(R.drawable.ic_delete);
        drawable.setBounds(0, 0, (int)(drawable.getIntrinsicWidth()*0.5),
                (int)(drawable.getIntrinsicHeight()*0.5));
        sd = new ScaleDrawable(drawable, 0, 20, 20);
        holder.b3.setCompoundDrawables(sd.getDrawable(), null, null, null);


        return view;
    }
}
