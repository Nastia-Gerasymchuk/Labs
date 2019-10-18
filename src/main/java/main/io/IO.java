package main.io;

import main.model.AllDepartments;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IO<T> {
    void serialize(AllDepartments allDepartments,String path) throws IOException, JAXBException;
    AllDepartments deserialize(String path)throws IOException, JAXBException;
}
