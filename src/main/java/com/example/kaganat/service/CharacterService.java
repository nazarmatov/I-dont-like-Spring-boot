package com.example.kaganat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.kaganat.repository.CharacterRepository;
import com.example.kaganat.model.Character;


@Service
public class CharacterService {
    private final CharacterRepository characterRepository;
    
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public List<Character> getCharacterBySide(String side) {
        return characterRepository.findBySideIgnoreCase(side);
    }

    public Character addCharacter(Character character) {
        if (characterRepository.existsById(character.getName())) {
            throw new IllegalStateException("Character already exists");
        }
        return characterRepository.save(character);
    }

    public void deleteCharacter(String name) {
        if (!characterRepository.existsById(name)) {
            throw new IllegalStateException("Character not found");
        }
        characterRepository.deleteById(name);
    }

    public Character updateCharacter(String name, Character updated) {
    return characterRepository.findById(name)
        .map(character -> {
            if (updated.getAge() != null) {
                character.setAge(updated.getAge());
            }
            if (updated.getForcePower() != null) {
                character.setForcePower(updated.getForcePower());
            }
            if (updated.getSide() != null) {
                character.setSide(updated.getSide());
            }
            return characterRepository.save(character);
        })
        .orElse(null);
    }



    public Optional<Character> getCharacterByName(String name) {
        return characterRepository.findByName(name);
    }
}
