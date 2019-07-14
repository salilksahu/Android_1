package com.kazontech.kazon;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomListAdapter extends ArrayAdapter<ListDataModel> implements View.OnClickListener{

    private ArrayList<ListDataModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtSymbol;
        TextView txtCallPrice;
        TextView txtCallTime;
        TextView txtLTP;
        TextView txtGainLoss;
    }

    public CustomListAdapter(ArrayList<ListDataModel> data, Context context) {
        super(context, R.layout.row_item, data);
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

        /*switch (v.getId())
        {
            case R.id.txtCallPrice:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }*/
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
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.txtSymbol = (TextView) convertView.findViewById(R.id.txtSymbol);
            viewHolder.txtCallTime = (TextView) convertView.findViewById(R.id.txtCallTime);
            viewHolder.txtCallPrice = (TextView) convertView.findViewById(R.id.txtCallPrice);
            viewHolder.txtLTP = (TextView) convertView.findViewById(R.id.txtLTP);
            viewHolder.txtGainLoss = (TextView) convertView.findViewById(R.id.txtGainLoss);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;*/

        viewHolder.txtSymbol.setText(dataModel.getSymbol());
        viewHolder.txtCallTime.setText(dataModel.getCallTime());
        viewHolder.txtCallPrice.setText(dataModel.getCallPrice());
        viewHolder.txtLTP.setText(dataModel.getLtp());
        viewHolder.txtGainLoss.setText(dataModel.getGainLoss()+ " %");
        //viewHolder.txtCallPrice.setOnClickListener(this);
        viewHolder.txtSymbol.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}