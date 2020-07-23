//EECS 2011 - SU2020
//Group Members:
// Adrian Angara 216090532
// Nadimul Hasan 216429516
// Mahid Ahmad 216652398

import java.lang.*;


public class QueueSimulator{
  public enum Event { ARRIVAL, DEPARTURE };
  private double currTime;
  private double arrivalRate;
  private double serviceTime;
  private double timeForNextArrival;
  private double timeForNextDeparture;
  private double totalSimTime;
  LinkedListQueue<Data> buffer = new LinkedListQueue<Data>();
  LinkedListQueue<Data> eventQueue = new LinkedListQueue<Data>();
  private Event e;
  
  public double getRandTime(double arrivalRate){
    double num, time1, max=1, min=0, randNUM;
    randNUM= Math.random();
    time1= (-1/arrivalRate) * (Math.log(1-randNUM));
    //System.out.println(time1);
    return time1;
  }
  
  public QueueSimulator(double aR, double servT, double simT){
	  this.arrivalRate = aR;
	  this.serviceTime = servT;
	  this.totalSimTime = simT;
  }
  
  public double calcAverageWaitingTime(){
	  Data packet = new Data();
	  double totalWait = 0.0;
	  int size = eventQueue.size();
	 
	  //Dequeue the first packet, and retrieve the previous time
	  packet = eventQueue.dequeue();
	  totalWait += packet.getDepartureTime();
	  double previous = packet.getDepartureTime();
 
	  while(!eventQueue.isEmpty()) {
		  packet = new Data();

		  //Dequeue a new packet, and increment the total wait time appropriately
		  packet = eventQueue.dequeue();
	          totalWait += (packet.getDepartureTime() - previous);
		  
		  //Set a new previous time for the next iteration
        	  previous = packet.getDepartureTime();

	  }
	  
	  double arrTime = size/totalWait;
          double num = arrTime/(1/serviceTime);
          double sojourn = (1/arrTime)*(num + (0.5*Math.pow(num, 2))/Math.abs(1-num));

          return sojourn;	  
  }
  
  
  public double runSimulation(){
	  Data packet;
	  
	  while (currTime <= totalSimTime) {
		  packet = new Data();		  


		  //Determine the event to occur
		  if (buffer.isEmpty()) {
			  e = Event.ARRIVAL;
		  }
		  else if (timeForNextArrival < timeForNextDeparture) {
			  e = Event.ARRIVAL;
		  }
		  else {
			  e = Event.DEPARTURE;
		  }
		  
		  switch(e) {
		  case ARRIVAL:
			//Define the Data object to be enqueued
                        timeForNextArrival += getRandTime(arrivalRate);
                        packet.setArrivalTime(timeForNextArrival);

                        //Enqueue the Data object
                        buffer.enqueue(packet);

                        //Set the current time to the arrival time
                        currTime = timeForNextArrival;
			break;


		  case DEPARTURE:
			//Get a departure time
                        timeForNextDeparture = buffer.first().getArrivalTime() + serviceTime;

                        //Dequeue the first Data Node, set its departure time, and enqueue into the event queue
                        packet = buffer.dequeue();
                        packet.setDepartureTime(timeForNextDeparture);
                        eventQueue.enqueue(packet);

                        //Set the current time to the departure time
                        currTime = timeForNextDeparture; 
			break; 
		}
		  
	  }
	  
	  double sojournTime = calcAverageWaitingTime();
	  return sojournTime;
  }

  
  
}






