package com.smarthost.booking.domain;

public class BookingResultTO {

	private Integer nbBookedPremium;
	private Integer nbBookedEconomy;
	private Integer totalProfitPremium;
	private Integer totalProfitEconomy;

	public BookingResultTO() {
		super();
		nbBookedPremium = 0;
		nbBookedEconomy = 0;
		totalProfitPremium = 0;
		totalProfitEconomy = 0;
	}

	public Integer getNbBookedPremium() {
		return nbBookedPremium;
	}

	public void setNbBookedPremium(Integer nbBookedPremium) {
		this.nbBookedPremium = nbBookedPremium;
	}

	public Integer getNbBookedEconomy() {
		return nbBookedEconomy;
	}

	public void setNbBookedEconomy(Integer nbBookedEconomy) {
		this.nbBookedEconomy = nbBookedEconomy;
	}

	public Integer getTotalProfitPremium() {
		return totalProfitPremium;
	}

	public void setTotalProfitPremium(Integer totalProfitPremium) {
		this.totalProfitPremium = totalProfitPremium;
	}

	public Integer getTotalProfitEconomy() {
		return totalProfitEconomy;
	}

	public void setTotalProfitEconomy(Integer totalProfitEconomy) {
		this.totalProfitEconomy = totalProfitEconomy;
	}

}
