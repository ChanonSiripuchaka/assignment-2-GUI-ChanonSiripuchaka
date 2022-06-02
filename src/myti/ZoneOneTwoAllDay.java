package myti;
 

import java.text.DecimalFormat;
import java.text.NumberFormat;

//Model class for Travel pass All day pass Zone 1/2
public class ZoneOneTwoAllDay extends TravelPass {

    private NumberFormat df = new DecimalFormat("#0.00");

    public static double price = 6.8;
    private String purchaseString;
    private String printString;
    private ZonesEnum zone = ZonesEnum.ZONE_BOTH;

    public ZoneOneTwoAllDay() {
        
        printString = "You purchased All Day pass for Zones 1 and 2, costing $" + df.format(price);
        purchaseString = "Purchased All Day pass for Zones 1 and 2, costing $" + df.format(price);
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getPurchaseString() {
        return purchaseString;
    }

    @Override
    public void printString() {
        System.out.println(printString);
    }
	@Override
	public ZonesEnum getZone() {
		return zone;
	}

	@Override
	public String toString() {
		return zone.toString();
	
	}
}
