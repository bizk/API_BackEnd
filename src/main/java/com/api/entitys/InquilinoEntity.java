
package com.api.entitys;

//@Entity
//@Table(name="inquilinos")
public class InquilinoEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="id")
	private int id;

//	@JoinColumn(name="identificador")
	private int unidad;
	
//	@JoinColumn(name="documento")
	private String inquilino;

	public InquilinoEntity() {
		
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
	
}
