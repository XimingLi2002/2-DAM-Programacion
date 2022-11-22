package UT1.EjercicioAccesoFicheroAleatorio;

import java.math.BigDecimal;
import java.util.Random;

public class Empleado {
    String name,telephoneNumber;
    Integer employeeNumber;
    Double salary;

    static Random random = new Random();
    Empleado(){
        //Suponemos que no saldrá empleados con el mismo id
        this.employeeNumber = random.nextInt(9999);
        this.name = getRandomName();
        this.telephoneNumber = getRandomTelephoneNumber();
        this.salary = getRandomSalary();
    }

    public String getRandomName() {
        String[] nameList = new String[]{"Mateo", "Diego", "Luis", "Carlos", "Rodrigo", "Carla", "Paula", "Cristina", "Juan", "Kira"};
        return nameList[random.nextInt(nameList.length)];
    }
    public String getRandomTelephoneNumber(){
        String[] telephoneNumberList = new String[]{"697858385", "629332417", "671388877","612541254","751243654","674637723","656777422","668723782","654555443","698776655"};
        return telephoneNumberList[random.nextInt(telephoneNumberList.length)];
    }

    public double getRandomSalary(){
        //Para que me genere el salario aleatoriamente con un maximo de 2 decimales
        BigDecimal bigDecimal = BigDecimal.valueOf(random.nextDouble(4999));
        return bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "employeeNumber=" + employeeNumber +
                ", name='" + name + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", salary=" + salary +
                '}';
    }
}
