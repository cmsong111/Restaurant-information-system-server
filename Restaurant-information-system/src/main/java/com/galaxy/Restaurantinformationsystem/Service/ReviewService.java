package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.ReviewDTO;
import com.galaxy.Restaurantinformationsystem.DTO.ReviewFormDTO;
import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.Entity.StoreEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import com.galaxy.Restaurantinformationsystem.Repository.ReviewRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;
    MenuRepository menuRepository;
    UserRepository userRepository;
    StoreRepository storeRepository;
    ModelMapper modelMapper;


    public ReviewDTO createReview(Long storeId, ReviewFormDTO reviewFormDTO, String token) {
        ReviewEntity reviewEntity = modelMapper.map(reviewFormDTO, ReviewEntity.class);
        reviewEntity.setStoreEntity(StoreEntity.builder().id(storeId).build());
        // TODO: token으로 부터 id를 받아와서 userEntity에 넣어주기
        //reviewEntity.setUserEntity(UserEntity.builder().id().build());
        return modelMapper.map(reviewRepository.save(reviewEntity), ReviewDTO.class);
    }

    public ReviewDTO updateReview(Long reviewid, ReviewFormDTO reviewFormDTO, String token) {
        Optional<ReviewEntity> reviewEntity = reviewRepository.findById(reviewid);

        if (reviewEntity.isEmpty()) {
            throw new RuntimeException("리뷰가 존재하지 않습니다.");
        }
//         작성자 본인이 아니면 수정 불가
//        if (reviewEntity.get().getUserEntity().getId() != 1L){
//            throw new RuntimeException("권한이 없습니다.");
//        }

        reviewEntity.get().setTitle(reviewFormDTO.getTitle());
        reviewEntity.get().setContent(reviewFormDTO.getContent());
        reviewEntity.get().setImage(reviewFormDTO.getImage());
        reviewEntity.get().setScore(reviewFormDTO.getScore());
        return modelMapper.map(reviewRepository.save(reviewEntity.get()), ReviewDTO.class);

    }

    public List<ReviewDTO> getMyReview(String token) {
        // token에서 id 받아오기
        Long userId = 1L;
        List<ReviewEntity> reviewEntityList = reviewRepository.findByUserEntity_Id(userId);

        List<ReviewDTO> reviewDTOList = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntityList) {
            reviewDTOList.add(modelMapper.map(reviewEntity, ReviewDTO.class));
        }

        return reviewDTOList;
    }

    @Transactional
    public void deleteReview(Long reviewId, String token) {
        Optional<ReviewEntity> reviewEntity = reviewRepository.findById(reviewId);

        if (reviewEntity.isEmpty()) {
            throw new RuntimeException("리뷰가 존재하지 않습니다.");
        }

        // 작성자 본인이 아니면 삭제 불가
//        if (reviewEntity.get().getUserEntity().getId() != 1L){
//            throw new RuntimeException("권한이 없습니다.");
//        }

        reviewRepository.delete(reviewEntity.get());
    }

}
