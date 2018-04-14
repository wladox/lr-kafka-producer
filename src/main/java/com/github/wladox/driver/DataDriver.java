package com.github.wladox.driver;

import com.sun.jna.Native;

public class DataDriver {

    private static final String LIB_NAME = "datadriver";
    private DataDriverLibrary instance;


    public DataDriver(String searchPath) {
        System.setProperty("jna.library.path", searchPath);
        this.instance =  (DataDriverLibrary) Native.loadLibrary(LIB_NAME, DataDriverLibrary.class);
    }

    public DataDriver() {
        this.instance =  (DataDriverLibrary) Native.loadLibrary(LIB_NAME, DataDriverLibrary.class);
    }

    public DataDriverLibrary getLibrary() {return instance;}

}
