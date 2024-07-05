package com.example.cinemahub;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class booked_tickets extends Fragment {

    private RecyclerView recyclerView;
    private BookedTicketsAdapter adapter;
    private List<Booking> bookedTicketsList;
    private DatabaseReference bookingDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookedTicketsList = new ArrayList<>();
        adapter = new BookedTicketsAdapter(bookedTicketsList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_tickets, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_booked_tickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        bookingDB = FirebaseDatabase.getInstance().getReference("BookingDB");
        bookingDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookedTicketsList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Booking booking = postSnapshot.getValue(Booking.class);
                    if (booking != null) {
                        Log.d("FirebaseData", "Booking: " + booking.toString());
                        bookedTicketsList.add(booking);
                    } else {
                        Log.e("FirebaseData", "Booking is null");
                    }
                }
                Log.d("FirebaseData", "Total bookings fetched: " + bookedTicketsList.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseData", "Database error: " + error.getMessage());
            }
        });

        return view;
    }
}
