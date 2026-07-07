package com.viraj.projectbackend.Attendance.repo;



import com.viraj.projectbackend.Attendance.model.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {

    List<AttendanceLog> findBySubjectId(Long subjectId);

}
