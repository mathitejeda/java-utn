package entidad;

public enum TipoMovimiento {
	ALTA_CUENTA(1, "Alta de cuenta"), ALTA_PRESTAMO(2, "Alta de prestamo"), PAGO_PRESTAMO(3, "Pago de prestamo"), TRANSFERENCIA(4, "Transferencia");
	
	private final int idTipoMovimiento;
	private final String descripcion;
	
	TipoMovimiento(int idTipoMovimiento, String descripcion){ 
		this.idTipoMovimiento = idTipoMovimiento; 
		this.descripcion = descripcion;
	}

	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}

	public String getDescripcion() {
		return descripcion;
	}		
}