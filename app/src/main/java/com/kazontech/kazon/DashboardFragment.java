package com.kazontech.kazon;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class DashboardFragment extends Fragment {

    ArrayList<ListDataModel> dataModels;
    ArrayList<ListDataModel> dataModelsClosed;
    ArrayList<ListDataModel> dataModelsNew;
    ListView listView;
    ListView listViewNew;
    private static CustomListAdapter adapter;
    private static CustomListAdapterNew adapterNew;
    private static CustomListAdapterClosed adapterClosed;
    ViewPager viewPager;
    private int iLoaded = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(getActivity().getBaseContext());

        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(customPagerAdapter);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(iLoaded == 0) {
                    UpdateListItemsNew(position);
                    UpdateListItems(position);
                }
                iLoaded = 1;
            }

            @Override
            public void onPageSelected(int position) {
                UpdateListItemsNew(position);
                UpdateListItems(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    public void UpdateListItemsNew(int position)
    {
        if (position == 0)
            listViewNew = viewPager.findViewById(R.id.listNewCalls);
        else
            return;

        dataModelsNew= new ArrayList<>();

        dataModelsNew.add(new ListDataModel("BUY", "NELCO", "100.2","2019-01-02 10:25",
                "0", "0", "0", "0"));
        dataModelsNew.add(new ListDataModel("BUY", "TATACOFFEE", "10.2","2019-01-02 10:25",
                "0", "0", "0", "0"));
        dataModelsNew.add(new ListDataModel("SELL", "IBM", "100.2","2019-01-02 10:25",
                "0", "0", "0", "0"));
        dataModelsNew.add(new ListDataModel("BUY", "ADANIENT", "1000.2","2019-01-02 10:25",
                "0", "0", "0", "0"));

        adapterNew= new CustomListAdapterNew(dataModelsNew,getActivity().getApplicationContext());
        listViewNew.setAdapter(adapterNew);

        listViewNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ListDataModel dataModel= dataModelsNew.get(position);
                if (dataModel.getBuySellFlag() == "BUY") {
                    Snackbar.make(view, "BUY given for " +dataModel.getSymbol() +
                            " at a price of "+dataModel.getCallPrice() +" on : "+dataModel.getCallTime(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                }
                else
                {
                    Snackbar.make(view, "Sell given for " +dataModel.getSymbol() +
                            " at a price of "+dataModel.getCallPrice() +" on : "+dataModel.getCallTime(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                }

            }
        });
    }

    public void UpdateListItems(int position)
    {
        if (position == 0) {
            listView = viewPager.findViewById(R.id.listOpenCalls);

            dataModels= new ArrayList<>();

            dataModels.add(new ListDataModel("BUY", "LIC", "100.2","2019-01-02 10:25",
                    "100.5", "0.3", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "HDFC", "10.2","2019-01-02 10:25",
                    "10.5", "0.3", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "ICICBANK", "100.2","2019-01-02 10:25",
                    "100.5", "-0.35", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "ADANIPORTS", "1000.2","2019-01-02 10:25",
                    "1002.5", "1.35", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "LIC", "1800.2","2019-01-02 10:25",
                    "1795.5", "1.53", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "VERTIGORA", "85.2","2019-01-02 10:25",
                    "1795.5", "1.22", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "CHAMBALFERTI", "85.2","2019-01-02 10:25",
                    "1795.5", "-1.11", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "KALKALJIO", "85.2","2019-01-02 10:25",
                    "1795.5", "-1.11", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "LOPMINAH", "27562.2","2019-01-02 10:25",
                    "1795.5", "-1.11", "0", "0"));
            dataModels.add(new ListDataModel("BUY", "ULOMANIE", "8555.2","2019-01-02 10:25",
                    "1795.5", "1.51", "0", "0"));


            adapter= new CustomListAdapter(dataModels,getActivity().getApplicationContext());
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ListDataModel dataModel= dataModels.get(position);
                    Snackbar.make(view, "Buy given for " + dataModel.getSymbol() +
                            " at a price of " + dataModel.getCallPrice() + " on : " + dataModel.getCallTime(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                }
            });
        }
        else {
            listView = viewPager.findViewById(R.id.listClosed);

            dataModelsClosed= new ArrayList<>();

            dataModelsClosed.add(new ListDataModel("BUY", "LIC", "100.2","2019-01-02",
                    "0", "0.3", "100.5", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "HDFC", "10.2","2019-01-02",
                    "0", "0.3", "10.25", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "ICICBANK", "100.2","2019-01-02",
                    "0", "-0.35", "100.0", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "ADANIPORTS", "1000.2","2019-01-02",
                    "0", "1.35", "1013.5", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "LIC", "1800.2","2019-01-02",
                    "0", "1.53", "1825.55", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "VERTIGORA", "85.2","2019-01-02",
                    "0", "1.22", "86.5", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "CHAMBALFERTI", "85.2","2019-01-02",
                    "0", "-1.11", "84.5", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "KALKALJIO", "85.2","2019-01-02",
                    "0", "-1.11", "84.5", "2019-01-02"));
            dataModelsClosed.add(new ListDataModel("BUY", "LOPMINAH", "27562.2","2019-01-02",
                    "0", "0", "27562.25", "2019-01-025"));
            dataModelsClosed.add(new ListDataModel("BUY", "ULOMANIE", "8555.2","2019-01-02",
                    "0", "1.51", "100.5", "2019-01-02"));


            adapterClosed= new CustomListAdapterClosed(dataModelsClosed,getActivity().getApplicationContext());
            listView.setAdapter(adapterClosed);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    ListDataModel dataModel= dataModelsClosed.get(position);
                    Snackbar.make(view, dataModel.getSymbol() + " call given at " + dataModel.getCallPrice() +
                            " on "+dataModel.getCallTime() +", closed on "+dataModel.getSellTime() + " at "+ dataModel.getSellPrice(), Snackbar.LENGTH_LONG)
                            .setAction("No action", null).show();
                }
            });
        }

    }
}


