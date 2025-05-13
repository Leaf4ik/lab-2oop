import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Первый экземпляр
        PermutationGroupFinder finder1 = PermutationGroupFinder.getInstance();
        System.out.print("Введите размер группы анаграмм (количество слов): ");
        int size = Integer.parseInt(in.nextLine());
        finder1.setGroupSize(size);

        System.out.print("Введите текст: ");
        String text = in.nextLine();
        System.out.println();
        finder1.findPermutationGroups(text);

        System.out.println("\nРазмер группы первого экземпляра: " + finder1.getGroupSize());

        // Второй «экземпляр» (синглтон, без установки размера)
        PermutationGroupFinder finder2 = PermutationGroupFinder.getInstance();
        System.out.println("Размер группы второго экземпляра (не установлен): " + finder2.getGroupSize());

        in.close();
    }
}
