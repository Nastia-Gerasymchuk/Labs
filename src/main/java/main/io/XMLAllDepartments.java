package main.io;

import main.model.AllDepartments;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLAllDepartments implements IO {
    public void serialize(AllDepartments allDepartments, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(AllDepartments.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(allDepartments, new File(path));
    }

    public AllDepartments deserialize(String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(AllDepartments.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (AllDepartments) jaxbUnmarshaller.unmarshal(new File(path));
    }
}
