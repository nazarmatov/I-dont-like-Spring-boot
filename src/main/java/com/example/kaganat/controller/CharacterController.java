package com.example.kaganat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.kaganat.service.CharacterService;
import com.example.kaganat.model.Character;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/characters")
@CrossOrigin
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }
    @GetMapping
    public List<Character> getAllCharacters(){
        return characterService.getAllCharacters();
    }

    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character){
        Character newCharacter = characterService.addCharacter(character);  
        return new ResponseEntity<>(newCharacter, HttpStatus.CREATED);
    }

    @PutMapping("/{name}")
    public ResponseEntity<Character> updateCharacter(@PathVariable String name, @RequestBody Character character){
        Character updatedCharacter = characterService.updateCharacter(name, character);
        if (updatedCharacter != null) {
            return ResponseEntity.ok(character);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable String name){
        characterService.deleteCharacter(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }   

    @GetMapping("/side/{side}")
    public List<Character> getCharacterBySide(@PathVariable String side){
        return characterService.getCharacterBySide(side);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Character> getCharacterByName(@PathVariable String name) {
        Optional<Character> character = characterService.getCharacterByName(name);

        if (character.isPresent()) {
            return ResponseEntity.ok(character.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}