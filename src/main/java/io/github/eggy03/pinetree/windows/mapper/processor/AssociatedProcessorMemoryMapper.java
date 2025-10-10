package io.github.eggy03.pinetree.windows.mapper.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.processor.AssociatedProcessorMemory;
import io.github.eggy03.pinetree.windows.enums.processor.AssociatedProcessorMemoryProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Converts raw WMI query results into {@link AssociatedProcessorMemory} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link AssociatedProcessorMemory} objects for easier consumption within the application.
 *
 * <h5>Thread Safety</h5>
 * Instances are stateless and therefore thread-safe.
 * @since 1.0
 */
public class AssociatedProcessorMemoryMapper implements CommonMappingInterface<AssociatedProcessorMemory, AssociatedProcessorMemoryProperty> {

    /**
     * Maps WMI result data into a list of {@link AssociatedProcessorMemory} entities.
     *
     * @param result the WMI query result containing properties defined in {@link AssociatedProcessorMemoryProperty}
     * @return a list of mapped {@link AssociatedProcessorMemory} instances
     */
    @Override
    public List<AssociatedProcessorMemory> toEntityList (WbemcliUtil.WmiResult<AssociatedProcessorMemoryProperty> result) {

        List<AssociatedProcessorMemory> associatedProcessorMemoryList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {

            AssociatedProcessorMemory associatedProcessorMemory = AssociatedProcessorMemory.builder()
                    .antecedent(toStringValue(result.getValue(AssociatedProcessorMemoryProperty.Antecedent, i)))
                    .dependent(toStringValue(result.getValue(AssociatedProcessorMemoryProperty.Dependent, i)))
                    .build();

            associatedProcessorMemoryList.add(associatedProcessorMemory);
        }

        return associatedProcessorMemoryList;
    }
}
