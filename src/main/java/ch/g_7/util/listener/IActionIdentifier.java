package ch.g_7.util.listener;

import ch.g_7.util.common.IIdentifier;

public interface IActionIdentifier<T extends Enum<T> & IActionIdentifier<T>> extends IIdentifier<T> {

}
