package Entities;
public interface Plan {
	public double getTarifaPorMinuto(CDR cdr);
	public String getNombre();
	public void setTarifa(Tarifa tarifa);
}
