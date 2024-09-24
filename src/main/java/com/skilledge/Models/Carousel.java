package com.skilledge.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name="carousel")
	public class Carousel{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private String coursename;
		private String coursedesc;
		private String trainingBtn;
		private String whyThisCourseBtn;
		private String carouselImageFileName;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
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
		public String getCarouselImageFileName() {
			return carouselImageFileName;
		}
		public void setCarouselImageFileName(String carouselImageFileName) {
			this.carouselImageFileName = carouselImageFileName;
		}
		public Carousel(int id, String coursename, String coursedesc, String trainingBtn, String whyThisCourseBtn, String carouselImageFileName) {
			super();
			this.id = id;
			this.coursename = coursename;
			this.coursedesc = coursedesc;
			this.trainingBtn = trainingBtn;
			this.whyThisCourseBtn = whyThisCourseBtn;
			this.carouselImageFileName = carouselImageFileName;
		}
		public Carousel() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Carousel [id=" + id + ", coursename=" + coursename + ", coursedesc=" + coursedesc + ", trainingBtn=" + trainingBtn + ", whyThisCourseBtn=" + whyThisCourseBtn
					+ ", carouselImageFileName=" + carouselImageFileName + "]";
		}
		
	}