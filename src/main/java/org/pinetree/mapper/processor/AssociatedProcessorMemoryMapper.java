package org.pinetree.mapper.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.processor.AssociatedProcessorMemory;
import org.pinetree.enums.processor.AssociatedProcessorMemoryProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts raw WMI query results into {@link AssociatedProcessorMemory} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link AssociatedProcessorMemory} objects for easier consumption within the application.
 *
 * <h3>Thread Safety</h3>
 * Instances are stateless and therefore thread-safe.
 */
public class AssociatedProcessorMemoryMapper {

    /**
     * Maps WMI result data into a list of {@link AssociatedProcessorMemory} entities.
     *
     * @param result the WMI query result containing properties defined in {@link AssociatedProcessorMemoryProperty}
     * @return a list of mapped {@link AssociatedProcessorMemory} instances
     */
    public List<AssociatedProcessorMemory> toEntityList (WbemcliUtil.WmiResult<AssociatedProcessorMemoryProperty> result) {

        List<AssociatedProcessorMemory> associatedProcessorMemoryList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {

            AssociatedProcessorMemory associatedProcessorMemory = AssociatedProcessorMemory.builder()
                    .antecedent((String) result.getValue(AssociatedProcessorMemoryProperty.Antecedent, i))
                    .dependent((String) result.getValue(AssociatedProcessorMemoryProperty.Dependent, i))
                    .build();

            associatedProcessorMemoryList.add(associatedProcessorMemory);
        }

        return associatedProcessorMemoryList;
    }
}
