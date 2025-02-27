package com.example.fragment.ui.cart;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragment.R;
import com.example.fragment.adapters.CartAdapter;
import com.example.fragment.databinding.FragmentCartBinding;
import com.example.fragment.models.CartItem;
import com.example.fragment.viewmodels.SharedViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private SharedViewModel sharedViewModel;
    private TextView totalPrice;
    private Button checkoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewCart);
        totalPrice = root.findViewById(R.id.total_price);
        checkoutButton = root.findViewById(R.id.checkout_button);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            adapter = new CartAdapter(cartItems, sharedViewModel);
            recyclerView.setAdapter(adapter);
            updateTotalPrice(cartItems);
        });

        checkoutButton.setOnClickListener(v -> {
            // Xử lý sự kiện khi nhấn nút "Thanh toán"
            // Ví dụ: Hiển thị dialog xác nhận đặt hàng
            // Sau khi đặt hàng thành công, thêm thông báo và xóa giỏ hàng
//            ConfirmOrderDialogFragment dialog = new ConfirmOrderDialogFragment();
//            dialog.show(getParentFragmentManager(), "ConfirmOrderDialog");
            sharedViewModel.addNotification("Bạn đã đặt hàng thành công!");
            sharedViewModel.clearCart();
        });

        return root;
    }

    private void updateTotalPrice(List<CartItem> cartItems) {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        totalPrice.setText("Tổng: " + total + " VND");
    }
}

