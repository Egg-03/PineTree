package io.github.eggy03.pinetree.windows.mapper;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Interface defining a contract for mapping raw result retrieved from
 * {@link WbemcliUtil.WmiResult} into one of the entity classes
 * defined in {@link io.github.eggy03.pinetree.windows.entity} package
 * <p>
 * Implementations of this interface are expected to provide concrete definitions for:
 * <ul>
 *   <li>{@link #toEntityList(WbemcliUtil.WmiResult)} – map raw WMI query result into a
 *   list of Plain Old Java Objects (POJOs) </li>
 * </ul>
 * </p>
 *
 * <h5>Flow Description</h5>
 * <p>The mapping flow typically follows this sequence:</p>
 * <ol>
 *   <li>A WMI query is executed using the utility class {@link WmiUtil}, returning a
 *       {@link WbemcliUtil.WmiResult} containing raw property data.</li>
 *   <li>The {@code WmiResult} uses an {@code Enum} (from
 *       {@link io.github.eggy03.pinetree.windows.enums}) to represent property names
 *       of the corresponding WMI class.</li>
 *   <li>An implementation of this interface then reads each property value
 *       from the {@code WmiResult} and converts it into an
 *       entity defined in the {@link io.github.eggy03.pinetree.windows.entity} package.</li>
 *   <li>The resulting list of entities is returned to the caller for processing or persistence.</li>
 * </ol>
 *
 * <h5>Type Parameters</h5>
 * <ul>
 *   <li><b>&lt;S&gt;</b> — the entity type defined in {@link io.github.eggy03.pinetree.windows.entity}
 *   to which the result will be mapped.</li>
 *   <li><b>&lt;T&gt;</b> — the enum type defined in {@link io.github.eggy03.pinetree.windows.enums}
 *   representing the case-sensitive property fields of the WMI class.</li>
 * </ul>
 *
 * <h5>Thread Safety</h5>
 * Implementations are typically stateless and thread-safe unless otherwise specified.
 *
 * <h5>Example Implementations</h5>
 * <pre>{@code
 * // Define a mapper for mapping raw WMI query result into a Monitor entity
 * public class MonitorMapper implements CommonMappingInterface<Monitor, MonitorProperty> {
 *
 *     @Override
 *     public List<Monitor> toEntityList (WbemcliUtil.WmiResult<MonitorProperty> result) {
 *
 *         List<Monitor> monitorList = new ArrayList<>();
 *
 *         for (int i = 0; i < result.getResultCount(); i++) {
 *             Monitor monitor = Monitor.builder()
 *                     .deviceId(toStringValue(result.getValue(MonitorProperty.DeviceID, i)))
 *                     .name(toStringValue(result.getValue(MonitorProperty.Name, i)))
 *                     .status(toStringValue(result.getValue(MonitorProperty.Status, i)))
 *                     .build();
 *
 *             monitorList.add(monitor);
 *         }
 *         return monitorList;
 *     }
 * }
 * }</pre>
 *
 * @param <S> the entity type to which the result will be mapped
 * @param <T> the enum type defining the property fields to extract from the WMI result
 */
public interface CommonMappingInterface<S, T extends Enum<T>> {

    List<S> toEntityList(WbemcliUtil.WmiResult<T> result);
}
