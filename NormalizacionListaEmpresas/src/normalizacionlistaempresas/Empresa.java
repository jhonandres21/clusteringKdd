package normalizacionlistaempresas;

public class Empresa {

    private String nombreEmpresa = "";
    private Double ingresosOperacionales;
    private String variacionIngresos;
    private Double utilidadOperacional, utilidadNeta, activoTotal, pasivoTotal, patrimonioTotal;

    public Empresa() {
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Double getIngresosOperacionales() {
        return ingresosOperacionales;
    }

    public void setIngresosOperacionales(Double ingresosOperacionales) {
        this.ingresosOperacionales = ingresosOperacionales;
    }

    public String getVariacionIngresos() {
        return variacionIngresos;
    }

    public void setVariacionIngresos(String variacionIngresos) {
        this.variacionIngresos = variacionIngresos;
    }

    public Double getUtilidadOperacional() {
        return utilidadOperacional;
    }

    public void setUtilidadOperacional(Double utilidadOperacional) {
        this.utilidadOperacional = utilidadOperacional;
    }

    public Double getUtilidadNeta() {
        return utilidadNeta;
    }

    public void setUtilidadNeta(Double utilidadNeta) {
        this.utilidadNeta = utilidadNeta;
    }

    public Double getActivoTotal() {
        return activoTotal;
    }

    public void setActivoTotal(Double activoTotal) {
        this.activoTotal = activoTotal;
    }

    public Double getPasivoTotal() {
        return pasivoTotal;
    }

    public void setPasivoTotal(Double pasivoTotal) {
        this.pasivoTotal = pasivoTotal;
    }

    public Double getPatrimonioTotal() {
        return patrimonioTotal;
    }

    public void setPatrimonioTotal(Double patrimonioTotal) {
        this.patrimonioTotal = patrimonioTotal;
    }
   
}
