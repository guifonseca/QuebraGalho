package com.app.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Guilherme on 04/02/2017.
 */

public class CategoryImageAdapter extends BaseAdapter
{
    private TextView textView;
    private View     gridView;
    private Context  context;
    private final String[] arrCategorias;

    public CategoryImageAdapter( Context context, String[] arrCategorias )
    {
        this.context = context;
        this.arrCategorias = arrCategorias;
    }

    @Override
    public int getCount( )
    {
        return arrCategorias.length;
    }

    @Override
    public Object getItem( int i )
    {
        return null;
    }

    @Override
    public long getItemId( int i )
    {
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        if( convertView == null )
        {
            gridView = new View( context );

            // get layout from mobile.xml
            gridView = inflater.inflate( R.layout.image_category, null ) ;

            // set image based on selected text
            ImageView imageView = (ImageView)gridView.findViewById( R.id.grid_item_image );

            TextView textView = (TextView)gridView.findViewById( R.id.grid_item_label );
            textView.setText( arrCategorias[position] );

            String categorias = arrCategorias[position];

            if( categorias.equals( "Windows" ) )
            {
                imageView.setImageResource( R.drawable.windows_logo );
            }
            else if( categorias.equals( "iOS" ) )
            {
                imageView.setImageResource( R.drawable.ios_logo );
            }
            else if( categorias.equals( "Blackberry" ) )
            {
                imageView.setImageResource( R.drawable.blackberry_logo );
            }
            else
            {
                imageView.setImageResource( R.drawable.android_logo );
            }

        }
        else
            gridView = (View)convertView;

        return gridView;
    }
}
