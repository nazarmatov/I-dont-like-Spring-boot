package com.example.kaganat.repository;
    
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kaganat.model.Character;

@Repository
public interface CharacterRepository extends JpaRepository<Character, String>{
    List<Character> findBySideIgnoreCase(String side);
    Optional<Character> findByName(String name);    
}
