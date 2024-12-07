package com.example.bookreservation.service;

import com.example.bookreservation.dao.entity.BookEntity;
import com.example.bookreservation.dao.entity.ReservationEntity;
import com.example.bookreservation.dao.entity.UserEntity;
import com.example.bookreservation.dao.exception.FoundException;
import com.example.bookreservation.dao.exception.NotFoundException;
import com.example.bookreservation.dao.repository.BookRepository;
import com.example.bookreservation.dao.repository.ReservationRepository;
import com.example.bookreservation.dao.repository.UserRepository;
import com.example.bookreservation.mapper.BookMapper;
import com.example.bookreservation.mapper.ReservationMapper;
import com.example.bookreservation.model.input.ReservationDtoInput;
import com.example.bookreservation.model.output.BookDtoOutput;
import com.example.bookreservation.model.output.ReservationDtoOutput;
import com.example.bookreservation.service.specification.ReservationSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    private final ReservationMapper reservationMapper;

    private final UserRepository userRepository;

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper, UserRepository userRepository, BookRepository bookRepository, BookMapper bookMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<ReservationDtoOutput> getAllReservations() {
        System.out.println("Get All Reservations Started...");

        List<ReservationEntity> reservationEntities = reservationRepository.
                findAll();
        if (reservationEntities.isEmpty()) {
            throw new NotFoundException("Reservations Not Found!");
        }
        List<ReservationDtoOutput> reservationDtoOutputs = reservationMapper.
                mapEntityToDtos(reservationEntities);
        return reservationDtoOutputs;
    }

    public ReservationDtoOutput findByCode(String code) {
        System.out.println("Find by Reservation Code Started...");

        ReservationEntity reservationEntity = reservationRepository.
                findByReservationCodeIgnoreCase(code);
        if (reservationEntity == null) {
            throw new NotFoundException("Reservation Not Found!");
        }
        ReservationDtoOutput reservationDtoOutput = reservationMapper.
                mapEntityToDto(reservationEntity);
        return reservationDtoOutput;
    }

    @Transactional
    public void saveReservation(ReservationDtoInput reservationDtoInput) {
        System.out.println("Save Reservation Started...");

        UserEntity userEntity = userRepository.
                findByUserFinCodeIgnoreCase(reservationDtoInput.getUserFinCode()).
                orElseThrow(() -> new NotFoundException("User Not Found!"));

        BookEntity bookEntity = bookRepository.
                findByBookCodeIgnoreCase(reservationDtoInput.getBookCode()).
                orElseThrow(() -> new NotFoundException("Book Not Found!"));

        if (reservationRepository.findByReservationCodeIgnoreCase(reservationDtoInput.getReservationCode()) != null) {
            throw new FoundException("Reservation is also Found!");
        }

        if (reservationRepository.findAll().stream()
                .anyMatch(reservation -> reservation.getBook().getBookCode().equals(reservationDtoInput.getBookCode()))) {
            throw new FoundException("This book is reserved!");
        }

        ReservationEntity reservationEntity = reservationMapper.mapDtoToEntity(reservationDtoInput);
        reservationEntity.setUser(userEntity);
        reservationEntity.setBook(bookEntity);

        reservationRepository.save(reservationEntity);
    }

    @Transactional
    public void deleteManual(String reservationCode) {
        System.out.println("Delete Manual Started...");

        ReservationEntity reservationEntity = reservationRepository.
                findByReservationCodeIgnoreCase(reservationCode);
        if (reservationEntity == null) {
            throw new NotFoundException("Reservation Not Found!");
        }
        reservationRepository.
                deleteById(reservationEntity.getReservationID());
    }

    @Transactional
    public void deleteAutomatic() {
        System.out.println("Delete Automatic Started...");
        List<ReservationEntity> expiredReservations = reservationRepository.
                findByExpiryDateBefore(ZonedDateTime.now());

        if (!expiredReservations.isEmpty()) {
            reservationRepository.deleteAll(expiredReservations);
        } else {
            System.out.println("Reservation Not Found");
        }
    }

    public List<ReservationDtoOutput> getGreaterThanByCreatedDate(ZonedDateTime createdDate) {
        System.out.println("Get Greater Than by Created Date Started...");
        Specification<ReservationEntity> specification = Specification.where(null);
        if (createdDate != null) {
            specification = specification.and(ReservationSpecification.hasGreaterThanByCreatedDate(createdDate));
        }
        List<ReservationEntity> reservationEntities = reservationRepository.
                findAll(specification);
        List<ReservationDtoOutput> reservationDtoOutputs = reservationMapper.
                mapEntityToDtos(reservationEntities);
        return reservationDtoOutputs;
    }

    public List<ReservationDtoOutput> getGreaterThanByExpiryDate(ZonedDateTime expiryDate){
        System.out.println("Get Greater Than by Expiry Date Started...");
        Specification<ReservationEntity> specification = Specification.where(null);
        if(expiryDate != null){
            specification = specification.and(ReservationSpecification.hasGreaterThanByExpiryDate(expiryDate));
        }
        List<ReservationEntity> reservationEntities = reservationRepository.
                findAll(specification);
        List<ReservationDtoOutput> reservationDtoOutputs = reservationMapper.
                mapEntityToDtos(reservationEntities);
        return reservationDtoOutputs;
    }
}
