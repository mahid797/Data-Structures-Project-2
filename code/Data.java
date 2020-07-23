//EECS 2011 - SU2020
//Group Members:
// Adrian Angara 216090532
// Nadimul Hasan 216429516
// Mahid Ahmad 216652398

public class Data{
	  double arrivalTime;
	  double departureTime;
	  public Data()
	  {
		  this.arrivalTime = 0 ; 
		  this.departureTime = 0 ;
	  }
	  public void setArrivalTime(double a)
	  {
		  this.arrivalTime = a ;
	  }
	  public void setDepartureTime(double d)
	  {
		  this.departureTime = d ; 
	  }
	  public double getDepartureTime()
	  {
		  return this.departureTime;
	  }
	  public double getArrivalTime()
	  {
		  return this.arrivalTime;
	  }
	}
