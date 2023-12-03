package entidad;

public enum Mes {
	ENERO(0, "Enero"), FEBRERO(1, "Febrero"), MARZO(2, "Marzo"), ABRIL(3, "Abril"), MAYO(4, "Mayo"), JUNIO(5, "Junio"), JULIO(6, "Julio"),
	AGOSTO(7, "Agosto"), SEPTIEMBRE(8, "Septiembre"), OCTUBRE(9, "Octubre"), NOVIEMBRE(10, "Noviembre"), DICIEMBRE(11, "Diciembre");
	
	private final int idMes;
	private final String nombre;
	
	Mes(int idMes, String nombre){ 
		this.idMes = idMes; 
		this.nombre = nombre;
	}

	public int getidMes() {
		return idMes;
	}

	public String getNombre() {
		return nombre;
	}		
}