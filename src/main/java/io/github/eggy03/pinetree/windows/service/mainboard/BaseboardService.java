package io.github.eggy03.pinetree.windows.service.mainboard;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.mainboard.Baseboard;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.mainboard.BaseboardProperty;
import io.github.eggy03.pinetree.windows.mapper.mainboard.BaseboardMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving motherboard-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch Motherboard info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link BaseboardMapper} to map the result
 * into a list of {@link Baseboard} entities.
 * </p>
 * <h2>Thread Safety</h2>
 * Instances are stateless and thread-safe.
 * @since 1.0
 */
public class BaseboardService implements CommonServiceInterface<Baseboard> {

    /**
     * Retrieves a list of Motherboards from the system.
     * Realistically, this will always be a list of 1 item since there cannot be multiple motherboards for a system.
     * <p>
     * This method requires you to manually initialize and uninitialize the COM library.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<Baseboard> baseboardList = new BaseboardService().get();
     *     baseboardList.forEach(baseboard -> System.out.println(baseboard.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link Baseboard} instances retrieved via WMI
     */
    @Override
    public List<Baseboard> get() {

        WbemcliUtil.WmiResult<BaseboardProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_BASEBOARD.getClassName(),
                BaseboardProperty.class
        );

        return new BaseboardMapper().toEntityList(result);
    }

    /**
     * Retrieves a list of Motherboards from the system.
     * Realistically, this will always be a list of 1 item since there cannot be multiple motherboards for a system.
     * <p>
     * This method automatically handles COM setup and cleanup,
     * so you don't need to initialize COM manually.
     * </p>
     *
     * <h4>Usage Example</h4>
     * <pre>{@code
     * List<Baseboard> baseboardList = new BaseboardService().getManaged();
     * baseboardList.forEach(baseboard -> System.out.println(baseboard.toString());
     * }
     * </pre>
     * @return list of {@link Baseboard} instances retrieved via WMI
     */
    @Override
    public List<Baseboard> getManaged() {
       try {
           ComUtil.initialize();

           WbemcliUtil.WmiResult<BaseboardProperty> result = WmiUtil.getResult(
                   Namespace.DEFAULT.getValue(),
                   WmiClassname.WIN32_BASEBOARD.getClassName(),
                   BaseboardProperty.class
           );

           return new BaseboardMapper().toEntityList(result);
       } finally {
           ComUtil.uninitialize();
       }
    }
}
