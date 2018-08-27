package com.smarthost.booking.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.smarthost.booking.domain.Customer;
import com.smarthost.booking.exception.BadFormattedDataFileException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationsDaoTest {

	@Autowired
	ReservationsDao reservationsDao;

	@Test
	public void testGetReservations() throws BadFormattedDataFileException {
		List<Customer> customers = reservationsDao.getCustomers();

		Assert.assertEquals(10, customers.size());
		Assert.assertEquals(Integer.valueOf(23), customers.get(0).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(45), customers.get(1).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(155), customers.get(2).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(374), customers.get(3).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(22), customers.get(4).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(99), customers.get(5).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(100), customers.get(6).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(101), customers.get(7).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(115), customers.get(8).getExpectedPrice());
		Assert.assertEquals(Integer.valueOf(209), customers.get(9).getExpectedPrice());

	}

}
