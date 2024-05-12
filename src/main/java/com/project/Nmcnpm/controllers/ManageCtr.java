package com.project.Nmcnpm.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.Nmcnpm.models.Staff;
import com.project.Nmcnpm.repositories.StaffResponsitory;

@Controller
public class ManageCtr {
    @Autowired
    private StaffResponsitory staffRepository;

    public ManageCtr() {
    }

    @RequestMapping("/staff")
    public String getAllstaff(Model model) {
        List<Staff> staffs = staffRepository.findAll();
        model.addAttribute("staffs", staffs);
        return "staffManageFrm";
    }

    @RequestMapping("/staff/{staffId}")
    public String getStaff(Model model, @PathVariable("staffId") Integer staffId) {
        Optional<Staff> staff = staffRepository.findById(staffId);
        if (staff.isPresent()) {
            model.addAttribute("staff", staff.get());
            model.addAttribute("error", null);
        } else {
            model.addAttribute("error", "Không tìm thấy staff với id = " + staffId);
        }
        return "EditFrm";
    }

    @RequestMapping(value = "/staffs", method = RequestMethod.POST)
    public String addStaff(
            Model model,
            @RequestBody Staff staff

    ) {
        Staff newStaff = staffRepository.save(staff);
        model.addAttribute("staff", newStaff);
        return "StaffManageFrm";
    }

    @RequestMapping(value = "/staffs/{id}", method = RequestMethod.PUT)
    public String editStaff(
            Model model,
            @RequestBody Staff staff,
            @PathVariable("id") Integer id

    ) {
        Optional<Staff> existingStaff = staffRepository.findById(id);
        if (existingStaff.isPresent()) {
            Staff newStaff = existingStaff.get();
            newStaff.setName(staff.getName());
            newStaff.setAccount(staff.getAccount());
            newStaff.setPhone(staff.getPhone());
            newStaff.setPassword(staff.getPassword());
            newStaff.setAddress(staff.getAddress());
            newStaff.setRole(staff.getRole());
            model.addAttribute("staff", newStaff);
        } else {
            model.addAttribute("error", "Lỗi lầm");

        }
        return "StaffManageFrm";
    }
    @RequestMapping(value = "/staffs/{id}", method = RequestMethod.DELETE)
    public String deleteStaff(
            Model model,
            @PathVariable("id") Integer id

    ) {
        staffRepository.deleteById(id);
        return "StaffManageFrm";
    }

    @RequestMapping("/staff/addStaff")
    public String addStaffFrm(Model model) {
       
        return "AddFrm";
    }


}
