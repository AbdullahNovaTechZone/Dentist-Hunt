package com.novatechzone.dentisthunt.domain.Favourite;

import com.novatechzone.dentisthunt.domain.Doctor.DoctorRepository;
import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import com.novatechzone.dentisthunt.dto.RequestMetaDTO;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private final DoctorRepository doctorRepository;
    private final RequestMetaDTO requestMetaDTO;

    public ApplicationResponseDTO addOrRemoveFromFavourite(Long doctorId) {
        if (doctorRepository.findById(doctorId).isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "INVALID_DOCTOR_ID", "Invalid Doctor Id");
        } else {
            Optional<Favourite> optionalFavourite = favouriteRepository.findByUserIdAndDoctorId(requestMetaDTO.getId(), doctorId);
            if (optionalFavourite.isPresent()) {
                favouriteRepository.delete(optionalFavourite.get());
                return new ApplicationResponseDTO(HttpStatus.OK, "DOCTOR_REMOVED_FROM_FAVOURITE", "Doctor Removed From Favourite");
            } else {
                favouriteRepository.save(new Favourite.FavouriteBuilder().userId(requestMetaDTO.getId()).doctorId(doctorId).build());
                return new ApplicationResponseDTO(HttpStatus.CREATED, "DOCTOR_ADDED_TO_FAVOURITE", "Doctor Added To Favourite");
            }
        }
    }

    public List<Favourite> getFavouriteDoctors() {
        List<Favourite> favourites = favouriteRepository.findAllByUserId(requestMetaDTO.getId());
        if (favourites.isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "NO_FAVOURITE_DOCTORS", "No Favourite Doctors");
        }
        return favourites;
    }
}
