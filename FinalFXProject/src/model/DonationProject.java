package model;

import java.util.Objects;

public class DonationProject {
	
	private int donationProjectID;
	private String donationProjectName;
	private String projectDetails;
	
	public DonationProject(int donationProjectID, String donationProjectName, String projectDetails) {
		super();
		this.donationProjectID = donationProjectID;
		this.donationProjectName = donationProjectName;
		this.projectDetails = projectDetails;
	}

	public int getDonationProjectID() {
		return donationProjectID;
	}

	public void setDonationProjectID(int donationProjectID) {
		this.donationProjectID = donationProjectID;
	}

	public String getDonationProjectName() {
		return donationProjectName;
	}

	public void setDonationProjectName(String donationProjectName) {
		this.donationProjectName = donationProjectName;
	}

	public String getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(String projectDetails) {
		this.projectDetails = projectDetails;
	}

	@Override
	public String toString() {
		return "DonationProject [donationProjectID=" + donationProjectID + ", donationProjectName="
				+ donationProjectName + ", projectDetails=" + projectDetails + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(donationProjectID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonationProject other = (DonationProject) obj;
		return donationProjectID == other.donationProjectID;
	}
	
	

}
