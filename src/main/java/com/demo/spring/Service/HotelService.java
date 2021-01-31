package com.demo.spring.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.DAO.HotelRepository;
import com.demo.spring.Model.Hotel;

@Service 
public class HotelService {

	
	@Autowired
	HotelRepository hotelRepo;
	
	public void saveHotel(Hotel hotel)
	{
		this.hotelRepo.save(hotel);
	}
	
	public List<Hotel>  getAll()
	{
		
		return Optional.ofNullable(this.hotelRepo.findAll()).orElseThrow();
	}
	
	public Optional<Hotel> getHotelById(int id)
	{
		return Optional.ofNullable(this.hotelRepo.findById(id).orElseThrow());
//		if(this.hotelRepo.findById(id).isPresent())
//			return this.hotelRepo.findById(id);
//		else
//			throw new NoSuchElementException();
	}
	
	public void updateHotelbyId(Hotel hotel,int id)
	{
		Optional<Hotel>ho=getHotelById(id);
		ho.get().setAdddress(hotel.getAddress());
		ho.get().setName(hotel.getName());
		ho.get().setPricePerNight(hotel.getPricePerNight());
		this.hotelRepo.save(hotel);
		
	}
	
}
