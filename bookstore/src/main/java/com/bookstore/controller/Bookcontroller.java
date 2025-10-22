package com.bookstore.controller;


import com.bookstore.entity.MyBookList;
import com.bookstore.service.BookService;
import com.bookstore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.bookstore.entity.Book;
import org.springframework.web.servlet.ModelAndView;;import java.util.List;
import org.springframework.ui.Model;


@Controller

public class Bookcontroller {

    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;


    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/book_register")
    public String bookregister(){
        return "bookRegister";
    }
    @GetMapping("/available_book")
    public ModelAndView getAllBock(){
        List<Book> list = service.getAllBook();
//        ModelAndView m = new ModelAndView();
//        m.setViewName("bookList");
//        m.addObject("book",list);
        return new ModelAndView("bookList","book" ,list) ;
    }
    @PostMapping("/save")
        public String addBook(@ModelAttribute Book b){
            service.save(b);
            return "redirect:/available_book";
        }

    @GetMapping("/my_Books")
    public String getMyBooks(Model model){
        List<MyBookList>list=myBookService.getAllMyBooks();
         model.addAttribute("book",list);
        return "myBooks";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
        System.out.println("######################################Before");
        Book b= service.getBookById(id);
        System.out.println("######################################After");
        MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookService.SaveMyBook(mb);
        return "redirect:/my_Books";

        }
        @RequestMapping("/editBook/{id}")
        public String editBook(@PathVariable("id") int id , Model model){
        Book b = service.getBookById(id);
        model.addAttribute("book",b);
            return "bookEdit";
       }

       @RequestMapping("/deleteBook/{id}")
        public String deleteBook(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/available_book";
       }




}
