package myti;


import java.text.DecimalFormat;
import java.text.NumberFormat;

//Model class to create credits
public class MyTi {

    private NumberFormat df = new DecimalFormat("#0.00");//formatted string to show credits

    private double MyTi;
    
    private User user;

    
    public MyTi(User user) {
		MyTi = 10;
		this.user = user;
	}
    
    
    

	public User getUser() {
		return user;
	}




	public void setUser(User user) {
		this.user = user;
	}




	
    public double getMyTi() {
        return MyTi;
    }

    public void setMyTi(double myTi) {
        MyTi = myTi;
    }

    public String remainingCredit() {
        return "Your remaining credit is $" + df.format(this.MyTi);
    }

    public String currentCredit() {
        return "Your credit = $" + df.format(this.MyTi);
    }




	@Override
	public String toString() {
		return "MyTi [MyTi=" + MyTi + ", user=" + user.toString() + "]";
	}

}
