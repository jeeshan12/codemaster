package testVagarant.automation.pageconstants;

public final class HotelPageConstants {

	public static final String HOTELS_LINK = "Hotels";

	public static final String WHERE_TEXTBOX = "Tags";

	public static final String TRAVELLERS_DROPDOWN_LIST = "travellersOnhome";

	public static final String SEARCH_HOTELS_LIST = "SearchHotelsButton";

	public static final String CHECKIN_DATE_TABLE = "//div[@class='monthBlock first']//table//tbody//td[not(contains(@class,'unselectable disabled'))]";
	
	public static final String CHECKOUT_DATE_TABLE = "//div[@class='monthBlock last']//table//tbody//td[not(contains(@class,'unselectable disabled'))]";
	
	public static final String CHECKIN_DATE_ICON = "//input[@id='CheckInDate']//following-sibling::i";
	
	
	public static final String HOTELS_LIST="//li[@class='category Locations']/following-sibling::li";
	
	public static final String CITY_LABEL="//span[@class='fillCityName']";
}
