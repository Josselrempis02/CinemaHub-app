package com.example.cinemahub;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

    public booked_tickets() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked_tickets, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_booked_tickets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookedTicketsList = new ArrayList<>();
        adapter = new BookedTicketsAdapter(bookedTicketsList);
        recyclerView.setAdapter(adapter);

        bookingDB = FirebaseDatabase.getInstance().getReference().child("BookingDB");
        bookingDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    bookedTicketsList.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Booking booking = postSnapshot.getValue(Booking.class);
                        if (booking != null) {
                            bookedTicketsList.add(booking);
                            Log.d("booked_tickets", "Booking added: " + booking.toString());
                        } else {
                            Log.e("booked_tickets", "Booking is null for postSnapshot: " + postSnapshot.getKey());
                        }
                    }
                    adapter.notifyDataSetChanged(); // Ensure adapter is notified of data change
                    Log.d("booked_tickets", "Number of bookings retrieved: " + bookedTicketsList.size());
                } catch (Exception e) {
                    Log.e("booked_tickets", "Error processing bookings", e);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("booked_tickets", "Database error: " + error.getMessage());
            }
        });


        return view;
    }
}
