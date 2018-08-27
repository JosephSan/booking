package com.smarthost.booking.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.smarthost.booking.FileContentUtils;
import com.smarthost.booking.domain.Customer;
import com.smarthost.booking.exception.BadFormattedDataFileException;

@Repository
public class ReservationsDao {

	String dataFile = "src/main/resources/data.txt";

	public List<Customer> getCustomers() throws BadFormattedDataFileException {

		try {
			List<Integer> expectedPrices = FileContentUtils.parseFile(FileContentUtils.readFile(dataFile));
			return expectedPrices.stream().map(i -> new Customer(i)).collect(Collectors.toList());
		} catch (NumberFormatException e) {
			throw new BadFormattedDataFileException();
		}

	}

}
