package edu.mga.G2_Final.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mga.G2_Final.bean.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}
