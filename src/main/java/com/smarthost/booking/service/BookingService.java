package com.smarthost.booking.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarthost.booking.dao.ReservationsDao;
import com.smarthost.booking.domain.BookingResultTO;
import com.smarthost.booking.domain.BookingStatus;
import com.smarthost.booking.domain.Customer;
import com.smarthost.booking.domain.CustomerComparator;
import com.smarthost.booking.exception.BadFormattedDataFileException;

@Service
public class BookingService {

	@Autowired
	private ReservationsDao reservationsDao;

	public List<Customer> book(Integer nbPremiumRooms, Integer nbEconomyRooms) throws BadFormattedDataFileException {
		List<Customer> customers = reservationsDao.getCustomers();

		Map<Boolean, List<Customer>> partitionedCustomers = upgradeCustomers(nbPremiumRooms, nbEconomyRooms, customers);
		bookEconomyRooms(partitionedCustomers.get(Boolean.FALSE), nbEconomyRooms);
		bookPremiumRooms(partitionedCustomers.get(Boolean.TRUE), nbPremiumRooms);

		return customers;
	}

	public BookingResultTO getBookedRoomsAndProfit(List<Customer> customers) {
		BookingResultTO bookingSummary = new BookingResultTO();

		for (Customer customer : customers) {
			if (customer.getBookingStatus().equals(BookingStatus.ECONOMY)) {
				bookingSummary.setNbBookedEconomy(bookingSummary.getNbBookedEconomy() + 1);
				bookingSummary
						.setTotalProfitEconomy(bookingSummary.getTotalProfitEconomy() + customer.getExpectedPrice());
			}
			if (customer.getBookingStatus().equals(BookingStatus.PREMIUM)) {
				bookingSummary.setNbBookedPremium(bookingSummary.getNbBookedPremium() + 1);
				bookingSummary
						.setTotalProfitPremium(bookingSummary.getTotalProfitPremium() + customer.getExpectedPrice());
			}
		}

		return bookingSummary;
	}

	/**
	 * If possible, upgrade highest paying economy customers.
	 * 
	 * @param nbPremiumRooms
	 * @param nbEconomyRooms
	 * @param customers
	 * @return Map key = TRUE : List of premium customers (including upgraded
	 *         customers), key = FALSE : List of economy customers
	 */
	private Map<Boolean, List<Customer>> upgradeCustomers(Integer nbPremiumRooms, Integer nbEconomyRooms,
			List<Customer> customers) {
		Integer nbCustomersToUpgrade = nbCustomersToUpgrade(customers, nbPremiumRooms, nbEconomyRooms);
		Collections.sort(customers, new CustomerComparator());
		Map<Boolean, List<Customer>> partitionedCustomers = customers.stream()
				.collect(Collectors.partitioningBy(c -> c.isPremium()));

		while (nbCustomersToUpgrade > 0 && partitionedCustomers.get(Boolean.FALSE).size() > 0) {
			partitionedCustomers.get(Boolean.TRUE).add(partitionedCustomers.get(Boolean.FALSE).remove(0));
			nbCustomersToUpgrade--;
		}
		return partitionedCustomers;
	}

	private Integer nbCustomersToUpgrade(List<Customer> customers, Integer nbPremiumRooms, Integer nbEconomyRooms) {
		Map<Boolean, List<Customer>> partitionedCustomers = customers.stream()
				.collect(Collectors.partitioningBy(c -> c.isPremium()));

		if (partitionedCustomers.get(Boolean.FALSE).size() <= nbEconomyRooms) {
			// all economy customers are assigned an economy room
			return 0;
		}

		return Math.max(0, nbPremiumRooms - partitionedCustomers.get(Boolean.TRUE).size());
	}

	/**
	 * Assign premium rooms to highest paying customers not already booked. This
	 * could result in an upgrade if there are less customers paying more than 100€
	 * than premium rooms. Higher paying customers will be upgraded first.
	 * 
	 * @param customers
	 */
	protected void bookPremiumRooms(List<Customer> customers, Integer nbAvailablePremiumRooms) {
		Collections.sort(customers, new CustomerComparator());
		Integer nbBookedRooms = 0;
		for (Customer customer : customers) {
			if (nbBookedRooms.equals(nbAvailablePremiumRooms)) {
				break;
			}

			if (customer.getBookingStatus().equals(BookingStatus.NOT_BOOKED)) {
				customer.setBookingStatus(BookingStatus.PREMIUM);
				nbBookedRooms++;
			}
		}
	}

	/**
	 * Book economy rooms. No customer willing to pay more than 100€ will be
	 * assigned an economy room. Highest paying (not premium) customers will be
	 * booked first.
	 * 
	 * @param customers
	 * @param nbAvailableEconomyRooms
	 */
	protected void bookEconomyRooms(List<Customer> customers, Integer nbAvailableEconomyRooms) {
		Collections.sort(customers, new CustomerComparator());
		Integer nbBookedRooms = 0;
		for (Customer customer : customers) {
			if (nbBookedRooms.equals(nbAvailableEconomyRooms)) {
				break;
			}

			if (customer.getBookingStatus().equals(BookingStatus.NOT_BOOKED) && !customer.isPremium()) {
				customer.setBookingStatus(BookingStatus.ECONOMY);
				nbBookedRooms++;
			}
		}
	}

}
