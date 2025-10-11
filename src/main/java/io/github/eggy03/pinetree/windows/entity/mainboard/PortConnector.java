package io.github.eggy03.pinetree.windows.entity.mainboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Value;
import org.jetbrains.annotations.Nullable;

/**
 * Immutable representation of a motherboard port on a Windows system.
 * <p>
 * Fields correspond to properties retrieved from the {@code Win32_PortConnector} WMI class.
 * Values are captured at query time and do not update automatically.
 * <p>
 * Instances are inherently thread-safe and may be safely cached or shared across threads.
 * <p>
 *
 * <h2>Usage example</h2>
 * <pre>{@code
 * PortConnector port = MainboardPort.builder()
 *     .externalReferenceDesignator("USB3_0")
 *     .build();
 *
 * // Create a modified copy
 * PortConnector updated = port.toBuilder()
 *     .externalReferenceDesignator("USB3_1")
 *     .build();
 * }</pre>
 *
 * {@link Baseboard} contains the details of the motherboard this port belongs to.
 *
 * @see <a href="https://learn.microsoft.com/en-us/windows/win32/cimwin32prov/win32-portconnector">Win32_PortConnector</a>
 */

@Value
@Builder(toBuilder = true)
public class PortConnector {

    @SerializedName("Tag")
    @Nullable
    String tag;

    @SerializedName("ExternalReferenceDesignator")
    @Nullable
    String externalReferenceDesignator;

    @SerializedName("InternalReferenceDesignator")
    @Nullable
    String internalReferenceDesignator;

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}