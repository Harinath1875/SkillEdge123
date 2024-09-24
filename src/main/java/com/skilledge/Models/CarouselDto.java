package com.skilledge.Models;

import org.springframework.web.multipart.MultipartFile;

public class CarouselDto {
		
	 private String coursename;
	 
	 private String coursedesc;
     
	 private String trainingBtn;

	 private String whyThisCourseBtn;

	 private MultipartFile carouselImageFileName;

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCoursedesc() {
		return coursedesc;
	}

	public void setCoursedesc(String coursedesc) {
		this.coursedesc = coursedesc;
	}

	public String getTrainingBtn() {
		return trainingBtn;
	}

	public void setTrainingBtn(String trainingBtn) {
		this.trainingBtn = trainingBtn;
	}

	public String getWhyThisCourseBtn() {
		return whyThisCourseBtn;
	}

	public void setWhyThisCourseBtn(String whyThisCourseBtn) {
		this.whyThisCourseBtn = whyThisCourseBtn;
	}

	public MultipartFile getCarouselImageFileName() {
		return carouselImageFileName;
	}

	public void setCarouselImageFileName(MultipartFile carouselImageFileName) {
		this.carouselImageFileName = carouselImageFileName;
	}

	
	 
}
