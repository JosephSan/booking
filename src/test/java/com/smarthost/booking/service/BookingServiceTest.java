package com.smarthost.booking.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.smarthost.booking.domain.BookingResultTO;
import com.smarthost.booking.domain.BookingStatus;
import com.smarthost.booking.domain.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

	@Autowired
	private BookingService bookingService;

	@Test
	public void testBookPremiums_noUpgrade() {
		List<Customer> customers = Stream.of(new Customer(102), new Customer(106), new Customer(10))
				.collect(Collectors.toList());

		bookingService.bookPremiumRooms(customers, 2);

		Assert.assertEquals(BookingStatus.PREMIUM, customers.get(0).getBookingStatus());
		Assert.assertEquals(BookingStatus.PREMIUM, customers.get(1).getBookingStatus());
		Assert.assertEquals(BookingStatus.NOT_BOOKED, customers.get(2).getBookingStatus());
		Assert.assertEquals(false, customers.get(0).isUpgraded());
		Assert.assertEquals(false, customers.get(1).isUpgraded());
	}

	@Test
	public void testBookPremiums_unordered() {
		Customer customer1 = new Customer(102);
		Customer customer2 = new Customer(10);
		Customer customer3 = new Customer(109);

		List<Customer> customers = Stream.of(customer1, customer2, customer3).collect(Collectors.toList());

		bookingService.bookPremiumRooms(customers, 2);

		Assert.assertEquals(BookingStatus.PREMIUM, customers.get(customers.indexOf(customer1)).getBookingStatus());
		Assert.assertEquals(BookingStatus.NOT_BOOKED, customers.get(customers.indexOf(customer2)).getBookingStatus());
		Assert.assertEquals(BookingStatus.PREMIUM, customers.get(customers.indexOf(customer3)).getBookingStatus());
	}

	@Test
	public void testBookEconomy() {
		Customer customer1 = new Customer(102);
		Customer customer2 = new Customer(10);
		Customer customer3 = new Customer(109);

		List<Customer> customers = Stream.of(customer1, customer2, customer3).collect(Collectors.toList());

		bookingService.bookEconomyRooms(customers, 2);

		Assert.assertEquals(BookingStatus.NOT_BOOKED, customers.get(customers.indexOf(customer1)).getBookingStatus());
		Assert.assertEquals(BookingStatus.ECONOMY, customers.get(customers.indexOf(customer2)).getBookingStatus());
		Assert.assertEquals(BookingStatus.NOT_BOOKED, customers.get(customers.indexOf(customer3)).getBookingStatus());
	}

	@Test
	public void testGetBookedRoomsAndProfit() {
		Customer customer1 = new Customer(102);
		customer1.setBookingStatus(BookingStatus.PREMIUM);
		Customer customer2 = new Customer(10);
		customer2.setBookingStatus(BookingStatus.ECONOMY);
		Customer customer3 = new Customer(109);
		customer3.setBookingStatus(BookingStatus.PREMIUM);

		List<Customer> customers = Stream.of(customer1, customer2, customer3).collect(Collectors.toList());

		BookingResultTO summary = bookingService.getBookedRoomsAndProfit(customers);

		Assert.assertEquals(Integer.valueOf(2), summary.getNbBookedPremium());
		Assert.assertEquals(Integer.valueOf(1), summary.getNbBookedEconomy());
		Assert.assertEquals(Integer.valueOf(10), summary.getTotalProfitEconomy());
		Assert.assertEquals(Integer.valueOf(211), summary.getTotalProfitPremium());

	}

}
