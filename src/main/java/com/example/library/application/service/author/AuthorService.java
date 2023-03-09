package com.example.library.application.service.author;

import com.example.library.domian.author.entity.Author;
import com.example.library.domian.author.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthorService {
    private AuthorRepository authorRepository;

    public List<Author> findAll(){
        return authorRepository.findAll();
    }
    public Optional<Author> findById(UUID id){
        return authorRepository.findById(id);
    }
    @Transactional
    public Author save(Author author){
        return authorRepository.save(author);
    }
    @Transactional
    public void delete(UUID id){
        authorRepository.deleteById(id);
    }
}
