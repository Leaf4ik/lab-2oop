import java.util.*;

public class PermutationGroupFinder implements PermutationProcessor {
    private static PermutationGroupFinder instance;
    private int groupSize;

    // Приватный конструктор для синглтона
    private PermutationGroupFinder() {}

    // Получение единственного экземпляра
    public static PermutationGroupFinder getInstance() {
        if (instance == null) {
            instance = new PermutationGroupFinder();
        }
        return instance;
    }

    // Сеттер для размера группы анаграмм
    public void setGroupSize(int size) {
        this.groupSize = size;
    }

    // Геттер для проверки
    public int getGroupSize() {
        return this.groupSize;
    }

    // Операция 1: поиск групп из groupSize слов-перестановок
    @Override
    public void findPermutationGroups(String text) {
        String[] words = text.replaceAll("[\\p{Punct}]+", "").split("\\s+");
        Map<String, List<String>> map = new HashMap<>();
        for (String w : words) {
            String key = sortChars(w.toLowerCase());
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(w);
        }

        boolean found = false;
        for (List<String> group : map.values()) {
            if (group.size() >= groupSize) {
                List<List<String>> combos = new ArrayList<>();
                combine(group, groupSize, 0, new ArrayList<>(), combos);
                for (List<String> combo : combos) {
                    System.out.println("Найдена группа анаграмм: " + combo);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Подходящие группы анаграмм не найдены.");
        }
    }

    // Сортировка символов слова для формирования ключа
    private String sortChars(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    // Рекурсивный метод для формирования комбинаций
    private void combine(List<String> arr, int k, int start, List<String> current, List<List<String>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < arr.size(); i++) {
            current.add(arr.get(i));
            combine(arr, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
