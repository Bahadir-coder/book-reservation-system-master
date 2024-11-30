package com.example.bookreservation.service;

import com.example.bookreservation.dao.entity.DeletedEntity;
import com.example.bookreservation.dao.exception.NotFoundException;
import com.example.bookreservation.dao.repository.DeletedRepository;
import com.example.bookreservation.mapper.DeletedMapper;
import com.example.bookreservation.model.DeletedDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletedService {
    private final DeletedRepository deletedRepository;

    private final DeletedMapper deletedMapper;

    public DeletedService(DeletedRepository deletedRepository, DeletedMapper deletedMapper){
        this.deletedRepository = deletedRepository;
        this.deletedMapper = deletedMapper;
    }

    public List<DeletedDto> getAllDeleted(){
        System.out.println("Get All Deleted Started...");

        List<DeletedEntity> deletedEntities = deletedRepository.
                findAll();
        if(deletedEntities.isEmpty()){
            throw new NotFoundException("Deleted is Empty");
        }
        List<DeletedDto> deletedDtos = deletedMapper.
                mapEntityToDtos(deletedEntities);
        return deletedDtos;
    }

    public DeletedDto findByBookCode(String bookCode){
        System.out.println("Find by Book Code Started...");

        DeletedEntity deletedEntity = deletedRepository.
                findByBookCodeIgnoreCase(bookCode);
        if(deletedEntity == null){
            throw new NotFoundException("Book Not Found!");
        }
        DeletedDto deletedDto = deletedMapper.
                mapEntityToDto(deletedEntity);
        return deletedDto;
    }

    public List<DeletedDto> findByBookName(String bookName){
        System.out.println("Find by Book Name Started...");

        List<DeletedEntity> deletedEntities = deletedRepository.
                findByBookNameIgnoreCase(bookName);
        if(deletedEntities.isEmpty()){
            throw new NotFoundException("Book Not Found!");
        }
        List<DeletedDto> deletedDtos = deletedMapper.
                mapEntityToDtos(deletedEntities);
        return deletedDtos;
    }
}
