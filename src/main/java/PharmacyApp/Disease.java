package PharmacyApp;
import java.util.List;

public class Disease {
	
    private String name;
    private List<Medicine> treatment;
    private String ICD_id;


    public void SetName(String _name) {

    }

    public String GetName() {
        return name;
    }

    public void ChangeICD(String _id) {

    }

    public String GetICD() {
        return ICD_id;
    }


    public void AddTreatment(Medicine medicine) {

    }

    public void RemoveTreatment(Medicine medicine) {

    }

    public List<Medicine> GetTreatments() {
        return treatment;
    }

    public void RemoveAllTreatments() {

    }
}
