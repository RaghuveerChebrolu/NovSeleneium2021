package com.java.oops;

interface Printable1234 {
	void raghu();
}

interface Showable123 extends Printable1234 {
	void show();
}

class interfaceExtendAnotherInterface implements Showable123 {
	public void raghu() {
		System.out.println("Hello");
	}

	public void show() {
		System.out.println("Welcome");
	}

	public static void main(String args[]) {
		interfaceExtendAnotherInterface obj = new interfaceExtendAnotherInterface();
		obj.raghu();
		obj.show();
		Showable123 obj1 =  new interfaceExtendAnotherInterface();
		obj1.raghu();
		obj.show();
	}
}