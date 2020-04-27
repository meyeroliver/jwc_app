package za.grounded.jwc_app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import za.grounded.jwc_app.R;
import za.grounded.jwc_app.adapter.ProductAdapter;
import za.grounded.jwc_app.models.Product;
import za.grounded.jwc_app.viewmodels.LandingViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComboMealFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private LandingViewModel landingViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        landingViewModel =  new ViewModelProvider(Objects.requireNonNull(getActivity())).get(LandingViewModel.class);
        View view = inflater.inflate(R.layout.fragment_combo_meal, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(ComboMealFragment.super.getContext()));
        recyclerView.setHasFixedSize(true);
        productAdapter = new ProductAdapter();
        recyclerView.setAdapter(productAdapter);

        landingViewModel.getCategorizedProjectList("combo").observe(getViewLifecycleOwner(), products -> {
            if (products != null) {
                productAdapter.setProductList(products);
                productAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        productAdapter.getOnClickedProduct().observe(getViewLifecycleOwner(),s -> {
            Toast.makeText(ComboMealFragment.super.getContext(), s, Toast.LENGTH_SHORT).show();
            landingViewModel.insertCartItem(s);
        });
    }
}
