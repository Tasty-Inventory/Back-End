package net.skhu.tastyinventory_be.service;

import lombok.RequiredArgsConstructor;
import net.skhu.tastyinventory_be.controller.staff.dto.StaffResponseDto;
import net.skhu.tastyinventory_be.domain.staff.Staff;
import net.skhu.tastyinventory_be.domain.staff.StaffRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StaffService {
    private final StaffRepository staffRepository;

    @Transactional
    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    @Transactional
    public List<StaffResponseDto> getStaff(){
        List<Staff> staffs = staffRepository.findAll();
        List<StaffResponseDto> staffResponseDtos = new ArrayList<>();
        for (Staff staff : staffs) {
            StaffResponseDto staffResponseDto = new StaffResponseDto();
            staffResponseDto.setId(staff.getId());
            staffResponseDto.setName(staff.getName());
            staffResponseDto.setPhoneNumber(staff.getPhoneNumber());
            staffResponseDto.setPosition(staff.getPosition());
            staffResponseDtos.add(staffResponseDto);
        }
        return staffResponseDtos;
    }

    @Transactional(readOnly = true)
    public StaffResponseDto getStaffDetails(Long id) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            StaffResponseDto staffResponseDto = new StaffResponseDto();
            staffResponseDto.setId(staff.getId());
            staffResponseDto.setName(staff.getName());
            staffResponseDto.setPhoneNumber(staff.getPhoneNumber());
            staffResponseDto.setPosition(staff.getPosition());

            return staffResponseDto;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteById(Long id) {
        staffRepository.deleteById(id);
    }
    @Transactional
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }
}