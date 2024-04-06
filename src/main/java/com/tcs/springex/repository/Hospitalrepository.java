package com.tcs.springex.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.springex.Entity.Hospital;

@Repository
public interface Hospitalrepository extends JpaRepository<Hospital, Long>{

	Optional<Hospital> findByName(String name);

	Hospital findFirstByNameAndLoc(String name, String loc);

	List<Hospital> findByRatingBetween(double minRating, double maxRating);

	List<Hospital> findByNameIn(List<String> names);

	
   

	

}
