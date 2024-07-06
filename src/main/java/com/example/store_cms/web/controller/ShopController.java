package com.example.store_cms.web.controller;

import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/references/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Shop shop = shopService.getById(id);
        model.addAttribute("shop", shop);
        return "edit_shop";
    }

    @PostMapping("/edit/{id}")
    public String editShop(@PathVariable("id") Long id, @ModelAttribute("shop") Shop shop) {
        shopService.update(id, shop);
        return "redirect:/api/v1/main";
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShop(@PathVariable Long id) {
        shopService.delete(id);
        return ResponseEntity.ok().build();
    }
}