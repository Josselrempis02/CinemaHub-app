package com.example.cinemahub;

public class Seat {
    private int seatNumber;
    private boolean booked;
    private OnSeatSelectedListener listener;

    public Seat(int seatNumber, boolean booked) {
        this.seatNumber = seatNumber;
        this.booked = booked;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
        if (listener != null) {
            listener.onSeatSelected(this);
        }
    }

    public void setOnSeatSelectedListener(OnSeatSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnSeatSelectedListener {
        void onSeatSelected(Seat seat);
    }
}
