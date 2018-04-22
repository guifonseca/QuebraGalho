package com.app.myapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.myapp.R;
import com.app.myapp.model.Categorias;
import com.app.myapp.model.Servicos;

public class ServiceListAdapter extends BaseAdapter {

    private TextView textView;
    private View     gridView;
    private Context context;
    private final Servicos servicos;

    public ServiceListAdapter( Context context, Servicos servicos )
    {
        this.context = context;
        this.servicos = servicos;
    }

    @Override
    public int getCount( )
    {
        return servicos.getServicos().size( );
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
            gridView = inflater.inflate( R.layout.lista_servicos, null ) ;

            // set image based on selected text
//            ImageView imageView = (ImageView)gridView.findViewById( R.id.grid_servicos_item_img );
//            imageView.setImageResource( R.drawable.android_logo );

            TextView textView = (TextView)gridView.findViewById(R.id.lbTitulo);
            textView.setText(servicos.getServicos().get(position).getTitulo());

            textView = (TextView)gridView.findViewById(R.id.lbValor);
            textView.setText(servicos.getServicos().get(position).getValor().toString());

            textView = (TextView)gridView.findViewById(R.id.lbDataCriacao);
            textView.setText("teste");

//             byte[] bMapArray = servicos.getServicos().get( position ).getImagem( ).getBytesImagem( );

//            Bitmap bMap = BitmapFactory.decodeByteArray(bMapArray, 0, bMapArray.length);
//            imageView.setImageBitmap( bMap );
        }
        else
            gridView = (View)convertView;

        return gridView;
    }
}
