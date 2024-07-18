package com.example.cinemahub;

import android.util.Log;
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

        Log.d("AdapterData", "Binding data for position: " + position + ", Booking: " + booking.toString());

        holder.movieTitle.setText(booking.getMovieTitle());
        holder.bookingId.setText("Booking ID: " + booking.getBookingId());
        holder.ticketNo.setText("Ticket No.: " + booking.getTicketNo()); // Set ticket number
        holder.cinema.setText("Cinema: " + booking.getCinema());
        holder.date.setText("Date: " + booking.getDate());
        holder.time.setText("Time: " + booking.getTime());
        holder.seats.setText(formatSeats(booking.getBookedSeats()));
        holder.totalPrice.setText("Total Price: ₱" + booking.getTotalPrice());
    }
    private String formatSeats(List<Integer> bookedSeats) {
        StringBuilder seatsString = new StringBuilder("Seats: ");
        for (int i = 0; i < bookedSeats.size(); i++) {
            if (i > 0) {
                seatsString.append(", ");
            }
            seatsString.append(bookedSeats.get(i));
        }
        return seatsString.toString();
    }

    @Override
    public int getItemCount() {
        return bookedTicketsList.size();
    }

    public static class BookedTicketsViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle, bookingId, ticketNo, cinema, date, time, seats, totalPrice;

        public BookedTicketsViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movie_title);
            bookingId = itemView.findViewById(R.id.booking_id);
            ticketNo = itemView.findViewById(R.id.ticket_no);
            cinema = itemView.findViewById(R.id.cinema);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            seats = itemView.findViewById(R.id.seats);
            totalPrice = itemView.findViewById(R.id.total_price);
        }
    }
}
