package main.io;

import main.model.AllDepartments;
import javax.xml.bind.JAXBException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TXTAllDepartments {
    public void serialize(AllDepartments allDepartments, String path) throws IOException, JAXBException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write("AllDepartments:\n" +
                allDepartments.getDepartments()+
                ", departmentAdd='" + allDepartments.getDepartmentAdd() + '\n' +
                ", hospitalAdd='" + allDepartments.getHospitalAdd() + '\n' +
                ", policlinicAdd='" + allDepartments.getPoliclinicAdd());
        if (writer != null) {
            writer.close();
        }
    }
}







