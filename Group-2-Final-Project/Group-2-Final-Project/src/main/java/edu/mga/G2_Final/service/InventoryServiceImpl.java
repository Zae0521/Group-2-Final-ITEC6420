package edu.mga.G2_Final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mga.G2_Final.bean.Inventory;
import edu.mga.G2_Final.repository.InventoryRepository;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	public void createInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		inventoryRepository.save(inventory);
	}

	public List<Inventory> getInventory() {
		// TODO Auto-generated method stub
		return (List<Inventory>) inventoryRepository.findAll();
	}

	public Inventory findById(Integer id) {
		// TODO Auto-generated method stub
		return inventoryRepository.findById(id).get();
	}

	public Inventory update(Inventory inventory, Integer l) {
		// TODO Auto-generated method stub
		return inventoryRepository.save(inventory);
	}

	public void deleteInventoryById(Integer id) {
		// TODO Auto-generated method stub
		inventoryRepository.deleteById(id);
	}

	public Inventory updatePartially(Inventory inventory, Integer id) {
		// TODO Auto-generated method stub
		Inventory invent = findById(id);
		
		if (inventory.getId() != null){
			invent.setId(inventory.getId());}
		
		if (inventory.getQuantity() != null){
		invent.setQuantity(inventory.getQuantity());}
		
		if (inventory.getBranchId() != null) {
		invent.setBranchId(inventory.getBranchId());}
		
		if (inventory.getCarId() != null) {
		invent.setCarId(inventory.getCarId());}
		
		return inventoryRepository.save(invent);
	}

}
