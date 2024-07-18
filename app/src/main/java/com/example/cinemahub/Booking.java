package com.example.cinemahub;

import java.util.List;

public class Booking {
    private String movieTitle;
    private String bookingId;
    private String ticketNo;
    private String cinema;
    private String date;
    private String time;
    private List<Integer> bookedSeats;
    private double totalPrice;

    // Constructor to match the parameters
    public Booking(String movieTitle, String bookingId, String ticketNo, String cinema, String date, String time, List<Integer> bookedSeats, double totalPrice) {
        this.movieTitle = movieTitle;
        this.bookingId = bookingId;
        this.ticketNo = ticketNo;
        this.cinema = cinema;
        this.date = date;
        this.time = time;
        this.bookedSeats = bookedSeats;
        this.totalPrice = totalPrice;
    }

    // Getters
    public String getMovieTitle() {
        return movieTitle;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getTicketNo() {
        return ticketNo;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "movieTitle='" + movieTitle + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", ticketNo='" + ticketNo + '\'' +
                ", cinema='" + cinema + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", bookedSeats=" + bookedSeats +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
