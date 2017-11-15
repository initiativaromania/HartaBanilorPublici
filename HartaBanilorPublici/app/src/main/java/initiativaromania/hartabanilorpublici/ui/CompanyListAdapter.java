package initiativaromania.hartabanilorpublici.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import initiativaromania.hartabanilorpublici.R;
import initiativaromania.hartabanilorpublici.comm.CommManager;
import initiativaromania.hartabanilorpublici.data.CompanyListItem;
import initiativaromania.hartabanilorpublici.data.ContractListItem;

/**
 * Created by claudiu on 11/15/17.
 */

public class CompanyListAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private final FragmentActivity context;
    private List<CompanyListItem> companies;

    public CompanyListAdapter(FragmentActivity context, List<CompanyListItem> companies) {
        this.context = context;
        this.companies = companies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.company_list_item, null);
        }

        TextView companyName = (TextView) convertView.findViewById(R.id.companyListName);
        companyName.setText((position + 1) + ". " + companies.get(position).name);

        convertView.setTag(companies.get(position));

        return convertView;
    }

    @Override
    public int getCount() {
        return companies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        CompanyListItem companyItem = (CompanyListItem) v.getTag();

        System.out.println("Click on company " + companyItem.id + " type " + companyItem.type);

//        Fragment contractFragment = new ContractFragment();
//        FragmentManager fragmentManager = context.getSupportFragmentManager();
//
//        /* Build Fragment Arguments */
//        Bundle bundle = new Bundle();
//        bundle.putInt(CommManager.BUNDLE_CONTRACT_ID, contractItem.id);
//        bundle.putInt(CommManager.BUNDLE_CONTRACT_TYPE, contractItem.type);
//        bundle.putInt(CommManager.BUNDLE_CONTRACT_PI_ID, contractItem.pi != null ?
//                contractItem.pi.id : 0);
//        bundle.putString(CommManager.BUNDLE_CONTRACT_PI_NAME, contractItem.pi != null ?
//                contractItem.pi.name : "");
//        bundle.putInt(CommManager.BUNDLE_CONTRACT_COMP_ID, contractItem.company != null ?
//                contractItem.company.id : 0);
//        bundle.putString(CommManager.BUNDLE_CONTRACT_COMP_NAME, contractItem.company != null ?
//                contractItem.company.name : "");
//        contractFragment.setArguments(bundle);
//
//
//        /* Got the Contract Fragment */
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.fragment_institution_layout, contractFragment).addToBackStack("TAG")
//                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}
