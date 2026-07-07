package com.viraj.projectbackend.Attendance.controller;



import com.viraj.projectbackend.Attendance.model.AttendanceLog;
import com.viraj.projectbackend.Attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    @GetMapping
    public List<AttendanceLog> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/subject/{subjectId}")
    public List<AttendanceLog> getAttendanceBySubject(
            @PathVariable Long subjectId) {

        return attendanceService.getAttendanceBySubject(subjectId);
    }

    @PostMapping
    public AttendanceLog addAttendance(
            @RequestBody AttendanceLog attendanceLog) {

        return attendanceService.addAttendance(attendanceLog);
    }

    @PutMapping("/{id}")
    public AttendanceLog updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceLog attendanceLog) {

        return attendanceService.updateAttendance(id, attendanceLog);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }

}
