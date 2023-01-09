REST API Consumer and Provider applications for weather sensor;
SPRING Framework, PostgreSQL;

**Before running app create sensor_db database and add tables from sensor.sql**

**to register new sensor - POST request sensors/registration:**
json format:
_{
"name": "***"
}_

**to register new weather measurement - POST request measurements/add:**
json format:
_{
"value": "91",
"raining": true,
"sensor": {
"name": "SensorTest"
}
}_

**to get all measurements - GET measurements**

**to get amount of rainy days - GET /measurements/rainyDaysCount**




