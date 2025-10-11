package io.github.eggy03.pinetree.windows.service.mainboard;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.mainboard.Bios;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.mainboard.BiosProperty;
import io.github.eggy03.pinetree.windows.mapper.mainboard.BiosMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving BIOS-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch BIOS info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link BiosMapper} to map the result
 * into a list of {@link Bios} entities.
 * </p>
 * <h2>Thread Safety</h2>
 * Instances are stateless and thread-safe.
 * @since 1.0
 */
public class BiosService implements CommonServiceInterface<Bios> {

    /**
     * Retrieves a list of BIOSes from the system.
     * <p>
     * This method requires you to manually initialize and uninitialize the COM library.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<Bios> biosList = new BiosService().get();
     *     biosList.forEach(bios -> System.out.println(bios.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link Bios} instances retrieved via WMI
     */
    @Override
    public List<Bios> get() {

        WbemcliUtil.WmiResult<BiosProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_BIOS.getClassName(),
                BiosProperty.class
        );

        return new BiosMapper().toEntityList(result);
    }

    /**
     * Retrieves a list of BIOSes from the system.
     * <p>
     * This method automatically handles COM setup and cleanup,
     * so you don't need to initialize COM manually.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * List<Bios> biosList = new BiosService().getManaged();
     * biosList.forEach(bios -> System.out.println(bios.toString());
     * }
     * </pre>
     * @return list of {@link Bios} instances retrieved via WMI
     */
    @Override
    public List<Bios> getManaged() {

        try {
            ComUtil.initialize();

            WbemcliUtil.WmiResult<BiosProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_BIOS.getClassName(),
                    BiosProperty.class
            );

            return new BiosMapper().toEntityList(result);
        } finally {
            ComUtil.uninitialize();
        }
    }
}
