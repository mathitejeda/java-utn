package entidad;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

public class Movimiento {
	private int idMovimiento;
	private Cuenta cuenta;
	private Date fechaOperacion;
	private String concepto;
	private BigDecimal importe;
	private TipoMovimiento tipoMovimiento;
	
	public Movimiento() {
		super();
	}
	
	public int getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public void setTipoMovimiento(int idTipoMovimiento) {		
		for(TipoMovimiento tipo : TipoMovimiento.values()){
			if(tipo.getIdTipoMovimiento() == idTipoMovimiento) {
				this.tipoMovimiento = tipo;
			}
		}
	}
		
    public String getMesAño() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaOperacion);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return String.format("%04d-%02d", year, month);
    }

	@Override
	public String toString() {
		return "Movimiento [idMovimiento=" + idMovimiento + ", cuenta=" + cuenta + ", fechaOperacion=" + fechaOperacion
				+ ", concepto=" + concepto + ", importe=" + importe + ", tipoMovimiento=" + tipoMovimiento + "]";
	}
}
