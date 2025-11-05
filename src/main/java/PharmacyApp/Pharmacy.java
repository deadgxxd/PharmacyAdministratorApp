package PharmacyApp;

import java.util.List;

public class Pharmacy {
	
    private List<Medicine> medicines;


    public void AddMedicine(Medicine medicine) {

    }

    public void RemoveMedicine(Medicine medicine) {

    }

    public List<Medicine> GetMedicines() {
        return medicines;
    }


    public int GetSellsCount(Medicine medicine, String period) {

        return medicine.getQuantity();
    }
}
