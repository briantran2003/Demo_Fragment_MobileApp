package com.example.fragment.ui.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragment.R;
import com.example.fragment.models.CartItem;
import com.example.fragment.viewmodels.SharedViewModel;

public class ConfirmOrderDialogFragment extends DialogFragment {
    private SharedViewModel sharedViewModel;
    private TextView orderSummary;
    private Button confirmButton, cancelButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm_order, container, false);
        orderSummary = view.findViewById(R.id.order_summary);
        confirmButton = view.findViewById(R.id.confirm_button);
        cancelButton = view.findViewById(R.id.cancel_button);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getCartItems().observe(getViewLifecycleOwner(), cartItems -> {
            StringBuilder summary = new StringBuilder();
            int total = 0;

            for (CartItem item : cartItems) {
                summary.append(item.getProduct().getName()).append(" x")
                        .append(item.getQuantity()).append(" - $")
                        .append(item.getProduct().getPrice() * item.getQuantity())
                        .append("\n");
                total += item.getProduct().getPrice() * item.getQuantity();
            }

            summary.append("\n**Tổng cộng: $").append(total);
            orderSummary.setText(summary.toString());
        });

        confirmButton.setOnClickListener(v -> {
            sharedViewModel.clearCart();
            Toast.makeText(getContext(), "Đơn hàng đã đặt thành công!", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        cancelButton.setOnClickListener(v -> dismiss());

        return view;
    }
}

