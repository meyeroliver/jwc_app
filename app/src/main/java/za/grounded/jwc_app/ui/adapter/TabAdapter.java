package za.grounded.jwc_app.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.ui.fragments.ComboMealFragment;
import za.grounded.jwc_app.ui.fragments.FullMealFragment;
import za.grounded.jwc_app.ui.fragments.HalfMealFragment;

public class TabAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    public TabAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        /**
         * Check best to copy arrayList
         */
        this.fragmentList = new ArrayList<>(fragmentList);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        /*switch (position) {
            case 0:
                return new ComboMealFragment();
            case 1:
                return new FullMealFragment();
            default:
                return new HalfMealFragment();
        }*/
        return this.fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return this.fragmentList.size();
    }


}
