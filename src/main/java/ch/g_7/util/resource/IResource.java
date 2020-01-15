package ch.g_7.util.resource;

import ch.g_7.util.common.Closeable;
import ch.g_7.util.common.Initializable;

public interface IResource extends Initializable, Closeable{

	int getResourceId();
}
