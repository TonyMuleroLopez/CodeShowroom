package ccuentaFicheros;

public class CCuentaTest 
{

	public static void main(String[] args) 
	{
		CCuentaCorrienteConIn C1 = new CCuentaCorrienteConIn ("Lorenzo1", "1234", 1.5, 1800, 2, 0);
		System.out.println(C1.getNombre());
		System.out.println(C1.getSaldo());
		C1.ingreso(50);
		C1.ingreso(50);
		C1.intereses();
		C1.comisiones();
		System.out.println(C1.getInteres());
		System.out.println(C1.getSaldo());
		
		CCuentaCorriente C2 = new CCuentaCorriente ("Lorenzo2", "1234", 0.015, 1800, 2, 0);
		System.out.println();
		System.out.println(C2.getNombre());
		System.out.println(C2.getSaldo());
		C2.ingreso(50);
		C2.ingreso(50);
		C2.intereses();
		C2.comisiones();
		System.out.println(C2.getInteres());
		System.out.println(C2.getSaldo());
		
		CCuentaAhorro C3 = new CCuentaAhorro ("Lorenzo3", "1234", 0.005, 1500, 50);
		System.out.println();
		System.out.println(C3.getNombre());
		System.out.println(C3.getSaldo());
		C3.ingreso(50);
		C3.ingreso(50);
		C3.intereses();
		C3.comisiones();
		System.out.println(C3.getInteres());
		System.out.println(C3.getSaldo());
		System.out.println(C3.getCuotaMantenimiento());
		
	}
}
