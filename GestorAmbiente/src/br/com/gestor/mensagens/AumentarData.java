package br.com.gestor.mensagens;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AumentarData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
//		
//		Date dataAumentar = new Date(System.currentTimeMillis());
//		
//		
//		for(int i = 0; i < 3; i++){
//			
//			System.out.println(dataAumentar);
//		
//		}
//		
//		Calendar d = new GregorianCalendar();
//		d.setTime(dataAumentar);
//		
//		System.out.println("\n"+d);
//		
//		
//		Date a = new Date("2004/09/01");        
//		a.setDate(a.getDate() + 10);        
//		  
//		String formato = "dd/MM/yyyy";  
//		SimpleDateFormat dataFormatada = new SimpleDateFormat(formato);     
//		System.out.println("Daqui há dez dias: " + dataFormatada.format(a)); 
//
//		//funcionando aumentar data
//		Calendar calendar = Calendar.getInstance();  
//		calendar.setTime( new java.util.Date() );  
//		calendar.add( Calendar.DAY_OF_MONTH , 30 );  
//		System.out.println( calendar.getTime() );  
//		
//		//funcionando aumentar data
//		GregorianCalendar dataParaCalculo = new GregorianCalendar(); 
//		dataParaCalculo.add(Calendar.DATE, 12);
//		System.out.println(dataParaCalculo.getTime());
		
		
		
		
		//usando gregorian
		GregorianCalendar dataParaAumentar = new GregorianCalendar(); 
		char turnoParaGravar = 'M';
		
		for(int i = 0; i < 2; i++){
		
			dataParaAumentar.add(Calendar.DATE, i);
			
			for(int r = 0; r < 3; r++){
				if(r == 0){
					turnoParaGravar = 'M';
				} else if(r == 1){
					turnoParaGravar = 'V';
				} else {
					turnoParaGravar = 'N';
				}
				System.out.println(dataParaAumentar.getTime()+"   "+turnoParaGravar);
			}
		
		}
		
	}

}
