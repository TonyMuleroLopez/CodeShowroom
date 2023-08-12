package ccuentaFicheros;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CCuentaCorrienteConIn extends CCuentaCorriente
{

	public CCuentaCorrienteConIn(String nombre, String cuenta, double interes, double saldo, double importePorTrans,
			int transExentas) 
	{
		super(nombre, cuenta, interes, saldo, importePorTrans, transExentas);
	}
	/////////////////////////////////
	public void ingreso(double cantidad)
	{
		super.ingreso(cantidad);
	}
	////////////////////////////////
	public void reintegro(double cantidad)
	{
		super.reintegro(cantidad);
	}
	public void comisiones() 
	{
		super.comisiones();		
	}
	public double intereses() 
	{
		double cantidadIntereses = 0;

		GregorianCalendar fecha = new GregorianCalendar();
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		if(dia == 13) 
		{
			if(getSaldo() >= 1800)
			{
			cantidadIntereses = ((getSaldo()*getInteres())/12);
			ingreso(cantidadIntereses);
			transacciones--;
			}
		}
		return cantidadIntereses;
	}

}
