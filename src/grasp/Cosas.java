package grasp;

/**
 * Implementacion abtracta de elementos que pueden ser introducidos a una mochila de acuerdo al problema de la mochila
 * donde se utiliza la heuristica de GRASP.
 * Los elementos deben de tener los atributos: nombre, peso y una utilidad.
 * 
 * @author Irvin Omar Galindo Becerra
 */
 /*

 */
public class Cosas {
    /**El peso del elemento*/
    private double peso;
    
    /**La utilidad del elemento, 
     * son las veces que puede llegar a utilizarse el elemento entre mayor sea la utilidad mayor veces puede llegar a usarse
     */
    private double utilidad;
    
    /**Identificador del elemento*/
    private String nombre;

    /**
     * Constructor para inicializar todos los atributos del objeto con los parametros enviados en este.  
     * @param nombre
     * @param peso
     * @param utilidad
     */
    public Cosas(String nombre, double peso, double utilidad) {
        this.nombre = nombre;
        this.peso = peso;
        this.utilidad = utilidad;
    }

    /**Regresa el nombre del objeto*/
   public String getNombre() {
        return nombre;
    }

   /**Modificador del nombre del objeto*/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**Regresa el peso del objeto*/
    public double getPeso() {
        return peso;
    }

    /**Modificador del peso del objeto*/
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**Regresa la utilidad del objeto*/
    public double getUtilidad() {
        return utilidad;
    }

    /**Modificador de la utilidad del objeto*/
    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    
    }

}
