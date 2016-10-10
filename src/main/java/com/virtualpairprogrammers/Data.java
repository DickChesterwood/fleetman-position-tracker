package com.virtualpairprogrammers;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.springframework.stereotype.Repository;

@Repository
// Just a standin for a proper database
public class Data 
{
	Map<String,Queue<Position>> positionQueues;
	
	public Data()
	{
		positionQueues = new HashMap<>();
	}
	
	public void updatePosition(Map<String,String> data)
	{
		String vehicleName = data.get("vehicle");
		Queue<Position> positions = positionQueues.get(vehicleName);
		if (positions == null) 
		{
			positions = new PriorityQueue<Position>();
			positionQueues.put(vehicleName, positions);
		}
		Position newPosition = new Position(data.get("lat"), data.get("long"), data.get("time"));
		positions.add(newPosition);
	}
	
	public Position getLatestPositionFor(String vehicleName) throws VehicleNotFoundException
	{
		Queue<Position> queue = positionQueues.get(vehicleName);
		if (queue == null) throw new VehicleNotFoundException();
		return queue.peek();
	}
}
