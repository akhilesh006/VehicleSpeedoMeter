// IVehicleHAL.aidl
package com.app.eularmotor;
import com.app.eularmotor.IVehicleSpeedListener;

interface IVehicleHAL {
    void startVehicleService(IVehicleSpeedListener listener);
}