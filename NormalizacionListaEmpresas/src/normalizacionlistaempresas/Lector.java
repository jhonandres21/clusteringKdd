package normalizacionlistaempresas;

import java.io.*;
import java.util.ArrayList;

public class Lector {

    private File archivo = null;
    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;
    private ArrayList<Empresa> empresas;
    private Empresa empresa;

    public Lector() {
        empresas = new ArrayList<>();
    }

    public void leerArchivo() {

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\Users\\Estefany\\Desktop\\John\\clusteringKdd\\Listado Empresas - Editado v2.txt");
            fileReader = new FileReader(archivo);
            bufferedReader = new BufferedReader(fileReader);

            // Lectura del fichero
            String linea;
            String[] cadena;

            while ((linea = bufferedReader.readLine()) != null) {

                cadena = linea.split(",");
                //a partir del índice 3 tenemos el nombre de la empresa y los demás atributos que nos interesan

                if (!cadena[5].equalsIgnoreCase("nd")) {

                    empresa = new Empresa();
                    empresa.setNombreEmpresa(cadena[3]);
                    empresa.setIngresosOperacionales(Long.parseLong(cadena[4].replace(".", "")));
                    empresa.setVariacionIngresos(cadena[5]);
                    empresa.setUtilidadOperacional(Long.parseLong(cadena[6].replace(".", "")));
                    empresa.setUtilidadNeta(Long.parseLong(cadena[7].replace(".", "")));
                    empresa.setActivoTotal(Long.parseLong(cadena[8].replace(".", "")));
                    empresa.setPasivoTotal(Long.parseLong(cadena[9].replace(".", "")));
                    empresa.setPatrimonioTotal(Long.parseLong(cadena[10].replace(".", "")));
                    empresas.add(empresa);
                    //this.imprimirEmpresa(empresa);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion.
            try {
                if (null != fileReader) {
                    fileReader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }//fin metodo leerArchivo

    public void escribirArchivo() {

        FileWriter fileWriter = null;
        PrintWriter pw = null;

        try {
            //Abro stream, crea el fichero si no existe
            fileWriter = new FileWriter("C:\\Users\\Estefany\\Desktop\\John\\clusteringKdd\\Listado Empresas - Normalizado.txt");
            pw = new PrintWriter(fileWriter);

            for (Empresa empresa1 : empresas) {

                pw.println("" + empresa1.getIngresosOperacionales() + " " + empresa1.getVariacionIngresos()
                        + " " + empresa1.getUtilidadOperacional() + " " + empresa1.getUtilidadNeta() + " " + empresa1.getActivoTotal()
                        + " " + empresa1.getPasivoTotal() + " " + empresa1.getPatrimonioTotal());
            }

        } catch (Exception e) {
            System.out.println("Error E/S: " + e);
        } finally {
            //cerramos el fichero, para asegurarnos que se cierra tanto si todo va bien como si salta una excepcion.
            try {
                if (null != fileWriter) {
                    fileWriter.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void imprimirEmpresa(Empresa empresa) {

        System.out.println(empresa.getNombreEmpresa() + " " + empresa.getIngresosOperacionales() + " " + empresa.getVariacionIngresos()
                + " " + empresa.getUtilidadOperacional() + " " + empresa.getUtilidadNeta() + " " + empresa.getActivoTotal()
                + " " + empresa.getPasivoTotal() + " " + empresa.getPatrimonioTotal());
    }

}
