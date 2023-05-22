package productservice;

import model.Product;
import util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ProductService {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Product> productList=new ArrayList<>();
    public static final String path = "E:\\Nguyen_Thithuchanh_M2\\ProductManager\\src\\main\\java\\data\\products.csv";

    public static void write(List<Product> products) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Product product : products) {
            bufferedWriter.write(product.toString() + "\n");
        }
        bufferedWriter.close();
        fileWriter.close();
    }
    public static List<Product> readProducts() {

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] items = line.split(",");
                long idProduct = Long.parseLong(items[0]);
                float price = Float.parseFloat(items[2]);
                int quantity = Integer.parseInt(items[3]);
                Product product = new Product(idProduct, items[1], price, quantity, items[4]);
                productList.add(product);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return productList;
    }

    public static void showProducts(List<Product> allProducts) {
        System.out.printf("%-15s %-20s %-10s %-10s %-20s\n", "ID", "Tên SP", "Giá SP", "Số lượng", "Mô tả");
        for (Product product : allProducts) {
            System.out.printf("%-15s %-20s %-10s %-10s %-20s\n", product.getIdProduct(), product.getNameProduct(), product.getPrice(),
                    product.getQuantity(), product.getDescription());
        }
    }
    public static void showProduct(Product product) {
        System.out.printf("%-15s %-20s %-10s %-10s %-20s\n", "ID", "Tên SP", "Giá SP", "Số lượng", "Mô tả");
            System.out.printf("%-15s %-20s %-10s %-10s %-20s\n", product.getIdProduct(), product.getNameProduct(), product.getPrice(),
                    product.getQuantity(), product.getDescription());
        }

    public static Product addProduct() {
        System.out.println("Nhập tên sản phẩm?");
        String nameProduct = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm?");
        float price = Float.parseFloat(Util.checkNumber());
        System.out.println("Nhập số lượng?");
        int quantity = Integer.parseInt(Util.checkNumber());
        System.out.println("Nhập mô tả?");
        String description = scanner.nextLine();
        Product product = new Product(System.currentTimeMillis() / 1000, nameProduct, price, quantity, description);
        productList.add(product);
        return product;
    }

    public static List<Product> sortProductCost() throws IOException {
        productList.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        return productList;
    }

    public static Product findProductByID(long ID, List<Product> products) {
        for (Product product : products) {
            if (product.getIdProduct() == ID) {
                return product;
            }
        }
        return null;
    }

    public static List<Product> fixProduct() {
        ProductService.showProducts(productList);
        System.out.println("Nhập ID cần sửa?");
        long ID = Long.parseLong(Util.checkNumber());
        Product product = findProductByID(ID, productList);
        boolean check = false;
        while (!check) {
            if (product == null) {
                System.out.println("ID không có trong danh sách, hãy thử với ID khác");
                long ID1 = Long.parseLong(scanner.nextLine());
                product = findProductByID(ID1, productList);
                ID = ID1;
            } else {
                System.out.println("Đây là sản phẩm bạn muốn sửa");
                System.out.println(product.toString());
                boolean actionFix = false;
                while (!actionFix) {
                    System.out.println("Bạn muốn sửa gì?");
                    System.out.println("1. Sửa mã sản phẩm");
                    System.out.println("2. Sửa tên sản phẩm");
                    System.out.println("3. Sửa giá sản phẩm");
                    System.out.println("4. Sửa số lượng sản phẩm");
                    System.out.println("5. Sửa mô tả");
                    System.out.println("0. Thoát");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập ID muốn sửa");
                            long ID1 = Long.parseLong(Util.checkNumber());
                            product.setIdProduct(ID1);
                            productList.add(product);
                            break;
                        case 2:
                            System.out.println("Nhập tên cần sửa?");
                            String name = scanner.nextLine();
                            product.setNameProduct(name);
                            productList.add(product);
                            break;
                        case 3:
                            System.out.println("Nhập giá cần sửa?");
                            float price = Float.parseFloat(Util.checkNumber());
                            product.setPrice(price);
                            productList.add(product);
                            break;
                        case 4:
                            System.out.println("Nhập số lượng cần sửa");
                            int quantity = Integer.parseInt(Util.checkNumber());
                            product.setQuantity(quantity);
                            productList.add(product);
                            break;
                        case 5:
                            System.out.println("Nhập mô tả muốn sửa?");
                            String description = scanner.nextLine();
                            product.setDescription(description);
                            productList.add(product);
                            break;
                        case 0:
                            actionFix = true;
                            break;
                        default:
                            System.out.println("Lụa chọn của bạn không có, vui lòng chọn lại!");
                    }
                    check=true;
                }
            }
        }
        return productList;
    }

    public static List<Product> clearProduct(long ID) {
        Product product = findProductByID(ID, productList);
        List<Product> products1 = new ArrayList<>();
        boolean check = false;
        while (!check) {
            if (product == null) {
                System.out.println("ID không có trong danh sách, hãy thử với ID khác");
                long ID1 = Long.parseLong(Util.checkNumber());
                product = findProductByID(ID, productList);
                ID = ID1;
            } else {
                for (Product product1 : productList) {
                    if (product1.getIdProduct() == ID) {
                        products1.add(product);
                        break;
                    }
                }
                boolean actionClear = false;
                while (!actionClear) {
                    System.out.println("Bạn có muốn xóa không?");
                    System.out.println("Y. Đồng ý");
                    String choice = scanner.nextLine();
                    switch (choice.toUpperCase()) {
                        case "Y":
                            productList.removeAll(products1);
                            actionClear=true;
                            break;
                        default:
                            actionClear = true;
                            break;
                    }
                }
                check = true;
            }
        }
        return productList;
    }
}
