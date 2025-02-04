package com.prishedko.homework_2;

public class Main {

    public static void main(String[] args) {
        // Тест 1 - Добавление элементов
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        System.out.println("Тест 1 - Добавление элементов: " + list.size());

        // Тест 2 - Получение элементов
        System.out.println("Тест 2 - Получение элемента: " + list.get(1));

        // Тест 3 - Удаление элемента
        list.remove(1);
        System.out.println("Тест 3 - Удаление элемента, новый размер: " + list.size());

        // Тест 4 - Очистка списка
        list.clear();
        System.out.println("Тест 4 - Очистка списка, размер: " + list.size());

        // Тест 5 - Сортировка
        MyArrayList<Integer> list2 = new MyArrayList<>(Integer::compareTo);
        list2.add(50);
        list2.add(10);
        list2.add(30);
        list2.sort();
        System.out.println("Тест 5 - Сортировка: " + list2.get(0) + " " + list2.get(1) + " " + list2.get(2));

        // Тест 6 - Добавление элемента по индексу
        list2.add(1, 40);
        System.out.println("Тест 6 - Добавление по индексу: " + list2.get(1));

        // Тест 7 - Обработка исключения (выход за границы массива)
        try {
            list2.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Тест 7 - Ожидаемое исключение: " + e.getMessage());
        }
    }
}

