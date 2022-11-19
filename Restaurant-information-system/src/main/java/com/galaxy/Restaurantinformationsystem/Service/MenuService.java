package com.galaxy.Restaurantinformationsystem.Service;

import com.galaxy.Restaurantinformationsystem.DTO.MenuDTO;
import com.galaxy.Restaurantinformationsystem.Entity.MenuEntity;
import com.galaxy.Restaurantinformationsystem.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository MenuRepository) {
        this.menuRepository = MenuRepository;
    }

    public MenuDTO createMenuDTO(MenuDTO MenuDTO) {
        MenuEntity MenuEntity = toEntity(MenuDTO);
        return toDTO(menuRepository.save(MenuEntity));
    }

    public MenuDTO readMenuDTO(MenuDTO MenuDTO) {
        return null;
    }

    public MenuDTO updateMenuDTO(MenuDTO menuDTO) {
        MenuEntity menuEntity = menuRepository.getReferenceById(menuDTO.getMPK());
        menuEntity.setName(menuDTO.getName());
        menuEntity.setStore(menuRepository.findById(menuDTO.getSPK()).get().getStore());
        menuEntity.setImage(menuEntity.getImage());
        menuEntity.setPrice(menuEntity.getPrice());

        return toDTO(menuRepository.save(menuEntity));
    }


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
                .store(menuRepository.findById(menuDTO.getSPK()).get().getStore())
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
