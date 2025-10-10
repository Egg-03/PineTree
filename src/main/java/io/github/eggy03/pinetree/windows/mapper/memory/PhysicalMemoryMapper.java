package io.github.eggy03.pinetree.windows.mapper.memory;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.memory.PhysicalMemory;
import io.github.eggy03.pinetree.windows.enums.memory.PhysicalMemoryProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toIntegerValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toLongValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Maps raw WMI query results into {@link PhysicalMemory} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link PhysicalMemory} objects for easier consumption within the application.
 *
 * <h2>Thread Safety</h2>
 * Instances are stateless and therefore thread-safe.
 * @since 1.0
 */
public class PhysicalMemoryMapper implements CommonMappingInterface<PhysicalMemory, PhysicalMemoryProperty> {

    /**
     * Maps WMI result data into a list of {@link PhysicalMemory} entities.
     *
     * @param result the WMI query result containing properties defined in {@link PhysicalMemoryProperty}
     * @return a list of mapped {@link PhysicalMemory} instances
     */
    @Override
    public List<PhysicalMemory> toEntityList (WbemcliUtil.WmiResult<PhysicalMemoryProperty> result) {

        List<PhysicalMemory> memoryList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {
            PhysicalMemory memory = PhysicalMemory.builder()
                    .bankLabel(toStringValue(result.getValue(PhysicalMemoryProperty.BankLabel, i)))
                    .capacity(toLongValue(result.getValue(PhysicalMemoryProperty.Capacity, i)))
                    .configuredClockSpeed(toLongValue(result.getValue(PhysicalMemoryProperty.ConfiguredClockSpeed, i)))
                    .dataWidth(toIntegerValue(result.getValue(PhysicalMemoryProperty.DataWidth, i)))
                    .deviceLocator(toStringValue(result.getValue(PhysicalMemoryProperty.DeviceLocator, i)))
                    .formFactor(toIntegerValue(result.getValue(PhysicalMemoryProperty.FormFactor, i)))
                    .manufacturer(toStringValue(result.getValue(PhysicalMemoryProperty.Manufacturer, i)))
                    .model(toStringValue(result.getValue(PhysicalMemoryProperty.Model, i)))
                    .name(toStringValue(result.getValue(PhysicalMemoryProperty.Name, i)))
                    .otherIdentifyingInfo(toStringValue(result.getValue(PhysicalMemoryProperty.OtherIdentifyingInfo, i)))
                    .partNumber(toStringValue(result.getValue(PhysicalMemoryProperty.PartNumber, i)))
                    .serialNumber(toStringValue(result.getValue(PhysicalMemoryProperty.SerialNumber, i)))
                    .speed(toLongValue(result.getValue(PhysicalMemoryProperty.Speed, i)))
                    .tag(toStringValue(result.getValue(PhysicalMemoryProperty.Tag, i)))
                    .build();

            memoryList.add(memory);
        }

        return memoryList;
    }
}
