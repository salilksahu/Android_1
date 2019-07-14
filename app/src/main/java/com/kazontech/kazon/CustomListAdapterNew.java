package com.kazontech.kazon;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapterNew extends ArrayAdapter<ListDataModel> implements View.OnClickListener{

    private ArrayList<ListDataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtBuySellN;
        TextView txtSymbolN;
        TextView txtCallPriceN;
        TextView txtCallTimeN;
        ConstraintLayout contRowNew;
    }

    public CustomListAdapterNew(ArrayList<ListDataModel> data, Context context) {
        super(context, R.layout.row_item_new, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        ListDataModel dataModel=(ListDataModel)object;

        Snackbar.make(v, "BUY given for " +dataModel.getSymbol() +
                " at a price of "+dataModel.getCallPrice() +" on : "+dataModel.getCallTime(), Snackbar.LENGTH_LONG)
                .setAction("No action", null).show();
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListDataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item_new, parent, false);
            viewHolder.txtBuySellN = (TextView) convertView.findViewById(R.id.txtBuySellN);
            viewHolder.txtSymbolN = (TextView) convertView.findViewById(R.id.txtSymbolN);
            viewHolder.txtCallTimeN = (TextView) convertView.findViewById(R.id.txtCallTimeN);
            viewHolder.txtCallPriceN = (TextView) convertView.findViewById(R.id.txtCallPriceN);
            viewHolder.contRowNew = (ConstraintLayout) convertView.findViewById(R.id.contRowNew);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;*/

        viewHolder.txtBuySellN.setText(dataModel.getBuySellFlag());
        viewHolder.txtSymbolN.setText(dataModel.getSymbol());
        viewHolder.txtCallTimeN.setText(dataModel.getCallTime());
        viewHolder.txtCallPriceN.setText(dataModel.getCallPrice());

        if(dataModel.getBuySellFlag().equals("BUY"))
        {
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                viewHolder.contRowNew.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_solid_green) );
                viewHolder.txtBuySellN.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_buy) );
                viewHolder.txtCallPriceN.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_white_green) );
            } else {
                viewHolder.contRowNew.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_solid_green));
                viewHolder.txtBuySellN.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_buy) );
                viewHolder.txtCallPriceN.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_white_green) );
            }
            viewHolder.txtBuySellN.setTextColor(ContextCompat.getColor(getContext(), R.color.colorGreen));
        }
        if(dataModel.getBuySellFlag().equals("SELL"))
        {
            final int sdk = android.os.Build.VERSION.SDK_INT;
            if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                viewHolder.contRowNew.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_solid_red) );
                viewHolder.txtBuySellN.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_sell) );
                viewHolder.txtCallPriceN.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_white_red) );
            } else {
                viewHolder.contRowNew.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_solid_red));
                viewHolder.txtBuySellN.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_sell) );
                viewHolder.txtCallPriceN.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.rec_rounded_white_red) );
            }
            viewHolder.txtBuySellN.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
        }

        //viewHolder.txtCallPrice.setOnClickListener(this);
        viewHolder.txtBuySellN.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}