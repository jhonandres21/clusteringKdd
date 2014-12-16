package normalizacionlistaempresas;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class Lector {

    private File archivo = null;
    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;

    public Lector() {
    }

    public ArrayList<Empresa> leerArchivo() {

        ArrayList<Empresa> empresas = new ArrayList<>();
        Empresa empresa;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("C:\\Users\\John\\Desktop\\clusteringKdd\\Listado Empresas - Editado v2.txt");
            fileReader = new FileReader(archivo);
            bufferedReader = new BufferedReader(fileReader);

            // Lectura del fichero
            String linea;
            String[] cadena;

            while ((linea = bufferedReader.readLine()) != null) {

                cadena = linea.split(",");
                //a partir del índice 3 tenemos el nombre de la empresa y los demás atributos que nos interesan

                if (!(cadena[5].equalsIgnoreCase("nd") || cadena[5].equals(""))) {

                    empresa = new Empresa();
                    empresa.setNombreEmpresa(cadena[3]);
                    empresa.setIngresosOperacionales(Float.parseFloat(cadena[4].replace(".", "")));
                    empresa.setVariacionIngresos(cadena[5]);
                    empresa.setUtilidadOperacional(Float.parseFloat(cadena[6].replace(".", "")));
                    empresa.setUtilidadNeta(Float.parseFloat(cadena[7].replace(".", "")));
                    empresa.setActivoTotal(Float.parseFloat(cadena[8].replace(".", "")));
                    empresa.setPasivoTotal(Float.parseFloat(cadena[9].replace(".", "")));
                    empresa.setPatrimonioTotal(Float.parseFloat(cadena[10].replace(".", "")));
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

        return empresas;

    }//fin metodo leerArchivo

    public void escribirArchivo(ArrayList<Empresa> empresas) {

        FileWriter fileWriter = null;
        PrintWriter pw = null;

        try {
            //Abro stream, crea el fichero si no existe
            fileWriter = new FileWriter("C:\\Users\\John\\Desktop\\clusteringKdd\\Listado Empresas - Normalizado.txt");
            pw = new PrintWriter(fileWriter);

            for (Empresa empresa1 : empresas) {

                //Conversion de expresión científica a decimal de 10 posiciones 
                String ingresosOperacionales = new BigDecimal(empresa1.getIngresosOperacionales(), new MathContext(10)).toPlainString();
                String utilidadOperacional = new BigDecimal(empresa1.getUtilidadOperacional(), new MathContext(10)).toPlainString();
                String utilidadNeta = new BigDecimal(empresa1.getUtilidadNeta(), new MathContext(10)).toPlainString();
                String activoTotal = new BigDecimal(empresa1.getActivoTotal(), new MathContext(10)).toPlainString();
                String pasivoTotal = new BigDecimal(empresa1.getPasivoTotal(), new MathContext(10)).toPlainString();
                String patrimonioTotal = new BigDecimal(empresa1.getPatrimonioTotal(), new MathContext(10)).toPlainString();

                pw.println("" + ingresosOperacionales + " " + empresa1.getVariacionIngresos()
                        + " " + utilidadOperacional + " " + utilidadNeta
                        + " " + activoTotal + " " + pasivoTotal
                        + " " + patrimonioTotal);
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
    }//fin método escribir archivo

    public void imprimirEmpresa(Empresa empresa) {

        System.out.println(empresa.getNombreEmpresa() + " " + empresa.getIngresosOperacionales() + " " + empresa.getVariacionIngresos()
                + " " + empresa.getUtilidadOperacional() + " " + empresa.getUtilidadNeta() + " " + empresa.getActivoTotal()
                + " " + empresa.getPasivoTotal() + " " + empresa.getPatrimonioTotal());
    }

}
