package org.fourgeeks.gha.domain.oru;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BPU implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String credential;
	private String roleId; //String???
	private String fullName;
	
	//TODO: relacion
	private long citizenId;
	
	private String credentialType; //String?
	private String speciality;
	private boolean hasToSign;
	private String reportTo;
	private String scalateTo;
	private String shiftOnDuty;
	private String waRestricted;
	private String horaryRestricted; //String?
	private String email;
	private String notifEmail;
	private String officePhone;
	private String mobilePhone;
	private String homePhone;
	private String escalation;
	private boolean freedActivity;
	private boolean delegateActivity;
	private String rolIdReportTo; //String???
	private String rolIdEscalateTo; //String??
	private String status; //String
	private String ownActivityType; //String
	private Timestamp lastLogonDate;
	private String lastLogonTerm; //String?
	private long onDutyIn; //number(19,0)?
	private String statusCurrentDay;
	private Date currentDayStatus;
	private Date horaryAbsence; //Date?
	private String motiveAbsence;
	private long absenceTime; //long???
	
	//TODO: Relationship fk
	private long shiftAssigned;

	/**
	 * 
	 */
	public BPU() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public String getCredential() {
		return credential;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getFullName() {
		return fullName;
	}

	public long getCitizenId() {
		return citizenId;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public String getSpeciality() {
		return speciality;
	}

	public boolean isHasToSign() {
		return hasToSign;
	}

	public String getReportTo() {
		return reportTo;
	}

	public String getScalateTo() {
		return scalateTo;
	}

	public String getShiftOnDuty() {
		return shiftOnDuty;
	}

	public String getWaRestricted() {
		return waRestricted;
	}

	public String getHoraryRestricted() {
		return horaryRestricted;
	}

	public String getEmail() {
		return email;
	}

	public String getNotifEmail() {
		return notifEmail;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String getEscalation() {
		return escalation;
	}

	public boolean isFreedActivity() {
		return freedActivity;
	}

	public boolean isDelegateActivity() {
		return delegateActivity;
	}

	public String getRolIdReportTo() {
		return rolIdReportTo;
	}

	public String getRolIdEscalateTo() {
		return rolIdEscalateTo;
	}

	public String getStatus() {
		return status;
	}

	public String getOwnActivityType() {
		return ownActivityType;
	}

	public Timestamp getLastLogonDate() {
		return lastLogonDate;
	}

	public String getLastLogonTerm() {
		return lastLogonTerm;
	}

	public long getOnDutyIn() {
		return onDutyIn;
	}

	public String getStatusCurrentDay() {
		return statusCurrentDay;
	}

	public Date getCurrentDayStatus() {
		return currentDayStatus;
	}

	public Date getHoraryAbsence() {
		return horaryAbsence;
	}

	public String getMotiveAbsence() {
		return motiveAbsence;
	}

	public long getAbsenceTime() {
		return absenceTime;
	}

	public long getShiftAssigned() {
		return shiftAssigned;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setCitizenId(long citizenId) {
		this.citizenId = citizenId;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public void setHasToSign(boolean hasToSign) {
		this.hasToSign = hasToSign;
	}

	public void setReportTo(String reportTo) {
		this.reportTo = reportTo;
	}

	public void setScalateTo(String scalateTo) {
		this.scalateTo = scalateTo;
	}

	public void setShiftOnDuty(String shiftOnDuty) {
		this.shiftOnDuty = shiftOnDuty;
	}

	public void setWaRestricted(String waRestricted) {
		this.waRestricted = waRestricted;
	}

	public void setHoraryRestricted(String horaryRestricted) {
		this.horaryRestricted = horaryRestricted;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNotifEmail(String notifEmail) {
		this.notifEmail = notifEmail;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public void setEscalation(String escalation) {
		this.escalation = escalation;
	}

	public void setFreedActivity(boolean freedActivity) {
		this.freedActivity = freedActivity;
	}

	public void setDelegateActivity(boolean delegateActivity) {
		this.delegateActivity = delegateActivity;
	}

	public void setRolIdReportTo(String rolIdReportTo) {
		this.rolIdReportTo = rolIdReportTo;
	}

	public void setRolIdEscalateTo(String rolIdEscalateTo) {
		this.rolIdEscalateTo = rolIdEscalateTo;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOwnActivityType(String ownActivityType) {
		this.ownActivityType = ownActivityType;
	}

	public void setLastLogonDate(Timestamp lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}

	public void setLastLogonTerm(String lastLogonTerm) {
		this.lastLogonTerm = lastLogonTerm;
	}

	public void setOnDutyIn(long onDutyIn) {
		this.onDutyIn = onDutyIn;
	}

	public void setStatusCurrentDay(String statusCurrentDay) {
		this.statusCurrentDay = statusCurrentDay;
	}

	public void setCurrentDayStatus(Date currentDayStatus) {
		this.currentDayStatus = currentDayStatus;
	}

	public void setHoraryAbsence(Date horaryAbsence) {
		this.horaryAbsence = horaryAbsence;
	}

	public void setMotiveAbsence(String motiveAbsence) {
		this.motiveAbsence = motiveAbsence;
	}

	public void setAbsenceTime(long absenceTime) {
		this.absenceTime = absenceTime;
	}

	public void setShiftAssigned(long shiftAssigned) {
		this.shiftAssigned = shiftAssigned;
	}
	
	
	
}
