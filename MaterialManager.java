
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MaterialManager {
    private List<Material> materials = new ArrayList<>();

    // khởi tạo 5 đối tượng thịt và bột chiên dòn
    public void createSampleMaterials() {
        materials.add(new CrispyFlour("CF1", "Bột A", LocalDate.now(), 5000, 2));
        materials.add(new CrispyFlour("CF2", "Bột B", LocalDate.now(), 6000, 3));
        materials.add(new CrispyFlour("CF3", "Bột C", LocalDate.now(), 7000, 1));
        materials.add(new CrispyFlour("CF4", "Bột D", LocalDate.now(), 8000, 5));
        materials.add(new CrispyFlour("CF5", "Bột E", LocalDate.now(), 9000, 4));

        materials.add(new Meat("M1", "Thịt Gà", LocalDate.now(), 20000, 2.5));
        materials.add(new Meat("M2", "Thịt Bò", LocalDate.now(), 25000, 1.5));
        materials.add(new Meat("M3", "Thịt Lợn", LocalDate.now(), 18000, 3.0));
        materials.add(new Meat("M4", "Thịt Vịt", LocalDate.now(), 15000, 2.0));
        materials.add(new Meat("M5", "Thịt Cá", LocalDate.now(), 22000, 1.8));
    }

    // thêm vật liệu mới
    public void addMaterial(Material material) {
        materials.add(material);
    }

    // xóa vật liệu
  public void removeMaterial(String id){
        materials.removeIf(material -> material.getId().equals(id));
  }


    // cập nhập thông tin vật liệu
    boolean isUpdated = false;
    public void updataMaterial(String id, String newName, LocalDate newExpiryDate, int newCost){
        for (Material material : materials) {
            if (material.getId().equals(id)) {
                if (newName != null){
                    material.setName(newName);
                }
                if (newExpiryDate != null){
                    material.setManufacturingDate(newExpiryDate);
                }
                if (newCost > 0){
                    material.setCost(newCost);
                }

                isUpdated = true;

                break;
            }
        }

        if (!isUpdated){
            System.out.println("Material with ID " + id + " not found.");
        }
    }

    // Tính tổng số tiền sau chiết khấu
    public double getTotalDiscountedAmount(){
        double total = 0;
        for (Material material : materials) {
            if (material instanceof Discount){
                total += ((Discount) material).getRealMoney();
            }
        }
        return total;
    }

    //Tính tổng số tiền
    public double getTotalAmount(){
        double total = 0;
        for (Material material : materials) {
            total += material.getAmount();
        }
        return total;

    }


    // sắp xếp theo chi phí int xắp xếp giảm dần
    public void sortByCost(){
        materials.sort((m1, m2) -> Integer.compare(m2.getCost(), m1.getCost()));

    }

    // hiển hị giao diện materials
    public void displayMaterials() {
        for (Material material : materials) {
            System.out.println(material.getId() + " - " + material.getName() + " - "
                    + material.getAmount() + " - Expiry: " + material.getExpiryDate());
        }
    }


    public static void main(String[] args) {
        MaterialManager manager = new MaterialManager();

        // khởi tạo 5 đối tượng
        manager.createSampleMaterials();
        System.out.println("Danh sách vật liệu ban đầu:");
        manager.displayMaterials();


        //  thêm vật liệu
        Material newMaterial = new CrispyFlour("CF6", "Bột F", LocalDate.now(), 10000, 2);
        manager.addMaterial(newMaterial);
        System.out.println("Danh sách vật liệu sau khi thêm:");
        manager.displayMaterials();

        // cập nhập thông tin
        manager.updataMaterial("CF1", "Bột G", LocalDate.now().plusDays(10), 5500);
        System.out.println("Danh sách vật liệu sau khi sửa:");
        manager.displayMaterials();

        // xóa vật liệu
        manager.removeMaterial("M1");
        System.out.println("Danh sách vật liệu sau khi xóa:");
        manager.displayMaterials();

        // tổng tiền chưa chiết khấu
        double totalAmount = manager.getTotalAmount();
        System.out.println("Tổng số tiền chưa chiết khấu: " + totalAmount);

        // tổng tiền sau chiết khấu
        double totalDiscountedAmount = manager.getTotalDiscountedAmount();
        System.out.println("Tổng số tiền sau chiết khấu: " + totalDiscountedAmount);

        // sắp xếp theo giảm dần
        manager.sortByCost();
        System.out.println("Danh sách vật liệu sau khi sắp xếp theo giá (giảm dần):");
        manager.displayMaterials();

    }

}
