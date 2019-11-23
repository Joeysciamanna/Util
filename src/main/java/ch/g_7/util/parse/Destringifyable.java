package ch.g_7.util.parse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;;

/**
 * Annotation for declaring a Constructor used to destringify
 * an object.
 * 
 * @author Joey Sciamanna
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface Destringifyable {

}
