package entidad;

import java.util.Date;

public class PagoPrestamo {
	private int idPago;
	private int idPrestamo;
	private Date fechaPago;
	private int nroPago;

	public PagoPrestamo() {
		super();
	}

	public PagoPrestamo(int idPago, int idPrestamo, Date fechaPago, int nroPago) {
		super();
		this.idPago = idPago;
		this.idPrestamo = idPrestamo;
		this.fechaPago = fechaPago;
		this.nroPago = nroPago;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public int getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public int getNroPago() {
		return nroPago;
	}

	public void setNroPago(int nroPago) {
		this.nroPago = nroPago;
	}

	@Override
	public String toString() {
		return "PagoPrestamo [idPago=" + idPago + ", idPrestamo=" + idPrestamo + ", fechaPago=" + fechaPago
				+ ", nroPago=" + nroPago + "]";
	}
}
