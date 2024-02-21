package edu.mga.G2_Final.service;

import java.util.List;

import edu.mga.G2_Final.bean.Car;

public interface CarService {
	public void createCar(Car car);
	public List<Car> getCar();
	public Car findById(Integer id);
	public Car update(Car car, Integer l);
	public void deleteCarById(Integer id);
	public Car updatePartially(Car car, Integer id);
}