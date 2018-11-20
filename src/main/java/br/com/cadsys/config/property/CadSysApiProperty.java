package br.com.cadsys.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application")
public class CadSysApiProperty {

	private String originPermitida = "http://localhost:4200";
	
	private String endpointTest = "http://localhost:8081";

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

	/**
	 * @return the endpointTest
	 */
	public String getEndpointTest() {
		return endpointTest;
	}

	/**
	 * @param endpointTest the endpointTest to set
	 */
	public void setEndpointTest(String endpointTest) {
		this.endpointTest = endpointTest;
	}


}