package io.github.eggy03.pinetree.service.display;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.entity.display.Monitor;
import io.github.eggy03.pinetree.enums.Namespace;
import io.github.eggy03.pinetree.enums.WmiClassname;
import io.github.eggy03.pinetree.enums.display.MonitorProperty;
import io.github.eggy03.pinetree.mapper.display.MonitorMapper;
import io.github.eggy03.pinetree.util.ComUtil;
import io.github.eggy03.pinetree.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving Monitor-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch Desktop Monitor info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link MonitorMapper} to map the result
 * into a list of {@link Monitor} entities.
 * </p>
 * <h5>Thread Safety</h5>
 * Instances are stateless and thread-safe.
 */
public class MonitorService {

    /**
     * Retrieves a list of Desktop Monitors from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<Monitor> monitorList = new MonitorService().getMonitors();
     *     monitorList.forEach(monitor-> System.out.println(monitor.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link Monitor} instances retrieved via WMI
     */
    public List<Monitor> getMonitors() {

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
     * <h5>Usage Example</h5>
     * <pre>{@code
     * List<Monitor> monitorList = new MonitorService().getMonitorsManaged();
     * monitorList.forEach(monitor-> System.out.println(monitor.toString());
     * }
     * </pre>
     * @return list of {@link Monitor} instances retrieved via WMI
     */
    public List<Monitor> getMonitorsManaged() {

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
