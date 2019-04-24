package com.memorynotfound.spring.security.service;

import com.memorynotfound.spring.security.model.Word;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WordSevice {
    public boolean insertWord(Word word);
    public boolean findById(int id);
    public boolean update(Word word);
}
