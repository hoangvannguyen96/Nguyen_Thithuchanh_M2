package util;

import java.util.Scanner;

public class Util {
    public static Scanner scanner=new Scanner(System.in);
    public static String checkNumber() {
        while (true) {
            try {
                String number = scanner.nextLine();
                if (number != null && !number.trim().isEmpty() && number.matches("\\d*")) {
                    int number2 = Integer.parseInt(number);
                    if(number2>0){
                        return number;
                    }else {
                        System.out.println("Số nhập vào phải lớn hơn 0, vui lòng nhập lại!");
                    }
                } else {
                    System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
                }
            } catch (Exception e) {
                System.out.println("Bạn đã nhập sai định dạng, vui lòng nhập lại!");
            }
        }
    }
}
