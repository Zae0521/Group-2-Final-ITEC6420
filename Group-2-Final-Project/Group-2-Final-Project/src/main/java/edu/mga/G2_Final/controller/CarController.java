package edu.mga.G2_Final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import edu.mga.G2_Final.bean.Car;
import edu.mga.G2_Final.service.CarService;

@RestController
@RequestMapping(value={"/car"})
public class CarController {
	@Autowired
	CarService carService;
	
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> getUserById(@PathVariable Integer id) {
        System.out.println("Fetching Car with id " + id);
        Car car = carService.findById(id);
        if (car == null) {
            return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createCar(@RequestBody Car car, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating Car "+car.getModel());
	     carService.createCar(car);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/car/{id}").buildAndExpand(car.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Car> getAllCars() {	 
	  List<Car> car=carService.getCar();
	  return car;
	
	 }

	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateCar(@RequestBody Car currentCar)
	{
		System.out.println("sd");
		Car car = carService.findById(currentCar.getId());
	if (car==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	carService.update(currentCar, currentCar.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Car> deleteUser(@PathVariable Integer id){
		Car user = carService.findById(id);
		if (user == null) {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}
		carService.deleteCarById(id);
		return new ResponseEntity<Car>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Car> updateUserPartially(@PathVariable Integer id, @RequestBody Car currentCar){
		Car user = carService.findById(id);
		if(user ==null){
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}
		Car usr =	carService.updatePartially(currentCar, id);
		return new ResponseEntity<Car>(usr, HttpStatus.OK);
	}
}