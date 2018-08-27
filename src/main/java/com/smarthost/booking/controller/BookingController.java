package com.smarthost.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smarthost.booking.domain.BookingResultTO;
import com.smarthost.booking.domain.Customer;
import com.smarthost.booking.exception.BadFormattedDataFileException;
import com.smarthost.booking.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	BookingService bookingService;

	@RequestMapping("/book")
	public BookingResultTO bookRooms(@RequestParam(value = "nbPremiumRooms", defaultValue = "0") Integer nbPremiumRooms,
			@RequestParam(value = "nbEconomyRooms", defaultValue = "0") Integer nbEconomyRooms)
			throws BadFormattedDataFileException {

		List<Customer> customers = bookingService.book(nbPremiumRooms, nbEconomyRooms);
		BookingResultTO bookingSummary = bookingService.getBookedRoomsAndProfit(customers);
		return bookingSummary;

	}

}
