package ch.g_7.util.fdt.function;

import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;

public abstract class Reciever extends Function {

	public abstract Response recieve(Request request) throws FDTException;
}
