/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.dao;

import com.metro.model.StopPlace;
import com.metro.model.StopPlaceEquipment;
import com.metro.model.impl.StopPlaceEquipmentImpl;
import com.metro.model.impl.StopPlaceImpl;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QueryParseException;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.springframework.stereotype.Repository;

/**
 *
 * @author elena.salcedo
 */
@Repository
public class StopPlaceDAO {

    private static final OntModel ontology = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
//    private static final String PATH = "C:\\Users\\elena.salcedo\\Dropbox\\AA-Clase\\TFGesalcedoo\\esalcedooTFG\\metroAccesibilityTFG\\bbdd.owl";
    private static final String PATH = "C:\\Users\\elena\\Dropbox\\AA-Clase\\TFGesalcedoo\\esalcedooTFG\\metroAccesibilityTFG\\bbdd.owl";
    private static final String RDF_PREFIX = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n";
    private static final String RDFS_PREFIX = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n";
    private static final String MAO_PREFIX = "PREFIX mao: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology#> \n";
    private static final String MAOP_PREFIX = "PREFIX maop: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology/Property#> \n";
    private static final String MAODT_PREFIX = "PREFIX maodt: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology/dataType#> \n";
    
    private static final String QUERY_PREFIX = RDF_PREFIX + RDFS_PREFIX + MAO_PREFIX + MAOP_PREFIX + MAODT_PREFIX;
    
    
    public StopPlaceDAO() {
    }

