package com.zc.superbooks.message;

public enum PurposeType {
	shopping("shopping"),
	book("book"),
	communication("communication"),
	food("food"),
	dog("dog"),
	traffic("traffic"),
	ticket("ticket"),
	treat("treat"),
	party("party");
	
	private final String type;

	private PurposeType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}