
import java.awt.BorderLayout;
import ma.projet.beans.Employee;
import ma.projet.beans.Salle;
import ma.projet.service.EmployeeService;
import ma.projet.service.MachineService;
import ma.projet.service.SalleService;
import ma.projet.service.ServiceService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LACHGAR
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServiceService sc = new ServiceService();
        for (Object[] o : sc.nbrEmpParServ()){
            System.out.println(Integer.parseInt(o[0].toString()) + "  " + o[1].toString());
        }
    }
}
