/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.model.impl;

import com.metro.dao.StopPlaceDAO;
import com.metro.model.StopPlace;
import com.metro.model.StopPlaceEquipment;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.NodeIterator;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

/**
 *
 * @author elena.salcedo
 */
public class StopPlaceImpl implements StopPlace {

    private final OntClass stopPlace_class;
    private final Individual stopPlace;
    private final OntProperty placeName;
    private final OntProperty coordX;
    private final OntProperty coordY;
    private final OntProperty lineCode;
    private final Map<String,Object> accesible_properties = null;

    public StopPlaceImpl(OntModel ontology, String name) {
        
        stopPlace_class = ontology.createClass(uriAccess.get_URI("SP"));
        stopPlace_class.addSuperClass(RDFS.Resource);

        stopPlace = ontology.createIndividual(uriAccess.get_URI("SP")+'#'+name, stopPlace_class);
        stopPlace.addLabel(name, lang);
   
        coordX = ontology.createOntProperty(uriAccess.get_URI("SP_CX"));
        coordX.addComment("Coordinate X", lang);
        coordX.addDomain(stopPlace_class);
        coordX.addRange(XSD.xdouble);

        coordY = ontology.createOntProperty(uriAccess.get_URI("SP_CY"));
        coordY.addComment("Coordinate Y", lang);
        coordY.addDomain(stopPlace_class);
        coordY.addRange(XSD.xdouble);

        placeName = ontology.createOntProperty(uriAccess.get_URI("SP_PN"));
        placeName.addComment("Place Name", lang);
        placeName.addDomain(stopPlace_class);
        placeName.addRange(XSD.xstring);
        
        lineCode = ontology.createOntProperty(uriAccess.get_URI("SP_LC"));
        lineCode.addComment("Line Code", lang);
        lineCode.addDomain(stopPlace_class);
        lineCode.addRange(XSD.integer);

    }

    @Override
    public double getdistanceFromPoint(double x, double y) {
        return Math.sqrt(
                Math.exp(Math.max(x,
                                stopPlace.getProperty(coordX).getLiteral().getDouble()))
                + Math.exp(Math.max(y,
                                stopPlace.getProperty(coordY).getLiteral().getDouble())));
    }

    @Override
    public void addLiteralCoordX(double data) {
        this.stopPlace.addLiteral(coordX, data);
    }

    @Override
    public void addLiteralCoordY(double data) {
        this.stopPlace.addLiteral(coordY, data);
    }

    @Override
    public void addLiteralPlaceName(String data) {
        this.stopPlace.addLiteral(placeName, data);
    }

    @Override
    public void addLiteralLineCode(String data) {
        this.stopPlace.addLiteral(lineCode, data);
    }

    @Override
    public double getLiteralCoordX() {
        try{
            return this.stopPlace.getPropertyValue(coordX).asLiteral().getDouble();
        }catch(NullPointerException e){
            Logger.getLogger(StopPlaceDAO.class.getName()).log(Level.SEVERE, null, e);
            return Double.NaN;            
        }
    }

    @Override
    public double getLiteralCoordY() {
        try{
        return this.stopPlace.getPropertyValue(coordY).asLiteral().getDouble();
        }catch(NullPointerException e){
            Logger.getLogger(StopPlaceDAO.class.getName()).log(Level.SEVERE, null, e);
            return Double.NaN;            
        }
    }

    @Override
    public String getLiteralPlaceName() {
        return this.stopPlace.getPropertyValue(placeName).asLiteral().toString();
    }

    @Override
    public List<Integer> getLiteralLineCode() {
        NodeIterator lines = this.stopPlace.listPropertyValues(lineCode);
        List<RDFNode> l =lines.toList();
        List<Integer> l2 = new ArrayList<>();
        for(RDFNode n: l){
            l2.add(n.asLiteral().getInt());
        }
        return l2;
    }

}
