package com.hepengju.java07.new04_genericInference;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 泛型的类型推断
 * 
 * @author hepengju
 *
 */
public class _GenericInference {

    @SuppressWarnings("unused")
    @Test public void testGenericInference() {
        List<String> list01 = new ArrayList<String>();
        List<String> list02 = new ArrayList<>();       // 后面的类型可以推断出来,可以省略不谢
    }
}
