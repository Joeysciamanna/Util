package ch.g_7.util.fdt.function.cani;

import ch.g_7.util.fdt.FDTConnection;
import ch.g_7.util.fdt.data.Response;
import ch.g_7.util.fdt.function.Sender;
import ch.g_7.util.parse.Destringifyable;
import ch.g_7.util.parse.ParserUtil;
import ch.g_7.util.parse.Stringifyable;

import java.util.function.Function;

public class CanISender<T> extends Sender {

    private Function<T, String> parser;
    private IEvent<?> event;

    public CanISender(FDTConnection connection, String endpoint, Class<T> clazz, IEvent<?> event) {
        super(connection, endpoint);
        this.parser = ParserUtil.getToStringParser(clazz);
        this.event = event;
    }

    protected final boolean canI(T newValue, T oldValue){
        return send(parser.apply(newValue) +"|"+ parser.apply(oldValue)).getData().equals("1");
    }


    @Override
    protected String getName() {
        return "cani_" + event.name();
    }

    public IEvent<?> getEvent() {
        return event;
    }
}
