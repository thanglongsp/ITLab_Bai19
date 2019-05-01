package com.memorynotfound.spring.security.service;

import com.memorynotfound.spring.security.model.Word;
import org.springframework.stereotype.Service;
import java.io.Serializable;

@Service
public interface WordSevice extends Serializable {
    public boolean insertWord(Word word);

    public Word findById(int id);

    public boolean update(Word word);
}
