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

    // suggestWord
    public List<Word> suggestWord(String key){
        return wordRepository.suggestWord(key);
    }

    // delete by id
    public boolean deleteWord(Integer key) {
        try {
            wordRepository.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // delete by word
    public boolean deleteByWord(String key) {
        try {
            Word word = wordRepository.findByWord(key);
            wordRepository.delete(word.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    public List<Word> getByKey(String key) {
        return wordRepository.findByKey(key);
    }

    @Override
    public boolean insertWord(Word word) {
        if (word.getKeyWord() != null && word.getMean() != null) ;
        wordRepository.save(word);
        return true;
    }

    @Override
    public Word findById(int id) {
        Word word;
        word = wordRepository.findOne(id);
        if (word != null)
            return word;
        return word;
    }

    @Override
    public boolean update(Word word) {
        wordRepository.save(word);
        return true;
    }
}
