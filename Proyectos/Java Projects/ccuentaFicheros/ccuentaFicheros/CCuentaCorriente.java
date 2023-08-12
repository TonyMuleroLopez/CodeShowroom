package ccuentaFicheros;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CCuentaCorriente extends CCuenta
{
	protected int transacciones;
	private int transExentas;
	private double importePorTrans;
	
	public CCuentaCorriente(String nombre, String cuenta, double interes, double saldo, double importePorTrans, int transExentas) 
	{
		super(nombre, cuenta, interes, saldo);
		this.transacciones = 0;
		this.transExentas = transExentas;
		this.importePorTrans = importePorTrans;
		
	}

	public int getTransacciones() 
	{
		return transacciones;
	}
	public int getTransExentas() 
	{
		return transExentas;
	}

	public void setTransExentas(int transExentas) 
	{
		this.transExentas = transExentas;
	}

	public double getImportePorTrans() 
	{
		return importePorTrans;
	}

	public void setImportePorTrans(double importePorTrans)
	{
		this.importePorTrans = importePorTrans;
	}
	//////////////////////////////////
	public void decrementarTransacciones()
	{
		transacciones = transacciones - 1;
	}
	/////////////////////////////////
	public void ingreso(double cantidad)
	{
		super.ingreso(cantidad);
		transacciones ++;
	}
	////////////////////////////////
	public void reintegro(double cantidad)
	{
		super.reintegro(cantidad);
		transacciones ++;
	}
	public void comisiones() 
	{
		GregorianCalendar fecha = new GregorianCalendar();
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		if(dia == 13) 
		{
			if(transExentas < transacciones)
			{
				reintegro(importePorTrans * (transacciones - transExentas));
			}
			transacciones = 0;
		}
	}

	public double intereses() 
	{
		double cantidadIntereses = 0;

		GregorianCalendar fecha = new GregorianCalendar();
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		if(dia == 13) 
		{
			if(getSaldo() <= 1800)
			{
				cantidadIntereses = ((getSaldo()* 0.005)/12);
			}
			else
			{
				cantidadIntereses = (((1800*0.005) + ((getSaldo()-1800)*getInteres()))/12);
			}
			
			ingreso(cantidadIntereses);
			transacciones --;
		}
		return cantidadIntereses;
	}

}
