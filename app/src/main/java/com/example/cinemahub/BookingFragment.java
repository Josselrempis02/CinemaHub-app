package com.example.cinemahub;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BookingFragment extends Fragment {

    private static final int NUM_COLUMNS = 5;
    private RecyclerView recyclerView;
    private SeatAdapter seatAdapter;
    private Spinner cinemaSpinner;
    private EditText dateEditText;
    private EditText timeEditText;
    private TextView priceTextView;
    private TextView movieTitleTextView;
    private Button bookButton;

    private List<Seat> seats;
    private DatabaseReference bookingDB;

    private static final String ARG_MOVIE_TITLE = "movie_title";

    public static BookingFragment newInstance(String movieTitle) {
        BookingFragment fragment = new BookingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MOVIE_TITLE, movieTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public BookingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSeats);
        cinemaSpinner = view.findViewById(R.id.cinema_spinner);
        dateEditText = view.findViewById(R.id.date_edit_text);
        timeEditText = view.findViewById(R.id.time_edit_text);
        priceTextView = view.findViewById(R.id.price_text_view);
        movieTitleTextView = view.findViewById(R.id.movie_title_text_view);
        bookButton = view.findViewById(R.id.book_button);

        if (getArguments() != null) {
            String movieTitle = getArguments().getString(ARG_MOVIE_TITLE);
            movieTitleTextView.setText(movieTitle);
        }

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), NUM_COLUMNS));
        seats = generateSeats();
        seatAdapter = new SeatAdapter(seats);
        seatAdapter.setOnSeatSelectedListener(new Seat.OnSeatSelectedListener() {
            @Override
            public void onSeatSelected(Seat seat) {
                updatePrice();
            }
        });
        recyclerView.setAdapter(seatAdapter);

        setupCinemaSpinner();
        setupDateEditText();
        setupTimeEditText();
        updatePrice();

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BookingFragment", "Book Now button clicked");
                saveBooking();
            }
        });

        bookingDB = FirebaseDatabase.getInstance().getReference("BookingDB");

        return view;
    }

    private List<Seat> generateSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            seats.add(new Seat(i + 1, false));
        }
        return seats;
    }

    private void setupCinemaSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cinema_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cinemaSpinner.setAdapter(adapter);
    }

    private void setupDateEditText() {
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                dateEditText.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void setupTimeEditText() {
        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                timeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, hour, minute, true);
                timePickerDialog.show();
            }
        });
    }

    private void updatePrice() {
        int pricePerSeat = 250; // Example price per seat
        int selectedSeats = 0;
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                selectedSeats++;
            }
        }
        int totalPrice = selectedSeats * pricePerSeat;
        priceTextView.setText("Total Price: ₱" + totalPrice);
    }

    private void saveBooking() {
        Log.d("BookingFragment", "saveBooking called");
        String cinema = cinemaSpinner.getSelectedItem().toString();
        String date = dateEditText.getText().toString();
        String time = timeEditText.getText().toString();
        List<Integer> bookedSeats = new ArrayList<>();
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                bookedSeats.add(seat.getSeatNumber());
            }
        }

        int totalPrice = bookedSeats.size() * 250; // Example price per seat
        String movieTitle = getArguments().getString(ARG_MOVIE_TITLE);

        Log.d("BookingFragment", "Cinema: " + cinema);
        Log.d("BookingFragment", "Date: " + date);
        Log.d("BookingFragment", "Time: " + time);
        Log.d("BookingFragment", "Booked Seats: " + bookedSeats);
        Log.d("BookingFragment", "Total Price: " + totalPrice);
        Log.d("BookingFragment", "Movie Title: " + movieTitle);

        DatabaseReference newBookingRef = bookingDB.push();
        String bookingId = newBookingRef.getKey(); // Generate unique booking ID
        int ticketNo = generateTicketNo(); // Generate unique ticket number
        Booking booking = new Booking(cinema, date, time, bookedSeats, totalPrice, movieTitle, bookingId, ticketNo); // Include ticket number
        newBookingRef.setValue(booking);

        showBookingSuccessDialog(bookingId); // Pass booking ID to dialog
    }

    private int generateTicketNo() {
        // Implement your logic to generate a unique ticket number
        // For example, using the current timestamp
        return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
    }

    private void showBookingSuccessDialog(String bookingId) {
        new AlertDialog.Builder(getActivity())
                .setTitle("Booking Successful")
                .setMessage("Your seats have been booked successfully.\nBooking ID: " + bookingId)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to the booked_tickets fragment
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, new Home());
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
