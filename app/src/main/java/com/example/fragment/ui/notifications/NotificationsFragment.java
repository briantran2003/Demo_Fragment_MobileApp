package com.example.fragment.ui.notifications;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.fragment.R;
import com.example.fragment.adapters.NotificationsAdapter;
import com.example.fragment.models.Notification;
import com.example.fragment.viewmodels.SharedViewModel;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView emptyMessage;
    private NotificationsAdapter adapter;
    private SharedViewModel sharedViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        recyclerView = root.findViewById(R.id.recyclerViewNotifications);
        emptyMessage = root.findViewById(R.id.empty_message);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getNotifications().observe(getViewLifecycleOwner(), notifications -> {
            if (notifications.isEmpty()) {
                emptyMessage.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                emptyMessage.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                adapter = new NotificationsAdapter(notifications);
                recyclerView.setAdapter(adapter);
            }
        });

        return root;
    }
}
