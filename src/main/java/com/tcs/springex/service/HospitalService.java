package com.tcs.springex.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tcs.springex.Entity.Hospital;
import com.tcs.springex.model.HospitalDetails;
import com.tcs.springex.repository.Hospitalrepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HospitalService {
	
	@Autowired
	Hospitalrepository hospitalrepository;

	public Hospital saveHospitalDetails(HospitalDetails hospitalDetails) {
		
		Hospital hospital = new Hospital();
		
		hospital.setName(hospitalDetails.getName());
		hospital.setLoc(hospitalDetails.getLoc());
		hospital.setRating(hospitalDetails.getRating());
		hospital.setEmail(hospitalDetails.getEmail());
		hospital.setMobile(hospitalDetails.getMobile());
		hospital.setCreateAt(LocalDateTime.now());
		hospital.setCreateBy(System.getProperty("user.name"));
		
		return hospitalrepository.save(hospital);
		
	
	}

	public Hospital getHospitalById(Long id) {
		
		Optional<Hospital> hospital = hospitalrepository.findById(id);
		
		if(hospital.isPresent()) {
			return hospital.get();
		}else {
			return null;
		}
		
	}

	public List<Hospital> getHospitalsList() {
		return hospitalrepository.findAll();
	}

	public void deleteHospitalById(long id) {
		hospitalrepository.deleteById(id);
		
	}

	public List<Hospital> saveAllHospitalsDetails(List<HospitalDetails> details) {
		List<Hospital> hospitals2 = new ArrayList<>();
		for(HospitalDetails hospitalDetails : details) {
			Hospital hospital = new Hospital();hospital.setName(hospitalDetails.getName());
			hospital.setLoc(hospitalDetails.getLoc());
			hospital.setRating(hospitalDetails.getRating());
			hospital.setEmail(hospitalDetails.getEmail());
    		hospital.setMobile(hospitalDetails.getMobile());
			hospital.setCreateAt(LocalDateTime.now());
			hospital.setCreateBy(System.getProperty("user.name"));
			
			hospitals2.add(hospital);
		}
		return hospitalrepository.saveAll(hospitals2);
	}

	public Hospital updateHospitalDetails(Long id, HospitalDetails hospitalDetails) {
        Optional<Hospital> optionalHospital = hospitalrepository.findById(id);
        if (optionalHospital.isPresent()) {
            Hospital hospital = optionalHospital.get();
            hospital.setName(hospitalDetails.getName());
            hospital.setLoc(hospitalDetails.getLoc());
            hospital.setRating(hospitalDetails.getRating());
            hospital.setEmail(hospitalDetails.getEmail());
    		hospital.setMobile(hospitalDetails.getMobile());
            hospital.setCreateAt(LocalDateTime.now());
            hospital.setCreateBy("user.name");
            return hospitalrepository.save(hospital);
        }
        return null; // or throw an exception if the hospital with the given id is not found
    }

	public Hospital getHospitalByName(String name) {
        return hospitalrepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Hospital with name " + name + " not found"));
    }

	public Hospital getHospitalByNameAndLoc(String name, String loc) {
		return hospitalrepository.findFirstByNameAndLoc(name, loc);
	}
	
	public List<Hospital> getHospitalsWithRatingInRange(double minRating, double maxRating) {
        return hospitalrepository.findByRatingBetween(minRating, maxRating);
    }

	public List<Hospital> getHospitalsByNames(List<String> names) {
		return hospitalrepository.findByNameIn(names);
	}

	public List<Hospital> getAllHospitalsBysorted() {
		Sort sort = Sort.by(Direction.DESC, "name");
		return hospitalrepository.findAll(sort);
	}

	public Page<Hospital> getDataByPaging(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return hospitalrepository.findAll(pageable);
	}
}


	
				
	
	
	
