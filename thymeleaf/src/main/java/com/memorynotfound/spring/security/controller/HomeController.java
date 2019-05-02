package com.memorynotfound.spring.security.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.memorynotfound.spring.security.model.Word;
import com.memorynotfound.spring.security.service.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    WordServiceImpl wordService;

    // insert data into table
    @GetMapping("/init/data")
    public void insertData() throws FileNotFoundException {
        String pathEV = "E:\\ITLab_Bai19\\thymeleaf\\src\\main\\java\\com\\memorynotfound\\" +
                "spring\\security\\data\\VE.txt";
        BufferedReader br = new BufferedReader(new FileReader(pathEV));
        try {
            String line = br.readLine();
            while (line != null) {
                String ar[] = line.split(":");
                if (ar.length > 1) {
                    Word word = new Word();
                    word.setKeyWord(ar[0].trim());
                    word.setMean(ar[1].trim());
                    word.setType(1);
                    wordService.insertWord(word);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Home guest
    @GetMapping("/")
    public String homeInit() {
        return "redirect:/home/all";
    }
    @GetMapping("/home/")
    public String homeNull() {
        return "redirect:/home/all";
    }

    @GetMapping("/home/{word}")
    public String root(Model model, @PathVariable("word") String key) {
        if (!key.equals("all")) {
            model.addAttribute("words", wordService.getByKey(key));
            return "index";
        }
        return "index";
    }

    @GetMapping("/suggest/{key}")
    public ResponseEntity<String> suggestWord(@PathVariable("key") String key) throws JsonProcessingException {
        List<Word> words = wordService.suggestWord(key);
        List<String> list = new ArrayList();
        for (Word word:words) {
            list.add(word.getKeyWord());
        }
        ObjectMapper mapper = new ObjectMapper();
        String resp = mapper.writeValueAsString(list);
        System.out.println(list);
        return new ResponseEntity<String>(resp, HttpStatus.OK);
    }
}
