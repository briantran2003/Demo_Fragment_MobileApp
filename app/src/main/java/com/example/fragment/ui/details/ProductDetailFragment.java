package com.example.fragment.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.fragment.R;
import com.example.fragment.databinding.FragmentProductDetailBinding;
import com.example.fragment.models.Product;
import com.example.fragment.viewmodels.SharedViewModel;
import com.google.android.material.snackbar.Snackbar;

public class ProductDetailFragment extends Fragment {
    private FragmentProductDetailBinding binding;
    private SharedViewModel sharedViewModel;
    private Product currentProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Lấy SharedViewModel
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Nhận dữ liệu sản phẩm từ Bundle
        if (getArguments() != null) {
            currentProduct = new Product(
                    getArguments().getString("id"),
                    getArguments().getString("name"),
                    getArguments().getString("description"),
                    getArguments().getInt("price"),
                    getArguments().getInt("image")
            );

            // Hiển thị thông tin sản phẩm
            binding.productName.setText(currentProduct.getName());
            binding.productDescription.setText(currentProduct.getDescription());
            binding.productPrice.setText(currentProduct.getPrice() + " VND");
            binding.productImage.setImageResource(currentProduct.getImageResId());
        }

        // Xử lý sự kiện nút "Quay lại"
        binding.btnBack.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigateUp();
        });

        // Xử lý sự kiện nút "Thêm vào giỏ hàng"
        binding.btnAddToCart.setOnClickListener(v -> {
            if (currentProduct != null) {
                sharedViewModel.addToCart(currentProduct);
                Snackbar.make(binding.getRoot(), "Đã thêm vào giỏ hàng!", Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
