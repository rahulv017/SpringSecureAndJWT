package com.demo.spring.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.Model.ApiResponse;
import com.demo.spring.Model.Hotel;
import com.demo.spring.Model.JWTOKEN;
import com.demo.spring.Service.HotelService;
import com.demo.spring.Service.UserBasedService;
import com.demo.spring.Service.UserService;

@RestController

public class HotelController {

	@Autowired
	HotelService hotelS;
	
	@Autowired
	JWTOKEN tokenProvider;
	
	@Autowired
	UserService userS;
	@Autowired
	UserBasedService myD;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/hotels")
	public ResponseEntity<ApiResponse> saveHotels(@RequestBody Hotel hotel)
	{
		this.hotelS.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Saved the hotel information in the database",HttpStatus.OK));
	}
	
	@GetMapping("/hotels")
	public List<Hotel > getAll()
	{
		return this.hotelS.getAll();
	}
	
	@GetMapping("/hotels/{id}")
	public Optional<Hotel> getHotelById(@PathVariable("id") int id)
	{
		return this.hotelS.getHotelById(id);
	}
	
	@PutMapping("/hotels/{id}")
	public ResponseEntity<ApiResponse> updateHotelById(@PathVariable("id") int id,@RequestBody Hotel hotel)
	{
		this.hotelS.updateHotelbyId(hotel, id);
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Updated the hotel information",HttpStatus.OK));
	}
	
	@DeleteMapping("/hotels/{id}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable("id") int id)
	{
		this.hotelS.deleteHotelById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("Deleted the hotel information",HttpStatus.ACCEPTED));
	}
	
	
}
