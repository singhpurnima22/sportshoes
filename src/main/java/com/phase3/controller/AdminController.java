package com.phase3.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.phase3.dto.ProductDto;
import com.phase3.model.Category;
import com.phase3.model.Product;
import com.phase3.service.CategotyService;
import com.phase3.service.ProductService;

@Controller
public class AdminController {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	@Autowired
	private CategotyService categoryservice;
	@Autowired
	private ProductService productservice;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryservice.getAllCategory());

		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postCategoryAdd(@ModelAttribute("category") Category category) {
		categoryservice.addCategory(category);
		return "redirect:/admin/categories";

	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryservice.removeCategoryById(id);
		return "redirect:/admin/categories";

	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		Optional<Category> category = categoryservice.getCategoryById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else {
			return "404";
		}
	}

	// products

	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productservice.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDto());
		model.addAttribute("categories", categoryservice.getAllCategory());
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO") ProductDto productDTO,
			@RequestParam("productImage") MultipartFile file, @RequestParam(name="imgname", required=false) String imgname)
			throws IOException {

		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryservice.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());

		String imageUUID;
		if (!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = productDTO.getImagename();
		}  
		product.setImagename(imageUUID);
		productservice.addProduct(product);
		return "redirect:/admin/products";

	}

	@GetMapping("/admin/products/delete/{id}")
	public String deleteProductById(@PathVariable long id) {

		productservice.removeProductById(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/products/update/{id}")
	public String updateProductGet(@PathVariable long id, Model model) {
		Product product = productservice.getProductById(id).get();
		ProductDto productDTO = new ProductDto();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImagename(product.getImagename());

		model.addAttribute("categories", categoryservice.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		return "productsAdd";
	}
}
