package com.viraj.projectbackend.Repo.LostnFound;

import com.viraj.projectbackend.model.LostnFound.LostFound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostFoundRepo extends JpaRepository<LostFound,Long> {

 List<LostFound> findByLostnFoundId(Long lostFoundId);


}
