package com.Github.ProgettoPersonale.Services;

import com.Github.ProgettoPersonale.Entities.Staff;
import com.Github.ProgettoPersonale.Repositories.StaffRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffService {

    @Autowired
    StaffRepo staffRepo;

    public List<Staff> getAllStaff(){
        return staffRepo.findAll();
    }

    public Staff getStaffById(long id) throws Exception {
        Optional<Staff> optionalStaff = staffRepo.findById(id);
        if (optionalStaff.isPresent()){
            return optionalStaff.get();
        }else throw new Exception("I did not find the ID");
    }

    public Staff saveStaff(Staff staff){
        return staffRepo.save(staff);
    }

    public String deleteStaff(Long id){
        try { staffRepo.deleteById(id);
            return "The staff with ID: " + id + " has been deleted correctly";
        } catch (Exception e) {
            return "I Couldn't find the staff ID: " +id;
        }
    }
}