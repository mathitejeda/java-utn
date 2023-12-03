package entidad;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

public class Prestamo {
	private int idPrestamo;
	private Cliente cliente;
	private String cbu;
	private Date fechaSolicitud;
	private BigDecimal montoCuota;
	private BigDecimal montoTotal;
	private int plazoPago;
	private boolean aprobado;
	private boolean pendiente;
	

	//agrego valores por defecto a los primitivos para poder validar si están vacíos
	public Prestamo() {
		this.plazoPago = 0;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public BigDecimal getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(BigDecimal montoCuota) {
		this.montoCuota = montoCuota;
	}
	public BigDecimal getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}
	public int getPlazoPago() {
		return plazoPago;
	}
	public void setPlazoPago(int plazoPago) {
		this.plazoPago = plazoPago;
	}
	public boolean isAprobado() {
		return aprobado;
	}
	public void setAprobado(boolean aprobado) {
		this.aprobado = aprobado;
	}

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}
	
    public String getMesAño() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaSolicitud);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return String.format("%04d-%02d", year, month);
    }
    
	@Override
	public String toString() {
		return "Prestamo [idPrestamo=" + idPrestamo + ", cliente=" + cliente + ", cbu=" + cbu + ", fechaSolicitud="
				+ fechaSolicitud + ", montoCuota=" + montoCuota + ", montoTotal=" + montoTotal + ", plazoPago="
				+ plazoPago + ", aprobado=" + aprobado + "]";
	}				
}