    public List<StopPlace> findAll(){
        System.out.println("Entra en findAll");
        List<StopPlace> stopPlaces = new ArrayList<>();
        
        try {
            System.out.println("Entra en findAll try read");
            ontology.read(new FileInputStream(PATH), null, "RDF/XML");
        
        
            Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
            InfModel inf = ModelFactory.createInfModel(reasoner, ontology);
            
            String queryString = QUERY_PREFIX +
"                SELECT * " +
"                WHERE { " +
"                       {?sp rdf:type mao:StopPlaceEquipment. " +
"                        ?sp rdfs:label ?label. " +
"                        ?sp maop:X ?x. " +
"                        ?sp maop:Y ?y. " +
"                	} UNION { " +
"                	?sp maop:has_headingSign_properties ?hsp." +
"                        ?hsp maop:LineCode ?lineCode." +
"                        } UNION {" +
"                        ?sp maop:has_headingSign_properties ?hsp." +
"                	?hsp maop:DirectionName ?dirName." +
"                        } UNION {" +
"                        ?sp maop:has_headingSign_properties ?hsp." +
"                	?hsp maop:AsBraille ?asBraille." +
"                	} UNION { " +
"                	?sp maop:has_passengerInfo_properties ?hsp. " +
"                	?hsp maop:PassengerInfoFacilityType ?pasInfoFT. " +
"                	} UNION { " +
"                	?sp maop:has_ramp_properties ?hsp. " +
"                	?hsp maop:TactileGuideStrips ?tacGuidStr. " +
"                	} UNION { " +
"                	?sp maop:has_stopPlaceSign_properties ?hsp. " +
"                	?hsp maop:PlaceName ?placeName. " +
"                	} UNION { " +
"                	?sp maop:has_stopPlaceSign_properties ?hsp. " +
"                	?hsp maop:AsBraille ?asBraille. " +
"                	} UNION { " +
"                	?sp maop:has_queueing_properties ?hsp. " +
"                	?hsp maop:RailedQueue ?railedQueue. " +
"                	}" +
"}";
            
            
            Query query = QueryFactory.create(queryString);

            //execute the query and obtain results
            QueryExecution queryExecution = QueryExecutionFactory.create(query, inf);
            ResultSet results = queryExecution.execSelect();
        

            while (results.hasNext()) {
                System.out.println("Entra en findAll while has next");
                QuerySolution qs = results.next();
                
                if (qs.getResource("sp")!=null){
                    System.out.println("Entra en findAll sp!=null");
                    
                    if(qs.getLiteral("name")!=null){
                        StopPlace sp = new StopPlaceImpl(ontology, qs.getLiteral("name").toString());

                        sp.addLiteralCoordX(qs.getLiteral("x").getDouble());
                        sp.addLiteralCoordY(qs.getLiteral("y").getDouble());
                        sp.addLiteralPlaceName(qs.getLiteral("name").toString());
                        sp.addLiteralLineCode(qs.getLiteral("lineCode").getString());
                        
                        StopPlaceEquipmentImpl spe = new StopPlaceEquipmentImpl(ontology, qs.getLiteral("name").getString());
                    
                        String passengerInfoFacilityTypeDataTypes[] = {"maoptpe","maodtgs","maodtdvi","maopvd","maodtdvi","maopvd","maodahi","maodai"};

                        for (String field: passengerInfoFacilityTypeDataTypes){
                            System.out.println("Entra en findAll for passengerInfoFacilityTypeDataTypes");
                            Literal value = qs.getLiteral(field);
                            System.out.println("field: "+field+", value: "+value);
                            if (value!=null){
                            spe.addLiteralPassengerInfoFacilityType_property(value);
                            }
                        }

                        stopPlaces.add(sp);
                    }
                }
                
            }
        }catch (FileNotFoundException | QueryParseException ex) {
            Logger.getLogger(StopPlaceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return stopPlaces;
    }
    
    public StopPlace findByName(String nameSp) {
        String queryString = "";
        StopPlace sp = null;
        StopPlaceEquipment spe = null;
        try {
            ontology.read(new FileInputStream(PATH), null, "RDF/XML");            
            Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
            InfModel inf = ModelFactory.createInfModel(reasoner, ontology);
            
            queryString =QUERY_PREFIX +"      SELECT ?sp" +
                        "	WHERE {	?sp rdf:type mao:StopPlaceEquipment." +
                        "               ?sp maop:PlaceName ?name." +
                        "               FILTER (?name = \""+nameSp+"\" ) } " ;
            
            Query query = QueryFactory.create(queryString);

            //execute the query and obtain results
            QueryExecution queryExecution = QueryExecutionFactory.create(query, inf);
            ResultSet results = queryExecution.execSelect();

            if (results.hasNext()) {    // query returns rows

                sp = new StopPlaceImpl(ontology, nameSp);
                spe = new StopPlaceEquipmentImpl(ontology, nameSp);
                
                queryString =
"                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
"                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
"                PREFIX maop: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology/Property#> \n" +
"                PREFIX mao: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology#> \n" +
"                PREFIX maodt: <http://www.vortic3.com/IFOPT/MetroAccesihbilityOntology/dataType#> \n" +
"                         SELECT * \n" +
"                         WHERE {\n" +
"                           {?sp rdf:type mao:StopPlaceEquipment. \n" +
"                           ?sp rdfs:label ?label. \n" +
"                           ?sp maop:X ?x. \n" +
"                           ?sp maop:Y ?y. \n" +
"                           ?sp maop:PlaceName ?name.\n" +
"                           FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_passengerInfo_properties ?hsp. \n" +
"                               ?hsp maop:AccessibilityInfo ?AccessibilityInfo. \n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_lift_properties ?hsp. \n" +
"                               ?hsp maop:AcousticAnnouncements ?AcousticAnnouncements. \n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_crossing_properties ?hsp. \n" +
"                               ?hsp maop:AcousticCrossingAids ?AcousticCrossingAids. \n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_crossing_properties ?hsp. \n" +
"                               ?hsp maop:AcousticDeviceSensors ?AcousticDeviceSensors. \n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_entrance_properties ?hsp. \n" +
"                               ?hsp maop:AcousticSensor ?AcousticSensor. \n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_headingSign_properties ?hsp.\n" +
"                               ?hsp maop:AsBraille ?AsBraille.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:Attendant ?Attendant.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_lift_properties ?hsp.\n" +
"                               ?hsp maop:Automatic ?Automatic.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_entrance_properties ?hsp.\n" +
"                               ?hsp maop:AutoDoor ?AutoDoor.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +  
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:BrandGraphic ?BrandGraphic.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +  
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_entrance_properties ?hsp.\n" +
"                               ?hsp maop:Barrier ?Barrier.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +       
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:Content ?Content.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +  
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_stair_properties ?hsp.\n" +
"                               ?hsp maop:Depth ?Depth.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +  
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:DirectionName ?DirectionName.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_access_properties ?hsp.\n" +
"                               ?hsp maop:DirectionsOfUSe ?DirectionsOfUSe.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_entrance_properties ?hsp.\n" +
"                               ?hsp maop:Door ?Door.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_stair_properties ?hsp.\n" +
"                               ?hsp maop:EnergySaving ?EnergySaving.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                        
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_entrance_properties ?hsp.\n" +
"                               ?hsp maop:EntranceRequiresTicket ?EntranceRequiresTicket.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_ramp_properties ?hsp.\n" +
"                               ?hsp maop:Gradient ?Gradient.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +  
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_stair_properties ?hsp.\n" +
"                               ?hsp maop:HandrailHeight ?HandrailHeight.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_ramp_properties ?hsp.\n" +
"                               ?hsp maop:HandrailHeight ?HandrailHeight.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_stair_properties ?hsp.\n" +
"                               ?hsp maop:HandrailType ?HandrailType.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_ramp_properties ?hsp.\n" +
"                               ?hsp maop:HandrailType ?HandrailType.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:Height ?Height.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_luggageLocker_properties ?hsp.\n" +
"                               ?hsp maop:Height ?Height.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:HeightFromFloor ?HeightFromFloor.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                        
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_passengerInfo_properties ?hsp.\n" +
"                               ?hsp maop:InfoFacility ?InfoFacility.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                          
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_ramp_properties ?hsp.\n" +
"                               ?hsp maop:Length ?Length.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?hsp maop:has_waiting_properties ?hsp.\n" +
"                               ?hsp maop:Length ?Length.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION { \n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop: ?hsp.\n" +
"                               ?hsp maop:LineCode ?lineCode.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_headingSign_properties ?hsp.\n" +
"                               ?hsp maop:LineMap ?LineMap.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_headingSign_properties ?hsp.\n" +
"                               ?hsp maop:LineName ?LineName.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                        
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_ticketing_properties ?hsp.\n" +
"                               ?hsp maop:LowCounterAccess ?LowCounterAccess.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +           
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:MachineReadable ?MachineReadable.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +   
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_lift_properties ?hsp.\n" +
"                               ?hsp maop:MaximumLoad ?MaximumLoad.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +  
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_lift_properties ?hsp.\n" +
"                               ?hsp maop:MirrorOnOppositeSide ?MirrorOnOppositeSide.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_entrance_properties ?hsp.\n" +
"                               ?hsp maop:NumberOfGates ?NumberOfGates.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                       
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_luggageLocker_properties ?hsp.\n" +
"                               ?hsp maop:NumberOfLockers ?NumberOfLockers.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_queueing_properties ?hsp.\n" +
"                               ?hsp maop:NumberOfServers ?NumberOfServers.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                        
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_stair_properties ?hsp.\n" +
"                               ?hsp maop:NumberOfSteps ?NumberOfSteps.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                        
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_ticketing_properties ?hsp.\n" +
"                               ?hsp maop:PaymentMethod ?PaymentMethod.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +      
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_luggageLocker_properties ?hsp.\n" +
"                               ?hsp maop:PaymentMethod ?PaymentMethod.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_ramp_properties ?hsp.\n" +
"                               ?hsp maop:Pedestal ?Pedestal.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_crossing_properties ?hsp.\n" +
"                               ?hsp maop:PedestrianLights ?PedestrianLights.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                         
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:Placement ?Placement.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_queueing_properties ?hsp.\n" +
"                               ?hsp maop:RailedQueue ?RailedQueue.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_access_properties ?hsp.\n" +
"                               ?hsp maop:RelativeWeigthing ?RelativeWeigthing.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_entrance_properties ?hsp.\n" +
"                               ?hsp maop:RevolvingDoor ?RevolvingDoor.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_sanitaryFacility_properties ?hsp.\n" +
"                               ?hsp maop:SanitaryFacilityType ?SanitaryFacilityType.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_waiting_properties ?hsp.\n" +
"                               ?hsp maop:Seats ?Seats.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:SignGraphic ?SignGraphic.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_travelator_properties ?hsp.\n" +
"                               ?hsp maop:Speed ?Speed.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_staircase_properties ?hsp.\n" +
"                               ?hsp maop:SpiralStair ?SpiralStair.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_waiting_properties ?hsp.\n" +
"                               ?hsp maop:StepFree ?StepFree.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                        
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_lift_properties ?hsp.\n" +
"                               ?hsp maop:TactileActuators ?TactileActuators.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_travelator_properties ?hsp.\n" +
"                               ?hsp maop:TactileActuators ?TactileActuators.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_escalator_properties ?hsp.\n" +
"                               ?hsp maop:TactileActuators ?TactileActuators.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_crossing_properties ?hsp.\n" +
"                               ?hsp maop:TactileGuideStrips ?TactileGuideStrips.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +        
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_ramp_properties ?hsp.\n" +
"                               ?hsp maop:TactileGuideStrips ?TactileGuideStrips.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                         
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_ticketing_properties ?hsp.\n" +
"                               ?hsp maop:TicketMachines ?TicketMachines.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_ticketing_properties ?hsp.\n" +
"                               ?hsp maop:TicketTypesAvaiable ?TicketTypesAvaiable.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_queueing_properties ?hsp.\n" +
"                               ?hsp maop:TicketedQueue ?TicketedQueue.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_ticketing_properties ?hsp.\n" +
"                               ?hsp maop:TicketingType ?TicketingType.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                      
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_lift_properties ?hsp.\n" +
"                               ?hsp maop:ThroughLoader ?ThroughLoader.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_crossing_properties ?hsp.\n" +
"                               ?hsp maop:VisualGuianceBands ?VisualGuianceBands.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_lift_properties ?hsp.\n" +
"                               ?hsp maop:WeelchairTurningCircle ?WeelchairTurningCircle.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_waiting_properties ?hsp.\n" +
"                               ?hsp maop:WheelchairAreaLength ?WheelchairAreaLength.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                        
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_waiting_properties ?hsp.\n" +
"                               ?hsp maop:WeelchairAreaWidth ?WeelchairAreaWidth.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_sign_properties ?hsp.\n" +
"                               ?hsp maop:Width ?Width.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_access_properties ?hsp.\n" +
"                               ?hsp maop:Width ?Width.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_waiting_properties ?hsp.\n" +
"                               ?hsp maop:Width ?Width.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" +                         
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_lugageLocker_properties ?hsp.\n" +
"                               ?hsp maop:Width ?Width.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n" + 
"                           } UNION {\n" +
"                               ?sp rdf:type mao:StopPlaceEquipment. \n" +
"                               ?sp maop:has_crossing_properties ?hsp.\n" +
"                               ?hsp maop:ZebraCrossing ?ZebraCrossing.\n" +
"                               ?sp maop:PlaceName ?name.\n" +
"                               FILTER (?name = \""+nameSp+"\")\n}}";            

                query = QueryFactory.create(queryString);
                queryExecution = QueryExecutionFactory.create(query, inf);
                results = queryExecution.execSelect();
                
                while (results.hasNext()) {    // query returns rows
                    QuerySolution qs = results.next();
                    
                    if(qs.getLiteral("x")!=null){
                        sp.addLiteralCoordX(qs.getLiteral("x").getDouble());
                    }
                    if(qs.getLiteral("y")!=null){
                        sp.addLiteralCoordY(qs.getLiteral("y").getDouble());
                    }
                    if(qs.getLiteral("name")!=null){
                        sp.addLiteralPlaceName(qs.getLiteral("name").toString());                        
                    }
                    if(qs.getLiteral("AccessibilityInfo")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralPassengerInfoFacilityType_property(qs.getLiteral("AccessibilityInfo"));
                    }
                    else if(qs.getLiteral("AcousticAnnouncements")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAcousticAnnouncements_property(qs.getLiteral("AcousticAnnouncements").asLiteral());
                    }
                    else if(qs.getLiteral("AcousticCrossingAids")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAcousticCrossingAid_property(qs.getLiteral("AcousticCrossingAids").asLiteral());
                    }
                    else if(qs.getLiteral("AcousticDeviceSensors")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAcousticDeviceSensors_property(qs.getLiteral("AcousticDeviceSensors").asLiteral());
                    }
                    else if(qs.getLiteral("AcousticSensor")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAcousticSensor_property(qs.getLiteral("AcousticDeviceSensors").asLiteral());
                    }
                    if(qs.getLiteral("AsBraille")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAsBraille_property(qs.getLiteral("AsBraille").asLiteral());
                    }
                    else if(qs.getLiteral("Attendant")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAttendant_property(qs.getLiteral("Attendant").asLiteral());
                    }
                    else if(qs.getLiteral("Automatic")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAutomatic_property(qs.getLiteral("Automatic").asLiteral());
                    }
                    else if(qs.getLiteral("AutoDoor")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralAutomaticDoor_property(qs.getLiteral("AutoDoor").asLiteral());
                    }
                    else if(qs.getLiteral("BrandGraphic")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralBrandGraphic_property(qs.getLiteral("BrandGraphic").asLiteral());
                    }
                    else if(qs.getLiteral("Barrier")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralBarrier_property(qs.getLiteral("Barrier").asLiteral());
                    }
                    else if(qs.getLiteral("Content")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralContent_property(qs.getLiteral("Content").asLiteral());
                    }
                    else if(qs.getLiteral("Depth")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralDepthStair_property(qs.getLiteral("Depth").asLiteral());
                    }
                    else if(qs.getLiteral("DirectionName")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralDirectionName_property(qs.getLiteral("DirectionName").asLiteral());
                    }
                    else if(qs.getLiteral("Door")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralDoor_property(qs.getLiteral("Door").asLiteral());
                    }
                    else if(qs.getLiteral("EnergySaving")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralEnergySavingEscalator_property(qs.getLiteral("EnergySaving").asLiteral());
                    }
                    else if(qs.getLiteral("EntranceRequiresTicket")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralEntranceRequiresTicket_property(qs.getLiteral("EntranceRequiresTicket").asLiteral());
                    }
                    else if(qs.getLiteral("Gradient")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralGradient_property(qs.getLiteral("Gradient").asLiteral());
                    }
                    else if(qs.getLiteral("HandrailHeight")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_ramp_properties")){
                        spe.addLiteralHandrailHeightRamp_property(qs.getLiteral("HandrailHeight").asLiteral());
                    }
                    else if(qs.getLiteral("HandrailHeight")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_stair_properties")){
                        spe.addLiteralHandRailHeightStaircase_property(qs.getLiteral("HandrailHeight").asLiteral());
                    }
                    else if(qs.getLiteral("HandrailType")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_ramp_properties")){
                        spe.addLiteralHandrailTypeRamp_property(qs.getLiteral("HandrailType").asLiteral());
                    }
                    else if(qs.getLiteral("HandrailType")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_stair_properties")){
                        spe.addLiteralHandRailTypeStaircase_property(qs.getLiteral("HandrailType").asLiteral());
                    }
                    else if(qs.getLiteral("Height")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_stair_properties")){
                        spe.addLiteralHeightSign_property(qs.getLiteral("Height").asLiteral());
                    }
                    else if(qs.getLiteral("HeightFromFloor")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralHeightFromFloor_property(qs.getLiteral("HeightFromFloor").asLiteral());
                    }
                    else if(qs.getLiteral("InfoFacility")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralInfoFacility_property(qs.getLiteral("InfoFacility").asLiteral());
                    }
                    else if(qs.getLiteral("Length")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_ramp_properties")){
                        spe.addLiteralRampLenght_property(qs.getLiteral("Length").asLiteral());
                    }
                    else if(qs.getLiteral("Length")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_waiting_properties")){
                        spe.addLiteralWaitingLength_property(qs.getLiteral("Length").asLiteral());
                    }
                    else if(qs.getLiteral("LineCode")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralLineCode_property(qs.getLiteral("LineCode").asLiteral());
                    }
                    else if(qs.getLiteral("LineMap")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralLineMap_property(qs.getLiteral("LineMap").asLiteral());
                    }
                    else if(qs.getLiteral("LineName")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralLineName_property(qs.getLiteral("LineName").asLiteral());
                    }
                    else if(qs.getLiteral("LowCounterAccess")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralLowCounterAccess_property(qs.getLiteral("LowCounterAccess").asLiteral());
                    }
                    else if(qs.getLiteral("MachineReadable")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralMachineReadable_property(qs.getLiteral("MachineReadable").asLiteral());
                    }
                    else if(qs.getLiteral("MaximumLoad")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralMaximumLoad_property(qs.getLiteral("MaximumLoad").asLiteral());
                    }
                    else if(qs.getLiteral("MirrorOnOppositeSide")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralMirrorOnOppositeSide_property(qs.getLiteral("MirrorOnOppositeSide").asLiteral());
                    }
                    else if(qs.getLiteral("NumberOfGates")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralNumberOfGates_property(qs.getLiteral("NumberOfGates").asLiteral());
                    }
                    else if(qs.getLiteral("NumberOfLockers")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralNumberOfLockers_property(qs.getLiteral("NumberOfLockers").asLiteral());
                    }
                    else if(qs.getLiteral("NumberOfServers")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralNumberOfServers_property(qs.getLiteral("NumberOfServers").asLiteral());
                    }
                    else if(qs.getLiteral("NumberOfSteps")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralNumberOfSteps_property(qs.getLiteral("NumberOfSteps").asLiteral());
                    }
                    else if(qs.getLiteral("PaymentMethod")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_ticketing_properties")){
                        spe.addLiteralPaymentMethodTicketing_property(qs.getLiteral("PaymentMethod").asLiteral());
                    }
                    else if(qs.getLiteral("PaymentMethod")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_luggageLocker_properties")){
                        spe.addLiteralPaymentMethodTicketing_property(qs.getLiteral("PaymentMethod").asLiteral());
                    }
                    else if(qs.getLiteral("Pedestal")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralPedestal_property(qs.getLiteral("Pedestal").asLiteral());
                    }
                    else if(qs.getLiteral("PedestrianLights")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralPedestrianLights_property(qs.getLiteral("PedestrianLights").asLiteral());
                    }
                    else if(qs.getLiteral("Placement")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralPlacement_property(qs.getLiteral("Placement").asLiteral());
                    }
                    else if(qs.getLiteral("RailedQueue")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralRailedQueue_property(qs.getLiteral("RailedQueue").asLiteral());
                    }
                    else if(qs.getLiteral("RelativeWeigthing")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralRelativeWeighting_property(qs.getLiteral("RelativeWeigthing").asLiteral());
                    }
                    else if(qs.getLiteral("RevolvingDoor")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralRevolvingDoor_property(qs.getLiteral("RevolvingDoor").asLiteral());
                    }
                    else if(qs.getLiteral("Seats")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralSeats_property(qs.getLiteral("Seats").asLiteral());
                    }
                    else if(qs.getLiteral("SignGraphic")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralSignGraphic_property(qs.getLiteral("SignGraphic").asLiteral());
                    }
                    else if(qs.getLiteral("Speed")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralSpeed_property(qs.getLiteral("Speed").asLiteral());
                    }
                    else if(qs.getLiteral("SpiralStair")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralSpiralStair_property(qs.getLiteral("SpiralStair").asLiteral());
                    }
                    else if(qs.getLiteral("StepFree")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralStepFree_property(qs.getLiteral("StepFree").asLiteral());
                    }
//                    else if(qs.getLiteral("Surface")!=null
//                            && qs.getLiteral("hsp")!=null){
//                        spe.addliteral(qs.getLiteral("Surface").asLiteral());
//                    }
                    else if(qs.getLiteral("TactileActuators")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_lift_properties")){
                        spe.addLiteralTactileActuatorsLift_property(qs.getLiteral("TactileActuators").asLiteral());
                    }
                    else if(qs.getLiteral("TactileActuators")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_escalator_properties")){
                        spe.addLiteralTactileActuatorsStair_property(qs.getLiteral("TactileActuators").asLiteral());
                    }
                    else if(qs.getLiteral("TactileActuators")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_travelator_properties")){
                        spe.addLiteralTactileActuatorsTravelator_property(qs.getLiteral("TactileActuators").asLiteral());
                    }
                    else if(qs.getLiteral("TactileGuideStrips")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_crossing_properties")){                       
                        spe.addLiteralTactileGuideStripsCrossing_property(qs.getLiteral("TactileGuideStrips").asLiteral());
                    }else if(qs.getLiteral("TactileGuideStrips")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_ramp_properties")){                        
                        spe.addLiteralTactileGuideStripsRamp_property(qs.getLiteral("TactileGuideStrips").asLiteral());
                    }else if(qs.getLiteral("TicketMachines")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralTicketMachines_property(qs.getLiteral("TicketMachines").asLiteral());
                    }else if(qs.getLiteral("TicketTypesAvaiable")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralTicketTypesAvaiable_property(qs.getLiteral("TicketTypesAvaiable").asLiteral());
                    }else if(qs.getLiteral("TicketedQueue")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralTicketedQueue_property(qs.getLiteral("TicketedQueue").asLiteral());
                    }else if(qs.getLiteral("TicketingType")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralTicketTypesAvaiable_property(qs.getLiteral("TicketingType").asLiteral());
                    }else if(qs.getLiteral("ThroughLoader")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralThroughLoader_property(qs.getLiteral("ThroughLoader").asLiteral());
                    }else if(qs.getLiteral("VisualGuianceBands")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralVisualGuianceBands_property(qs.getLiteral("VisualGuianceBands").asLiteral());
                    }else if(qs.getLiteral("WeelchairTurningCircle")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralWheelchairTurningCircle_property(qs.getLiteral("WeelchairTurningCircle").asLiteral());
                    }else if(qs.getLiteral("WheelchairAreaLength")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralWheelchairAreaLength_property(qs.getLiteral("WheelchairAreaLength").asLiteral());
                    }
                    else if(qs.getLiteral("WeelchairAreaWidth")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralWheelchairAreaWidth_property(qs.getLiteral("WeelchairAreaWidth").asLiteral());
                    }
                    else if(qs.getLiteral("Width")!=null
                            && qs.getLiteral("hsp")!=null
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("")
                            && qs.getLiteral("hsp").asLiteral().toString().equalsIgnoreCase("has_luggageLocker_properties")){
                        spe.addLiteralLockerWidth_property(qs.getLiteral("Width").asLiteral());
                    }
                    else if(qs.getLiteral("Width")!=null
                            && qs.getLiteral("hsp")!=null){
                        spe.addLiteralWidthSign_property(qs.getLiteral("Width").asLiteral());
                    }
                }
            }

        } catch (java.lang.NullPointerException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Query Failed ! " + e + queryString);
        }

        return sp;
    }
    
    public int countStopPlace() {
        int count = 0;
        try {
            ontology.read(new FileInputStream(PATH), null, "RDF/XML");            
            Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
            InfModel inf = ModelFactory.createInfModel(reasoner, ontology);
            
            String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                    "SELECT (COUNT(*) AS ?count)" +
                                    "	WHERE { ?sp rdf:type mao:StopPlaceEquipment }";

            Query query = QueryFactory.create(queryString);

            //execute the query and obtain results
            QueryExecution queryExecution = QueryExecutionFactory.create(query, inf);
            ResultSet results = queryExecution.execSelect();

            while (results.hasNext()) {

                QuerySolution qs = results.next();
                count = qs.getLiteral("count").getInt();
            }

        } catch (java.lang.NullPointerException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Query Failed ! " + e);
        }

        return count;
    }
    
    public List<StopPlace> findAdjacents(String nameSp) {
        List<StopPlace> adjacents = new ArrayList<>();
        try {
            ontology.read(new FileInputStream(PATH), null, "RDF/XML");            
            Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();
            InfModel inf = ModelFactory.createInfModel(reasoner, ontology);
            
            String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                                "PREFIX maop: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology/Property#>" +
                                "PREFIX mao: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology#>" +
                                "SELECT ?nameAdj ?x ?y ?lineCode" +
                                "WHERE {	?sp rdf:type mao:StopPlaceEquipment." +
                                "               ?sp maop:has_conection ?spAdj." +
                                "               ?sp maop:PlaceName ?name." +
                                "               ?spAdj maop:PlaceName ?nameAdj." +
                                "               ?spAdj maop:X ?x." +
                                "               ?spAdj maop:Y ?y." +
                                "               ?spAdj maop:has_headingSign_properties ?hhsp." +
                                "               ?hhsp maop:LineCode ?lineCode." +
                                "               FILTER (?name = \""+nameSp+"\" ) }";

            Query query = QueryFactory.create(queryString);

            //execute the query and obtain results
            QueryExecution queryExecution = QueryExecutionFactory.create(query, inf);
            ResultSet results = queryExecution.execSelect();

            while (results.hasNext()) {
                QuerySolution qs = results.next();
                
                StopPlace sp = new StopPlaceImpl(ontology,qs.getLiteral("nameAdj").toString());

                sp.addLiteralCoordX(qs.getLiteral("x").getDouble());
                sp.addLiteralCoordY(qs.getLiteral("y").getDouble());
                sp.addLiteralPlaceName(qs.getLiteral("nameAdj").toString());
                sp.addLiteralLineCode(qs.getLiteral("lineCode").getString());
                        
                adjacents.add(sp);
            }

        } catch (java.lang.NullPointerException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Query Failed ! " + e);
        }

        return adjacents;
    }
    
}



//                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
//                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> 
//                PREFIX maop: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology/Property#>  
//                PREFIX mao: <http://www.vortic3.com/IFOPT/MetroAccesibilityOntology#>  
//                PREFIX maodt: <http://www.vortic3.com/IFOPT/MetroAccesihbilityOntology/dataType#>  
//                         SELECT *  
//                         WHERE { 
//                           {?sp rdf:type mao:StopPlaceEquipment.  
//                           ?sp rdfs:label ?label.  
//                           ?sp maop:X ?x.  
//                           ?sp maop:Y ?y.  
//                           ?sp maop:PlaceName ?name. 
//                           FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_passengerInfo_properties ?hsp.  
//                               ?hsp maop:AccessibilityInfo ?AccessibilityInfo.  
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_lift_properties ?hsp.  
//                               ?hsp maop:AcousticAnnouncements ?AcousticAnnouncements.  
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_crossing_properties ?hsp.  
//                               ?hsp maop:AcousticCrossingAids ?AcousticCrossingAids.  
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_crossing_properties ?hsp.  
//                               ?hsp maop:AcousticDeviceSensors ?AcousticDeviceSensors.  
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_entrance_properties ?hsp.  
//                               ?hsp maop:AcousticSensor ?AcousticSensor.  
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_headingSign_properties ?hsp. 
//                               ?hsp maop:AsBraille ?AsBraille. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:Attendant ?Attendant. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_lift_properties ?hsp. 
//                               ?hsp maop:Automatic ?Automatic. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_entrance_properties ?hsp. 
//                               ?hsp maop:AutoDoor ?AutoDoor. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:BrandGraphic ?BrandGraphic. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_entrance_properties ?hsp. 
//                               ?hsp maop:Barrier ?Barrier. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")        
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:Content ?Content. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_escalator_properties ?hsp. 
//                               ?hsp maop:Depth ?Depth. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION {
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_staircase_properties ?hsp. 
//                               ?hsp maop:Depth ?Depth. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION {
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_travelator_properties ?hsp. 
//                               ?hsp maop:Depth ?Depth. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION {
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:DirectionName ?DirectionName. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_access_properties ?hsp. 
//                               ?hsp maop:DirectionsOfUSe ?DirectionsOfUSe. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_entrance_properties ?hsp. 
//                               ?hsp maop:Door ?Door. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_stair_properties ?hsp. 
//                               ?hsp maop:EnergySaving ?EnergySaving. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                         
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_entrance_properties ?hsp. 
//                               ?hsp maop:EntranceRequiresTicket ?EntranceRequiresTicket. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_sanitaryFacility_properties ?hsp. 
//                               ?hsp maop:Gender ?Gender. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                           
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_ramp_properties ?hsp. 
//                               ?hsp maop:Gradient ?Gradient. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_stair_properties ?hsp. 
//                               ?hsp maop:HandrailHeight ?HandrailHeight. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_ramp_properties ?hsp. 
//                               ?hsp maop:HandrailHeight ?HandrailHeight. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_stair_properties ?hsp. 
//                               ?hsp maop:HandrailType ?HandrailType. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_ramp_properties ?hsp. 
//                               ?hsp maop:HandrailType ?HandrailType. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:Height ?Height. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_luggageLocker_properties ?hsp. 
//                               ?hsp maop:Height ?Height. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:HeightFromFloor ?HeightFromFloor. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                         
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_passengerInfo_properties ?hsp. 
//                               ?hsp maop:InfoFacility ?InfoFacility. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                           
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_ramp_properties ?hsp. 
//                               ?hsp maop:Length ?Length. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?hsp maop:has_waiting_properties ?hsp. 
//                               ?hsp maop:Length ?Length. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION {  
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop: ?hsp. 
//                               ?hsp maop:LineCode ?lineCode. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//
//
//
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_headingSign_properties ?hsp. 
//                               ?hsp maop:LineMap ?LineMap. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_headingSign_properties ?hsp. 
//                               ?hsp maop:LineName ?LineName. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                         
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_ticketing_properties ?hsp. 
//                               ?hsp maop:LowCounterAccess ?LowCounterAccess. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")            
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:MachineReadable ?MachineReadable. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")    
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_lift_properties ?hsp. 
//                               ?hsp maop:MaximumLoad ?MaximumLoad. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")   
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_lift_properties ?hsp. 
//                               ?hsp maop:MirrorOnOppositeSide ?MirrorOnOppositeSide. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_entrance_properties ?hsp. 
//                               ?hsp maop:NumberOfGates ?NumberOfGates. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                        
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_luggageLocker_properties ?hsp. 
//                               ?hsp maop:NumberOfLockers ?NumberOfLockers. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_queueing_properties ?hsp. 
//                               ?hsp maop:NumberOfServers ?NumberOfServers. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                         
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_stair_properties ?hsp. 
//                               ?hsp maop:NumberOfSteps ?NumberOfSteps. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                         
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_ticketing_properties ?hsp. 
//                               ?hsp maop:PaymentMethod ?PaymentMethod. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")       
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_luggageLocker_properties ?hsp. 
//                               ?hsp maop:PaymentMethod ?PaymentMethod. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_ramp_properties ?hsp. 
//                               ?hsp maop:Pedestal ?Pedestal. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_crossing_properties ?hsp. 
//                               ?hsp maop:PedestrianLights ?PedestrianLights. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                          
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:Placement ?Placement. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_queueing_properties ?hsp. 
//                               ?hsp maop:RailedQueue ?RailedQueue. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_access_properties ?hsp. 
//                               ?hsp maop:RelativeWeigthing ?RelativeWeigthing. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_entrance_properties ?hsp. 
//                               ?hsp maop:RevolvingDoor ?RevolvingDoor. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_sanitaryFacility_properties ?hsp. 
//                               ?hsp maop:SanitaryFacilityType ?SanitaryFacilityType. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_waiting_properties ?hsp. 
//                               ?hsp maop:Seats ?Seats. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:SignGraphic ?SignGraphic. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_travelator_properties ?hsp. 
//                               ?hsp maop:Speed ?Speed. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_staircase_properties ?hsp. 
//                               ?hsp maop:SpiralStair ?SpiralStair. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_waiting_properties ?hsp. 
//                               ?hsp maop:StepFree ?StepFree. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                         
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_roughSurface_properties ?hsp. 
//                               ?hsp maop:Surface ?Surface. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_access_properties ?hsp. 
//                               ?hsp maop:TactileActuators ?TactileActuators. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_crossing_properties ?hsp. 
//                               ?hsp maop:TactileGuideStrips ?TactileGuideStrips. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")         
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_ramp_properties ?hsp. 
//                               ?hsp maop:TactileGuideStrips ?TactileGuideStrips. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                          
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_ticketing_properties ?hsp. 
//                               ?hsp maop:TicketMachines ?TicketMachines. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_ticketing_properties ?hsp. 
//                               ?hsp maop:TicketTypesAvaiable ?TicketTypesAvaiable. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_queueing_properties ?hsp. 
//                               ?hsp maop:TicketedQueue ?TicketedQueue. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_ticketing_properties ?hsp. 
//                               ?hsp maop:TicketingType ?TicketingType. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                       
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_lift_properties ?hsp. 
//                               ?hsp maop:ThroughLoader ?ThroughLoader. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_crossing_properties ?hsp. 
//                               ?hsp maop:VisualGuianceBands ?VisualGuianceBands. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_lift_properties ?hsp. 
//                               ?hsp maop:WeelchairTurningCircle ?WeelchairTurningCircle. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_waiting_properties ?hsp. 
//                               ?hsp maop:WheelchairAreaLength ?WheelchairAreaLength. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                         
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_waiting_properties ?hsp. 
//                               ?hsp maop:WeelchairAreaWidth ?WeelchairAreaWidth. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_sign_properties ?hsp. 
//                               ?hsp maop:Width ?Width. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles") 
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_access_properties ?hsp. 
//                               ?hsp maop:Width ?Width. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_waiting_properties ?hsp. 
//                               ?hsp maop:Width ?Width. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")                          
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_lugageLocker_properties ?hsp. 
//                               ?hsp maop:Width ?Width. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")  
//                           } UNION { 
//                               ?sp rdf:type mao:StopPlaceEquipment.  
//                               ?sp maop:has_crossing_properties ?hsp. 
//                               ?hsp maop:ZebraCrossing ?ZebraCrossing. 
//                               ?sp maop:PlaceName ?name. 
//                               FILTER (?name = "Argüelles")}}