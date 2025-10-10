package io.github.eggy03.pinetree.windows.util;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Ole32;

/**
 * Utility class for managing the initialization and uninitialization
 * of the COM library on the current thread.
 * <p>
 * Provides an abstraction for initializing and closing the COM library.
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
 * @since 1.0
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
     * @see <a href="https://learn.microsoft.com/en-us/windows/win32/wmisdk/example--getting-wmi-data-from-the-local-computer">
     *     C++ equivalent example used in the official microsoft documentation</a>
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
     * Should always be called at the end of the methods implementing it to ensure the COM library is closed for the
     * given thread.
     */
    public static void uninitialize() {
        Ole32.INSTANCE.CoUninitialize();
    }
}
