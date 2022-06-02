package myti;



//TravelPass abstract class, all other travel passes inherits this class
public abstract class TravelPass {

    public abstract double getPrice();
    
    public abstract String getPurchaseString();
    public abstract void printString();
    public abstract ZonesEnum getZone();
    public abstract String toString();

}
