package com.smarthost.booking.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.smarthost.booking.domain.BookingResultTO;
import com.smarthost.booking.exception.BadFormattedDataFileException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingIntegrationTests {

	@Autowired
	BookingController bookingController;

	@Test
	public void testBooking_case1() throws BadFormattedDataFileException {
		BookingResultTO summary = bookingController.bookRooms(3, 3);

		Assert.assertEquals(Integer.valueOf(3), summary.getNbBookedPremium());
		Assert.assertEquals(Integer.valueOf(3), summary.getNbBookedEconomy());
		Assert.assertEquals(Integer.valueOf(738), summary.getTotalProfitPremium());
		Assert.assertEquals(Integer.valueOf(167), summary.getTotalProfitEconomy());
	}

	@Test
	public void testBooking_case2() throws BadFormattedDataFileException {
		BookingResultTO summary = bookingController.bookRooms(7, 5);

		Assert.assertEquals(Integer.valueOf(6), summary.getNbBookedPremium());
		Assert.assertEquals(Integer.valueOf(4), summary.getNbBookedEconomy());
		Assert.assertEquals(Integer.valueOf(1054), summary.getTotalProfitPremium());
		Assert.assertEquals(Integer.valueOf(189), summary.getTotalProfitEconomy());
	}

	@Test
	public void testBooking_case3() throws BadFormattedDataFileException {
		BookingResultTO summary = bookingController.bookRooms(2, 7);

		Assert.assertEquals(Integer.valueOf(2), summary.getNbBookedPremium());
		Assert.assertEquals(Integer.valueOf(4), summary.getNbBookedEconomy());
		Assert.assertEquals(Integer.valueOf(583), summary.getTotalProfitPremium());
		Assert.assertEquals(Integer.valueOf(189), summary.getTotalProfitEconomy());
	}

}
