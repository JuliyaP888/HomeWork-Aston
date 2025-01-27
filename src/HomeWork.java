import java.util.Arrays;
import java.util.stream.IntStream;

public class HomeWork {

    public static void main(String[] args) {
        String string = "I love Java";
        turnString(string);

        int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
        getDistinctNumbers(ints);

        int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
        findSecondMaxElement(arr);

        var str = "    fly me    to the moon   ";
        System.out.println(lengthOfLastWord(str));

        String palindrome = "112211";
        System.out.println(isPalindrome(palindrome));

    }

    // Перевернуть строку и вывести на консоль
    //  String string = "I love Java";
    public static void turnString(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.reverse();
        String reversed = stringBuilder.toString();
        System.out.println("Строка " + string);
        System.out.println("Перевернутая строка " + reversed);
    }

    // int[] ints = {1, 2, 2, 3, 4, 5, 5, 6, 7, 8, 8, 9};
    // Удалить дубликаты из массива и вывести в консоль
    public static void getDistinctNumbers(int[] ints) {
        int[] result = IntStream.of(ints)
                .distinct()
                .toArray();
        System.out.println("Массив без дубликатов " + Arrays.toString(result));
    }

    // Дан массив, заполненный уникальными значениями типа int.
    // int[] arr = {10, 15, 23, 11, 44, 13, 66, 1, 6, 47};
    // Необходимо найти элемент, который меньше максимума, но больше всех остальных.
    public static Integer findSecondMaxElement(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num < max) {
                secondMax = num;
            }
        }

        return secondMax == Integer.MIN_VALUE ? null : secondMax;
    }

    // Найти длину последнего слова в строке. В строке только буквы и пробелы.
    // "Hello world" - 5
    // "    fly me    to the moon   " - 4
    public static Integer lengthOfLastWord(String string) {
        string = string.trim();
        var chars = string.toCharArray();

        int i = chars.length - 1;
        int lastWordLength = 0;

        while (i >= 0 && chars[i] != ' ') {
            lastWordLength++;
            i--;
        }

        return lastWordLength;
    }


    // Определить, что строка является палиндромом
    // Сложность по памяти O(1), не создавать новые String, StringBuilder
    // Примеры:
    // abc - false
    // 112233 - false
    // aba - true
    // 112211 - true
    public static boolean isPalindrome(String string) {
        int n = string.length();
        int leftIndex = 0;
        int rightIndex = n - 1;

        // Перебор строки с двух концов
        while (leftIndex < rightIndex) {
            if (string.charAt(leftIndex) != string.charAt(rightIndex)) {
                return false;
            }
            leftIndex++;
            rightIndex--;
        }

        return true;
    }

}
