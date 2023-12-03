package entidad;

import java.math.BigDecimal;
import java.sql.Date;

public class Cuenta {
	private String cbu;
	private Cliente cliente;
	private int nroCuenta;
	private int idTipoCuenta;
	private TipoCuenta tipoCuenta;
	private Date fechaAlta;
	private BigDecimal saldo; 
	private boolean estado;	
	
	public Cuenta() {
		this.idTipoCuenta = 0;
		this.saldo = BigDecimal.ZERO;
	}
	
	public Cuenta(String cbu, Cliente cliente, int nroCuenta, int idTipoCuenta, TipoCuenta tipoCuenta, Date fechaAlta, BigDecimal saldo,
			boolean estado) {
		super();
		this.cbu = cbu;
		this.cliente = cliente;
		this.nroCuenta = nroCuenta;
		this.idTipoCuenta = idTipoCuenta;
		this.tipoCuenta = tipoCuenta;
		this.fechaAlta = fechaAlta;
		this.saldo = saldo;
		this.estado = estado;
	}
	
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente usuario) {
		this.cliente = usuario;
	}
	public int getNroCuenta() {
		return nroCuenta;
	}
	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}	
	public int getIdTipoCuenta() {
		return idTipoCuenta;
	}
	public void setIdTipoCuenta(int idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date alta) {
		this.fechaAlta = alta;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [cbu=" + cbu + ", cliente=" + cliente + ", nroCuenta=" + nroCuenta + ", idTipoCuenta="
				+ idTipoCuenta + ", tipoCuenta=" + tipoCuenta + ", fechaAlta=" + fechaAlta + ", saldo=" + saldo
				+ ", estado=" + estado + "]";
	}	
}
