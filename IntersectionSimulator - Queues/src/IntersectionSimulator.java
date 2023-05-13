/**
 * The IntersectionSimulator class represents the manager of the simulation 
 * The main function's responsibility is to get the parameters for the 
 * simulation and pass them to the simulate() method, either by interactive 
 * prompt or command line.
 * 
 * @author Bryan Wong
 * 		SBUID#115226034
 * 		Recitation#04
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IntersectionSimulator {
	
	/**
	 * The application as the user to input values for the simulation time, 
	 * arrival probability, number of roads, name of the roads, and a green
	 * time for each of the roads. It then makes an intersection that 
	 * simulates cars moving in and out of the intersection.
	 * 
	 * @param args
	 *    The input that the user enters 
	 */
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		
		boolean param = true;
		
		System.out.println("Welcome to IntersectionSimulator 2023");
		System.out.println();
		while (param) {
			try {
				System.out.print("Input the simulation time: ");
				int simulationTime = stdin.nextInt();
				System.out.print("Input the arrival probability: ");
				double arrivalProbability = stdin.nextDouble();
				System.out.print("Input number of streets: ");
				int streetNum = stdin.nextInt();
				stdin.nextLine();
				
				ArrayList<String> tempRoadNames = new ArrayList<String>();
				
				int streetCount = 1;
				boolean roadCount = false;
				while (roadCount == false) {
					System.out.print("Input Street " + streetCount + " name: ");
					String input = stdin.nextLine();
					if (streetCount == 1) {
						tempRoadNames.add(input);
						streetCount++;
					}
					else {
						for (int i = tempRoadNames.size()-1; i >= 0; i--) {
							if (input.equals(tempRoadNames.get(i))) {
								System.out.println("Duplicate Detected");
							}
							else if (i == tempRoadNames.size()-1) {
								tempRoadNames.add(input);
								streetCount++;
								if (streetCount > streetNum) {
									roadCount = true;
								}
							}
						}
					}
				}
				
				String[] roadNames = tempRoadNames.toArray(new String[tempRoadNames.size()]);
				int[] maxGreenTimes = new int[streetNum];
				for (int i = 0; i < streetNum; i++) {
					System.out.print("Input max green time for " + tempRoadNames.get(i) + ": ");
					maxGreenTimes[i] = stdin.nextInt();
				}
				
				System.out.println();
				System.out.println("Starting simulation: ");
				System.out.println();
				
				simulate(simulationTime, arrivalProbability, roadNames, maxGreenTimes);

				param = false;
				stdin.close();
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * The method that does the actual simulation
	 * 
	 * @param simulationTime
	 *    How long cars can appear within the simulation
	 *    
	 * @param arrivalProbability
	 *    Indicates the probability that a vehicle will arrive at any lane 
	 *    during a time step.
	 *    
	 * @param roadNames
	 *    A String array that contains the names of each road
	 *    
	 * @param maxGreenTimes
	 *    An Integer Array that contains the Green Times of each road
	 */
	public static void simulate(int simulationTime, double arrivalProbability, String[] roadNames, int[] maxGreenTimes) {
		int currentTime = 1;
		BooleanSourceHW4 probability = new BooleanSourceHW4(arrivalProbability);
		TwoWayRoad[] input = new TwoWayRoad[roadNames.length];
		int carsPassed = 0;
		int totalWaitTime = 0;
		int longest = 0;
		for (int i = 0; i < roadNames.length; i++) {
			input[i] = new TwoWayRoad(roadNames[i], maxGreenTimes[i]);
		}
		
		Intersection intersection = new Intersection(input);

		while (intersection.isEmpty() == false || currentTime < simulationTime) {
			ArrayList<Vehicle> arrivingCars = new ArrayList<Vehicle>();
			ArrayList<Integer> roadCount = new ArrayList<Integer>();
			ArrayList<String> arrivingWay = new ArrayList<String>();
			ArrayList<String> arrivingLane = new ArrayList<String>(); 
			if (currentTime < simulationTime) {
				for (int i = 0; i < intersection.getNumRoads(); i++) {
					for (int j = 0; j < intersection.getRoad(i).NUM_WAYS; j++) {
						for (int k = 0; k < intersection.getRoad(i).NUM_LANES; k++) {
							if (probability.occursHW4()) {
								Vehicle vehicleInput = new Vehicle(currentTime);
								intersection.enqueueVehicle(i, j, k, vehicleInput);
								arrivingCars.add(vehicleInput);
								roadCount.add(i);
								switch (j) {
									case 0:
										arrivingWay.add("FORWARD");
										break;
									case 1: 
										arrivingWay.add("BACKWARD");
										break;
								}
								switch (k) {
									case 0:
										arrivingLane.add("LEFT");
										break;
									case 1:
										arrivingLane.add("MIDDLE");
										break;
									case 2:
										arrivingLane.add("RIGHT");
										break;
								}
							}
						}
					}
				}
			}

			List<Vehicle> passingCars = Arrays.asList(intersection.timeStep());
			
			System.out.println("################################################################################");
			System.out.println();
			System.out.println("Time Step: " + currentTime);
			System.out.println();
			
			System.out.println("Green light for " + intersection.getGreenLight());
			System.out.println("Timer = " + intersection.getCountdownTimer());
			System.out.println();
			
			System.out.println("ARRIVING CARS: ");
			for (int i = 0; i < arrivingCars.size(); i++) {
				System.out.println("Car[" + arrivingCars.get(i).getSerialID() + "] entered " + intersection.getRoad(roadCount.get(i)).getName() + ", going " + arrivingWay.get(i) + " in " + arrivingLane.get(i) + " lane.");
			}
			
			System.out.println();
			System.out.println("PASSING CARS: "); 
			for (int i = 0; i < passingCars.size(); i++) {
				int waitTime = currentTime - passingCars.get(i).getTimeArrived(); 
				System.out.println("Car[" + passingCars.get(i).getSerialID() + "] passes through. Wait time of " + waitTime + ".");
				if (waitTime > longest) {
					longest = waitTime;
				}
				totalWaitTime += waitTime;
			}
			currentTime++;	
			carsPassed += passingCars.size();
			double averageWaitTime = (double)totalWaitTime/(double)carsPassed;
			
			System.out.println();
			intersection.display();
			System.out.println();
			
			System.out.println("STATISTICS");
			System.out.println(String.format("%-24s", "Cars currently waiting:") + intersection.getVehicleCount() + " cars");
			System.out.println(String.format("%-24s", "Total cars passed: ") + carsPassed + " cars");
			System.out.println(String.format("%-24s", "Total wait time: ") + totalWaitTime + " turns");
			System.out.println(String.format("%-24s", "Average wait time: ") + averageWaitTime + " turns");
			System.out.println("################################################################################");

		}
		
		System.out.println("################################################################################\r\n"
				+ "################################################################################");
		System.out.println();
		
		System.out.println("SIMULATION SUMMARY: ");
		System.out.println();
		
		System.out.println(String.format("%-22s", "Total Time: ") + currentTime + " steps");
		System.out.println(String.format("%-22s", "Total vehicles: ") + carsPassed + " vehicles");
		System.out.println(String.format("%-22s", "Longest wait time: ") + longest + " turns");
		System.out.println(String.format("%-22s", "Total wait time: ") + totalWaitTime + " turns");
		double averageWaitTime = (double)totalWaitTime/(double)carsPassed;
		System.out.println(String.format("%-22s", "Average wait Time: ") + averageWaitTime + " turns");

		System.out.println();
		System.out.println("End Simulation");

	}
}
