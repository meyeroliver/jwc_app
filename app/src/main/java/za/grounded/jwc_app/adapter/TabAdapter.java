package za.grounded.jwc_app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import za.grounded.jwc_app.fragments.ComboMealFragment;
import za.grounded.jwc_app.fragments.FullMealFragment;
import za.grounded.jwc_app.fragments.HalfMealFragment;

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
