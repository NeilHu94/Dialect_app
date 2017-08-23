package com.demo.neilhu.dialect;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by NeilHu on 2016/10/16.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    private int colorID;
    public WordAdapter(Context context,ArrayList<Word> objects ,int color) {
        super(context,0,objects);
        this.colorID =color;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.my_list_item, parent, false);
        }

        Word current = getItem(position);//找到传入的数据列表的当前位置，并且赋值给current（word类型）
        TextView dialect = (TextView)listItemView.findViewById(R.id.dialect);//找到并绑定自定义XML文件中的view
        dialect.setText(current.getDialectTranslation());//设置item中的变量数据

        TextView mandarin = (TextView)listItemView.findViewById(R.id.mandarin);
        mandarin.setText(current.getMandarinTranslation());

        ImageView imageView = (ImageView)listItemView.findViewById(R.id.word_image);
        if (current.hasImageView()){
            imageView.setImageResource(current.getWord_imageID());
            imageView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
        }
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),colorID);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}
