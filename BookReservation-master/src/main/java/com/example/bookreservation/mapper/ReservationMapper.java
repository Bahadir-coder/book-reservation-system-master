package com.example.bookreservation.mapper;

import com.example.bookreservation.dao.entity.ReservationEntity;
import com.example.bookreservation.model.input.ReservationDtoInput;
import com.example.bookreservation.model.output.ReservationDtoOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "user.userFinCode", source = "userFinCode")
    @Mapping(target = "book.bookCode", source = "bookCode")
    @Mapping(target = "reservationType", source = "reservationType")
    @Mapping(target = "reservationCode", source = "reservationCode")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "expiryDate", source = "expiryDate")
    ReservationEntity mapDtoToEntity(ReservationDtoInput reservationDto);

    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "userSurname", source = "user.userSurname")
    @Mapping(target = "bookName", source = "book.bookName")
    @Mapping(target = "bookCode", source = "book.bookCode")
    @Mapping(target = "reservationType", source = "reservationType")
    @Mapping(target = "reservationCode", source = "reservationCode")
    @Mapping(target = "createdDate", source = "createdDate")
    @Mapping(target = "expiryDate", source = "expiryDate")
    ReservationDtoOutput mapEntityToDto(ReservationEntity reservationEntity);

    List<ReservationDtoOutput> mapEntityToDtos(List<ReservationEntity> reservationEntities);
}
