package com.example.fragment.ui.home;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragment.R;
import com.example.fragment.adapters.ProductAdapter;
import com.example.fragment.databinding.FragmentHomeBinding;
import com.example.fragment.models.Product;
import com.example.fragment.ui.details.ProductDetailFragment;
import com.example.fragment.viewmodels.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Product> productList;
    private ProductAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recyclerViewProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        productList = new ArrayList<>();
        productList.add(new Product("111","Laptop Dell XPS", "High performance laptop", 65900000, R.drawable.dell_xps_17_9730));
        productList.add(new Product("112","iPhone 16 Pro Max", "Latest iPhone model", 31990000, R.drawable.ip_16pm_titan_sa_mac));
        productList.add(new Product("113","Tai nghe Sony", "Tai nghe chống ồn", 5890000, R.drawable.sony_wf_1000xm5));

        adapter = new ProductAdapter(productList, product -> {
            Bundle bundle = new Bundle();
            bundle.putString("id", product.getId());
            bundle.putString("name", product.getName());
            bundle.putString("description", product.getDescription());
            bundle.putInt("price", product.getPrice());
            bundle.putInt("image", product.getImageResId());

            NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment_container);
            navController.navigate(R.id.action_navigation_home_to_productDetailFragment, bundle);
        });

        recyclerView.setAdapter(adapter);
        return root;
    }
}


