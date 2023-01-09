REST API Consumer and Provider applications for weather sensor;
SPRING Framework, PostgreSQL;

**Before running app create sensor_db database and add tables from sensor.sql**

**json format for POST request sensors/registration:**
_{
"name": "***"
}_

**json format for POST request measurements/add:**
_{
"value": "91",
"raining": true,
"sensor": {
"name": "SensorTest"
}
}_




