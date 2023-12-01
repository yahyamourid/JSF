/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.domaine;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employee;
import ma.projet.beans.Machine;
import ma.projet.beans.Service;
import ma.projet.beans.Service;
import ma.projet.beans.Service;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author YahyaMrd
 */
@ManagedBean(name ="serviceBean")
public class ServiceBean {

    private Service service;
    private ServiceService serviceService;
    private List<Service> services ;
    private static ChartModel barModel;
    private List<Employee> employees = new ArrayList<>();
    private Employee chefService  = new Employee();

    public ServiceBean() {
        service = new Service();
        serviceService = new ServiceService();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceService getServiceService() {
        return serviceService;
    }

    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public List<Service> getServices() {
        if (services == null)
            services = serviceService.getAll();
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getChefService() {
        return chefService;
    }

    public void setChefService(Employee chefService) {
        this.chefService = chefService;
    }
    

    public String onCreateAction() {
        serviceService.create(service);
        service  = new Service();
        return null;
    }

    public void onDeleteAction() {
        System.out.println("onDeleteAction called");
        serviceService.delete(service);

    }

    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        serviceService.update(service);
    }
    
     public static ChartModel getBarModel() {
        return barModel;
    }

    public static void setBarModel(ChartModel barModel) {
        ServiceBean.barModel = barModel;
    }
    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries services = new ChartSeries();
        services.setLabel("services");
        model.setAnimate(true);
        for (Object[] m : serviceService.nbrEmpParServ()) {
            services.set(m[1], Integer.parseInt(m[0].toString()));
        }
        model.addSeries(services);

        return model;
    }
    public void chercherEmployees(){
        
    }
    
}
