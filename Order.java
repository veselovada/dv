package avto2;


class Order {
    User user;
    Car car;
    int orderNumber;

    public Order(User user, Car car, int orderNumber) {
        this.user = user;
        this.car = car;
        this.orderNumber = orderNumber;
    }
}