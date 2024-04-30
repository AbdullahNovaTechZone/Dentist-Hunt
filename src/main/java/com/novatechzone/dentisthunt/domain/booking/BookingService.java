package com.novatechzone.dentisthunt.domain.booking;

import com.novatechzone.dentisthunt.domain.DoctorAvailability.TimeSlotRepository;
import com.novatechzone.dentisthunt.dto.ApplicationResponseDTO;
import com.novatechzone.dentisthunt.dto.RequestMetaDTO;
import com.novatechzone.dentisthunt.exception.ApplicationCustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final RequestMetaDTO requestMetaDTO;

    public ApplicationResponseDTO addBooking(Long slotId, BookingDTO bookingDTO) {
        if (timeSlotRepository.findById(slotId).isEmpty()) {
            throw new ApplicationCustomException(HttpStatus.NOT_FOUND, "INVALID_TIME_SLOT", "Invalid Time Slot");
        } else if(bookingRepository.findBySlotId(slotId).isPresent()) {
            throw new ApplicationCustomException(HttpStatus.BAD_REQUEST, "THIS_SLOT_ALREADY_BOOKED", "This Slot Already Booked");
        }else{
            bookingRepository.save(Booking.builder().slotId(slotId).userId(requestMetaDTO.getId()).patientName(bookingDTO.getPatientName()).patientContactNumber(bookingDTO.getPatientContactNumber()).build());
            return new ApplicationResponseDTO(HttpStatus.CREATED, "YOUR_BOOKING_ADDED", "Your Booking Added");
        }
    }
}
