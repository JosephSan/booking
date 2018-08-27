package com.smarthost.booking.domain;

public class Customer {

	private Integer expectedPrice;
	private BookingStatus bookingStatus;

	public Customer(Integer price) {
		expectedPrice = price;
		bookingStatus = BookingStatus.NOT_BOOKED;
	}

	public Integer getExpectedPrice() {
		return expectedPrice;
	}

	@Override
	public String toString() {
		return "Customer [expectedPrice=" + expectedPrice + ", bookingStatus=" + bookingStatus + "]";
	}

	public void setExpectedPrice(Integer expectedPrice) {
		this.expectedPrice = expectedPrice;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Boolean isUpgraded() {
		return bookingStatus.equals(BookingStatus.PREMIUM) && !isPremium();
	}

	public boolean isPremium() {
		return expectedPrice.compareTo(100) >= 0;
	}

}
