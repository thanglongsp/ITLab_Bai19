package com.memorynotfound.spring.security.service;

import com.memorynotfound.spring.security.model.Word;
import org.springframework.stereotype.Service;
import java.io.Serializable;

@Service
public interface WordSevice extends Serializable {
    boolean insertWord(Word word);

    Word findById(int id);

    boolean update(Word word);
}
