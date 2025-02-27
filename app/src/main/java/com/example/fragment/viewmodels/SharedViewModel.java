package com.example.fragment.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fragment.models.CartItem;
import com.example.fragment.models.Notification;
import com.example.fragment.models.Product;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<Notification>> notifications = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public LiveData<List<Notification>> getNotifications() {
        return notifications;
    }

    public void addToCart(Product product) {
        List<CartItem> currentItems = cartItems.getValue();
        boolean exists = false;
        for (CartItem item : currentItems) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                exists = true;
                break;
            }
        }
        if (!exists) {
            currentItems.add(new CartItem(product, 1));
        }
        cartItems.setValue(new ArrayList<>(currentItems));
    }

    public void addCartItem(CartItem item) {
        List<CartItem> currentItems = cartItems.getValue();
        currentItems.add(item);
        cartItems.setValue(currentItems);
    }

    public void removeCartItem(CartItem cartItem) {
        List<CartItem> currentItems = cartItems.getValue();
        currentItems.remove(cartItem);
        cartItems.setValue(new ArrayList<>(currentItems));
    }

    public void updateQuantity(CartItem cartItem, int newQuantity) {
        if (newQuantity <= 0) {
            removeCartItem(cartItem);
            return;
        }
        List<CartItem> currentItems = cartItems.getValue();
        for (CartItem item : currentItems) {
            if (item.getProduct().getId() == cartItem.getProduct().getId()) {
                item.setQuantity(newQuantity);
                break;
            }
        }
        cartItems.setValue(new ArrayList<>(currentItems));
    }

    public void clearCart() {
        cartItems.setValue(new ArrayList<>());
    }

    public void addNotification(String message) {
        List<Notification> currentNotifications = notifications.getValue();
        currentNotifications.add(new Notification(message, System.currentTimeMillis()));
        notifications.setValue(currentNotifications);
    }
}
