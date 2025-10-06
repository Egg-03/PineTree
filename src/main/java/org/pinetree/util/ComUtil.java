package org.pinetree.util;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Ole32;

/**
 * Utility class to initialize and un-initialize the COM library and DLLs for the current thread
 */
public class ComUtil {

    private ComUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Initialize and set general security levels for the calling thread
     */
    public static void initialize() {

        // Initialize COM
        Ole32.INSTANCE.CoInitializeEx(Pointer.NULL, Ole32.COINIT_MULTITHREADED);

        // Set general COM security levels
        Ole32.INSTANCE.CoInitializeSecurity(
                null,
                -1,
                null,
                null,
                Ole32.RPC_C_AUTHN_LEVEL_DEFAULT,
                Ole32.RPC_C_IMP_LEVEL_IMPERSONATE,
                null,
                Ole32.EOAC_NONE,
                null
        );
    }

    /**
     * Unload COM for the current thread
     */
    public static void uninitialize() {
        Ole32.INSTANCE.CoUninitialize();
    }
}
