package io.github.eggy03.pinetree.windows.service.display;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.display.VideoController;
import io.github.eggy03.pinetree.windows.enums.Namespace;
import io.github.eggy03.pinetree.windows.enums.WmiClassname;
import io.github.eggy03.pinetree.windows.enums.display.VideoControllerProperty;
import io.github.eggy03.pinetree.windows.mapper.display.VideoControllerMapper;
import io.github.eggy03.pinetree.windows.service.CommonServiceInterface;
import io.github.eggy03.pinetree.windows.util.ComUtil;
import io.github.eggy03.pinetree.windows.util.WmiUtil;

import java.util.List;

/**
 * Service class responsible for retrieving Video-Controller-related data from WMI.
 * <p>
 * Contacts {@link WmiUtil} to fetch GPU info in the form of {@link WbemcliUtil.WmiResult} and then, calls {@link VideoControllerMapper} to map the result
 * into a list of {@link VideoController} entities.
 * </p>
 * <h5>Thread Safety</h5>
 * Instances are stateless and thread-safe.
 */
public class VideoControllerService implements CommonServiceInterface<VideoController> {

    /**
     * Retrieves a list of Video Controllers (GPUs) from the system.
     * <p>
     * The caller is responsible for initializing and uninitializing COM.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * try{
     *     ComUtil.initialize();
     *     List<VideoController> videoList = new VideoControllerService().get();
     *     videoList.forEach(gpu -> System.out.println(gpu.toString());
     * } finally {
     *     ComUtil.uninitialize();
     * }
     * }
     * </pre>
     * @return list of {@link VideoController} instances retrieved via WMI
     */
    @Override
    public List<VideoController> get() {

        WbemcliUtil.WmiResult<VideoControllerProperty> result = WmiUtil.getResult(
                Namespace.DEFAULT.getValue(),
                WmiClassname.WIN32_VIDEO_CONTROLLER.getClassName(),
                VideoControllerProperty.class
        );

        return new VideoControllerMapper().toEntityList(result);
    }

    /**
     * Retrieves GPU related data while managing COM initialization and cleanup automatically.
     * <p>
     * Recommended for most callers who do not require manual COM control.
     *
     * <h5>Usage Example</h5>
     * <pre>{@code
     * List<VideoController> videoList = new VideoControllerService().getManaged();
     * videoList.forEach(gpu -> System.out.println(gpu.toString());
     * }
     * </pre>
     * @return list of {@link VideoController} instances retrieved via WMI
     */
    @Override
    public List<VideoController> getManaged() {

        try {
            ComUtil.initialize();

            WbemcliUtil.WmiResult<VideoControllerProperty> result = WmiUtil.getResult(
                    Namespace.DEFAULT.getValue(),
                    WmiClassname.WIN32_VIDEO_CONTROLLER.getClassName(),
                    VideoControllerProperty.class
            );

            return new VideoControllerMapper().toEntityList(result);
        } finally {
            ComUtil.uninitialize();
        }
    }
}
