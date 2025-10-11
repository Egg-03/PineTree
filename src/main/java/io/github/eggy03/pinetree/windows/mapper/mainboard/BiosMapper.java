package io.github.eggy03.pinetree.windows.mapper.mainboard;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.mainboard.Bios;
import io.github.eggy03.pinetree.windows.enums.mainboard.BiosProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toBooleanValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Maps raw WMI query results into {@link Bios} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link Bios} objects for easier consumption within the application.
 *
 * <h2>Thread Safety</h2>
 * Instances are stateless and therefore thread-safe.
 * @since 1.0
 */
public class BiosMapper implements CommonMappingInterface<Bios, BiosProperty> {

    /**
     * Maps WMI result data into a list of {@link Bios} entities.
     *
     * @param result the WMI query result containing properties defined in {@link BiosProperty}
     * @return a list of mapped {@link Bios} instances
     */
    @Override
    public List<Bios> toEntityList(WbemcliUtil.WmiResult<BiosProperty> result) {

        List<Bios> biosList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {
            Bios bios = Bios.builder()
                    .caption(toStringValue(result.getValue(BiosProperty.Caption, i)))
                    .currentLanguage(toStringValue(result.getValue(BiosProperty.CurrentLanguage, i)))
                    .manufacturer(toStringValue(result.getValue(BiosProperty.Manufacturer, i)))
                    .name(toStringValue(result.getValue(BiosProperty.Name, i)))
                    .primaryBios(toBooleanValue(result.getValue(BiosProperty.PrimaryBIOS, i)))
                    .releaseDate(toStringValue(result.getValue(BiosProperty.ReleaseDate, i)))
                    .smbiosBiosVersion(toStringValue(result.getValue(BiosProperty.SMBIOSBIOSVersion, i)))
                    .smbiosPresent(toBooleanValue(result.getValue(BiosProperty.SMBIOSPresent, i)))
                    .status(toStringValue(result.getValue(BiosProperty.Status, i)))
                    .version(toStringValue(result.getValue(BiosProperty.Version, i)))
                    .build();

            biosList.add(bios);
        }
        return biosList;
    }
}
