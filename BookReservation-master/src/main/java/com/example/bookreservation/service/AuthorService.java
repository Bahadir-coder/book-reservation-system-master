package com.example.bookreservation.service;

import com.example.bookreservation.dao.entity.AuthorEntity;
import com.example.bookreservation.dao.exception.FoundException;
import com.example.bookreservation.dao.exception.NotFoundException;
import com.example.bookreservation.dao.repository.AuthorRepository;
import com.example.bookreservation.mapper.AuthorMapper;
import com.example.bookreservation.model.AuthorDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<AuthorDto> getAllAuthors() {
        System.out.println("Get All Authors Started...");

        List<AuthorEntity> authorEntities = authorRepository.findAll();
        if (authorEntities.isEmpty()) {
            throw new NotFoundException("Authors Not Found!");
        }
        List<AuthorDto> authorDtos = authorMapper.mapEntityToDtos(authorEntities);
        return authorDtos;
    }

    public AuthorDto findByFinCode(String finCode) {
        System.out.println("Find Author by FinCode Started...");

        AuthorEntity authorEntity = authorRepository.
                findByAuthorFinCodeIgnoreCase(finCode)
                .orElseThrow(() -> new NotFoundException("Author Not Found!"));
        return authorMapper.mapEntityToDto(authorEntity);
    }

    @Transactional
    public void saveAuthor(AuthorDto authorDto) {
        System.out.println("Save Author Started...");

        AuthorEntity authorEntity = authorMapper.
                mapDtoToEntity(authorDto);

        List<AuthorEntity> authorEntities = authorRepository.findAll();
        for (int i = 0; i < authorEntities.size(); i++) {
            if (authorEntities.get(i).getAuthorFinCode().equals(authorEntity.getAuthorFinCode())) {
                throw new FoundException("Author is Found!");
            }
        }
        authorRepository.save(authorEntity);
    }

    @Transactional
    public void deleteByFinCode(String finCode) {
        System.out.println("Delete by Fin Code Started...");

        AuthorEntity authorEntity = authorRepository.
                findByAuthorFinCodeIgnoreCase(finCode).
                orElseThrow(() -> new NotFoundException("Author Not Found!"));
        authorRepository.delete(authorEntity);
    }
}