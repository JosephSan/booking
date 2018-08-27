package com.smarthost.booking.domain;

import java.util.Comparator;

public class CustomerComparator implements Comparator<Customer> {

	// Used for sorting in descending order of
	// expected price
	public int compare(Customer a, Customer b) {
		return -1 * (a.getExpectedPrice() - b.getExpectedPrice());
	}

}
