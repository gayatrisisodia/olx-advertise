package com.olx.actuator;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomeInfoIndicator implements InfoContributor {

	@Override
	public void contribute(Builder builder) {
		// TODO Auto-generated method stub
		
		builder.withDetail("total-registerd-users", 478);
		builder.withDetail("active-login-count", 35);
		
		
	}

}
