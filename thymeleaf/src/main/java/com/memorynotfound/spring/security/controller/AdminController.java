package com.memorynotfound.spring.security.controller;

import com.memorynotfound.spring.security.model.Word;
import com.memorynotfound.spring.security.service.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    WordServiceImpl wordService;

    // manager page
    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    // delete
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String deleteWord(@RequestParam("id") int id, @RequestParam("word") String word) {
        if (id != 0)
            wordService.deleteWord(id);
        if (!word.equals(""))
            wordService.deleteByWord(word);
        return "redirect:/user";
    }

    // insert
    @RequestMapping(path = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam("word") String key_word,@RequestParam("mean") String mean,
                         @RequestParam("type") int type) {
        Word word = new Word();
        word.setKeyWord(key_word);
        word.setMean(mean);
        word.setType(type);
        wordService.insertWord(word);
        return "redirect:/user";
    }

    // Update
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") int id, @RequestParam("word") String key_word,
                         @RequestParam("mean") String mean, @RequestParam("type") int type) {
        Word word = wordService.findById(id);
        if (key_word != null)
            word.setKeyWord(key_word);
        if (mean != null)
            word.setMean(mean);
        if (type != 0)
            word.setType(type);
        wordService.insertWord(word);
        return "redirect:/user";
    }
}
