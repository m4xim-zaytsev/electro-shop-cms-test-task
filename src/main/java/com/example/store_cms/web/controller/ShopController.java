package com.example.store_cms.web.controller;

import com.example.store_cms.mapper.ShopMapper;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.service.ShopService;
import com.example.store_cms.web.request.ShopRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final ShopMapper shopMapper;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("shopRequest", new ShopRequest());
        return "create-shop";
    }

    @PostMapping("/create")
    public String createShop(@ModelAttribute("shopRequest") @Valid ShopRequest shopRequest, BindingResult result) {
        if (shopService.existByNameAndAddress(shopRequest.getName(),shopRequest.getAddress())) {
            result.rejectValue("name", "error.shopRequest", "Shop with this name adn address already exists.");
        }
        if (result.hasErrors()) {
            return "create-shop";
        }
        shopService.create(shopMapper.requestToShop(shopRequest));
        return "redirect:/api/v1/main";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Shop shop = shopService.getById(id);
        model.addAttribute("shop", shopMapper.shopToRequest(shop));
        return "edit_shop";
    }

    @PostMapping("/edit/{id}")
    public String editShop(@PathVariable("id") Long id, @ModelAttribute("shop") @Valid ShopRequest shopRequest, BindingResult result) {
        if (shopService.existByNameAndAddress(shopRequest.getName(),shopRequest.getAddress())) {
            result.rejectValue("name", "error.shopRequest", "Shop with this name already exists.");
            result.rejectValue("address", "error.shopRequest", "Shop with this address already exists.");

        }
        if (result.hasErrors()) {
            return "edit_shop";
        }
        shopService.update(id, shopMapper.requestToShop(shopRequest));
        return "redirect:/api/v1/main";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable Long id) {
        shopService.delete(id);
        return ResponseEntity.ok().build();
    }
}