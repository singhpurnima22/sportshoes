package com.phase3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.phase3.global.GlobalData;
import com.phase3.service.CategotyService;
import com.phase3.service.ProductService;

@Controller
public class HomeConroller {
	@Autowired
	CategotyService categoryservice;
	@Autowired
	ProductService productservice;

	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryservice.getAllCategory());
		model.addAttribute("products", productservice.getAllProduct());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}

	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryservice.getAllCategory());
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("products", productservice.getAllProductByCategory(id));
		return "shop";
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id) {
		model.addAttribute("product", productservice.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";
	}
}
