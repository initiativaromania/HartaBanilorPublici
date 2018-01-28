package initiativaromania.hartabanilorpublici.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import initiativaromania.hartabanilorpublici.R;

/**
 * Created by claudiu on 9/12/17.
 */

public class TabbedViewPageFragment extends Fragment implements ViewPager.OnPageChangeListener {

    public static final String TAB_ACHIZITII_DIRECTE    = "Achizitii directe";
    public static final String TAB_LICITATII            = "Licitatii";
    public static final String TAB_INSTITUTII_PUBLICE   = "Institutii publice";
    public static final String TAB_COMPANII             = "Companii";

    private String tabTitlesCompany[] = new String[]{TAB_ACHIZITII_DIRECTE, TAB_LICITATII,
            TAB_INSTITUTII_PUBLICE};
    private String tabTitlesInstitution[] = new String[]{TAB_ACHIZITII_DIRECTE, TAB_LICITATII,
            TAB_COMPANII};
    public String tabTitlesSearch[] = new String[]{TAB_INSTITUTII_PUBLICE, TAB_COMPANII,
            TAB_ACHIZITII_DIRECTE, TAB_LICITATII};

    public static EntityViewPageAdapter pageAdapter;
    public static ViewPager pager;
    public int pagePosition;
    private ArrayList<TabbedViewPageListener> pageListeners = new ArrayList<TabbedViewPageListener>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("On create view TabbedViewPageFragment fragment");
        View tabbedView = inflater.inflate(R.layout.fragment_viewpager, container, false);

        /* This has the actual pages */
        pager = (ViewPager) tabbedView.findViewById(R.id.viewpager);
        pager.setOnPageChangeListener(this);

        return tabbedView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        System.out.println("CREATING THE Participant view page fragment");
    }


    /* Set the viewpager with the corresponding fragments */
    public void setViewPager(int fragmentType) {
        System.out.println("Setting participant view pager to " + fragmentType);
        String tabTitles[];

        /* Get the fragments */
        List<Fragment> fragments = buildFragments(fragmentType);

        /* Set up the viewpager */
        switch (fragmentType) {
            case InstitutionFragment.CONTRACT_LIST_FOR_PUBLIC_INSTITUTION:
                tabTitles = tabTitlesInstitution;
                break;

            case InstitutionFragment.CONTRACT_LIST_FOR_COMPANY:
                tabTitles = tabTitlesCompany;
                break;

            case InstitutionFragment.CONTRACT_LIST_FOR_SEARCH:
                tabTitles = tabTitlesSearch;
                break;

            default:
                tabTitles = null;
                System.out.println("TabbedViewPageFragment no fragment type");
        }

        pageAdapter = new EntityViewPageAdapter(getFragmentManager(), fragments, tabTitles);
        pager.setAdapter(pageAdapter);
    }


    /* Build the viewpager fragments */
    private List<Fragment> buildFragments(int fragmentType) {
        List<Fragment> fList = new ArrayList<Fragment>();

        if (fragmentType == InstitutionFragment.CONTRACT_LIST_FOR_SEARCH) {
            fList.add(InstitutionListFragment.newInstance());
            fList.add(CompanyListFragment.newInstance());
            fList.add(ContractListFragment.newInstance());
            fList.add(ContractListFragment.newInstance());
            return fList;
        }

        fList.add(ContractListFragment.newInstance());
        fList.add(ContractListFragment.newInstance());

        if (fragmentType == InstitutionFragment.CONTRACT_LIST_FOR_PUBLIC_INSTITUTION)
            fList.add(CompanyListFragment.newInstance());
        else
            fList.add(InstitutionListFragment.newInstance());

        return fList;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pagePosition = position;

        for (TabbedViewPageListener pageListener : pageListeners)
            pageListener.onPageChanged(position);

        System.out.println("Page position is now " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void registerPageListener(TabbedViewPageListener pageListener) {
        pageListeners.add(pageListener);
    }
}