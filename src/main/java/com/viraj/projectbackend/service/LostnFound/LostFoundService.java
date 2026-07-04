package com.viraj.projectbackend.service.LostnFound;

import com.viraj.projectbackend.Repo.LostnFound.LostFoundRepo;
import com.viraj.projectbackend.model.LostnFound.LostFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostFoundService {
    private LostFoundRepo lostFoundRepo;

    //Get All items in lostnfound
        public List<LostFound> getallLostnfounditems(){
            return lostFoundRepo.findAll();
        }
    //get Lostnfound items BY id
        public List<LostFound> getById(Long lostFoundId) {
            return lostFoundRepo.findByLostnFoundId(lostFoundId);}
    //Add LostnFound Item
        public LostFound addLostnFound(LostFound lostFound){
            return lostFoundRepo.save(lostFound);}
    //Update LOstnFound
        public LostFound upadteLOstnFound(LostFound lostFound,Long id){
            lostFound.setId(id);
            return lostFoundRepo.save(lostFound);}
    //delete LostnFound
    public void deleteItem(Long id) {
        lostFoundRepo.deleteById(id);
    }

}
