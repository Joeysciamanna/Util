package ch.g_7.util.fdt.function;

import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.exception.FDTException;

public abstract class Reciever extends Function {

	public abstract String recieve(Request request) throws FDTException;
}
