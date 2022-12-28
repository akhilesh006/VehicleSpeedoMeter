# VehicleSpeedoMeter
## Problem Statement:
    This application is intended to receive the Vehicle's speed data every 500 milliseconds, The Application is responsible to
    render the real-time speed data on UI. The application is also required to upload the data on the backend server every 5 seconds.

## Solution Proposed:
The Application has created a service that broadcasts data every 500 milliseconds(I haven't used Local broadcast manager as it would be an external app)
    which is assumed a HAL service.
    The VehicleService class is responsible for this task and can be found under the service package.

The Application will receive the broadcast and set the current vehicle speed in SpeedManager class.
SpeedManager is responsible for posting the data on the live objects, which is being observed by UI which is the MainFragment of the application.
SpeedManager also adds data to the database, and later by a VehicleDataDispatcher class, it will be updated to the server.
If the network is available and data is sent to the server successfully then the data can be deleted from the database.
If data is not sent to the server for any reason then data will be kept in the database and the dispatcher will retry after 5 seconds.

VehicleService -> SpeedReceiver: Send broadcast event every 500ms.
SpeedReceiver -> SpeedManager:Post on MutableLiveData<SpeedModel> and Insert into SpeedRepository(database)

The following library is used in the project:
1. Retrofit - REST API calls
2. OKHTTP - Socket communication
3. Room - ORM for Database
4. Live data: Observer pattern
