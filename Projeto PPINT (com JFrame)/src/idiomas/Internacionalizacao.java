package idiomas;

import java.util.Locale;
import java.util.ResourceBundle;

public class Internacionalizacao 
{
   private ResourceBundle bn = null;
	private int Indice;
    
   public ResourceBundle troca(int indice)
   {
      switch(indice)
      {	               
         case 1:
            bn = ResourceBundle.getBundle("idiomas.prj", new Locale("es", "ES")); 
            Indice = 1; 
            break;  
         case 2:
            bn = ResourceBundle.getBundle("idiomas.prj", new Locale("en", "US"));  
            Indice = 2;   
            break;
         default:
            bn = ResourceBundle.getBundle("idiomas.prj", new Locale("pt", "BR"));
            Indice = 3;	               
            break;
      }  
      return bn;
   }
   
 public void setIndice(int indice) 
   {
      Indice = indice;
   }
   
   public int getIndice() 
   {
      return Indice;
   }		 
}