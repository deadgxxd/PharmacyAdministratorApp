package PharmacyApp;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "disease")
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private Integer id;

    @Column(name = "disease_name", nullable = false)
    private String name;

    @Column(name = "icd_code")
    private String icdCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "disease_medicine",
        joinColumns = @JoinColumn(name = "disease_id"),
        inverseJoinColumns = @JoinColumn(name = "medicine_id")
    )
    private List<Medicine> treatments = new ArrayList<>();

    public Disease() {}

    public Disease(String name, String icdCode) {
        this.name = name;
        this.icdCode = icdCode;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIcdCode() { return icdCode; }
    public void setIcdCode(String icdCode) { this.icdCode = icdCode; }

    public List<Medicine> getTreatments() { return treatments; }
    public void addTreatment(Medicine m) { treatments.add(m); }
    public void removeTreatment(Medicine m) { treatments.remove(m); }
    @Override
    public String toString() {
    	return name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disease)) return false;
        Disease disease = (Disease) o;
        return Objects.equals(id, disease.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
