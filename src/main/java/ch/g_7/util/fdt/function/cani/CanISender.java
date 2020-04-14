package ch.g_7.util.fdt.function.cani;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.function.Sender;
import ch.g_7.util.parse.ParserUtil;

import java.util.function.Function;

public class CanISender<T> extends Sender {

    private Function<T, String> parser;
    private IEvent<?> event;

    public CanISender(FDTConnection connection, Class<T> clazz, IEvent<?> event) {
        super(connection);
        this.parser = ParserUtil.getToStringParser(clazz);
        this.event = event;
    }

    protected final boolean canI(T newValue, T oldValue, String additionalData){
        return send(parser.apply(newValue) + "|" + parser.apply(oldValue) + "|" + additionalData).getData().equals("1");
    }

    protected final boolean canI(String data){
        return send(data).getData().equals("1");
    }

    @Override
    protected String getName() {
        return "cani_" + event;
    }

    public IEvent<?> getEvent() {
        return event;
    }
}
