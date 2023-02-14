package com.solvd.taxiservices.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.taxiservices.models.people.Client;
import com.solvd.taxiservices.models.vehicles.Car;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.*;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Serializer {
    public static DateAdapter dateAdapter = new DateAdapter();

    public static <T> void serialize(T data, String filename) {
        try {
            JAXBContext context;
            context = JAXBContext.newInstance(data.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(data, new File(filename));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserialize(T data, String filename) {
        try {
            JAXBContext context = JAXBContext.newInstance(data.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            data = (T) unmarshaller.unmarshal(new File(filename));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static Car deserializeSAX (String filename) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            CarHandler carHandler = new CarHandler();
            xmlReader.setContentHandler(carHandler);
            xmlReader.parse(filename);
            return carHandler.getCar();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void serializeStAX (Car car, String filename) {
        try {
            StringWriter stringWriter = new StringWriter();

            XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xMLStreamWriter =
                    xMLOutputFactory.createXMLStreamWriter(stringWriter);

            xMLStreamWriter.writeStartDocument();
            xMLStreamWriter.writeStartElement("car");

            xMLStreamWriter.writeStartElement("carId");
            xMLStreamWriter.writeCharacters(Integer.toString(car.getId()));
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("model");
            xMLStreamWriter.writeCharacters(car.getModel());
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("color");
            xMLStreamWriter.writeCharacters(car.getColor());
            xMLStreamWriter.writeEndElement();
            xMLStreamWriter.writeStartElement("carNumber");
            xMLStreamWriter.writeCharacters(car.getNumber());
            xMLStreamWriter.writeEndElement();
            DateAdapter dateAdapter = new DateAdapter();
            xMLStreamWriter.writeStartElement("dateOfAcquirement");
            xMLStreamWriter.writeCharacters(dateAdapter.marshal(car.getDateOfAcquirement()));
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeStartElement("clients");
            car.clientsInCar.stream().forEach(c -> {
                try {
                    xMLStreamWriter.writeStartElement("client");
                    xMLStreamWriter.writeStartElement("email");
                    xMLStreamWriter.writeCharacters(c.getEmail());
                    xMLStreamWriter.writeEndElement();
                    xMLStreamWriter.writeStartElement("id");
                    xMLStreamWriter.writeCharacters(Integer.toString(c.getId()));
                    xMLStreamWriter.writeEndElement();
                    xMLStreamWriter.writeStartElement("password");
                    xMLStreamWriter.writeCharacters(c.getPassword());
                    xMLStreamWriter.writeEndElement();
                    xMLStreamWriter.writeStartElement("username");
                    xMLStreamWriter.writeCharacters(c.getUsername());
                    xMLStreamWriter.writeEndElement();
                    xMLStreamWriter.writeStartElement("number");
                    xMLStreamWriter.writeCharacters(c.getNumber());
                    xMLStreamWriter.writeEndElement();
                    xMLStreamWriter.writeEndElement();
                } catch (XMLStreamException ignored) { }
            });
            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeEndElement();

            xMLStreamWriter.writeEndDocument();

            xMLStreamWriter.flush();
            xMLStreamWriter.close();
            String xmlString = stringWriter.getBuffer().toString();
            Files.writeString(Paths.get(filename), xmlString, StandardCharsets.UTF_8);
            stringWriter.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car deserializeStAX(String filename) {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(filename));
            Car car = null;
            Client currClient = null;
            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "car":
                            car = new Car();
                            break;
                        case "carId":
                            nextEvent = reader.nextEvent();
                            car.setId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "model":
                            nextEvent = reader.nextEvent();
                            car.setModel(nextEvent.asCharacters().getData());
                            break;
                        case "color":
                            nextEvent = reader.nextEvent();
                            car.setColor(nextEvent.asCharacters().getData());
                            break;
                        case "carNumber":
                            nextEvent = reader.nextEvent();
                            car.setNumber(nextEvent.asCharacters().getData());
                            break;
                        case "dateOfAcquirement":
                            nextEvent = reader.nextEvent();
                            car.setDateOfAcquirement(dateAdapter.unmarshal(nextEvent.asCharacters().getData()));
                            break;
                        case "clients":
                            car.clientsInCar = new ArrayList<>();
                            currClient = new Client();
                            break;
                        case "id":
                            nextEvent = reader.nextEvent();
                            currClient.setId(Integer.parseInt(nextEvent.asCharacters().getData()));
                            break;
                        case "email":
                            nextEvent = reader.nextEvent();
                            currClient.setEmail(nextEvent.asCharacters().getData());
                            break;
                        case "password":
                            nextEvent = reader.nextEvent();
                            currClient.setPassword(nextEvent.asCharacters().getData());
                            break;
                        case "username":
                            nextEvent = reader.nextEvent();
                            currClient.setUsername(nextEvent.asCharacters().getData());
                            break;
                        case "number":
                            nextEvent = reader.nextEvent();
                            currClient.setNumber(nextEvent.asCharacters().getData());
                            break;
                    }
                }
                if (nextEvent.isEndElement()) {
                    EndElement endElement = nextEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("client")) {
                        car.clientsInCar.add(currClient);
                    }
                }
            }
            return car;
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void serializeJSON(T data, String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String result = objectMapper.writeValueAsString(data);
            FileWriter writer = new FileWriter(filename, false);
            writer.write(result);
            writer.flush();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T deserializeJSON(T data, String filename) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = Files.readString(Path.of(filename));
            data = (T) mapper.readValue(jsonString, data.getClass());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    private static class CarHandler extends DefaultHandler {
        static final String CAR_TAG = "car";
        static final String CAR_ID_TAG = "carId";
        static final String MODEL_TAG = "model";
        static final String COLOR_TAG = "color";
        static final String CAR_NUMBER_TAG = "carNumber";
        static final String DATA_TAG = "dateOfAcquirement";
        static final String CLIENTS_TAG = "clients";
        static final String CLIENT_TAG = "client";
        static final String EMAIL_TAG = "email";
        static final String PASSWORD_TAG = "password";
        static final String USERNAME_TAG = "username";
        static final String ID_TAG = "id";
        static final String NUMBER_TAG = "number";

        private Car car;
        private Client currClient;
        private String currElement;

        public Car getCar() {
            return car;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currElement = qName;

            switch (currElement) {
                case CAR_TAG -> {
                    car = new Car();
                }
                case CLIENTS_TAG -> {
                    car.clientsInCar = new ArrayList<>();
                }
                case CLIENT_TAG -> {
                    currClient = new Client();
                }
                default -> {}
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case CLIENT_TAG -> {
                    car.clientsInCar.add(currClient);
                    currClient = null;
                }
                default -> {}
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String text = new String(ch, start, length);

            if (text.contains("<") || text.contains("\n") || currElement.isBlank()) {
                return;
            }

            switch (currElement) {
                case CAR_ID_TAG -> {
                    try {
                        car.setId(Integer.parseInt(text));
                    }
                    catch (Exception ignored) {}
                }
                case MODEL_TAG -> {
                    car.setModel(text);
                }
                case COLOR_TAG -> {
                    car.setColor(text);
                }
                case CAR_NUMBER_TAG -> {
                    car.setNumber(text);
                }
                case DATA_TAG -> {
                    try {
                        car.setDateOfAcquirement(new SimpleDateFormat("dd-MM-yyyy").parse(text));
                    } catch (ParseException ignored) {
                    }
                }
                case EMAIL_TAG -> {
                    currClient.setEmail(text);
                }
                case PASSWORD_TAG -> {
                    currClient.setPassword(text);
                }
                case USERNAME_TAG -> {
                    currClient.setUsername(text);
                }
                case ID_TAG -> {
                    try {
                        currClient.setId(Integer.parseInt(text));
                    }
                    catch (Exception ignored) {}
                }
                case NUMBER_TAG -> {
                    try {
                        currClient.setNumber(text);
                    }
                    catch (Exception ignored) {}
                }
                default -> {}
            }
        }
    }
}
