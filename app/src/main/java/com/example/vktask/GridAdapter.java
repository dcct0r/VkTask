package com.example.vktask;

import android.content.Context;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    private Context context;
    private String[] userName;
    private Integer[] mainUserIcon;
    private LayoutInflater layoutInflater;
    private ImageView imageView, gridImageViewBackground;
    private TextView textView;
    public GridAdapter(Context context, List<String> userName, Integer[] mainUserIcon) {
        this.context = context;
        this.userName = userName.toArray(new String[0]);
        this.mainUserIcon = mainUserIcon;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userName.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.grid_items, null);
        if(convertView == null) {
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() / 2 - 16));
        }
        else {
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, parent.getHeight() / 2 - 16));
        }

        imageView = convertView.findViewById(R.id.gridImageIcon);
        gridImageViewBackground = convertView.findViewById(R.id.gridBackground);
        textView = convertView.findViewById(R.id.gridUserName);
        imageView.setImageResource(mainUserIcon[position]);
        textView.setText(userName[position]);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            gridImageViewBackground.setBackgroundResource(mainUserIcon[position]);
            gridImageViewBackground.setRenderEffect(RenderEffect.createBlurEffect(500f, 500f, Shader.TileMode.CLAMP));
        }

        return convertView;
    }
}
