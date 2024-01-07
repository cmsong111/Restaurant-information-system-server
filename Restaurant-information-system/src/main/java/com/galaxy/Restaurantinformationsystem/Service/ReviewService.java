package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.ReviewDTO;
import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import com.galaxy.Restaurantinformationsystem.Repository.ReviewRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ReviewService {

    ReviewRepository reviewRepository;
    MenuRepository menuRepository;
    UserRepository userRepository;
    StoreRepository storeRepository;


    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        return toDTO(reviewRepository.save(toEntity(reviewDTO)));
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        if (reviewDTO.getRPK() == null || reviewDTO.getUPK() == null || reviewDTO.getSPK() == null) {
            return null;
        } else {
            return toDTO(reviewRepository.save(toEntity(reviewDTO)));
        }
    }

    public ReviewDTO readReview(ReviewDTO reviewDTO) {
        return toDTO(reviewRepository.findById(reviewDTO.getRPK()).get());
    }

    @Transactional
    public void deleteReview(ReviewDTO reviewDTO) {
        ReviewEntity reviewEntity = reviewRepository.findById(reviewDTO.getRPK()).get();
        reviewEntity.setStoreEntity(null);
        reviewEntity.setUserEntity(userRepository.findById(1L).get());
        reviewEntity = reviewRepository.save(reviewEntity);

        reviewRepository.delete(reviewEntity);
    }

    // 가게별 리뷰 읽어 오기
    public ArrayList<ReviewDTO> findByStore(StoreDTO storeDTO) {
        ArrayList<ReviewDTO> resultArray = new ArrayList<>();
        ArrayList<ReviewEntity> queryList = reviewRepository.findByStore_SPK(storeDTO.getSPK());
        if (queryList != null) {
            for (ReviewEntity item : queryList) {
                resultArray.add(toDTO(item));
            }
        }
        return resultArray;
    }

    public ReviewEntity toEntity(ReviewDTO reviewDTO) {
        return ReviewEntity.builder()
                .RPK(reviewDTO.getRPK())
                .title(reviewDTO.getTitle())
                .content(reviewDTO.getContent())
                .image(reviewDTO.getImage())
                .storeEntity(storeRepository.findById(reviewDTO.getSPK()).get())
                .userEntity(userRepository.findById(reviewDTO.getUPK()).get())
                .build();
    }

    public ReviewDTO toDTO(ReviewEntity reviewEntity) {
        return ReviewDTO.builder()
                .RPK(reviewEntity.getRPK())
                .title(reviewEntity.getTitle())
                .content(reviewEntity.getContent())
                .image(reviewEntity.getImage())
                .UPK(reviewEntity.getUserEntity().getId())
                .SPK(reviewEntity.getStoreEntity().getSPK())
                .build();
    }
}
