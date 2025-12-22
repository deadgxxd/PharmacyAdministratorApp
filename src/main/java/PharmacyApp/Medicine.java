package PharmacyApp;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Integer id;

    @Column(name = "medicine_name", nullable = false)
    private String name;

    @Column(name = "medicine_quantity")
    private Integer quantity;

    @Column(name = "medicine_cost")
    private Float cost;


    @ManyToMany(mappedBy = "treatments", fetch = FetchType.EAGER)
    private List<Disease> diseases = new ArrayList<>();

    public Medicine() {}

    public Medicine(String name, Integer quantity, Float cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Integer getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Float getCost() { return cost; }
    public void setCost(Float cost) { this.cost = cost; }


    public List<Disease> getDiseases() { return diseases; }

    public void addDisease(Disease d) { diseases.add(d); }

    public void removeDisease(Disease d) { diseases.remove(d); }

    @Override
    public String toString() {
    	/*
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
        */
    	return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine)) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
