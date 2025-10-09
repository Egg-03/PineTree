/**
 * <p>
 * This illustration demonstrates how to define a custom entity, enum, mapper, and service
 * to retrieve WMI data and make it available for consumption outside of this library.
 * This is intended for developers, contributors, and advanced users who wish to extend
 * the functionality of this library by adding support for additional WMI classes.
 * </p>
 *
 * <p>
 * Towards the end of this illustration an example of how to consume your defined service is also included.
 * This is intended for consumers of this library.
 * </p>
 *
 * <h5>Implementation</h5>
 *
 * <p> For illustration, we will implement {@code Win32_DesktopMonitor} WMI class.</p>
 *
 * <p>
 * The following example demonstrates how to implement the {@code Win32_DesktopMonitor} class which includes
 * defining an entity, an enum, a mapper and a service.
 * </p>
 *
 * Check out: <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-desktopmonitor">Microsoft Documentation for Win32_DesktopMonitor</a>
 * <p>
 *
 * According to the documentation, this class exposes properties such as {@code DeviceID},
 * {@code Name}, {@code Status}, and more. These names are case-sensitive and must be used
 * exactly as defined when building your enum.
 * </p>
 *
 * <h6>1. Define the Entity</h6>
 * Entities are defined under {@link io.github.eggy03.pinetree.entity}
 * <pre>{@code
 * @Value
 * @Builder(toBuilder = true)
 * public class Monitor {
 *
 *     @SerializedName("DeviceID") // must match the property names found in the docs
 *     @Nullable
 *     String deviceId;
 *
 *     @SerializedName("Name")
 *     @Nullable
 *     String name;
 *
 *     @SerializedName("Status")
 *     @Nullable
 *     String status;
 *
 *     @Override
 *     public String toString() {
 *         Gson gson = new GsonBuilder().setPrettyPrinting().create();
 *         return gson.toJson(this);
 *     }
 * }
 * }</pre>
 *
 * <h6>2. Define the Enum</h6>
 * Enums are defined under {@link io.github.eggy03.pinetree.enums}.
 * The names must match the exact property names found in the WMI class documentation.
 * <pre>{@code
 * public enum MonitorProperty {
 *     DeviceID, Name, Status; // once again the names must match the property names found in the docs
 * }
 * }</pre>
 *
 * Add the corresponding WMI class name to {@link io.github.eggy03.pinetree.enums.WmiClassname}:
 * <pre>{@code
 * public enum WmiClassname {
 *   //... other classnames
 *   WIN32_DESKTOP_MONITOR("Win32_DesktopMonitor");
 * }
 * }</pre>
 *
 * The {@link io.github.eggy03.pinetree.enums.Namespace} enum defines supported namespaces.
 * {@code Win32_DesktopMonitor} resides in the default namespace: {@code root/cimv2}.
 * You may also add other namespaces, should your code need it.
 *
 <pre>{@code
 * public enum Namespace {
 *
 *   DEFAULT("root/cimv2");
 *
 *   private final String value;
 * }
 * }</pre>
 *
 * <h6>3. Implement the Mapper</h6>
 * <p>
 * Mappers reside in the {@link io.github.eggy03.pinetree.mapper} package and implement
 * {@link io.github.eggy03.pinetree.mapper.CommonMappingInterface}.
 * They handle conversion of raw {@link com.sun.jna.platform.win32.COM.WbemcliUtil.WmiResult}
 * data into your defined entities.
 * </p>
 * <pre>{@code
 * public class MonitorMapper implements CommonMappingInterface<Monitor, MonitorProperty> {
 *
 *     @Override
 *     public List<Monitor> toEntityList (WbemcliUtil.WmiResult<MonitorProperty> result) {
 *
 *         List<Monitor> monitorList = new ArrayList<>();
 *
 *         for (int i = 0; i < result.getResultCount(); i++) {
 *             Monitor monitor = Monitor.builder()
 *                     .deviceId(toStringValue(result.getValue(MonitorProperty.DeviceID, i)))
 *                     .name(toStringValue(result.getValue(MonitorProperty.Name, i)))
 *                     .status(toStringValue(result.getValue(MonitorProperty.Status, i)))
 *                     .build();
 *
 *             monitorList.add(monitor);
 *         }
 *         return monitorList;
 *     }
 * }
 * }</pre>
 * <p>{@link com.sun.jna.platform.win32.COM.WbemcliUtil.WmiResult} returns values of type {@link java.lang.Object} which
 * needs to be type-casted. For easier casting, we have defined utility methods in {@link io.github.eggy03.pinetree.util.CastUtil}
 * </p>
 *
 * <h6>4. Implement the Service</h6>
 * <p>
 * Services reside in the {@link io.github.eggy03.pinetree.service} package and implement
 * {@link io.github.eggy03.pinetree.service.CommonServiceInterface}.
 * Their flow involves, querying WMI via {@link io.github.eggy03.pinetree.util.WmiUtil}, using a defined mapper to map
 * the raw results into entities and returning them to the caller.
 * Additionally, they manage COM initialization and uninitialization using {@link io.github.eggy03.pinetree.util.ComUtil}.
 * </p>
 * <pre>{@code
 * public class MonitorService implements CommonServiceInterface<Monitor> {
 *
 *     @Override
 *     public List<Monitor> get() {
 *
 *         WbemcliUtil.WmiResult<MonitorProperty> result = WmiUtil.getResult(
 *                 Namespace.DEFAULT.getValue(),
 *                 WmiClassname.WIN32_DESKTOP_MONITOR.getClassName(),
 *                 MonitorProperty.class
 *         );
 *
 *         return new MonitorMapper().toEntityList(result);
 *     }
 *
 *     @Override
 *     public List<Monitor> getManaged() {
 *
 *         try {
 *             ComUtil.initialize();
 *
 *             WbemcliUtil.WmiResult<MonitorProperty> result = WmiUtil.getResult(
 *                     Namespace.DEFAULT.getValue(),
 *                     WmiClassname.WIN32_DESKTOP_MONITOR.getClassName(),
 *                     MonitorProperty.class
 *             );
 *
 *             return new MonitorMapper().toEntityList(result);
 *         } finally {
 *             ComUtil.uninitialize();
 *         }
 *     }
 * }
 * }</pre>
 *
 * <h5>Consumption</h5>
 * <p>
 *     Once implemented, your service can be consumed as follows:
 * </p>
 * <h6> Consuming the {@code Win32_DesktopMonitor} class</h6>
 * <pre>{@code
 * public static void main(String[] args) {
 *     // auto managed COM
 *     List<Monitor> monitorList = new MonitorService().getManaged();
 *     monitorList.forEach(monitor-> System.out.println(monitor.toString());
 *
 *     // self managed COM
 *      try{
 *          ComUtil.initialize();
 *          List<Monitor> monitorList = new MonitorService().get();
 *          monitorList.forEach(monitor-> System.out.println(monitor.toString());
 *      } finally {
 *          ComUtil.uninitialize();
 *      }
 * }
 * }</pre>
 */

package io.github.eggy03.pinetree;