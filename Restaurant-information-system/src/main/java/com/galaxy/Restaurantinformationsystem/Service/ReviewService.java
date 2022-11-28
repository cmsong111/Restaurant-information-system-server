package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.ReviewDTO;
import com.galaxy.Restaurantinformationsystem.DTO.StoreDTO;
import com.galaxy.Restaurantinformationsystem.Entity.ReviewEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import com.galaxy.Restaurantinformationsystem.Repository.ReviewRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import com.galaxy.Restaurantinformationsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ReviewService {

    ReviewRepository reviewRepository;
    MenuRepository menuRepository;
    UserRepository userRepository;
    StoreRepository storeRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MenuRepository menuRepository, StoreRepository storeRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.menuRepository = menuRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

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
        reviewRepository.delete(toEntity(reviewDTO));
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
                .userEntity(userRepository.findById(reviewDTO.getRPK()).get())
                .build();
    }

    public ReviewDTO toDTO(ReviewEntity reviewEntity) {
        return ReviewDTO.builder()
                .RPK(reviewEntity.getRPK())
                .title(reviewEntity.getTitle())
                .content(reviewEntity.getContent())
                .image(reviewEntity.getImage())
                .UPK(reviewEntity.getUserEntity().getUPK())
                .SPK(reviewEntity.getStoreEntity().getSPK())
                .build();
    }
}
