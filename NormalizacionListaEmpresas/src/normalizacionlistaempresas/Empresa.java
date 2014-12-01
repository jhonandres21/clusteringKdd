package normalizacionlistaempresas;

public class Empresa {

    private String nombreEmpresa = "";
    private float ingresosOperacionales;
    private String variacionIngresos;
    private float utilidadOperacional, utilidadNeta, activoTotal, pasivoTotal, patrimonioTotal;

    public Empresa() {
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public float getIngresosOperacionales() {
        return ingresosOperacionales;
    }

    public void setIngresosOperacionales(float ingresosOperacionales) {
        this.ingresosOperacionales = ingresosOperacionales;
    }

    public String getVariacionIngresos() {
        return variacionIngresos;
    }

    public void setVariacionIngresos(String variacionIngresos) {
        this.variacionIngresos = variacionIngresos;
    }

    public float getUtilidadOperacional() {
        return utilidadOperacional;
    }

    public void setUtilidadOperacional(float utilidadOperacional) {
        this.utilidadOperacional = utilidadOperacional;
    }

    public float getUtilidadNeta() {
        return utilidadNeta;
    }

    public void setUtilidadNeta(float utilidadNeta) {
        this.utilidadNeta = utilidadNeta;
    }

    public float getActivoTotal() {
        return activoTotal;
    }

    public void setActivoTotal(float activoTotal) {
        this.activoTotal = activoTotal;
    }

    public float getPasivoTotal() {
        return pasivoTotal;
    }

    public void setPasivoTotal(float pasivoTotal) {
        this.pasivoTotal = pasivoTotal;
    }

    public float getPatrimonioTotal() {
        return patrimonioTotal;
    }

    public void setPatrimonioTotal(float patrimonioTotal) {
        this.patrimonioTotal = patrimonioTotal;
    }

}
