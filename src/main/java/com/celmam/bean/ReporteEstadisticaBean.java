package com.celmam.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class ReporteEstadisticaBean implements Serializable {

    final Logger logger = LoggerFactory.getLogger(ReporteEstadisticaBean.class);
    private static final long serialVersionUID = 1L;

    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries aprobados = new ChartSeries();
        aprobados.setLabel("Aprobados");
        aprobados.set("Solicitudes", 120);
        aprobados.set("Cartas", 44);
        aprobados.set("Resoluciones", 150);
        aprobados.set("Memorandum", 25);
        aprobados.set("Otros", 25);

        ChartSeries rechazados = new ChartSeries();
        rechazados.setLabel("Rechazados");
        rechazados.set("Oficios", 100);
        rechazados.set("Solicitudes", 20);
        rechazados.set("Cartas", 12);
        rechazados.set("Resoluciones", 80);
        rechazados.set("Memorandum", 25);
        rechazados.set("Otros", 15);
        
        
        model.addSeries(aprobados);
        model.addSeries(rechazados);

        return model;
    }

    private void createBarModels() {
        createBarModel();

    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Diagrama de Barras");
        barModel.setLegendPosition("ne");
        barModel.setZoom(true);
        barModel.setLegendCols(4);

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Tipo Documentos");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

}
