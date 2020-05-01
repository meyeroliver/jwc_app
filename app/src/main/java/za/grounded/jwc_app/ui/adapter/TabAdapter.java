package za.grounded.jwc_app.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import za.grounded.jwc_app.ui.fragments.ComboMealFragment;
import za.grounded.jwc_app.ui.fragments.FullMealFragment;
import za.grounded.jwc_app.ui.fragments.HalfMealFragment;

public class TabAdapter extends FragmentStateAdapter {

    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ComboMealFragment();
            case 1:
                return new FullMealFragment();
            default:
                return new HalfMealFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }


}
