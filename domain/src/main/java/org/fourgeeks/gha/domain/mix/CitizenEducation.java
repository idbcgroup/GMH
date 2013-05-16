package org.fourgeeks.gha.domain.mix;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CitizenEducation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String educationalInstitution; //String?
	private String educationalInstitutionName;
	private String educationalInstitutionType; //String?
	private String educationType; //String?
	private int endYear;
	private int startYear;
	private String study;
	private String studyName;
	/**
	 * 
	 */
	public CitizenEducation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public String getEducationalInstitution() {
		return educationalInstitution;
	}
	public String getEducationalInstitutionName() {
		return educationalInstitutionName;
	}
	public String getEducationalInstitutionType() {
		return educationalInstitutionType;
	}
	public String getEducationType() {
		return educationType;
	}
	public int getEndYear() {
		return endYear;
	}
	public int getStartYear() {
		return startYear;
	}
	public String getStudy() {
		return study;
	}
	public String getStudyName() {
		return studyName;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setEducationalInstitution(String educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}
	public void setEducationalInstitutionName(String educationalInstitutionName) {
		this.educationalInstitutionName = educationalInstitutionName;
	}
	public void setEducationalInstitutionType(String educationalInstitutionType) {
		this.educationalInstitutionType = educationalInstitutionType;
	}
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public void setStudy(String study) {
		this.study = study;
	}
	public void setStudyName(String studyName) {
		this.studyName = studyName;
	}
	
	
}
