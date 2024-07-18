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
        holder.movieTitle.setText(booking.getMovieTitle());
        holder.bookingId.setText("Booking ID: " + booking.getBookingId());
        holder.ticketNo.setText("Ticket No.: " + booking.getTicketNo());
        holder.cinema.setText("Cinema: " + booking.getCinema());
        holder.date.setText("Date: " + booking.getDate());
        holder.time.setText("Time: " + booking.getTime());
        holder.seats.setText(formatSeats(booking.getBookedSeats()));
        holder.totalPrice.setText("Total Price: ₱" + booking.getTotalPrice());
        Log.d("BookedTicketsAdapter", "Binding data for booking: " + booking.toString());
    }

    @Override
    public int getItemCount() {
        return bookedTicketsList.size();
    }

    private String formatSeats(List<Integer> seats) {
        StringBuilder sb = new StringBuilder();
        for (int seat : seats) {
            sb.append(seat).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

    static class BookedTicketsViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        TextView bookingId;
        TextView ticketNo;
        TextView cinema;
        TextView date;
        TextView time;
        TextView seats;
        TextView totalPrice;

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
