package com.prishedko.homework_2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MyArrayList - реализация списка на основе массива.
 *
 * @param <T> Тип данных, которые хранятся в массиве
 */
public class MyArrayList<T> {

    /**
     * Первоначальный размер массив
     */
    private static final int DEFAULT_ARRAY_SIZE = 16;

    /**
     * Кэф. увеличения массива при пересечении порога DEFAULT_ARRAY_SIZE
     */
    public static final int SCALE_FACTOR = 2;

    private Object[] elements;
    private int size = 0;
    private Comparator<T> comparator;

    /**
     * Конструктор - дефолтная инициализация списка.
     */
    public MyArrayList() {
        elements = new Object[DEFAULT_ARRAY_SIZE];
    }

    /**
     * Конструктор с компаратором.
     *
     * @param comparator компаратор для сортировки
     */
    public MyArrayList(Comparator<T> comparator) {
        this();
        this.comparator = comparator;
    }

    /**
     * Устанавливает компаратор для сортировки (если не был задан в конструкторе или необходимо его заменить).
     *
     * @param comparator новый компаратор
     */
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    public void add(T element) {
        if (size == elements.length) {
            scaleArray();
        }
        elements[size++] = element;
    }

    /**
     * Добавляет элемент в список по индексу.
     *
     * @param index   индекс, куда вставить элемент
     * @param element элемент для вставки
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Указанный Индекс выходит за допустимые границы списка, index:  " + index);
        }

        if (size == elements.length) {
            scaleArray();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Получает элемент по индексу.
     *
     * @param index индекс элемента
     * @return элемент, расположенный по указанному индексу
     * @throws IndexOutOfBoundsException - индекс выходит за границы списка
     */
    public T get(int index) {
        checkIndexBounds(index);
        return (T) elements[index];
    }

    /**
     * Удаляет элемент по индексу.
     *
     * @param index индекс элемента
     * @throws IndexOutOfBoundsException - индекс выходит за границы списка
     */
    public void remove(int index) {
        checkIndexBounds(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        elements[size] = null;
        size--;
    }

    /**
     * Очищает список.
     */
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    /**
     * Сортирует список, используя переданный компаратор.
     *
     * @param comparator компаратор для сортировки
     */
    public void sort(Comparator<T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Сортирует список (компаратор уже передан)
     */
    public void sort() {
        if (comparator == null) {
            throw new IllegalStateException("Компаратор не был установлен");
        }
        quickSort(0, size - 1, comparator);
    }

    /**
     * Получает текущий размер списка.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Выполняет быструю сортировку массива.
     *
     * @param low        начальный индекс
     * @param high       конечный индекс
     * @param comparator компаратор для сортировки
     */
    private void quickSort(int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Разделяет массив на две части относительно опорного элемента.
     *
     * @param low        начальный индекс
     * @param high       конечный индекс
     * @param comparator компаратор для сортировки
     * @return индекс опорного элемента
     */
    private int partition(int low, int high, Comparator<T> comparator) {
        T pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Меняет местами два элемента в массиве.
     *
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private void swap(int i, int j) {
        T temp = get(i);
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Выделяем новую память, под увеличенный массив размером увеличенным на SCALE_FACTOR
     */
    private void scaleArray() {
        elements = Arrays.copyOf(elements, elements.length * SCALE_FACTOR);
    }

    /**
     * Проверяет не вышел ли индекс за переделы списка
     *
     * @param index - индекс элемента
     */
    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс выходит за границы списка: " + index);
        }
    }
}








