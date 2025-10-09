package io.github.eggy03.pinetree.service;

import io.github.eggy03.pinetree.util.ComUtil;
import io.github.eggy03.pinetree.util.WmiUtil;

import java.util.List;

/**
 * Common service interface defining a contract for retrieving entity data {@link io.github.eggy03.pinetree.entity},
 * from WMI.
 * <p>
 * Implementations of this interface are expected to provide:
 * <ul>
 *   <li>{@link #get()} – retrieve entity data while requiring the method caller to initialize and cleanup COM resources.</li>
 *   <li>{@link #getManaged()} – retrieve entity data without delegating the COM initialization and cleanup to the method caller.</li>
 * </ul>
 * </p>
 *
 * <p>
 *     Implementations of the methods should take the help of utility classes {@link WmiUtil}
 *     and {@link ComUtil} to fetch results and maintain COM resource lifecycle.
 *     The examples in this documentation show their usages.
 * </p>
 *
 * <h5>Type Parameter</h5>
 * <ul>
 *   <li><b>&lt;S&gt;</b> – the entity type defined in {@link io.github.eggy03.pinetree.entity} returned by the service implementation.</li>
 * </ul>
 *
 * <h5>Thread Safety</h5>
 * Implementations are typically stateless and thread-safe unless otherwise specified.
 *
 * <h5>Example Implementations</h5>
 * <pre>{@code
 * // Example 1: Define a service that fetches Desktop Monitors
 * public class MonitorService implements CommonServiceInterface<Monitor> {
 *
 *     @Override
 *     public List<Monitor> get() {
 *         WbemcliUtil.WmiResult<MonitorProperty> result = WmiUtil.getResult(
 *             Namespace.DEFAULT.getValue(),
 *             WmiClassname.WIN32_DESKTOP_MONITOR.getClassName(),
 *             MonitorProperty.class
 *         );
 *         return new MonitorMapper().toEntityList(result);
 *     }
 *
 *     @Override
 *     public List<Monitor> getManaged() {
 *         try {
 *             ComUtil.initialize();
 *             WbemcliUtil.WmiResult<MonitorProperty> result = WmiUtil.getResult(
 *                 Namespace.DEFAULT.getValue(),
 *                 WmiClassname.WIN32_DESKTOP_MONITOR.getClassName(),
 *                 MonitorProperty.class
 *             );
 *             return new MonitorMapper().toEntityList(result);
 *         } finally {
 *             ComUtil.uninitialize();
 *         }
 *     }
 * }
 *
 * // Example 2: Define a service that fetches GPU information
 * public class VideoControllerService implements CommonServiceInterface<VideoController> {
 *
 *     @Override
 *     public List<VideoController> get() {
 *         WbemcliUtil.WmiResult<VideoControllerProperty> result = WmiUtil.getResult(
 *             Namespace.DEFAULT.getValue(),
 *             WmiClassname.WIN32_VIDEO_CONTROLLER.getClassName(),
 *             VideoControllerProperty.class
 *         );
 *         return new VideoControllerMapper().toEntityList(result);
 *     }
 *
 *     @Override
 *     public List<VideoController> getManaged() {
 *         try {
 *             ComUtil.initialize();
 *             WbemcliUtil.WmiResult<VideoControllerProperty> result = WmiUtil.getResult(
 *                 Namespace.DEFAULT.getValue(),
 *                 WmiClassname.WIN32_VIDEO_CONTROLLER.getClassName(),
 *                 VideoControllerProperty.class
 *             );
 *             return new VideoControllerMapper().toEntityList(result);
 *         } finally {
 *             ComUtil.uninitialize();
 *         }
 *     }
 * }
 * }</pre>
 *
 * @param <S> the entity type returned by the service implementation
 */
public interface CommonServiceInterface<S> {

    List<S> get();

    List<S> getManaged();
}
