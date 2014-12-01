package normalizacionlistaempresas;

public class Empresa {

    private String nombreEmpresa = "";
    private Long ingresosOperacionales;
    private String variacionIngresos;
    private Long utilidadOperacional, utilidadNeta, activoTotal, pasivoTotal, patrimonioTotal;

    public Empresa() {
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Long getIngresosOperacionales() {
        return ingresosOperacionales;
    }

    public void setIngresosOperacionales(Long ingresosOperacionales) {
        this.ingresosOperacionales = ingresosOperacionales;
    }

    public String getVariacionIngresos() {
        return variacionIngresos;
    }

    public void setVariacionIngresos(String variacionIngresos) {
        this.variacionIngresos = variacionIngresos;
    }

    public Long getUtilidadOperacional() {
        return utilidadOperacional;
    }

    public void setUtilidadOperacional(Long utilidadOperacional) {
        this.utilidadOperacional = utilidadOperacional;
    }

    public Long getUtilidadNeta() {
        return utilidadNeta;
    }

    public void setUtilidadNeta(Long utilidadNeta) {
        this.utilidadNeta = utilidadNeta;
    }

    public Long getActivoTotal() {
        return activoTotal;
    }

    public void setActivoTotal(Long activoTotal) {
        this.activoTotal = activoTotal;
    }

    public Long getPasivoTotal() {
        return pasivoTotal;
    }

    public void setPasivoTotal(Long pasivoTotal) {
        this.pasivoTotal = pasivoTotal;
    }

    public Long getPatrimonioTotal() {
        return patrimonioTotal;
    }

    public void setPatrimonioTotal(Long patrimonioTotal) {
        this.patrimonioTotal = patrimonioTotal;
    }

}
