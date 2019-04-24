package com.memorynotfound.spring.security.repository;

import com.memorynotfound.spring.security.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    @Query("delete from Word wor where wor.keyWord = :key")
    boolean deleteByKey(@Param("key") String key);
}
