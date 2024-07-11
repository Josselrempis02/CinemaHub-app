package com.example.cinemahub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class BookedTicketsAdapter extends RecyclerView.Adapter<BookedTicketsAdapter.BookedTicketsViewHolder> {

    private List<Booking> bookedTicketsList;

    public BookedTicketsAdapter(List<Booking> bookedTicketsList) {
        this.bookedTicketsList = bookedTicketsList;
    }

    @NonNull
    @Override
    public BookedTicketsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booked_tickets, parent, false);
        return new BookedTicketsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedTicketsViewHolder holder, int position) {
        Booking booking = bookedTicketsList.get(position);
        holder.movieTitle.setText(booking.getMovieTitle());
        holder.cinema.setText(booking.getCinema());
        holder.date.setText(booking.getDate());
        holder.time.setText(booking.getTime());
        holder.seats.setText("Seats: " + booking.getBookedSeats().toString());
        holder.totalPrice.setText("Total Price: ₱" + booking.getTotalPrice());
        holder.bookingId.setText("Booking ID: " + booking.getBookingId()); // Set booking id
    }

    @Override
    public int getItemCount() {
        return bookedTicketsList.size();
    }

    public static class BookedTicketsViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle, cinema, date, time, seats, totalPrice, bookingId;

        public BookedTicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            cinema = itemView.findViewById(R.id.cinema);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            seats = itemView.findViewById(R.id.seats);
            totalPrice = itemView.findViewById(R.id.total_price);
            bookingId = itemView.findViewById(R.id.booking_id); // Initialize booking id TextView
        }
    }
}
