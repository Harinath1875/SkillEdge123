package com.skilledge.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilledge.Models.Carousel;
import com.skilledge.Services.CarouselRepository;


@Controller
@RequestMapping({"/Users"})
public class UsersController {

	@Autowired
	private CarouselRepository userrepo;
	@GetMapping("/userindex")
	private String index(Model model) {
		List<Carousel> userindex=userrepo.findAll();
		model.addAttribute("userCarousel",userindex);
		return "users/userindex";
	}
}
