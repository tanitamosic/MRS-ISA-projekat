package com.Projekat.repository;

import com.Projekat.model.services.Boat;
import com.Projekat.model.services.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoatRepository extends JpaRepository<Boat,Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value="UPDATE BOATS SET is_deleted=true WHERE id=?1")
    void deleteBoat(Integer id);
}
