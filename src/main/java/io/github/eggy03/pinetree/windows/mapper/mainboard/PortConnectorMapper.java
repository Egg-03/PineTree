package io.github.eggy03.pinetree.windows.mapper.mainboard;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.mainboard.PortConnector;
import io.github.eggy03.pinetree.windows.enums.mainboard.PortConnectorProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Maps raw WMI query results into {@link PortConnector} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link PortConnector} objects for easier consumption within the application.
 *
 * <h2>Thread Safety</h2>
 * Instances are stateless and therefore thread-safe.
 * @since 1.0
 */
public class PortConnectorMapper implements CommonMappingInterface<PortConnector, PortConnectorProperty> {

    /**
     * Maps WMI result data into a list of {@link PortConnector} entities.
     *
     * @param result the WMI query result containing properties defined in {@link PortConnectorProperty}
     * @return a list of mapped {@link PortConnector} instances
     */
    @Override
    public List<PortConnector> toEntityList(WbemcliUtil.WmiResult<PortConnectorProperty> result) {

        List<PortConnector> portConnectorList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {

            PortConnector portConnector = PortConnector.builder()
                    .tag(toStringValue(result.getValue(PortConnectorProperty.Tag, i)))
                    .internalReferenceDesignator(toStringValue(result.getValue(PortConnectorProperty.InternalReferenceDesignator, i)))
                    .externalReferenceDesignator(toStringValue(result.getValue(PortConnectorProperty.ExternalReferenceDesignator, i)))
                    .build();

            portConnectorList.add(portConnector);
        }
        return portConnectorList;
    }
}
