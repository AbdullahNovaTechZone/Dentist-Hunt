package com.novatechzone.dentisthunt.domain.booking;

import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingResource {
    private final BookingService bookingService;

    @PostMapping("/add-booking/{slot-id}")
    public ResponseEntity<ApplicationResponseDTO> addBooking(@PathVariable("slot-id") Long slotId, @Valid @RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.addBooking(slotId, bookingDTO));
    }
}
