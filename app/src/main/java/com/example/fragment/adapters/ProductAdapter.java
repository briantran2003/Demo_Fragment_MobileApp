package com.example.fragment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.R;
import com.example.fragment.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnItemClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description, price;

        ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productImage);
            name = itemView.findViewById(R.id.productName);
            description = itemView.findViewById(R.id.productDescription);
            price = itemView.findViewById(R.id.productPrice);

            itemView.setOnClickListener(v -> listener.onItemClick(productList.get(getAdapterPosition())));
        }

        void bind(Product product) {
            imageView.setImageResource(product.getImageResId());
            name.setText(product.getName());
            description.setText(product.getDescription());
            price.setText(product.getPrice() + " VND");
        }
    }
}




