package com.memorynotfound.spring.security.repository;

import com.memorynotfound.spring.security.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    @Query("select word from Word word where word.keyWord = :key")
    List<Word> findByKey(@Param("key") String key);

    @Query("select word from Word word where word.keyWord = :key")
    Word findByWord(@Param("key") String key);

    @Query(value = "SELECT * FROM Word WHERE key_word like ?1% LIMIT 1", nativeQuery = true)
    List<Word> suggestWord(@Param("key") String key);
}
