# IS-311 : Plants and sensors
![Python](https://img.shields.io/badge/python-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54) ![Raspberry Pi](https://img.shields.io/badge/-RaspberryPi-C51A4A?style=for-the-badge&logo=Raspberry-Pi) ![Made with love in Norway](https://madewithlove.now.sh/no?heart=true&template=for-the-badge&text=Norway)

Welcome to a small Raspberry Pi project that was part of the IS-311 course. This guide will show you step by step how to configure your Raspberry Pi to get data inside a IoT dashboard. There are some technical prerequisite before using this guide. Example: Know how to use terminal, know how to edit code and general knowledge about Raspberry Pi. 

*Note - This project used Raspberry Pi 3 instead of Zero, due to not having some of the parts necessary.*

Thanks to [InitialState](https://medium.com/initial-state/how-to-use-a-soil-moisture-sensor-to-keep-your-plants-alive-51a2294b88e) for guide.

| Libraries used                |
| ----------------------------- |
| adafruit-blinka               |
| adafruit-circuitpython-seesaw |
| adafruit-circuitpython-bh1750 |
| mysql connector               |
| ISStreamer                    |


| Components used                           |
| ----------------------------------------- |
| Raspberry Pi Zero WH OR Raspberry Pi 3    |
| Switching PowerSupply with 20AWG          |
| 16GB Card with NOOBS 3.1 for Raspberry Pi |
| Adafruit STEMMA Soil Sensor               |
| Adafruit BH1750 Light Sensor              |
| Qwiic JST SH 4 pin                        |
| JST PH 4 pin FEMALE                       |
| InitialState Subscription                 |

## Python setup
#### Updating Python
Check what Python version you have, if this says <3.9 follow the next 2 steps.

```
$ python3 --version
```

This general purpose command for updating apt (package manager for Ubuntu and Raspbian).

```
$ sudo apt-get update
```

This updates to the latest version of Python. Change the 3.9 if there is a newer version.

```
$ sudo apt-get install python3.9
```

Updates the package manager for Python (used for downloading libraries).

```
$ python3 -m pip install --upgrade pip
```

#### Installing libraries
Run these commands to install the libraries
```
$ sudo pip3 install adafruit-blinka

$ sudo pip3 install adafruit-circuitpython-seesaw

$ sudo pip3 install adafruit-circuitpython-bh1750

$ sudo pip3 install ISStreamer
```

## Raspberry Pi 3 setup
#### Setup the sensors
The figure down below shows how to connect the sensors. The left one is for moisture sensor and the right is for ambient light. The orientation is IO should be phasing down.

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211126113646](https://user-images.githubusercontent.com/60610137/144413873-417f27e1-9f8c-45d9-8969-9c2183a98487.png)


### Configure the to get the data
We need to access the I2C to get data from the sensors. The commands to do that are: 

```
$ sudo raspi-config
```

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211126114024](https://user-images.githubusercontent.com/60610137/144414093-3e0e24a0-1311-43dc-bc04-50e0fca38e92.png)

Select the fifth option `5 Interfacing Options` then `P5 I2C` and click `<YES>`. You have to reboot for the changes to take into effect.

To check if the setting is correct, you can write the following command:

```
$ sudo i2cdetect -y 1
```

This should prompt you to a screen that looks like this: 

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211126114243](https://user-images.githubusercontent.com/60610137/144414143-e1c29625-a18c-42f5-9020-54b0ced82186.png)

If it does not show up, try to reconfigure or re-seat the sensor connectors. If it shows `0X76 OR 0X77 OR 0X38` it is correct and you can continue. 

### Setup the database
This is needed if store the data locally. Use the following commands in the terminal:
```
sudo apt install mariadb-server
```

If you want to have an secure database, you need to install `mysql_secure_installation`. Answer `Y` to all the prompts. *This will make it harder to access the database.*
```
sudo mysql_secure_installation
```

After everything is installed, you can now login to the database with:
```
sudo mysql -u root -p
```

You can now run the SQL-script that is included in the repository (plantsiodb.sql).


## InitialState setup
This requires that you either buy InitialState or have 7-day trial available and created an account. When you are at the frontage of the app, you need to click in the top right and then click my settings. 

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211126114855](https://user-images.githubusercontent.com/60610137/144414176-044b4e7f-bdf4-465c-99bc-74bf721ba5f4.png)


This prompt you to create an `Access Keys` that should start with `ist-` and a lot of random characters. After configuring the script with your own "Bucket key" and running the script, it should pop-up a Bucket at the left menu. If you click that Bucket, it should look like this: 

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211126115619](https://user-images.githubusercontent.com/60610137/144414208-bcec8fd4-0a0c-463a-be67-b387bd3ad75b.png)


### Changing the appearance 
If you don't like the line, you could always change it to something more fun, such as: 

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211202122535](https://user-images.githubusercontent.com/60610137/144414298-7021263f-8204-4dd9-9a14-c9e4d50e025d.png)

This is done by right-clicking the *tile* and choosing edit tile. if you want it to look the same, go to *Tile Type* and change it to *Gauge Chart*, and then change the *Gauge Style* to *Liquid or Thermometer* Here is the settings used:
Moisture

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211126115835](https://user-images.githubusercontent.com/60610137/144414390-e6c9810e-8dea-4a44-b904-5f6d778cfe72.png)

Temperature

![C__Users_micha_My Drive_ObsidianMD_Pasted image 20211126115853](https://user-images.githubusercontent.com/60610137/144414407-0199fff1-dcd8-4867-9060-e4eee25c49be.png)





