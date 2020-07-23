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
	  
	  while(!eventQueue.isEmpty()) {
		  packet = eventQueue.dequeue();
		  totalWait = totalWait + (packet.getArrivalTime() - packet.getDepartureTime());
	  }
	  
	  double avgTime = totalWait/size;
	  
	  return avgTime;
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
		}
		  
	  }
	  
	  double sojournTime = calcAverageWaitingTime();
	  return sojournTime;
  }

  
  
}






