package com.viraj.projectbackend.Attendance.service;

import com.viraj.projectbackend.Attendance.model.AttendanceLog;
import com.viraj.projectbackend.Attendance.repo.AttendanceLogRepository;
import com.viraj.projectbackend.Subject.model.Subject;
import com.viraj.projectbackend.Subject.repo.SubjectRepository;
import com.viraj.projectbackend.user.model.User;
import com.viraj.projectbackend.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceLogRepository attendanceLogRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    // Get all attendance of a user
    public List<AttendanceLog> getAttendanceByUser(Long userId) {
        return attendanceLogRepository.findByUserId(userId);
    }

    // Get attendance of a user's subject
    public List<AttendanceLog> getAttendanceByUserAndSubject(Long userId, Long subjectId) {
        return attendanceLogRepository.findByUserIdAndSubjectId(userId, subjectId);
    }

    // Add attendance
    public AttendanceLog addAttendance(Long userId, AttendanceLog attendanceLog) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subject subject = subjectRepository.findById(attendanceLog.getSubject().getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        if (!subject.getUser().getId().equals(userId)) {
            throw new RuntimeException("This subject does not belong to the user.");
        }

        attendanceLog.setUser(user);
        attendanceLog.setSubject(subject);

        return attendanceLogRepository.save(attendanceLog);
    }

    // Update attendance
    public AttendanceLog updateAttendance(Long id, Long userId, AttendanceLog updatedAttendance) {

        AttendanceLog existing = attendanceLogRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        Subject subject = subjectRepository.findById(updatedAttendance.getSubject().getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        if (!subject.getUser().getId().equals(userId)) {
            throw new RuntimeException("This subject does not belong to the user.");
        }

        existing.setAttendanceDate(updatedAttendance.getAttendanceDate());
        existing.setStatus(updatedAttendance.getStatus());
        existing.setNotes(updatedAttendance.getNotes());
        existing.setSubject(subject);

        return attendanceLogRepository.save(existing);
    }

    // Delete attendance
    public void deleteAttendance(Long id, Long userId) {

        AttendanceLog attendance = attendanceLogRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        attendanceLogRepository.delete(attendance);
    }
}