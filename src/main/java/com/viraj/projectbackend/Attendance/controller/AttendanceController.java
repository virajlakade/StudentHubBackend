package com.viraj.projectbackend.Attendance.controller;

import com.viraj.projectbackend.Attendance.model.AttendanceLog;
import com.viraj.projectbackend.Attendance.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Get all attendance of a user
    @GetMapping("/user/{userId}")
    public List<AttendanceLog> getAttendanceByUser(
            @PathVariable Long userId) {

        return attendanceService.getAttendanceByUser(userId);
    }

    // Get attendance of a user's subject
    @GetMapping("/user/{userId}/subject/{subjectId}")
    public List<AttendanceLog> getAttendanceByUserAndSubject(
            @PathVariable Long userId,
            @PathVariable Long subjectId) {

        return attendanceService.getAttendanceByUserAndSubject(userId, subjectId);
    }

    // Add attendance
    @PostMapping("/user/{userId}")
    public AttendanceLog addAttendance(
            @PathVariable Long userId,
            @RequestBody AttendanceLog attendanceLog) {

        return attendanceService.addAttendance(userId, attendanceLog);
    }

    // Update attendance
    @PutMapping("/user/{userId}/{id}")
    public AttendanceLog updateAttendance(
            @PathVariable Long userId,
            @PathVariable Long id,
            @RequestBody AttendanceLog attendanceLog) {

        return attendanceService.updateAttendance(id, userId, attendanceLog);
    }

    // Delete attendance
    @DeleteMapping("/user/{userId}/{id}")
    public void deleteAttendance(
            @PathVariable Long userId,
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id, userId);
    }
}