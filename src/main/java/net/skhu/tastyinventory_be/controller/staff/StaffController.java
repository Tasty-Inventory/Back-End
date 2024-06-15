package net.skhu.tastyinventory_be.controller.staff;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.skhu.tastyinventory_be.common.dto.BaseResponse;
import net.skhu.tastyinventory_be.domain.staff.Staff;
import net.skhu.tastyinventory_be.controller.staff.dto.StaffResponseDto;
import net.skhu.tastyinventory_be.exception.ErrorCode;
import net.skhu.tastyinventory_be.exception.SuccessCode;
import net.skhu.tastyinventory_be.service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import net.skhu.tastyinventory_be.controller.staff.dto.StaffEdit;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("staff")
@Slf4j
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<StaffResponseDto>>> findAll() {
        return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, staffService.getStaff()));
    }


    @PostMapping
    public ResponseEntity<?> createStaff(@Valid @RequestBody StaffEdit staffEdit) {

        Staff staff = new Staff();
        staff.setName(staffEdit.getName());
        staff.setRrn(staffEdit.getRrn());
        staff.setPhoneNumber(staffEdit.getPhoneNumber());
        staff.setEmail(staffEdit.getEmail());
        staff.setAddress(staffEdit.getAddress());
        staff.setPosition(staffEdit.getPosition());
        staff.setHireDate(staffEdit.getHireDate());
        staff.setEmploymentStatus(staffEdit.getEmploymentStatus());
        staff.setBankAccount(staffEdit.getBankAccount());
        staff.setNote(staffEdit.getNote());

        staffService.save(staff);

        return ResponseEntity.ok(BaseResponse.success(SuccessCode.STAFF_CREATE_SUCCESS));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getStaffDetails(@PathVariable(name ="id") Long id) {
        StaffResponseDto staff = staffService.getStaffDetails(id);
        if (staff != null) {
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.GET_SUCCESS, staff));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> editStaffDetails(@PathVariable(name ="id") Long id, @Valid @RequestBody StaffEdit staffEdit) {

        Optional<Staff> staffOptional = staffService.findById(id);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();

            if (staffEdit.getName() != null) {
                staff.setName(staffEdit.getName());
            }
            if (staffEdit.getRrn() != null) {
                staff.setRrn(staffEdit.getRrn());
            }
            if (staffEdit.getPhoneNumber() != null) {
                staff.setPhoneNumber(staffEdit.getPhoneNumber());
            }
            if (staffEdit.getEmail() != null) {
                staff.setEmail(staffEdit.getEmail());
            }
            if (staffEdit.getAddress() != null) {
                staff.setAddress(staffEdit.getAddress());
            }
            if (staffEdit.getPosition() != null) {
                staff.setPosition(staffEdit.getPosition());
            }
            if (staffEdit.getHireDate() != null) {
                staff.setHireDate(staffEdit.getHireDate());
            }
            if (staffEdit.getEmploymentStatus() != null) {
                staff.setEmploymentStatus(staffEdit.getEmploymentStatus());
            }
            if (staffEdit.getBankAccount() != null) {
                staff.setBankAccount(staffEdit.getBankAccount());
            }
            if (staffEdit.getNote() != null) {
                staff.setNote(staffEdit.getNote());
            }


            staffService.save(staff);

                return ResponseEntity.ok(BaseResponse.success(SuccessCode.STAFF_PATCH_SUCCESS));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_STAFF_EXCEPTION));
            }
        }



    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStaff(@PathVariable(name ="id") Long id) {
        Optional<Staff> staffOptional = staffService.findById(id);
        if (staffOptional.isPresent()) {
            staffService.deleteById(id);
            return ResponseEntity.ok(BaseResponse.success(SuccessCode.STAFF_DELETE_SUCCESS));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.error(ErrorCode.NOT_FOUND_STAFF_EXCEPTION));
        }
    }
}

