package com.viraj.projectbackend.Attendance.controller;

import com.viraj.projectbackend.Attendance.model.AttendanceLog;
import com.viraj.projectbackend.Attendance.service.AttendanceService;
import com.viraj.projectbackend.Subject.model.Subject;
import com.viraj.projectbackend.Subject.repo.SubjectRepository;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

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

        if (attendanceLog.getUser() != null &&
                attendanceLog.getUser().getId() != null) {

            User user = userRepository.findById(
                    attendanceLog.getUser().getId()
            ).orElseThrow(() -> new RuntimeException("User not found"));

            attendanceLog.setUser(user);
        }

        if (attendanceLog.getSubject() != null &&
                attendanceLog.getSubject().getId() != null) {

            Subject subject = subjectRepository.findById(
                    attendanceLog.getSubject().getId()
            ).orElseThrow(() -> new RuntimeException("Subject not found"));

            attendanceLog.setSubject(subject);
        }

        return attendanceService.addAttendance(attendanceLog);
    }

    @PutMapping("/{id}")
    public AttendanceLog updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceLog attendanceLog) {

        if (attendanceLog.getUser() != null &&
                attendanceLog.getUser().getId() != null) {

            User user = userRepository.findById(
                    attendanceLog.getUser().getId()
            ).orElseThrow(() -> new RuntimeException("User not found"));

            attendanceLog.setUser(user);
        }

        if (attendanceLog.getSubject() != null &&
                attendanceLog.getSubject().getId() != null) {

            Subject subject = subjectRepository.findById(
                    attendanceLog.getSubject().getId()
            ).orElseThrow(() -> new RuntimeException("Subject not found"));

            attendanceLog.setSubject(subject);
        }

        return attendanceService.updateAttendance(id, attendanceLog);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }
}