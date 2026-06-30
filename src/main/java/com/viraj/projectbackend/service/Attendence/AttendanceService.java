package com.viraj.projectbackend.service;

import com.viraj.projectbackend.Repo.Attendence.AttendanceLogRepository;
import com.viraj.projectbackend.model.Attendence.AttendanceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceLogRepository attendanceLogRepository;

    // Get all attendance logs
    public List<AttendanceLog> getAllAttendance() {
        return attendanceLogRepository.findAll();
    }

    // Get attendance logs for a particular subject
    public List<AttendanceLog> getAttendanceBySubject(Long subjectId) {
        return attendanceLogRepository.findBySubjectId(subjectId);
    }

    // Add new attendance
    public AttendanceLog addAttendance(AttendanceLog attendanceLog) {
        return attendanceLogRepository.save(attendanceLog);
    }

    // Update attendance
    public AttendanceLog updateAttendance(Long id, AttendanceLog attendanceLog) {
        attendanceLog.setId(id);
        return attendanceLogRepository.save(attendanceLog);
    }

    // Delete attendance
    public void deleteAttendance(Long id) {
        attendanceLogRepository.deleteById(id);
    }
}