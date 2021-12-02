import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int dayCounter = 0;

        Random random = new Random();
        int minClientWaterAmount = 10;
        int maxClientWaterAmount = 20;

        int one = 1;
        int two = 2;
        int three = 3;

        int minPlantWaterAmount = 0;
        int maxPlantWaterAmount = 100;

        int coldWeather = 5;
        int hotWeather = 10;

        int plantTemperatureCold = 5;
        int plantTemperatureHot = 15;

        int humidity= 50;

        while(true) {
            System.out.println("#---------------#");
            dayCounter++;

            int clientWaterAmount = random.nextInt(maxClientWaterAmount - minClientWaterAmount + 1) + minClientWaterAmount;
            int randomClientWaterPlant = random.nextInt(two - one + 1) + one;
            int plantTemperature = random.nextInt(plantTemperatureHot - plantTemperatureCold + 1) + plantTemperatureCold;
            int plantWeather = random.nextInt(hotWeather - coldWeather + 1) + coldWeather;
            int randomChance = random.nextInt(three - one + 1) + one;

            switch(randomChance) {
                case 1:
                    humidity-= plantTemperature;
                    break;
                case 2:
                    humidity-= plantWeather;
                    break;
                default:
                    humidity--;
            }

            if (humidity> 85) {
                humidity-= randomChance;
            }

            if (humidity<= 40) {
                if (randomClientWaterPlant == 2) {
                    humidity= 40;
                    humidity+= clientWaterAmount;
                    System.out.println("Watering plant with " + clientWaterAmount + "DL of water");
                } else {
                    humidity--;
                    System.out.println("Did not water the plant");
                }
            }

            if (humidity< minPlantWaterAmount) {
                System.out.println("The plant is dead");
                break;
            }

            if (humidity== maxPlantWaterAmount) {
                humidity-= coldWeather + plantTemperatureHot;
            }


            System.out.println("Day: " + dayCounter);
            System.out.println("Humidity: " + humidity+"%");
            System.out.println("#---------------#\n");
            Thread.sleep(400);
        }
    }
}
