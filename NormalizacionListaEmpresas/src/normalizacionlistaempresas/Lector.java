package normalizacionlistaempresas;

import java.io.*;
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
            //empresas.get(i).setVariacionIngresos((empresas.get(i).getVariacionIngresos() - media[1]) / desviacionEstandar[1]);
            empresas.get(i).setUtilidadOperacional((empresas.get(i).getUtilidadOperacional() - media[2]) / desviacionEstandar[2]);
            empresas.get(i).setUtilidadNeta((empresas.get(i).getUtilidadNeta() - media[3]) / desviacionEstandar[3]);
            empresas.get(i).setActivoTotal((empresas.get(i).getActivoTotal() - media[4]) / desviacionEstandar[4]);
            empresas.get(i).setPasivoTotal((empresas.get(i).getPasivoTotal() - media[5]) / desviacionEstandar[5]);
            empresas.get(i).setPatrimonioTotal((empresas.get(i).getPatrimonioTotal() - media[6]) / desviacionEstandar[6]);
        }
    }

    private float[] calculoMedia() {
        //Calculamos la media para cada variable, exceptuamos por ahora VariacionIngresos
        //tenemos 7 atributos por lo tanto tenemos 7 medias
        float media[] = new float[7];
        float sumatoria[] = new float[7];
        int n = 0;

        for (int j = 0; j < 7; j++) {

            for (int i = 0; i < empresas.size(); i++) {

                sumatoria[0] += empresas.get(i).getIngresosOperacionales();
                //sumatoria[1] += empresas.get(i).getVariacionIngresos();
                sumatoria[2] += empresas.get(i).getUtilidadOperacional();
                sumatoria[3] += empresas.get(i).getUtilidadNeta();
                sumatoria[4] += empresas.get(i).getActivoTotal();
                sumatoria[5] += empresas.get(i).getPasivoTotal();
                sumatoria[6] += empresas.get(i).getPatrimonioTotal();
                n++;
            }
            //System.out.println("N = " + n + "; " + "Sumatoria = " + sumatoria[j]);
            media[j] = ((float) 1 / n) * sumatoria[j];
            numeroRegistros = n;
            n = 0;
            //System.out.println("Media #" + j + " = " + media[j]);
        }
        return media;
    }

    private float[] calculoDesviacionEstandar(float media[]) {

        float desviacionEstandar[] = new float[7];

        for (int i = 0; i < empresas.size(); i++) {

            desviacionEstandar[0] += ((float) 1 / numeroRegistros) * (empresas.get(i).getIngresosOperacionales() - media[0]);
            //desviacionEstandar[1] += (1 / numeroRegistros) * (empresas.get(i).getVariacionIngresos()- media[1]);
            desviacionEstandar[2] += ((float) 1 / numeroRegistros) * (empresas.get(i).getUtilidadOperacional() - media[2]);
            desviacionEstandar[3] += ((float) 1 / numeroRegistros) * (empresas.get(i).getUtilidadNeta() - media[3]);
            desviacionEstandar[4] += ((float) 1 / numeroRegistros) * (empresas.get(i).getActivoTotal() - media[4]);
            desviacionEstandar[5] += ((float) 1 / numeroRegistros) * (empresas.get(i).getPasivoTotal() - media[5]);
            desviacionEstandar[6] += ((float) 1 / numeroRegistros) * (empresas.get(i).getPatrimonioTotal() - media[6]);
        }
        return desviacionEstandar;
    }

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
