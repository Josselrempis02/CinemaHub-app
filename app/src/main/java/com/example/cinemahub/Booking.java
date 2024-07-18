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

    public Booking() {
        // Default constructor required for calls to DataSnapshot.getValue(Booking.class)
    }

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

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<Integer> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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
