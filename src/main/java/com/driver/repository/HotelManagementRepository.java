package com.driver.repository;

import com.driver.model.Booking;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class HotelManagementRepository {
   public HashMap<String, Hotel> hotelDB = new HashMap<>();
   public HashMap<Integer, User> UserDB = new HashMap<>();
   public HashMap<String, Booking> BookingDB = new HashMap<>();

   public String addHotel(Hotel hotel) {
     if(hotel==null){
        return "FAILURE";
     }
      String h = hotel.getHotelName();
      if(h.isEmpty() || h.length()==0 || hotel==null || h==null ||
              hotelDB.containsKey(h)){
      return "FAILURE";
      }else{
      hotelDB.put(hotel.getHotelName(),hotel);
      return "SUCCESS";
   }
   }

   public Integer addUser(User user) {

         UserDB.put(user.getaadharCardNo(), user);
         return user.getaadharCardNo();


   }
}
