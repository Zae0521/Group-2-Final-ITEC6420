package edu.mga.G2_Final.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mga.G2_Final.bean.Branch;
import edu.mga.G2_Final.repository.BranchRepository;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

	@Autowired
	BranchRepository branchRepository;

	public void createBranch(Branch branch) {
		// TODO Auto-generated method stub
		branchRepository.save(branch);
	}

	public List<Branch> getBranchName() {
		// TODO Auto-generated method stub
		return (List<Branch>) branchRepository.findAll();
	}

	public Branch findById(Integer id) {
		// TODO Auto-generated method stub
		return branchRepository.findById(id).get();
	}

	public Branch update(Branch branch, Integer l) {
		// TODO Auto-generated method stub
		return branchRepository.save(branch);
	}

	public void deleteBranchById(Integer id) {
		// TODO Auto-generated method stub
		branchRepository.deleteById(id);
	}

	public Branch updatePartially(Branch branch, Integer id) {
		// TODO Auto-generated method stub
		Branch brnch = findById(id);
		
		if (branch.getId() != null){
			brnch.setId(branch.getId());}
		
		if (branch.getBranchName() != null){
		brnch.setBranchName(branch.getBranchName());}
		
		if (branch.getAddress() != null){
			brnch.setAddress(branch.getAddress());}
		
		if (branch.getCity() != null){
			brnch.setCity(branch.getCity());}
		
		if (branch.getState() != null){
			brnch.setState(branch.getState());}
		
		if (branch.getZip() != null){
			brnch.setZip(branch.getZip());}
		
		if (branch.getPhone() != null){
			brnch.setPhone(branch.getPhone());}
		
		return branchRepository.save(brnch);
	}

}
