/*
 * package com.productmanagement.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.*; import
 * org.springframework.web.servlet.ModelAndView;
 * 
 * import com.productmanagement.entity.Product; import
 * com.productmanagement.service.ProductService;
 * 
 * @Controller public class Product1Controller {
 * 
 * @Autowired private ProductService productService;
 * 
 * 
 * @GetMapping("/") public String home() { return "index"; }
 * 
 * @GetMapping("/book_register") public String bookRegister() { return
 * "bookRegister"; }
 * 
 * @GetMapping("/available_books") public ModelAndView getAllBook() {
 * List<Product>list=productService.getAllProduct(); return new
 * ModelAndView("productList","product",list); }
 * 
 * @PostMapping("/save") public String addBook(@ModelAttribute Book b) {
 * bookService.save(b); return "redirect:/available_books"; }
 * 
 * @GetMapping("/my_books") public String getMyBooks() { return "myBooks"; }
 * 
 * @RequestMapping("/mylist/{id}") public String
 * getMyList(@PathVariable("id")Long id) { return ""; }
 * 
 * }
 */