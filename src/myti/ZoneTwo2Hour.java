package myti;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ZoneTwo2Hour extends TravelPass {
	private NumberFormat df = new DecimalFormat("#0.00");

    public static double price = 2.5;
    private String purchaseString;
    private String printString;
    private static ZonesEnum zone = ZonesEnum.ZONE_TWO;
    
    public ZoneTwo2Hour() {
        
        printString = "You purchased 2 Hour pass for Zone 2, costing $" + df.format(price);
        purchaseString = "Purchased 2 Hour pass for Zone 2, costing $" + df.format(price);
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
