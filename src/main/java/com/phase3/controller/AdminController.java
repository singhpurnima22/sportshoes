package com.phase3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.phase3.dto.ProductDto;
import com.phase3.model.Category;
import com.phase3.service.CategotyService;
import com.phase3.service.ProductService;



@Controller
public class AdminController {
	@Autowired
	private CategotyService categoryservice;
	@Autowired
	private ProductService productservice;
	
	@GetMapping("/admin")
	 public String adminHome()
	 {
		 return "adminHome";
	 }
	
	@GetMapping("/admin/categories")
	 public String getCat(Model model) {
	 model.addAttribute("categories" ,categoryservice.getAllCategory());
	 
		 return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model)
	{
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	@PostMapping("/admin/categories/add")
	public String postCategoryAdd(@ModelAttribute("category")  Category category) {
		categoryservice.addCategory(category);
		return "redirect:/admin/categories";
		
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryservice.removeCategoryById(id);
		return "redirect:/admin/categories";
		
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id,Model model) {
		Optional<Category> category = categoryservice.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}
		else {
			return "404";
		}
	}
	
	//products
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("Product", productservice.getAllProduct());

		return "products";
	}
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO",new ProductDto());
		model.addAttribute("categories", categoryservice.getAllCategory());
		return "productsAdd";
	}
}	
