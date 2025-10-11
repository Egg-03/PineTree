package io.github.eggy03.pinetree.windows.service.mainboard;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.mainboard.PortConnector;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.mainboard.PortConnectorProperty;
import io.github.eggy03.pinetree.windows.mapper.mainboard.PortConnectorMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving Motherboard port related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch BIOS info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link PortConnectorMapper} to map the result
 * into a list of {@link PortConnector} entities.
 * </p>
 * <h2>Thread Safety</h2>
 * Instances are stateless and thread-safe.
 * @since 1.0
 */
public class PortConnectorService implements CommonServiceInterface<PortConnector> {

    /**
     * Retrieves a list of Motherboard ports from the system.
     * <p>
     * This method requires you to manually initialize and uninitialize the COM library.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<PortConnector> portConnectorList = new PortConnectorService().get();
     *     portConnectorList.forEach(portConnector -> System.out.println(portConnector.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link PortConnector} instances retrieved via WMI
     */
    @Override
    public List<PortConnector> get() {
        WbemcliUtil.WmiResult<PortConnectorProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_PORT_CONNECTOR.getClassName(),
                PortConnectorProperty.class
        );
        return new PortConnectorMapper().toEntityList(result);
    }

    /**
     * Retrieves a list of Motherboard ports from the system.
     * <p>
     * This method automatically handles COM setup and cleanup,
     * so you don't need to initialize COM manually.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * List<PortConnector> portConnectorList = new PortConnectorService().getManaged();
     * portConnectorList.forEach(portConnector -> System.out.println(portConnector.toString());
     * }
     * </pre>
     * @return list of {@link PortConnector} instances retrieved via WMI
     */
    @Override
    public List<PortConnector> getManaged() {
        try {
            ComUtil.initialize();
            WbemcliUtil.WmiResult<PortConnectorProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_PORT_CONNECTOR.getClassName(),
                    PortConnectorProperty.class
            );
            return new PortConnectorMapper().toEntityList(result);
        } finally {
            ComUtil.uninitialize();
        }
    }
}
