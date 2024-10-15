package com.productmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.productmanagement.entity.Product;
import com.productmanagement.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;

@Controller

public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/load_form")
	public String loadForm() {
		return "add";
	}

	@GetMapping("/edit_form")
	public String editForm() {
		return "edit";
	}

	@PostMapping("/save_products")
	//@ResponseBody
	public ResponseEntity<?> addProduct(@ModelAttribute Product product) {
		Product savedProduct = productRepository.save(product);
		return ResponseEntity.ok().body(savedProduct);
	}

	/*
	 * @PostMapping("/save_products") public String createNewHospital ( @RequestBody
	 * Product product) { productRepository.save(product); return
	 * "Database is created"; }
	 * 
	 */
	@GetMapping("/add_product")
	@ResponseBody
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> pList = new ArrayList<>();
		productRepository.findAll().forEach(pList::add);
		return new ResponseEntity<List<Product>>(pList, HttpStatus.OK);
	}

	@GetMapping("/get_product/{id}")
	@ResponseBody
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		System.out.println("hello");
		Optional<Product> pro = productRepository.findById(id);
		if (pro.isPresent()) {
			return new ResponseEntity<Product>(pro.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/editData")

	@ResponseBody
	public String updateProductById(@RequestBody Product product) {
		Optional<Product> sch = productRepository.findById(product.getId());
		if (sch.isPresent()) {
			Product existsch = sch.get();
			existsch.setName(product.getName());
			existsch.setDescription(product.getDescription());
			existsch.setCategory(product.getCategory());
			existsch.setPrice(product.getPrice());
			existsch.setQuantity(product.getQuantity());
			productRepository.save(existsch);

			return "Product Details Against id " + product.getId() + " updated";
		} else {
			return "Product Details does not exixt " + product.getId();
		}
	}

	@DeleteMapping("/deleteData/{id}")
	@ResponseBody
	public String deleteProductById(@PathVariable Long id) {
		productRepository.deleteById(id);
		return "Product Details Deleted Successfully";
	}

}
