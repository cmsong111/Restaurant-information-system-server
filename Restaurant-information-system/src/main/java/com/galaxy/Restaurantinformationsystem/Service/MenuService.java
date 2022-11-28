package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import com.galaxy.Restaurantinformationsystem.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class MenuService {

    MenuRepository menuRepository;
    StoreRepository storeRepository;

    @Autowired
    public MenuService(MenuRepository MenuRepository, StoreRepository storeRepository) {
        this.menuRepository = MenuRepository;
        this.storeRepository = storeRepository;
    }

    public MenuDTO createMenuDTO(MenuDTO MenuDTO) {
        MenuEntity MenuEntity = toEntity(MenuDTO);
        return toDTO(menuRepository.save(MenuEntity));
    }

    public MenuDTO readMenuDTO(MenuDTO MenuDTO) {
        return null;
    }

    public ArrayList<MenuDTO> readMenuInStore(Long spk) {
        ArrayList<MenuDTO> result = new ArrayList<>();
        ArrayList<MenuEntity> found = menuRepository.findByStore_SPK(spk);
        if (found != null) {
            for (MenuEntity menuEntity : found) {
                result.add(toDTO(menuEntity));
            }
        }
        return result;
    }

    public MenuDTO updateMenuDTO(MenuDTO menuDTO) {
        MenuEntity menuEntity = menuRepository.getReferenceById(menuDTO.getMPK());
        menuEntity.setName(menuDTO.getName());
        menuEntity.setStore(storeRepository.findById(menuDTO.getSPK()).get());
        menuEntity.setImage(menuEntity.getImage());
        menuEntity.setPrice(menuEntity.getPrice());

        return toDTO(menuRepository.save(menuEntity));
    }

    @Transactional
    public void deleteMenu(MenuDTO MenuDTO) {
        MenuEntity MenuEntity = toEntity(MenuDTO);
        menuRepository.delete(MenuEntity);
    }

    public MenuEntity toEntity(MenuDTO menuDTO) {
        return MenuEntity.builder()
                .MPK(menuDTO.getMPK())
                .name(menuDTO.getName())
                .price(menuDTO.getPrice())
                .image(menuDTO.getImage())
                .store(storeRepository.findById(menuDTO.getSPK()).get())
                .build();
    }

    public MenuDTO toDTO(MenuEntity menuEntity) {
        return MenuDTO.builder()
                .MPK(menuEntity.getMPK())
                .name(menuEntity.getName())
                .price(menuEntity.getPrice())
                .image(menuEntity.getImage())
                .SPK(menuEntity.getStore().getSPK())
                .build();
    }
}
