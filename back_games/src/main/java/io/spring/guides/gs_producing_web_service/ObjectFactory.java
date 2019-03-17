//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.03.17 à 03:03:00 PM CET 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the io.spring.guides.gs_producing_web_service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: io.spring.guides.gs_producing_web_service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetThemesResponse }
     * 
     */
    public GetThemesResponse createGetThemesResponse() {
        return new GetThemesResponse();
    }

    /**
     * Create an instance of {@link Theme }
     * 
     */
    public Theme createTheme() {
        return new Theme();
    }

    /**
     * Create an instance of {@link GetQuestionResponse }
     * 
     */
    public GetQuestionResponse createGetQuestionResponse() {
        return new GetQuestionResponse();
    }

    /**
     * Create an instance of {@link Question }
     * 
     */
    public Question createQuestion() {
        return new Question();
    }

    /**
     * Create an instance of {@link GetThemesRequest }
     * 
     */
    public GetThemesRequest createGetThemesRequest() {
        return new GetThemesRequest();
    }

    /**
     * Create an instance of {@link GetNbQuestionRequest }
     * 
     */
    public GetNbQuestionRequest createGetNbQuestionRequest() {
        return new GetNbQuestionRequest();
    }

    /**
     * Create an instance of {@link GetNbQuestionResponse }
     * 
     */
    public GetNbQuestionResponse createGetNbQuestionResponse() {
        return new GetNbQuestionResponse();
    }

    /**
     * Create an instance of {@link GetQuestionRequest }
     * 
     */
    public GetQuestionRequest createGetQuestionRequest() {
        return new GetQuestionRequest();
    }

    /**
     * Create an instance of {@link Answer }
     * 
     */
    public Answer createAnswer() {
        return new Answer();
    }

}
