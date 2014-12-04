package normalizacionlistaempresas;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Lector {

    private File archivo = null;
    private FileReader fileReader = null;
    private BufferedReader bufferedReader = null;
    private ArrayList<Empresa> empresas;
    private Empresa empresa;
    private int numeroRegistros = 0;

    public Lector() {
        empresas = new ArrayList<>();
    }

    public void leerArchivo() {

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("/home/juan/GitProjects/clusteringKdd/Listado Empresas - Editado v2.txt");
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

    }//fin metodo leerArchivo

    public void normalizacionDatos() {

        float media[] = this.calculoMedia();
        float desviacionEstandar[] = this.calculoDesviacionEstandar(media);

        for (int i = 0; i < empresas.size(); i++) {

            empresas.get(i).setIngresosOperacionales((empresas.get(i).getIngresosOperacionales() - media[0]) / desviacionEstandar[0]);
            String split[] = empresas.get(i).getVariacionIngresos().split("%");
            float valor = Float.parseFloat(split[0]);
            float porcentaje = valor / 100;
            empresas.get(i).setVariacionIngresos("" + porcentaje);
            empresas.get(i).setUtilidadOperacional((empresas.get(i).getUtilidadOperacional() - media[2]) / desviacionEstandar[2]);
            empresas.get(i).setUtilidadNeta((empresas.get(i).getUtilidadNeta() - media[3]) / desviacionEstandar[3]);
            empresas.get(i).setActivoTotal((empresas.get(i).getActivoTotal() - media[4]) / desviacionEstandar[4]);
            empresas.get(i).setPasivoTotal((empresas.get(i).getPasivoTotal() - media[5]) / desviacionEstandar[5]);
            empresas.get(i).setPatrimonioTotal((empresas.get(i).getPatrimonioTotal() - media[6]) / desviacionEstandar[6]);
        }
    }

    private float[] calculoMedia() {

        //tenemos 7 atributos por lo tanto tenemos 7 medias
        float media[] = new float[7];
        float sumatoria[] = new float[7];
        int n = 0;

        for (int i = 0; i < empresas.size(); i++) {

            sumatoria[0] += empresas.get(i).getIngresosOperacionales();
//            String split[] = empresas.get(i).getVariacionIngresos().split("%");
//            float valor = Float.parseFloat(split[0]);
//            float porcentaje = valor / 100;
//            sumatoria[1] += porcentaje;
            sumatoria[2] += empresas.get(i).getUtilidadOperacional();
            sumatoria[3] += empresas.get(i).getUtilidadNeta();
            sumatoria[4] += empresas.get(i).getActivoTotal();
            sumatoria[5] += empresas.get(i).getPasivoTotal();
            sumatoria[6] += empresas.get(i).getPatrimonioTotal();
            n++;
        }

        for (int j = 0; j < 7; j++) {
            media[j] = (float) sumatoria[j] / n;
        }

        numeroRegistros = n;

        return media;
    }

    private float[] calculoDesviacionEstandar(float media[]) {

        float desviacionEstandar[] = new float[7];

        for (int i = 0; i < empresas.size(); i++) {

            desviacionEstandar[0] += Math.pow((empresas.get(i).getIngresosOperacionales() - media[0]), 2);
//            String split[] = empresas.get(i).getVariacionIngresos().split("%");
//            float valor = Float.parseFloat(split[0]);
//            float porcentaje = valor / 100;
//            desviacionEstandar[1] += ((float) 1 / numeroRegistros) * (porcentaje - media[1]);
            desviacionEstandar[2] += Math.pow((empresas.get(i).getUtilidadOperacional() - media[2]), 2);
            desviacionEstandar[3] += Math.pow((empresas.get(i).getUtilidadNeta() - media[3]), 2);
            desviacionEstandar[4] += Math.pow((empresas.get(i).getActivoTotal() - media[4]), 2);
            desviacionEstandar[5] += Math.pow((empresas.get(i).getPasivoTotal() - media[5]), 2);
            desviacionEstandar[6] += Math.pow((empresas.get(i).getPatrimonioTotal() - media[6]), 2);
        }

        desviacionEstandar[0] = (float) Math.sqrt((desviacionEstandar[0] / numeroRegistros));
        desviacionEstandar[2] = (float) Math.sqrt((desviacionEstandar[2] / numeroRegistros));
        desviacionEstandar[3] = (float) Math.sqrt((desviacionEstandar[3] / numeroRegistros));
        desviacionEstandar[4] = (float) Math.sqrt((desviacionEstandar[4] / numeroRegistros));
        desviacionEstandar[5] = (float) Math.sqrt((desviacionEstandar[5] / numeroRegistros));
        desviacionEstandar[6] = (float) Math.sqrt((desviacionEstandar[6] / numeroRegistros));

        return desviacionEstandar;
    }

    public void escribirArchivo() {

        FileWriter fileWriter = null;
        PrintWriter pw = null;

        try {
            //Abro stream, crea el fichero si no existe
            fileWriter = new FileWriter("/home/juan/GitProjects/clusteringKdd/Listado Empresas - Normalizado.txt");
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
    }

    public void imprimirEmpresa(Empresa empresa) {

        System.out.println(empresa.getNombreEmpresa() + " " + empresa.getIngresosOperacionales() + " " + empresa.getVariacionIngresos()
                + " " + empresa.getUtilidadOperacional() + " " + empresa.getUtilidadNeta() + " " + empresa.getActivoTotal()
                + " " + empresa.getPasivoTotal() + " " + empresa.getPatrimonioTotal());
    }

}
