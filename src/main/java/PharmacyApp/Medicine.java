package PharmacyApp;

import jakarta.persistence.*;


@Entity
@Table(name = "medicine", schema = "test")
public class Medicine {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
	private Integer id;
    
    @Column(name = "medicine_name")
    private String name;
    
    @Column(name = "medicine_quantity")
    private Integer quantity;
    
    @Column(name = "medicine_cost")
    private Float cost;
    
    
    //private List<Disease> treats;

    
    public Integer getId() { return id; }
    public void setIdm(Integer _id) { this.id = _id; }
    public String getName() { return name; }
    public void setName(String _name) { this.name = _name; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer _quantity) { this.quantity = _quantity; }
    public Float getCost() { return cost; }
    public void setCost(Float _cost) { this.cost = _cost; }


    //public void AddDisease(Disease disease) {}

    //public void RemoveDisease(Disease disease) {}

    //public List<Disease> GetTreats() {return treats;}

    //public void RemoveAllDiseases() {}
}
