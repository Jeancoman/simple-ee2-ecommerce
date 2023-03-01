package config;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Fecha {
    public static Calendar calendario = Calendar.getInstance();
    private static String fecha;
    
    public static String fecha(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        fecha = formato.format(calendario.getTime());
        return fecha;
    }
    
}
