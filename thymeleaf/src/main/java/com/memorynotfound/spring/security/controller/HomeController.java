package com.memorynotfound.spring.security.controller;

import com.memorynotfound.spring.security.model.Word;
import com.memorynotfound.spring.security.repository.WordRepository;
import com.memorynotfound.spring.security.service.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    WordServiceImpl wordSevice;
    WordRepository wordRepository;

    List<Word> list = new ArrayList<>();

    @GetMapping("/init/data")
    public void insertData() throws FileNotFoundException {
        String pathEV = "E:\\T - topica\\T - template\\thymeleaf\\src\\main\\java\\com\\memorynotfound\\spring\\security\\data\\VE.txt";
        BufferedReader br = new BufferedReader(new FileReader(pathEV));
        try {
            String line = br.readLine();
            while (line != null) {
                String ar[] = line.split(":");
                if (ar.length > 1) {
                    Word word = new Word();
                    word.setKeyWord(ar[0]);
                    word.setMean(ar[1]);
                    word.setType(1);
                    wordSevice.insertWord(word);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/insert/{key_word}/{mean}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public void insert(@PathVariable String key_word, @PathVariable String mean, @PathVariable int type) {
        Word word = new Word();
        word.setKeyWord(key_word);
        word.setMean(mean);
        word.setType(type);
        wordSevice.insertWord(word);
    }

    @RequestMapping(path = "update/{id}/{type}", method = RequestMethod.GET)
    @ResponseBody
    public void update(@PathVariable int id, @PathVariable int type) {
        Word word = new Word();
        word.setId(id);
        word.setType(type);
        wordSevice.insertWord(word);
    }

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("words", wordSevice.getAll());
        return "index";
    }

    public List<Word> getAll() {
        list = wordSevice.getAll().parallelStream().collect(Collectors.toList());
        return list;
    }

    @RequestMapping(path = "/delete/{key}", method = RequestMethod.GET)
    @ResponseBody
    public String deleteWord(@PathVariable("key") Integer key) {
        try {
            wordSevice.deleteWord(key);
            return "user/index";
        } catch (Exception e) {
            e.printStackTrace();
            return "user/index";
        }
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

}
