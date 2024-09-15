package avto2;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CarDealership implements DealershipActions {
    List<Car> cars = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    List<Manager> managers = new ArrayList<>();
    Admin admin = new Admin();
    double revenue = 0;

    public CarDealership() {
        // Инициализация автомобилей
        cars.add(new Car("Toyota", "Красный", 5, 1500000));
        cars.add(new Car("Honda", "Синий", 3, 1200000));
        cars.add(new Car("BMW", "Черный", 2, 2500000));
        cars.add(new Car("Mercedes", "Белый", 4, 3000000));
        cars.add(new Car("Ford", "Зеленый", 6, 900000));
        cars.add(new Car("Chevrolet", "Желтый", 1, 1800000));
        cars.add(new Car("Nissan", "Серебристый", 3, 1700000));
        cars.add(new Car("Kia", "Красный", 2, 1300000));
        cars.add(new Car("Hyundai", "Синий", 4, 1400000));
        cars.add(new Car("Volkswagen", "Черный", 5, 1600000));

        // Инициализация пользователей
        users.add(new User("Иван", "Иванов"));
        users.add(new User("Петр", "Петров"));
        users.add(new User("Сидор", "Сидоров"));

        // Инициализация менеджеров
        managers.add(new Manager("Менеджер1", "Фамилия1", "001", "123"));
        managers.add(new Manager("Менеджер2", "Фамилия2", "002", "456"));
    }

    @Override
    public void showAvailableCars() {
        System.out.println("Доступные автомобили:");
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    @Override
    public void createOrder(User user) {
        Scanner scanner = new Scanner(System.in);
        showAvailableCars();

        System.out.print("Введите марку автомобиля: ");
        String brand = scanner.nextLine();

        System.out.print("Введите цвет автомобиля: ");
        String color = scanner.nextLine();

        for (Car car : cars) {
            if (car.brand.equalsIgnoreCase(brand) && car.color.equalsIgnoreCase(color) && car.quantity > 0) {
                if (user.bankAccount >= car.price) {
                    int orderNumber = orders.size() + 1; // номер заказа
                    orders.add(new Order(user, car, orderNumber));
                    car.quantity--;
                    user.bankAccount -= car.price;
                    revenue += car.price;
                    System.out.println("Заказ оформлен. Номер заказа: " + orderNumber);
                    return;
                } else {
                    System.out.println("Недостаточно средств на счете.");
                    return;
                }
            }
        }

        System.out.println("Автомобиль не найден.");
    }

    @Override
    public void acceptOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите пин-код менеджера: ");
        String pinCode = scanner.nextLine();

        for (Manager manager : managers) {
            if (manager.pinCode.equals(pinCode)) {
                System.out.println("Менеджер: " + manager.firstName + " " + manager.lastName);
                System.out.println("Список заказов:");

                for (Order order : orders) {
                    System.out.println("Номер заказа: " + order.orderNumber + ", Пользователь: " + order.user.firstName + " " + order.user.lastName + ", Автомобиль: " + order.car.brand + " " + order.car.color);
                }

                System.out.print("Введите номер заказа для одобрения: ");
                int orderNumber = scanner.nextInt();

                for (Order order : orders) {
                    if (order.orderNumber == orderNumber) {
                        System.out.println("Заказ " + orderNumber + " одобрен.");
                        return;
                    }
                }

                System.out.println("Заказ не найден.");
                return;
            }
        }

        System.out.println("Неверный пин-код.");
    }

    public void adminActions() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин администратора: ");
        String username = scanner.nextLine();

        System.out.print("Введите пароль администратора: ");
        String password = scanner.nextLine();

        if (username.equals(admin.username) && password.equals(admin.password)) {
            System.out.print("Введите номер заказа для удаления: ");
            int orderNumber = scanner.nextInt();

            for (Order order : orders) {
                if (order.orderNumber == orderNumber) {
                    orders.remove(order);
                    System.out.println("Заказ " + orderNumber + " удален.");
                    return;
                }
            }

            System.out.println("Заказ не найден.");
        } else {
            System.out.println("Неверные учетные данные администратора.");
        }
    }

}

