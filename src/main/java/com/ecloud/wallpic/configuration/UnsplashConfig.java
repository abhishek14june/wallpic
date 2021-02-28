package com.ecloud.wallpic.configuration;

import org.springframework.stereotype.Component;

@Component
public class UnsplashConfig {

		private  String authorizationCode ="vBrr9UJIyCb0kxYzofA22RnZOq4bTowsLNFQ1BY8q-M";
		private  String accesskey= "voRW7duATMoZC1LuTwaOtrY8H0dhhK7vdd7LWJO_l_g";
		private  String secretKey = "bOjVELVsGGaSNyuBUFr9YopyOV-PmqVCSNIkbf6mV78";
		private String clientId = "22cd2f47a286bc269c11fc442f99f4ea5960a21f1cd70783dabe5b9a74a49c62";
		public String getAuthorizationCode() {
			return authorizationCode;
		}
		public void setAuthorizationCode(String authorizationCode) {
			this.authorizationCode = authorizationCode;
		}
		public String getAccesskey() {
			return accesskey;
		}
		public void setAccesskey(String accesskey) {
			this.accesskey = accesskey;
		}
		public String getSecretKey() {
			return secretKey;
		}
		public void setSecretKey(String secretKey) {
			this.secretKey = secretKey;
		}
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		
}
