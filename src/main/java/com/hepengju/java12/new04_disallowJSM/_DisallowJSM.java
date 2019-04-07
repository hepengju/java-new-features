package com.hepengju.java12.new04_disallowJSM;

import org.junit.Test;

/**
 * disallow and allow Options for java.security.manager System Property
 *
 * <pre>
 *  New "disallow" and "allow" token options have been added to the java.security.manager system property.
 *  In the JDK implementation, if the Java Virtual Machine starts with the system property
 *  java.security.manager set to "disallow", then the System.setSecurityManager method cannot be used to
 *  set a security manager and will throw an UnsupportedOperationException.
 *
 *  The "disallow" option can improve run-time performance for applications that never set a security manager.
 *  For further details on the behavior of these options, see the class description of java.lang.SecurityManager.
 *
 * </pre>
 *
 * @author hepengju
 */
public class _DisallowJSM {
    @Test public void test(){}
}
