package normalizacionlistaempresas;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Lector lector = new Lector();
        NormalizacionEmpresas normalizacion = new NormalizacionEmpresas(lector.leerArchivo());
        lector.escribirArchivo(normalizacion.normalizacionDatos());
    }

}
