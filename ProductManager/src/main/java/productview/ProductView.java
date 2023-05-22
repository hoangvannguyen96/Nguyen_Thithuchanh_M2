package productview;

import model.Product;
import productservice.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    public static final String path = "E:\\Nguyen_Thithuchanh_M2\\ProductManager\\src\\main\\java\\data\\products.csv";
    public static Scanner scanner = new Scanner(System.in);

    public static void ProductView() throws IOException {
        boolean actionView = false;
        while (!actionView) {
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ SẢN PHẨM----");
            System.out.println("Chọn chức năng theo số (để tiếp tục)");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Sắp xếp");
            System.out.println("6. Tìm sản phẩm có giá đắt nhất");
            System.out.println("7. Đọc từ file");
            System.out.println("8. Ghi vào file");
            System.out.println("9. Thoát");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    ProductService.showProducts(ProductService.readProducts());
                    break;
                case 2:
                    Product product = ProductService.addProduct();
                    product.toString();
                    break;
                case 3:
                    ProductService.fixProduct();
                    ProductService.showProducts(ProductService.productList);
                    break;
                case 4:
                    ProductService.showProducts(ProductService.productList);
                    System.out.println("Nhập ID sản phẩm cần xóa?");
                    long ID = Long.parseLong(scanner.nextLine());
                    ProductService.clearProduct(ID);
                    break;
                case 5:
                    boolean actionSort = false;
                    while (!actionSort) {
                        System.out.println("1 or 2. Sắp sếp sản phẩm theo giá tăng dần");
                        System.out.println("3. Thoát");
                        int choice1 = Integer.parseInt(scanner.nextLine());
                        switch (choice1) {
                            case 1:
                                ProductService.showProducts(ProductService.sortProductCost());
                                break;
                            case 2:
                                ProductService.showProducts(ProductService.sortProductCost());
                                break;
                            case 3:
                                actionSort = true;
                                break;
                            default:
                                System.out.println("Lụa chọn của bạn không có, vui lòng chọn lại!");
                        }
                    }
                    break;
                case 6:
                    List<Product> list = ProductService.sortProductCost();
                    Product product1 = list.get(list.size() - 1);
                    System.out.println("Sản phẩm có giá đắt nhất là: \n");
                    ProductService.showProduct(product1);
                    break;
                case 7:
                    boolean actionRead = false;
                    while (!actionRead) {
                        System.out.println("Bạn có muốn cập nhật bộ nhớ không?");
                        System.out.println("Y. Đồng ý");
                        String choice1 = scanner.nextLine();
                        switch (choice1) {
                            case "Y":
                                ProductService.readProducts();
                                break;
                            default:
                                actionRead = true;
                        }
                    }
                    break;
                case 8:
                    boolean actionWrite = false;
                    while (!actionWrite) {
                        System.out.println("Bạn có muốn lưu vào bộ nhớ không?");
                        System.out.println("Y. Đồng ý");
                        String choice1 = scanner.nextLine();
                        switch (choice1) {
                            case "Y":
                                ProductService.write(ProductService.productList);
                                break;
                            default:
                                actionWrite = true;
                        }
                    }
                    break;
                case 9:
                    actionView = true;
                    break;
                default:
                    System.out.println("Lựa chọn của bạn không có, vui lòng chọn lại!");
            }
        }
    }
}
