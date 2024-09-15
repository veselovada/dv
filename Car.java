package avto2;

class Car {
    String brand;
    String color;
    int quantity;
    double price;

    public Car(String brand, String color, int quantity, double price) {
        this.brand = brand;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return brand + " | Цвет: " + color + " | Кол-во: " + quantity + " | Цена: " + price + " руб.";
    }
}
