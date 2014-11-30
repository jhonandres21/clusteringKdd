package normalizacionlistaempresas;

import java.io.*;
import java.util.ArrayList;
import sun.misc.FloatingDecimal;

public class Lector {

    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;
    private ArrayList<Empresa> empresas;
    private Empresa empresa;

    public Lector() {
    }

    public void leerArchivo() {

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\Users\\Estefany\\Desktop\\John\\clusteringKdd\\Listado Empresas - Editado v2.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            String[] cadena;

            while ((linea = br.readLine()) != null) {

                cadena = linea.split(",");
                //a partir del índice 3 tenemos el nombre de la empresa y los demás atributos que nos interesan
                
                Empresa empresa = new Empresa();
                empresa.setNombreEmpresa(cadena[3]);
                empresa.setIngresosOperacionales(Double.parseDouble(cadena[4].replace(".", "")));
                empresa.setVariacionIngresos(cadena[5]);
                empresa.setUtilidadOperacional(Double.parseDouble(cadena[6].replace(".", "")));
                empresa.setUtilidadNeta(Double.parseDouble(cadena[7].replace(".", "")));
                empresa.setActivoTotal(Double.parseDouble(cadena[8].replace(".", "")));
                empresa.setPasivoTotal(Double.parseDouble(cadena[9].replace(".", "")));
                empresa.setPatrimonioTotal(Double.parseDouble(cadena[10].replace(".", "")));

                this.imprimirEmpresa(empresa);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }//fin metodo leerArchivo

    public void imprimirEmpresa(Empresa empresa) {

        System.out.println(empresa.getNombreEmpresa() + " " + empresa.getIngresosOperacionales() + " " + empresa.getVariacionIngresos()
                + " " + empresa.getUtilidadOperacional() + " " + empresa.getUtilidadNeta() + " " + empresa.getActivoTotal()
                + " " + empresa.getPasivoTotal() + " " + empresa.getPatrimonioTotal());
    }

}
