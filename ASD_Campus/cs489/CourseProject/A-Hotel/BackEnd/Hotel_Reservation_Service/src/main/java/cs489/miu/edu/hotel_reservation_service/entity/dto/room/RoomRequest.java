package cs489.miu.edu.hotel_reservation_service.entity.dto.room;

import cs489.miu.edu.hotel_reservation_service.entity.dto.reservation.ReservationRequest;
import cs489.miu.edu.hotel_reservation_service.entity.dto.roomDetail.RoomDetailRequest;

import java.util.List;

public record RoomRequest(
        Integer roomNumber,
        RoomDetailRequest roomDetailRequest
) {
}
