package com.ecloud.wallpic.configuration;

public class UserDefinedEnums {

	enum OrderBy{
		LATEST("latest"),
		OLDEST("oldest"),
		POPULAR("popular"),
		RELEVANT("relevant");
		private String value;		
		OrderBy(String value) {
			this.value=value;
		}
	}
	
	enum Orientations{
		LANDSCAPE("landscape"),PORTRAIT("portrait"),SQUARISH("squarish");
		private String value;		
		Orientations(String value) {
			this.value=value;
		}
	}
	
	enum Color{
		BLACK("black"),
		WHITE("white"),
		BLACKNWHITE("black_and_white"),
		YELLOW("yellow"),
		ORANGE("orange"),
		RED("red"),
		PURPLE("purple"),
		GREEN("green"),
		TEAL("teal"),
		BLUE("blue");
		private String value;
		Color(String value) {
			this.value=value;
		}
	}
}			
