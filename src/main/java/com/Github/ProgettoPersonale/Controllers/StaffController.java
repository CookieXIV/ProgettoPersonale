package com.Github.ProgettoPersonale.Controllers;

import com.Github.ProgettoPersonale.Entities.Staff;
import com.Github.ProgettoPersonale.Repositories.StaffRepo;
import com.Github.ProgettoPersonale.Services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    public StaffService staffServices;
    @Autowired
    private StaffRepo staffRepository;

    @GetMapping("/all")
    public List<Staff> getStaffList(){
        return staffServices.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable long id) throws Exception {
        return staffServices.getStaffById(id);
    }

    @PostMapping("/save")
    public void saveStaff(@RequestBody Staff staff){
        staffServices.saveStaff(staff);
    }

    @DeleteMapping("/delete")
    public void deleteStaffById(@RequestParam long id){
        staffServices.deleteStaff(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable long id, @RequestBody Staff staffDetails) throws Exception {
        Staff updateStaff = staffServices.getStaffById(id);

        updateStaff.setNameSurname(staffDetails.getNameSurname());
        updateStaff.setRole(staffDetails.getRole());

        staffRepository.save(updateStaff);

        return ResponseEntity.ok(updateStaff);
    }

}
