package com.api.entitys;



//@Entity
//@Table(name="duenios")
public class DuenioEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="id")
	private int id;
	
//	@Column (name="identificador")
	private int unidad;

//	@Column(name="documento")
	private String duenio;
	
	public DuenioEntity() {
		
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getUnidad() {
		return this.unidad; 
	}
	
	public String toString() {
		return new String(id + " " + unidad + " " + duenio.toString());
	}
}
