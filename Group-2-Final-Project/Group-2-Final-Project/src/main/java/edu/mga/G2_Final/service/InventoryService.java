package edu.mga.G2_Final.service;

import java.util.List;

import edu.mga.G2_Final.bean.Inventory;

public interface InventoryService {
	public void createInventory(Inventory inventory);
	public List<Inventory> getInventory();
	public Inventory findById(Integer id);
	public Inventory update(Inventory inventory, Integer l);
	public void deleteInventoryById(Integer id);
	public Inventory updatePartially(Inventory inventory, Integer id);
}