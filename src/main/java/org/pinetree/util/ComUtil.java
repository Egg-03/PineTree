package org.pinetree.util;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Ole32;

/**
 * Utility class for managing the initialization and uninitialization
 * of the COM library on the current thread.
 * <p>
 * This ensures that Windows Management Instrumentation (WMI) calls can be executed safely.
 * </p>
 * <h2>Usage</h2>
 * <pre>{@code
 * ComUtil.initialize();
 * try {
 *     // Perform COM-dependent operations
 * } finally {
 *     ComUtil.uninitialize();
 * }
 * }</pre>
 */
public class ComUtil {

    private ComUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * Initializes COM for the current thread and sets default security levels.
     * <p>
     * Should be called before performing any WMI operations if not using a managed service method.
     * </p>
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
     * Uninitializes COM for the current thread.
     * <p>
     * Should always be called in a {@code finally} block to ensure unloading of DLLs and freeing of resources.
     */
    public static void uninitialize() {
        Ole32.INSTANCE.CoUninitialize();
    }
}
