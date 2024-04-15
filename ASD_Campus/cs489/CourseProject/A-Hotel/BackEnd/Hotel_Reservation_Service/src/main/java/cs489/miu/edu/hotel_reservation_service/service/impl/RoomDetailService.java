package cs489.miu.edu.hotel_reservation_service.service.impl;

import cs489.miu.edu.hotel_reservation_service.entity.FileData;
import cs489.miu.edu.hotel_reservation_service.entity.RoomDetail;
import cs489.miu.edu.hotel_reservation_service.entity.dto.FileDataResponseDTO;
import cs489.miu.edu.hotel_reservation_service.entity.dto.RoomDetailRequestDTO;
import cs489.miu.edu.hotel_reservation_service.entity.dto.RoomDetailResponseDTO;
import cs489.miu.edu.hotel_reservation_service.entity.mapper.RoomDetailValueMapper;
import cs489.miu.edu.hotel_reservation_service.exception.RoomDetailNotFoundException;
import cs489.miu.edu.hotel_reservation_service.exception.RoomDetailServiceException;
import cs489.miu.edu.hotel_reservation_service.repository.IRoomDetailRepository;
import cs489.miu.edu.hotel_reservation_service.service.IImageService;
import cs489.miu.edu.hotel_reservation_service.service.IRoomDetailService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomDetailService implements IRoomDetailService {

    private IRoomDetailRepository roomDetailRepository;
    private IImageService imageService;

    @Override
    public RoomDetailResponseDTO createRoomDetail(RoomDetailRequestDTO roomDetailRequestDTO) {
        try {
            RoomDetail roomDetail = RoomDetailValueMapper.convertToEntity(roomDetailRequestDTO);
            RoomDetail roomDetailSaved = roomDetailRepository.save(roomDetail);
            return RoomDetailValueMapper.convertToDTO(roomDetailSaved);
        } catch (Exception e) {
            throw new RoomDetailServiceException("Exception occurred while create a new room detail");
        }
    }

    @Override
    public RoomDetailResponseDTO updateRoomDetail(Integer roomId, RoomDetailRequestDTO roomDetailRequestDTO) {
        RoomDetailResponseDTO roomDetailResponseDTO;
        try {
            RoomDetail roomDetail = roomDetailRepository.findById(roomId)
                    .orElseThrow(() -> new RoomDetailNotFoundException("Room detail not found with id " + roomId));
            roomDetail.setType(roomDetailRequestDTO.getType());
            roomDetail.setPrice(roomDetailRequestDTO.getPrice());
            roomDetail.setBedType(roomDetailRequestDTO.getBedType());
            roomDetail.setNumberOfBeds(roomDetailRequestDTO.getNumberOfBeds());
            roomDetail.setDescription(roomDetailRequestDTO.getDescription());
            return RoomDetailValueMapper.convertToDTO(roomDetailRepository.save(roomDetail));
        } catch (Exception e) {
            throw new RoomDetailServiceException("Exception occurred while update room detail");
        }
    }

    @Override
    public void deleteRoomDetail(Integer roomId) {
        try {
            RoomDetail roomDetail = roomDetailRepository.findById(roomId)
                    .orElseThrow(() -> new RoomDetailNotFoundException("Room detail not found with id " + roomId));
            roomDetail.getImages().forEach(image -> {imageService.deleteImage(image.getId());});
            roomDetailRepository.delete(roomDetail);
        } catch (Exception e) {
            throw new RoomDetailServiceException("Exception occurred while delete room detail id " + roomId);
        }
    }

    @Override
    public RoomDetailResponseDTO getRoomDetailById(Integer roomId) {
        try {
            RoomDetail roomDetail = roomDetailRepository.findById(roomId)
                    .orElseThrow(() -> new RoomDetailNotFoundException("Room detail not found with id " + roomId));
            return RoomDetailValueMapper.convertToDTO(roomDetail);
        } catch (Exception e) {
            throw new RoomDetailServiceException("Exception occurred while get room detail id " + roomId);
        }
    }

    @Override
    public List<RoomDetailResponseDTO> getAllRoomDetails() {
        try {
            return roomDetailRepository.findAll().stream().map(RoomDetailValueMapper::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RoomDetailServiceException("Exception occurred while get all room detail");
        }
    }

    @Override
    public RoomDetailResponseDTO addImages(Integer roomId, List<MultipartFile> imageFiles) {
        RoomDetailResponseDTO roomDetailResponseDTO;

        try {
            RoomDetail roomDetail = roomDetailRepository.findById(roomId)
                    .orElseThrow(() -> new RoomDetailNotFoundException("Room detail not found with id " + roomId));
            List<FileData> images = new ArrayList<>();
            List<FileDataResponseDTO> fileDataResponseDTOList = new ArrayList<>();
            for (MultipartFile imageFile : imageFiles) {
                FileDataResponseDTO imageFileResponseDTO = imageService.saveImage(imageFile);
                fileDataResponseDTOList.add(imageFileResponseDTO);
                FileData image = new FileData();
                image.setId(imageFileResponseDTO.getId());
                image.setType(imageFileResponseDTO.getType());
                image.setName(imageFileResponseDTO.getName());
                images.add(image);
            }
            roomDetail.setImages(images);
            roomDetailResponseDTO = RoomDetailValueMapper.convertToDTO(roomDetailRepository.save(roomDetail));
            roomDetailResponseDTO.setImages(fileDataResponseDTOList);

            return roomDetailResponseDTO;
        } catch (Exception e) {
            throw new RoomDetailServiceException("Exception occurred while update image for room detail id " + roomId);
        }
    }

    @Override
    public RoomDetailResponseDTO updateImages(Integer roomId, Integer imageId, MultipartFile image) {
        return null;
    }

//    private List<FileDataResponseDTO> saveImages(List<MultipartFile> images) {
//        if (images == null || images.isEmpty()) {
//            return Collections.emptyList();
//        }
//        return images.stream()
//                .map(imageService::saveImage)
//                .collect(Collectors.toList());
//    }
}