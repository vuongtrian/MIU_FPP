package cs489.miu.edu.hotel_reservation_service.entity.dto.file;

public record FileRequest(
        String type,
        String name,
        String path
) {
}