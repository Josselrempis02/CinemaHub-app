package com.example.cinemahub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {

    private List<Seat> seatList;
    private Seat.OnSeatSelectedListener listener;

    public SeatAdapter(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public void setOnSeatSelectedListener(Seat.OnSeatSelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seat, parent, false);
        return new SeatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);
        holder.bind(seat);
    }

    @Override
    public int getItemCount() {
        return seatList.size();
    }

    class SeatViewHolder extends RecyclerView.ViewHolder {
        private TextView seatNumberTextView;
        private View seatView;

        public SeatViewHolder(@NonNull View itemView) {
            super(itemView);
            seatNumberTextView = itemView.findViewById(R.id.seat_number);
            seatView = itemView.findViewById(R.id.seatView);
        }

        public void bind(final Seat seat) {
            seatNumberTextView.setText(String.valueOf(seat.getSeatNumber()));
            updateSeatView(seat.isBooked());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    seat.setBooked(!seat.isBooked());
                    updateSeatView(seat.isBooked());
                    if (listener != null) {
                        listener.onSeatSelected(seat);
                    }
                }
            });
        }

        private void updateSeatView(boolean isBooked) {
            if (isBooked) {
                seatView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.seat_selected_color));
            } else {
                seatView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), android.R.color.transparent));
            }
        }
    }
}
