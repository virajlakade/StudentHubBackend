package com.viraj.projectbackend.Repo.Attendence;



import com.viraj.projectbackend.model.Attendence.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {

    List<AttendanceLog> findBySubjectId(Long subjectId);

}
