// Booking.java
package com.example.cinemahub;

import java.util.List;

public class Booking {
    private String cinema;
    private String date;
    private String time;
    private List<Integer> bookedSeats;
    private int totalPrice;
    private String movieTitle; // New field for movie title

    public Booking() {
        // Default constructor required for calls to DataSnapshot.getValue(Booking.class)
    }

    public Booking(String cinema, String date, String time, List<Integer> bookedSeats, int totalPrice, String movieTitle) {
        this.cinema = cinema;
        this.date = date;
        this.time = time;
        this.bookedSeats = bookedSeats;
        this.totalPrice = totalPrice;
        this.movieTitle = movieTitle; // Initialize the new field
    }

    // Getters and setters for all fields, including the new movieTitle field
    public String getCinema() { return cinema; }
    public void setCinema(String cinema) { this.cinema = cinema; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public List<Integer> getBookedSeats() { return bookedSeats; }
    public void setBookedSeats(List<Integer> bookedSeats) { this.bookedSeats = bookedSeats; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }

    public String getMovieTitle() { return movieTitle; }
    public void setMovieTitle(String movieTitle) { this.movieTitle = movieTitle; }
}
