package com.ssafy.house.model;

public class SearchHouse {
    private String sidoName;
    private String gugunName;
    private String dongName;
    private String aptName;
    private String dealYear;
    private String dealMonth;
	
    public SearchHouse() {
		super();
	}

	public SearchHouse(String sidoName, String gugunName, String dongName, String aptName, String dealYear,
			String dealMonth) {
		super();
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.aptName = aptName;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
}
