# VehicleSpeedoMeter
##Problem Statement:
    This application is intended to receive the Vehicle's speed data every 500 milliseconds, The Application is responsible to
    render the real time speed data on UI. Application is also require to upload the data on backend server every 5 seconds.

## Solution Proposed:
The Application has created an service which broadcasts data every 500 milliseconds(I haven't used Local broadcast manager as it would be external app)
    which is assumed as HAL service.
    VehicleService class is responsible for this task and can be found under service package.

The Application will receive the broadcast and set the current vehicle speed in SpeedManager class.
SpeedManager is responsible to post the data on live object, which is being observed by UI which is the MainFragment of the application.
SpeedManager also add data into database, and later by a VehicleDataDispatcher class, it will be updated to the server.
If network is available and data is sent to server successfully then the data can be deleted from the database.
If data is not sent to server by any reason then data will be kept in database and dispatcher will retry after 5 seconds.

VehicleService -> SpeedReceiver: Send broadcast event every 500ms.
SpeedReceiver -> SpeedManager:Post on MutableLiveData<SpeedModel> and Insert into SpeedRepository(database)
