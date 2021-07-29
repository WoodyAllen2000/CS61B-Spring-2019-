import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    PriorityQueue<Flight> startTimeFlights;
    PriorityQueue<Flight> endTimeFlights;

    public FlightSolver(ArrayList<Flight> flights) {
        startTimeFlights = new PriorityQueue<>(new Flight.startTimeCmp());
        endTimeFlights = new PriorityQueue<>(new Flight.endTimeCmp());
        startTimeFlights.addAll(flights);
        endTimeFlights.addAll(flights);
    }

    public int solve() {
        int large = 0;
        int curr = 0;
        while (!startTimeFlights.isEmpty()) {
            if (startTimeFlights.peek().startTime < endTimeFlights.peek().endTime) {
                curr += startTimeFlights.poll().passengers;
            }else {
                curr -= endTimeFlights.poll().passengers;
            }
            if (curr > large) {
                large = curr;
            }
        }
        return large;
    }

}
