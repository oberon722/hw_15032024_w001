// Создаем базовый класс ГорячийНапиток
class HotBeverage {
    protected String name;
    protected int volume;

    public HotBeverage(String name, int volume) {
        this.name = name;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }
}

// Создаем класс наследник с дополнительным полем температура
class HotDrink extends HotBeverage {
    private final int temperature;

    public HotDrink(String name, int volume, int temperature) {
        super(name, volume);
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }
}

// Создаем интерфейс ТорговыйАвтомат
interface VendingMachine {
    HotBeverage getProduct(String name, int volume, int temperature);
}

// Создаем класс ГорячихНапитковАвтомат, реализующий интерфейс ТорговыйАвтомат
class HotBeverageMachine implements VendingMachine {
    private final HotBeverage[] beverages;

    public HotBeverageMachine(HotBeverage[] beverages) {
        this.beverages = beverages;
    }

    @Override
    public HotBeverage getProduct(String name, int volume, int temperature) {
        for (HotBeverage beverage : beverages) {
            if (beverage instanceof HotDrink hotDrink && hotDrink.getName().equals(name) && hotDrink.getVolume() == volume && hotDrink.getTemperature() == temperature) {
                return hotDrink;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем несколько объектов Горячих напитков
        HotBeverage tea = new HotDrink("Tea", 250, 70);
        HotBeverage coffee = new HotDrink("Coffee", 300, 80);

        // Инициализируем Горячий Напиток автомат
        HotBeverage[] beverages = {tea, coffee};
        HotBeverageMachine machine = new HotBeverageMachine(beverages);

        // Проверяем логику автомата
        HotBeverage beverage = machine.getProduct("Tea", 250, 70);
        if (beverage != null) {
            System.out.println("Выдан напиток: " + beverage.getName() + ", объем: " + beverage.getVolume() + ", температура: " + ((HotDrink) beverage).getTemperature());
        } else {
            System.out.println("Напиток не найден");
        }
    }
}