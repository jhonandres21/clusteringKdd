package normalizacionlistaempresas;

import java.util.ArrayList;

public class NormalizacionEmpresas {

    private ArrayList<Empresa> empresas;
    private int numeroRegistros = 0;

    public NormalizacionEmpresas(ArrayList<Empresa> empresas) {
        this.empresas = empresas;
    }

    public ArrayList<Empresa> normalizacionDatos() {

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

        return empresas;
    }

    private float[] calculoMedia() {

        float media[] = new float[7];
        float sumatoria[] = new float[7];
        int n = 0;

        for (int i = 0; i < empresas.size(); i++) {

            sumatoria[0] += empresas.get(i).getIngresosOperacionales();
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
}
