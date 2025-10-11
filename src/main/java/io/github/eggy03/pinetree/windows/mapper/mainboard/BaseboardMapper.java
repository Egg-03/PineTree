package io.github.eggy03.pinetree.windows.mapper.mainboard;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.mainboard.Baseboard;
import io.github.eggy03.pinetree.windows.enums.mainboard.BaseboardProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Maps raw WMI query results into {@link Baseboard} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link Baseboard} objects for easier consumption within the application.
 *
 * <h2>Thread Safety</h2>
 * Instances are stateless and therefore thread-safe.
 * @since 1.0
 */
public class BaseboardMapper implements CommonMappingInterface<Baseboard, BaseboardProperty> {

    /**
     * Maps WMI result data into a list of {@link Baseboard} entities.
     *
     * @param result the WMI query result containing properties defined in {@link BaseboardProperty}
     * @return a list of mapped {@link Baseboard} instances
     */
    @Override
    public List<Baseboard> toEntityList(WbemcliUtil.WmiResult<BaseboardProperty> result) {

        List<Baseboard> baseboardList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {
            Baseboard baseboard = Baseboard.builder()
                    .manufacturer(toStringValue(result.getValue(BaseboardProperty.Manufacturer, i)))
                    .model(toStringValue(result.getValue(BaseboardProperty.Model, i)))
                    .product(toStringValue(result.getValue(BaseboardProperty.Product, i)))
                    .serialNumber(toStringValue(result.getValue(BaseboardProperty.SerialNumber, i)))
                    .version(toStringValue(result.getValue(BaseboardProperty.Version, i)))
                    .build();

            baseboardList.add(baseboard);
        }
        return baseboardList;
    }
}
