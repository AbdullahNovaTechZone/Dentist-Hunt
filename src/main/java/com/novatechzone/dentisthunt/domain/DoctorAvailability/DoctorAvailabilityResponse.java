package com.novatechzone.dentisthunt.domain.DoctorAvailability;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DoctorAvailabilityResponse {
    private DoctorAvailability doctorAvailabilities;
    private List<LocalTime> slotList;
}
