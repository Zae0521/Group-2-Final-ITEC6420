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

import edu.mga.G2_Final.bean.Inventory;
import edu.mga.G2_Final.service.InventoryService;

@RestController
@RequestMapping(value={"/inventory"})
public class InventoryController {
	@Autowired
	InventoryService inventoryService;
	
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Integer id) {
        System.out.println("Fetching Inventory with id " + id);
        Inventory inventory = inventoryService.findById(id);
        if (inventory == null) {
            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
    }
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createInventory(@RequestBody Inventory inventory, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating Inventory "+inventory.getId());
	     inventoryService.createInventory(inventory);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/inventory/{id}").buildAndExpand(inventory.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Inventory> getAllInventory() {	 
	  List<Inventory> inventory=inventoryService.getInventory();
	  return inventory;
	
	 }

	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateInventory(@RequestBody Inventory currentInv)
	{
		System.out.println("sd");
		Inventory inventory = inventoryService.findById(currentInv.getId());
	if (inventory==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	inventoryService.update(currentInv, currentInv.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Inventory> deleteInventory(@PathVariable Integer id){
		Inventory inventory = inventoryService.findById(id);
		if (inventory == null) {
			return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
		}
		inventoryService.deleteInventoryById(id);
		return new ResponseEntity<Inventory>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Inventory> updateInventoryPartially(@PathVariable Integer id, @RequestBody Inventory currentInv){
		Inventory inventory = inventoryService.findById(id);
		if(inventory ==null){
			return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
		}
		Inventory invt =	inventoryService.updatePartially(currentInv, id);
		return new ResponseEntity<Inventory>(invt, HttpStatus.OK);
	}
}