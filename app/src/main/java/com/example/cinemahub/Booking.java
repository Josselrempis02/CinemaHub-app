package com.example.cinemahub;

import java.util.List;

public class Booking {
    private String cinema;
    private String date;
    private String time;
    private List<Integer> bookedSeats;
    private int totalPrice;

    public Booking() {
        // Default constructor required for calls to DataSnapshot.getValue(Booking.class)
    }

    public Booking(String cinema, String date, String time, List<Integer> bookedSeats, int totalPrice) {
        this.cinema = cinema;
        this.date = date;
        this.time = time;
        this.bookedSeats = bookedSeats;
        this.totalPrice = totalPrice;
    }

    public String getCinema() {
        return cinema;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
