package com.driver.service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.HotelManagementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class HotelManagementService {

    HotelManagementRepository HMR = new HotelManagementRepository();

    public String addHotel(Hotel hotel) {
        return HMR.addHotel(hotel);
    }

    public Integer addUser(User user) {
       return HMR.addUser(user);
    }

    public String getHotelWithMostFacilities() {
       ArrayList<String> ans = new ArrayList<>();
       int maxFac = Integer.MIN_VALUE;

        for(String n : HMR.hotelDB.keySet()){
            Hotel h = HMR.hotelDB.get(n);
            if(h.getFacilities().size() >= maxFac){
                maxFac = h.getFacilities().size();
                ans.add(n);
            }
        }
        Collections.sort(ans);
        return ans.get(0);
    }

    public int bookARoom(Booking booking) {
        String hotelName = booking.getHotelName();
        Hotel hotel = HMR.hotelDB.get(hotelName);
        int noOfRooms = hotel.getAvailableRooms();

        if(noOfRooms < booking.getNoOfRooms()){
            return -1;
        }
        booking.setBookingId(String.valueOf(UUID.randomUUID()));
        int amount = booking.getNoOfRooms()* hotel.getPricePerNight();
        booking.setAmountToBePaid(amount);
        HMR.BookingDB.put(booking.getBookingId(), booking);
        return amount;
    }

    public int getBookings(Integer aadharCard) {
        int ans = 0;
        for(String bookid : HMR.BookingDB.keySet()){
            Booking b = HMR.BookingDB.get(bookid);
            if(b.getBookingAadharCard()==aadharCard){
                ans++;
            }

        }  return ans;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
      Hotel h = HMR.hotelDB.get(hotelName);
      List<Facility> lf = h.getFacilities();

      for(Facility fac : newFacilities){
          if(lf.contains(fac)==false){
              lf.add(fac);
          }
      }
        h.setFacilities(lf);
      HMR.hotelDB.put(hotelName,h);
      return h;
    }
}
