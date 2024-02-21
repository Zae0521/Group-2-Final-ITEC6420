package edu.mga.G2_Final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mga.G2_Final.bean.Car;
import edu.mga.G2_Final.repository.CarRepository;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepository;

	public void createCar(Car car) {
		// TODO Auto-generated method stub
		carRepository.save(car);
	}

	public List<Car> getCar() {
		// TODO Auto-generated method stub
		return (List<Car>) carRepository.findAll();
	}

	public Car findById(Integer id) {
		// TODO Auto-generated method stub
		return carRepository.findById(id).get();
	}

	public Car update(Car car, Integer l) {
		// TODO Auto-generated method stub
		return carRepository.save(car);
	}

	public void deleteCarById(Integer id) {
		// TODO Auto-generated method stub
		carRepository.deleteById(id);
	}

	public Car updatePartially(Car car, Integer id) {
		// TODO Auto-generated method stub
		Car cr = findById(id);
		
		if (car.getId() != null){
			cr.setId(car.getId());}
		
		if (car.getModel() != null){
		cr.setModel(car.getModel());}
		
		if (car.getType() != null){
			cr.setType(car.getType());}
		
		if (car.getYear() != null){
			cr.setYear(car.getYear());}
		
		if (car.getPrice() != null){
			cr.setPrice(car.getPrice());}
		
		return carRepository.save(cr);
	}

}
