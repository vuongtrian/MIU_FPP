package cs489.miu.edu.hotel_reservation_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private Integer roomNumber;
    @ManyToOne
    @JoinColumn(name = "room_detail_id")
    private RoomDetail roomDetail;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reservations = new ArrayList<>();

    public Room(Integer roomNumber, RoomDetail roomDetail) {
        this.roomNumber = roomNumber;
        this.roomDetail = roomDetail;
    }
}
