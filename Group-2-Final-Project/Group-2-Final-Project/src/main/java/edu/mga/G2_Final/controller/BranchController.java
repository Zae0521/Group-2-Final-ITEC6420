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

import edu.mga.G2_Final.bean.Branch;
import edu.mga.G2_Final.service.BranchService;

@RestController
@RequestMapping(value={"/branch"})
public class BranchController {
	@Autowired
	BranchService branchService;
	
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Branch> getBranchById(@PathVariable Integer id) {
        System.out.println("Fetching Branch with id " + id);
        Branch branch = branchService.findById(id);
        if (branch == null) {
            return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Branch>(branch, HttpStatus.OK);
    }
    
	 @PostMapping(value="/create",headers="Accept=application/json")
	 public ResponseEntity<Void> createBranch(@RequestBody Branch branch, UriComponentsBuilder ucBuilder){
	     System.out.println("Creating Branch "+branch.getBranchName());
	     branchService.createBranch(branch);
	     HttpHeaders headers = new HttpHeaders();
	     headers.setLocation(ucBuilder.path("/branch/{id}").buildAndExpand(branch.getId()).toUri());
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/get", headers="Accept=application/json")
	 public List<Branch> getAllBranch() {	 
	  List<Branch> branch=branchService.getBranchName();
	  return branch;
	
	 }

	@PutMapping(value="/update", headers="Accept=application/json")
	public ResponseEntity<String> updateBranch(@RequestBody Branch currentBranch)
	{
		System.out.println("sd");
		Branch branch = branchService.findById(currentBranch.getId());
	if (branch==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	branchService.update(currentBranch, currentBranch.getId());
	return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<Branch> deleteBranch(@PathVariable Integer id){
		Branch branch = branchService.findById(id);
		if (branch == null) {
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		}
		branchService.deleteBranchById(id);
		return new ResponseEntity<Branch>(HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping(value="/{id}", headers="Accept=application/json")
	public ResponseEntity<Branch> updateBranchPartially(@PathVariable Integer id, @RequestBody Branch currentBranch){
		Branch branch = branchService.findById(id);
		if(branch ==null){
			return new ResponseEntity<Branch>(HttpStatus.NOT_FOUND);
		}
		Branch brnch =	branchService.updatePartially(currentBranch, id);
		return new ResponseEntity<Branch>(brnch, HttpStatus.OK);
	}
}