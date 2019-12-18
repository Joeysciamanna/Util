package ch.g_7.util.fdt.function.rom;

import java.util.HashMap;
import java.util.Map;

import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.exception.StatusCode;
import ch.g_7.util.fdt.function.Reciever;
import ch.g_7.util.reflection.ObjectCallWrapper;
import ch.g_7.util.task.SecureRunner;

public class ROMReciever extends Reciever {

	private Map<Long, ObjectCallWrapper> romObjects;

	public ROMReciever() {
		romObjects = new HashMap<Long, ObjectCallWrapper>();
	}

	@Override
	public Response recieve(Request request) throws FDTException {
		ROMData romData = new ROMData(request.getData());
		if (!romObjects.containsKey(romData.getObjId())) {
			throw new FDTException("No ROMObject with id " + romData.getObjId() + " found",
					StatusCode.INVALLID_REQUEST_PARAMS);
		}
		ObjectCallWrapper romObject = romObjects.get(romData.getObjId());
		return new Response(StatusCode.SUCCESS, "",
				new SecureRunner<>(
						() -> romObject.call(romData.getMethodName(), romData.getArgs().toArray(new String[] {})))
								.throwException(new FDTException("Invalid Method parameters",
										StatusCode.INVALLID_REQUEST_PARAMS))
								.get());

	}

	@Override
	protected String getName() {
		return "rom";
	}

	public void add(Object object, long romId) {
		romObjects.put(romId, new ObjectCallWrapper(object, RemoteCallable.class));
	}
}
