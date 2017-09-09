package com.github.wladox.driver;

import com.sun.jna.Callback;
import com.sun.jna.Library;

public interface DataDriverLibrary extends Library{

    interface TupleReceivedCallback extends Callback {
        void invoke(String tuple);
    }
    int startProgram(String argv,  TupleReceivedCallback tupleReceivedCallback);
    int  test();

}
