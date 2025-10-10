package io.github.eggy03.pinetree.windows.mapper.display;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import io.github.eggy03.pinetree.windows.entity.display.VideoController;
import io.github.eggy03.pinetree.windows.enums.display.VideoControllerProperty;
import io.github.eggy03.pinetree.windows.mapper.CommonMappingInterface;

import java.util.ArrayList;
import java.util.List;

import static io.github.eggy03.pinetree.windows.util.CastUtil.toIntegerValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toLongValue;
import static io.github.eggy03.pinetree.windows.util.CastUtil.toStringValue;

/**
 * Converts raw WMI query results into {@link VideoController} entities.
 * <p>
 * Methods of this class map the result provided by {@link WbemcliUtil.WmiResult} to
 * immutable {@link VideoController} objects for easier consumption within the application.
 *
 * <h5>Thread Safety</h5>
 * Instances are stateless and therefore thread-safe.
 * @since 1.0
 */
public class VideoControllerMapper implements CommonMappingInterface<VideoController, VideoControllerProperty> {

    /**
     * Maps WMI result data into a list of {@link VideoController} entities.
     *
     * @param result the WMI query result containing properties defined in {@link VideoControllerProperty}
     * @return a list of mapped {@link VideoController} instances
     */
    @Override
    public List<VideoController> toEntityList (WbemcliUtil.WmiResult<VideoControllerProperty> result) {

        List<VideoController> videoControllerList = new ArrayList<>();

        for (int i = 0; i < result.getResultCount(); i++) {
            VideoController videoController = VideoController.builder()
                    .adapterDacType(toStringValue(result.getValue(VideoControllerProperty.AdapterDACType, i)))
                    .adapterRam(toLongValue(result.getValue(VideoControllerProperty.AdapterRAM, i)))
                    .currentBitsPerPixel(toIntegerValue(result.getValue(VideoControllerProperty.CurrentBitsPerPixel, i)))
                    .currentHorizontalResolution(toIntegerValue(result.getValue(VideoControllerProperty.CurrentHorizontalResolution, i)))
                    .currentRefreshRate(toIntegerValue(result.getValue(VideoControllerProperty.CurrentRefreshRate, i)))
                    .currentVerticalResolution(toIntegerValue(result.getValue(VideoControllerProperty.CurrentVerticalResolution, i)))
                    .deviceId(toStringValue(result.getValue(VideoControllerProperty.DeviceID, i)))
                    .driverDate(toStringValue(result.getValue(VideoControllerProperty.DriverDate, i)))
                    .driverVersion(toStringValue(result.getValue(VideoControllerProperty.DriverVersion, i)))
                    .maxRefreshRate(toIntegerValue(result.getValue(VideoControllerProperty.MaxRefreshRate, i)))
                    .minRefreshRate(toIntegerValue(result.getValue(VideoControllerProperty.MinRefreshRate, i)))
                    .name(toStringValue(result.getValue(VideoControllerProperty.Name, i)))
                    .pnpDeviceId(toStringValue(result.getValue(VideoControllerProperty.PNPDeviceID, i)))
                    .videoProcessor(toStringValue(result.getValue(VideoControllerProperty.VideoProcessor, i)))
                    .build();

            videoControllerList.add(videoController);
        }

        return videoControllerList;
    }
}
