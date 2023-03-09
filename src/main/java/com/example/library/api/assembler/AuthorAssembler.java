package com.example.library.api.assembler;

import com.example.library.domian.author.dto.AuthorRequest;
import com.example.library.domian.author.dto.AuthorResponse;
import com.example.library.domian.author.entity.Author;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthorAssembler {
    private ModelMapper modelMapper;

    public AuthorResponse entityToDto(Author author){
        return modelMapper.map(author, AuthorResponse.class);
    }
    public Author dtoToEntity(AuthorRequest authorRequest){
        return  modelMapper.map(authorRequest, Author.class);
    }

    public List<AuthorResponse> toCollectionDto(List<Author> authors){
        return authors.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
