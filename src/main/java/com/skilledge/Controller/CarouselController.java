package com.skilledge.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.skilledge.Models.Carousel;
import com.skilledge.Models.CarouselDto;
import com.skilledge.Services.CarouselRepository;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/carousel")
public class CarouselController {

	@Autowired
	private CarouselRepository repo;

	private final Path rootLocation = Paths.get("public/images");

	@GetMapping({ "", "/" })
	public String index() {
		return "index";
	}

	@GetMapping("/display")
	public String carouselList(Model model) {
		List<Carousel> carousel = repo.findAll();
		model.addAttribute("carousellist", carousel);
		return "Admin/carouselDisplayTable";
	}


	@GetMapping("/create")
	public String showCreatePage(Model model) {
		model.addAttribute("carousellistDto", new CarouselDto());
		return "carousel/carouselInsert";
	}

	@PostMapping("/create")
	public String createCarousel(@Valid @ModelAttribute CarouselDto carouselDto, BindingResult result) {
		if (result.hasErrors()) {
			return "carousel/carouselInsert";
		}

		try {
			Carousel carousel = new Carousel();
			carousel.setCoursename(carouselDto.getCoursename());
			carousel.setCoursedesc(carouselDto.getCoursedesc());
			carousel.setTrainingBtn(carouselDto.getTrainingBtn());
			carousel.setWhyThisCourseBtn(carouselDto.getWhyThisCourseBtn());

			String filename = storeFile(carouselDto.getCarouselImageFileName());
			carousel.setCarouselImageFileName(filename);

			repo.save(carousel);
		} catch (IOException e) {
			// Handle the exception (e.g., add error message to model)
			return "carousel/carouselInsert";
		}

		return "redirect:/carousel/carouselDisplayTable";
	}

	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int id) {
		return repo.findById(id).map(carousel -> {
			CarouselDto carouselDto = new CarouselDto();
			carouselDto.setCoursename(carousel.getCoursename());
			carouselDto.setCoursedesc(carousel.getCoursedesc());
			carouselDto.setTrainingBtn(carousel.getTrainingBtn());
			carouselDto.setWhyThisCourseBtn(carousel.getWhyThisCourseBtn());
			model.addAttribute("carousellist", carousel);
			model.addAttribute("carousellistDto", carouselDto);
			return "carousel/carouselEdit";
		}).orElse("redirect:/Admin/carouselDisplayTable");
	}

	@PostMapping("/edit")
	public String updateCarousel(@RequestParam int id, @Valid @ModelAttribute CarouselDto carouselDto,
			BindingResult result) {
		if (result.hasErrors()) {
			return "carousel/carouselEdit";
		}

		try {
			Carousel carousel = repo.findById(id).orElseThrow(() -> new RuntimeException("Carousel not found"));

			carousel.setCoursename(carouselDto.getCoursename());
			carousel.setCoursedesc(carouselDto.getCoursedesc());
			carousel.setTrainingBtn(carouselDto.getTrainingBtn());
			carousel.setWhyThisCourseBtn(carouselDto.getWhyThisCourseBtn());

			if (!carouselDto.getCarouselImageFileName().isEmpty()) {
				deleteFile(carousel.getCarouselImageFileName());
				String filename = storeFile(carouselDto.getCarouselImageFileName());
				carousel.setCarouselImageFileName(filename);
			}

			repo.save(carousel);
		} catch (IOException e) {
			// Handle the exception (e.g., add error message to model)
			return "carousel/carouselEdit";
		}

		return "redirect:/Admin/carouselDisplayTable";
	}

	@GetMapping("/delete")
	public String deleteCarousel(@RequestParam int id) {
		try {
			Carousel carousel = repo.findById(id).orElseThrow(() -> new RuntimeException("Carousel not found"));

			deleteFile(carousel.getCarouselImageFileName());
			repo.delete(carousel);
		} catch (IOException e) {
		}

		return "redirect:/carousel";
	}

	private String storeFile(MultipartFile file) throws IOException {
		if (!Files.exists(rootLocation)) {
			Files.createDirectories(rootLocation);
		}

		String filename = file.getOriginalFilename();
		Files.copy(file.getInputStream(), rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		return filename;
	}

	private void deleteFile(String filename) throws IOException {
		Path file = rootLocation.resolve(filename);
		Files.deleteIfExists(file);
	}
}