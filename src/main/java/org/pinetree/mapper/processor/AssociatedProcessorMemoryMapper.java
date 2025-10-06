package org.pinetree.mapper.processor;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import org.pinetree.entity.processor.AssociatedProcessorMemory;
import org.pinetree.enums.processor.AssociatedProcessorMemoryProperty;

import java.util.ArrayList;
import java.util.List;

public class AssociatedProcessorMemoryMapper {

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
