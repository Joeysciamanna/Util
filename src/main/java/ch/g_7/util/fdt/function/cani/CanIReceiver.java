package ch.g_7.util.fdt.function.cani;

import ch.g_7.util.fdt.data.Metadata;
import ch.g_7.util.fdt.data.Request;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.exception.FDTException;
import ch.g_7.util.fdt.function.Receiver;
import ch.g_7.util.parse.ParserUtil;
import ch.g_7.util.parse.Stringifyable;

import java.util.function.Function;

public abstract class CanIReceiver<T> extends Receiver {

    private Function<String, T> parser;
    private IEvent<?> event;

    public CanIReceiver(String endpoint, Class<T> clazz, IEvent<?> event) {
        super(endpoint);
        this.parser = ParserUtil.getFromStringParser(clazz);
        this.event = event;
    }

    @Override
    public Response receive(Request request) throws FDTException {
        String[] args = request.getData().split("\\|");
        T newValue = parser.apply(args[0]);
        T oldValue = parser.apply(args[1]);
        return new Response(canHe(newValue, oldValue, args[2], request.getMetadata()) ? "1" : "0");
    }

    protected abstract boolean canHe(T newValue, T oldValue, String additionalData, Metadata metadata);

    @Override
    protected String getName() {
        return "cani_" + event.name();
    }

    public IEvent<?> getEvent() {
        return event;
    }

}
