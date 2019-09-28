package ch.g_7.util.parse.test;

import ch.g_7.util.reflection.InterfaceInvokeListner;
import ch.g_7.util.stuff.Passable;

public class Test {

	public static void main(String[] args) throws Exception {
		InterfaceInvokeListner<Passable> invokeListner = new InterfaceInvokeListner<>(Passable.class);
		Passable passable = invokeListner.getInterface();
		
		passable.close();
		invokeListner.addCallListner((o) -> {System.out.println("close"); return null; });
		passable.close();
		
		passable.open();
		invokeListner.addCallListner((o) -> {System.out.println("open"); return null; });
		passable.open();
	}
}
