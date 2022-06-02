package myti;


import java.text.DecimalFormat;
import java.text.NumberFormat;

//Model class for Travel Pass 2 Hour for Zone 1 and 2 ,2 Hour
public class ZoneOneTwo2Hour extends TravelPass {

    private NumberFormat df = new DecimalFormat("#0.00");

    public static double price = 3.5;
    private String purchaseString;
    private String printString;
    private ZonesEnum zone = ZonesEnum.ZONE_BOTH;
    public ZoneOneTwo2Hour() {
       
        printString = "You purchased 2 Hour pass for Zones 1 and 2, costing $" + df.format(price);
        purchaseString = "Purchased 2 Hour pass for Zones 1 and 2, costing $" + df.format(price);
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
