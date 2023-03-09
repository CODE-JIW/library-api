package com.example.library.api.controller;

import com.example.library.api.assembler.AuthorAssembler;
import com.example.library.application.service.author.AuthorService;
import com.example.library.domian.author.dto.AuthorRequest;
import com.example.library.domian.author.dto.AuthorResponse;
import com.example.library.domian.author.entity.Author;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/authors")
@AllArgsConstructor
public class AuthorController {

    private AuthorService authorService;
    private AuthorAssembler authorAssembler;


    @GetMapping
    public List<AuthorResponse> findAll(){
        return authorAssembler.toCollectionDto(authorService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable UUID id){
        return authorService.findById(id)
                .map(author -> ResponseEntity.ok(authorAssembler.entityToDto(author)))
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author insert(@Valid @RequestBody  AuthorRequest authorRequest){
        var author = authorAssembler.dtoToEntity(authorRequest);
        return authorService.save(author);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable UUID id, @RequestBody AuthorRequest authorRequest){
        var authorResult = authorService.findById(id);

        if(!authorResult.isPresent()){
            return ResponseEntity.notFound().build();
        }

        var author = authorAssembler.dtoToEntity(authorRequest);
        author.setId(id);

        return ResponseEntity.ok(authorService.save(author));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        var author = authorService.findById(id);

        if(!author.isPresent()){
            return ResponseEntity.notFound().build();
        }

        authorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
