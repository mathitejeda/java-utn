package entidad;

public enum TipoCuenta {
	CAJA_AHORRO(1), CUENTA_CORRIENTE(2);
	
	private final int idTipoCuenta;
	
	TipoCuenta(int idTipoCuenta){ this.idTipoCuenta = idTipoCuenta; }

	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}
}