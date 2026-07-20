package com.viraj.projectbackend.Attendance.repo;

import com.viraj.projectbackend.Attendance.model.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {

    // Get all attendance records of a user
    List<AttendanceLog> findByUserId(Long userId);

    // Get all attendance records of a subject
    List<AttendanceLog> findBySubjectId(Long subjectId);

    // Get attendance records of a user's specific subject
    List<AttendanceLog> findByUserIdAndSubjectId(Long userId, Long subjectId);

    // Get a specific attendance record belonging to a user
    Optional<AttendanceLog> findByIdAndUserId(Long id, Long userId);
}