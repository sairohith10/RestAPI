package com.tcs.springex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.springex.Entity.Hospital;
import com.tcs.springex.model.HospitalDetails;
import com.tcs.springex.service.HospitalService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/hospital")
public class Hospitalcontroller {
	
	@Autowired
	HospitalService hospitalService;
	
	@PostMapping("/save")
	public ResponseEntity<Hospital> createHospital(@Valid @RequestBody HospitalDetails hospitalDetails) {
		Hospital hospital= hospitalService.saveHospitalDetails(hospitalDetails);
		return ResponseEntity.status(HttpStatus.CREATED).header("status", "datacreated").body(hospital);
			
		}
	
	
	@GetMapping("/{id}")
	public Hospital getHospitalDetailsById(@PathVariable Long id) {
		return hospitalService.getHospitalById(id);
	}
	
	@GetMapping("/getall")
	public List<Hospital> getHospitals(){
		return hospitalService.getHospitalsList();
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteHospitalData(@PathVariable long id) {
		hospitalService.deleteHospitalById(id);
	}

	@PostMapping("/saveall")
	public List<Hospital> saveAll(@RequestBody List<HospitalDetails> hospitals){
		return hospitalService.saveAllHospitalsDetails(hospitals);
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospitalDetails
    (
      @PathVariable Long id, @RequestBody HospitalDetails hospitalDetails
    ) 
    {
        Hospital updatedHospital = hospitalService.updateHospitalDetails(id, hospitalDetails);
        if (updatedHospital != null) 
        {
            return ResponseEntity.ok(updatedHospital);
        } 
        else 
        {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/hospital/{name}")
    public ResponseEntity<Hospital> getHospitalByName(@PathVariable String name) {
        Hospital hospital = hospitalService.getHospitalByName(name);
        return ResponseEntity.ok(hospital);
    }
    
    @GetMapping("/details")
    public ResponseEntity<Hospital> getHospitalDetails(@RequestParam String name, @RequestParam String loc) {
        Hospital hospital = hospitalService.getHospitalByNameAndLoc(name, loc);
        if (hospital != null) {
            return ResponseEntity.ok().body(hospital);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @GetMapping("/rating-range")
    public ResponseEntity<List<Hospital>> getHospitalsWithRatingInRange(@RequestParam double minRating, @RequestParam double maxRating) {
        List<Hospital> hospitals = hospitalService.getHospitalsWithRatingInRange(minRating, maxRating);
       if(hospitals!=null) {
        return ResponseEntity.ok().body(hospitals);
       }
        else {
        	return ResponseEntity.notFound().build();
        }
        }
    
    @GetMapping("/details/{names}")
    public ResponseEntity<List<Hospital>> getHospitalDetailsByNames(@PathVariable List<String> names) {
        List<Hospital> hospitals = hospitalService.getHospitalsByNames(names);
       if(hospitals!=null) {
    	   return ResponseEntity.ok().body(hospitals); 
       }
       else
       {
    	   return ResponseEntity.notFound().build();
       }
        
    }
    
    @GetMapping("/sortdesc")
    public List<Hospital> getHosiptalSortedDesc(){
    	return hospitalService.getAllHospitalsBysorted();
    }
    
    @GetMapping("{pageNumber}/{pageSize}")
    public Page<Hospital> getHospitalPaging(@PathVariable int pageNumber,@PathVariable int pageSize){
    	return hospitalService.getDataByPaging(pageNumber,pageSize);
    }
    
    }

	
