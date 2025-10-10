package io.github.eggy03.pinetree.windows.service.display;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.display.Monitor;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.display.MonitorProperty;
import io.github.eggy03.pinetree.windows.mapper.display.MonitorMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving Monitor-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch Desktop Monitor info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link MonitorMapper} to map the result
 * into a list of {@link Monitor} entities.
 * </p>
 * <h2>Thread Safety</h2>
 * Instances are stateless and thread-safe.
 * @since 1.0
 */
public class MonitorService implements CommonServiceInterface<Monitor> {

    /**
     * Retrieves a list of Desktop Monitors from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<Monitor> monitorList = new MonitorService().get();
     *     monitorList.forEach(monitor-> System.out.println(monitor.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link Monitor} instances retrieved via WMI
     */
    @Override
    public List<Monitor> get() {

        WbemcliUtil.WmiResult<MonitorProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_DESKTOP_MONITOR.getClassName(),
                MonitorProperty.class
        );

        return new MonitorMapper().toEntityList(result);
    }

    /**
     * Retrieves Desktop Monitor related data while managing COM initialization and cleanup automatically.
     * <p>
     * Recommended for most callers who do not require manual COM control.
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * List<Monitor> monitorList = new MonitorService().getManaged();
     * monitorList.forEach(monitor-> System.out.println(monitor.toString());
     * }
     * </pre>
     * @return list of {@link Monitor} instances retrieved via WMI
     */
    @Override
    public List<Monitor> getManaged() {

        try {
            ComUtil.initialize();

            WbemcliUtil.WmiResult<MonitorProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_DESKTOP_MONITOR.getClassName(),
                    MonitorProperty.class
            );

            return new MonitorMapper().toEntityList(result);
        } finally {
            ComUtil.uninitialize();
        }
    }
}
