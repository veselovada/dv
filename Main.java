package avto2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CarDealership dealership = new CarDealership();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Посмотреть доступные автомобили");
            System.out.println("2. Оформить заказ");
            System.out.println("3. Принять заказ");
            System.out.println("4. Выйти из программы");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    dealership.showAvailableCars();
                    break;

                case 2:
                    // Логика входа пользователя
                    System.out.print("Введите имя: ");
                    String firstName = scanner.next();
                    System.out.print("Введите фамилию: ");
                    String lastName = scanner.next();

                    boolean userFound = false;

                    for (User user : dealership.users) {
                        if (user.firstName.equals(firstName) && user.lastName.equals(lastName)) {
                            userFound = true;
                            dealership.createOrder(user);
                            break;
                        }
                    }

                    if (!userFound) {
                        System.out.println("Пользователь не найден. Хотите зарегистрироваться? (да/нет)");
                        String registerChoice = scanner.next();
                        if (registerChoice.equalsIgnoreCase("да")) {
                            dealership.users.add(new User(firstName, lastName));
                            System.out.println("Пользователь зарегистрирован.");
                        }
                    }
                    break;

                case 3:
                    dealership.acceptOrder();
                    break;

                case 4:
                    System.out.println("Общий доход автосалона: " + dealership.revenue + " руб.");
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
