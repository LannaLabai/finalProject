package model;

import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import utils.*;

public class PaidService extends Service {
	
	private double serviceCost;

	public PaidService(int serviceID, String serivceName, ServiceType serviceType, int maxNumOfParticipants, 
			String serviceDesc, double serviceCost) {
		super(serviceID, serivceName, serviceType,maxNumOfParticipants,serviceDesc);
		this.serviceCost = serviceCost;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}

	@Override
	public String toString() {
		return super.toString()+"PaidService [serviceCost=" + serviceCost + "]";
	}
	

}
