package main.model;



import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@XmlRootElement(name = "allDepartments")
@XmlAccessorType(XmlAccessType.FIELD)//access to all fields
public class AllDepartments {

    @XmlElements({
            @XmlElement(name="department",type=Department.class)//елементи типу Department, кожен з них має обгортку з назвою (department)
    })
    @XmlElementWrapper(name = "departments")
    private Set<Department> departments;
    private String departmentAdd;
    private String hospitalAdd;
    private String policlinicAdd;

    public AllDepartments(){
        this(new HashSet<>(),"","","");
    }

    public AllDepartments(Set<Department> departments, String departmentAdd, String hospitalAdd, String policlinicAdd) {
        this.departments = departments;
        this.departmentAdd = departmentAdd;
        this.hospitalAdd = hospitalAdd;
        this.policlinicAdd = policlinicAdd;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public String getDepartmentAdd() {
        return departmentAdd;
    }

    public String getHospitalAdd() {
        return hospitalAdd;
    }

    public String getPoliclinicAdd() {
        return policlinicAdd;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public void setDepartmentAdd(String departmentAdd) {
        this.departmentAdd = departmentAdd;
    }

    public void setHospitalAdd(String hospitalAdd) {
        this.hospitalAdd = hospitalAdd;
    }

    public void setPoliclinicAdd(String policlinicAdd) {
        this.policlinicAdd = policlinicAdd;
    }

    public Department getDepartmentByName(String departmentName){
        List<Department> res =  departments.stream().filter(d->d.getName().equals(departmentName)).collect(Collectors.toList());
        return res.get(0);
    }

    public Set<String> findSetNames(){
        return departments.stream().map((Department::getName)).collect(Collectors.toSet());
    }
    public Long countDepartments(){
        return findSetNames().stream().count();
    }

    public List<Employee> getEmployeesByPost(String post){
        List<Employee> res=new ArrayList<>();
        Iterator<Department> iterator=departments.iterator();
        List<Employee> temp;
        Department d;
        while (iterator.hasNext()){
            d=iterator.next();
            temp=d.getEmployees();
            for (Employee e:temp ){
                if(e.getMyPost().equals(post)){
                    res.add(e);
                }
            }



        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllDepartments that = (AllDepartments) o;
        return Objects.equals(departments, that.departments) &&
                Objects.equals(departmentAdd, that.departmentAdd) &&
                Objects.equals(hospitalAdd, that.hospitalAdd) &&
                Objects.equals(policlinicAdd, that.policlinicAdd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departments, departmentAdd, hospitalAdd, policlinicAdd);
    }

    @Override
    public String toString() {
        return "AllDepartments:" +
                departments.toString()+
                ", departmentAdd=" + departmentAdd + '\'' +
                ", hospitalAdd=" + hospitalAdd + '\'' +
                ", policlinicAdd=" + policlinicAdd + '\'' +
                '}';
    }
}
