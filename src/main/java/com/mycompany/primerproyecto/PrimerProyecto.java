package com.mycompany.primerproyecto;

import controlador.Prueba;
import controlador.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import javax.xml.stream.*;
import javax.xml.stream.events.*;

/**
 *
 * @author matprog06
 */
public class PrimerProyecto {

    public static void main(String[] args) {
        Prueba prueba = new Prueba();
        Test test = new Test();
//        prueba.saludo();
//        prueba.saludo();
//        prueba.contar();
//        prueba.contar2();
//        prueba.despedida();
//        prueba.despedida();
//
//        test.alfabeto();

        //
        boolean bFirstName = false;
        boolean bLastName = false;
        boolean bNickName = false;
        boolean bMarks = false;
        boolean isRequestRollNo = false;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader
                    = factory.createXMLEventReader(new FileReader("C:\\Users\\matprog06\\Documents\\GitHub\\primerProyecto\\src\\main\\java\\com\\mycompany\\primerproyecto\\input.xml"));

            String requestedRollNo = "393";

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();

                        if (qName.equalsIgnoreCase("student")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            String rollNo = attributes.next().getValue();

                            if (rollNo.equalsIgnoreCase(requestedRollNo)) {
                                System.out.println("Start Element : student");
                                System.out.println("Roll No : " + rollNo);
                                isRequestRollNo = true;
                            }
                        } else if (qName.equalsIgnoreCase("firstname")) {
                            bFirstName = true;
                        } else if (qName.equalsIgnoreCase("lastname")) {
                            bLastName = true;
                        } else if (qName.equalsIgnoreCase("nickname")) {
                            bNickName = true;
                        } else if (qName.equalsIgnoreCase("marks")) {
                            bMarks = true;
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if (bFirstName && isRequestRollNo) {
                            System.out.println("First Name: " + characters.getData());
                            bFirstName = false;
                        }
                        if (bLastName && isRequestRollNo) {
                            System.out.println("Last Name: " + characters.getData());
                            bLastName = false;
                        }
                        if (bNickName && isRequestRollNo) {
                            System.out.println("Nick Name: " + characters.getData());
                            bNickName = false;
                        }
                        if (bMarks && isRequestRollNo) {
                            System.out.println("Marks: " + characters.getData());
                            bMarks = false;
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();

                        if (endElement.getName().getLocalPart().equalsIgnoreCase(
                                "student") && isRequestRollNo) {
                            System.out.println("End Element : student");
                            System.out.println();
                            isRequestRollNo = false;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();

        }
    }
}
