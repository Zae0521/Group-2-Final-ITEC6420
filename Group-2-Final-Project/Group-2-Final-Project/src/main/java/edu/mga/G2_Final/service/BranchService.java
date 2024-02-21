package edu.mga.G2_Final.service;

import java.util.List;

import edu.mga.G2_Final.bean.Branch;

public interface BranchService {
	public void createBranch(Branch branch);
	public List<Branch> getBranchName();
	public Branch findById(Integer id);
	public Branch update(Branch branch, Integer l);
	public void deleteBranchById(Integer id);
	public Branch updatePartially(Branch branch, Integer id);
}