package com.example.fragment.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.R;
import com.example.fragment.models.CartItem;
import com.example.fragment.viewmodels.SharedViewModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<CartItem> cartItems;
    private SharedViewModel sharedViewModel;

    public CartAdapter(List<CartItem> cartItems, SharedViewModel sharedViewModel) {
        this.cartItems = cartItems;
        this.sharedViewModel = sharedViewModel;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.bind(cartItems.get(position));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView productName, productPrice, removeItem;
        EditText productQuantity;

        CartViewHolder(View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.cart_product_image);
            productName = itemView.findViewById(R.id.cart_product_name);
            productPrice = itemView.findViewById(R.id.cart_product_price);
            productQuantity = itemView.findViewById(R.id.cart_product_quantity);
            removeItem = itemView.findViewById(R.id.cart_remove_item);

            // Xử lý xóa sản phẩm
            removeItem.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    sharedViewModel.removeCartItem(cartItems.get(position));
                }
            });

            // Xử lý thay đổi số lượng
            productQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        int newQuantity = s.toString().isEmpty() ? 1 : Integer.parseInt(s.toString());
                        sharedViewModel.updateQuantity(cartItems.get(position), newQuantity);

                        // Thông báo cập nhật RecyclerView
                    }
                }
            });
        }

        void bind(CartItem item) {
            productImg.setImageResource(item.getProduct().getImageResId());
            productName.setText(item.getProduct().getName());
            productPrice.setText(item.getProduct().getPrice() + " VND");
            productQuantity.setText(String.valueOf(item.getQuantity()));
        }
    }
}
