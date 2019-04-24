package com.memorynotfound.spring.security.service;

import com.memorynotfound.spring.security.model.Word;
import com.memorynotfound.spring.security.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordSevice {
    @Autowired
    WordRepository wordRepository;

    public boolean deleteWord(Integer key) {
        try {
            wordRepository.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    @Override
    public boolean insertWord(Word word) {
        if (word.getKeyWord() != null && word.getMean() != null) ;
        wordRepository.save(word);
        return true;
    }

    @Override
    public boolean findById(int id) {
        Word word = new Word();
        word = wordRepository.findOne(id);
        if (word != null)
            return true;
        return false;
    }

    @Override
    public boolean update(Word word) {
        wordRepository.save(word);
        return true;
    }
}
