package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class Service {
	
	private int serviceID;
	private String serviceName;
	private ServiceType serviceType;
	private int maxNumOfParticipants; 
	private String serviceDesc;
	
	
	
	public Service(int serviceID, String serivceName, ServiceType serviceType, int maxNumOfParticipants,
			String serviceDesc) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serivceName;
		this.serviceType = serviceType;
		this.maxNumOfParticipants = maxNumOfParticipants;
		this.serviceDesc = serviceDesc;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setSerivceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serivceName) {
		this.serviceName = serivceName;
	}
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	public int getMaxNumOfParticipants() {
		return maxNumOfParticipants;
	}
	public void setMaxNumOfParticipants(int maxNumOfParticipants) {
		this.maxNumOfParticipants = maxNumOfParticipants;
	}
	
	
	
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	
	

	@Override
	public String toString() {
		return "Service [serviceID=" + serviceID + ", serivceName=" + serviceName + ", serviceType=" + serviceType
				+ ", maxNumOfParticipants=" + maxNumOfParticipants + ", serviceDesc=" + serviceDesc + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(serviceID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		return serviceID == other.serviceID;
	}
	
	
}
