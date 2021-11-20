import time
from ISStreamer.Streamer import Streamer
from board import SCL, SDA
import busio
from adafruit_seesaw.seesaw import Seesaw
import mysql.connector

# --------- User Settings ---------
SENSOR_LOCATION_NAME = "House Plant"
BUCKET_NAME = "House Plant"
BUCKET_KEY = "soilsensor"
ACCESS_KEY = "ist_8u0aHz7qqYO-w8HJaUDyLPIGWOyTcp3p"
MINUTES_BETWEEN_READS = 0.25
# ---------------------------------

# ------- Database Connection ----
database = mysql.connector.connect("localhost", "plantsiodb", "root")
cursor = connection.cursor(prepared=True)
sql_insert_query = "INSERT INTO plantsIOTable(plant_moisture, plant_temp) VALUES(%s,%S)"
# ---------------------------------

# ------- InitialState+Sensors ----
i2c_bus = busio.I2C(SCL, SDA)
ss = Seesaw(i2c_bus, addr=0x36)
streamer = Streamer(bucket_name=BUCKET_NAME, bucket_key=BUCKET_KEY, access_key=ACCESS_KEY)

while True:
    # read moisture level through capacitive touch pad
    touch = ss.moisture_read()

    # read temperature from the temperature sensor and convert to F
    temp = ss.get_temp()
    temp_f = format(temp * 9.0 / 5.0 + 32, ".2f")

    streamer.log(SENSOR_LOCATION_NAME + " Moisture", touch)
    streamer.log(SENSOR_LOCATION_NAME + " Temperature", temp_f)
    streamer.flush()

    sql_touple = (ss.moisture_read, ss.get_temp)
    cursor.execute(sql_insert_query, sql_touple)
    connection.commit()

    except mysql.connector.Error as error:
        print("Error message {} ".format(error))
    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()
            print("MySQL connection is closed")

    time.sleep(60*MINUTES_BETWEEN_READS)
# -------------------------------