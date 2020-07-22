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
	  Data time = new Data();
	  double totalWait = 0.0;
	  int size = eventQueue.size();
	  
	  while(!eventQueue.isEmpty()) {
		  time = eventQueue.dequeue();
		  totalWait = totalWait + (time.getArrivalTime() - time.getDepartureTime());
	  }
	  
	  double avgTime = totalWait/size;
	  
	  return avgTime;
  }
  
  
  public double runSimulation(){
	  Data packet = new Data();
	  Data oldPacket = new Data();
	  timeForNextArrival = getRandTime(arrivalRate);
	  while (currTime >= totalSimTime) {
		  
		  //currTime = timeForNextArrival;
		  timeForNextDeparture = timeForNextArrival + serviceTime;
		  timeForNextArrival = getRandTime(arrivalRate);
		  
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
			  packet.setArrivalTime(currTime);
			  buffer.enqueue(packet);
			  //currTime = timeForNextArrival;
			  break;
			  
		  case DEPARTURE:
			  oldPacket = buffer.dequeue();
			  oldPacket.setDepartureTime(currTime);
			  eventQueue.enqueue(oldPacket);
			  //currTime = timeForNextDeparture;
			  break;
		  }
		  
	  }
	  
	  double sojournTime = calcAverageWaitingTime();
	  return sojournTime;
  }

  
  
}






