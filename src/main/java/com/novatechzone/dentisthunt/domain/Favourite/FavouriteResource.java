package com.novatechzone.dentisthunt.domain.Favourite;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favourite")
public class FavouriteResource {
    private final FavouriteService favouriteService;

    @PostMapping("/add-or-remove-from-favourite/{doctor-id}")
    public ResponseEntity<ApplicationResponseDTO> addOrRemoveFromFavourite(@PathVariable("doctor-id") Long doctorId) {
        return ResponseEntity.ok(favouriteService.addOrRemoveFromFavourite(doctorId));
    }

    @GetMapping("/favourite-doctors")
    public ResponseEntity<List<Favourite>> getFavouriteDoctors() {
        return ResponseEntity.ok(favouriteService.getFavouriteDoctors());
    }
}