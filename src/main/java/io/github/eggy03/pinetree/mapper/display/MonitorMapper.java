package io.github.eggy03.pinetree.mapper.display;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.entity.display.Monitor;
import io.github.eggy03.pinetree.enums.display.MonitorProperty;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.util.CastUtil.toIntegerValue;
import static io.github.eggy03.pinetree.util.CastUtil.toStringValue;

/**
 * Converts raw WMI query results into {@link Monitor} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link Monitor} objects for easier consumption within the application.
 *
 * <h5>Thread Safety</h5>
 * Instances are stateless and therefore thread-safe.
 */
public class MonitorMapper {

    /**
     * Maps WMI result data into a list of {@link Monitor} entities.
     *
     * @param result the WMI query result containing properties defined in {@link MonitorProperty}
     * @return a list of mapped {@link Monitor} instances
     */
    public List<Monitor> toEntityList (WbemcliUtil.WmiResult<MonitorProperty> result) {

        List<Monitor> monitorList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {
            Monitor monitor = Monitor.builder()
                    .monitorManufacturer(toStringValue(result.getValue(MonitorProperty.MonitorManufacturer, i)))
                    .monitorType(toStringValue(result.getValue(MonitorProperty.MonitorType, i)))
                    .deviceId(toStringValue(result.getValue(MonitorProperty.DeviceID, i)))
                    .name(toStringValue(result.getValue(MonitorProperty.Name, i)))
                    .pixelsPerXLogicalInch(toIntegerValue(result.getValue(MonitorProperty.PixelsPerXLogicalInch, i)))
                    .pixelsPerYLogicalInch(toIntegerValue(result.getValue(MonitorProperty.PixelsPerYLogicalInch, i)))
                    .pnpDeviceId(toStringValue(result.getValue(MonitorProperty.PNPDeviceID, i)))
                    .status(toStringValue(result.getValue(MonitorProperty.Status, i)))
                    .build();

            monitorList.add(monitor);
        }
        return monitorList;
    }
}
