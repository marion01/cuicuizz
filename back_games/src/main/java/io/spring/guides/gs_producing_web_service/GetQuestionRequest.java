//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.0 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.03.14 à 03:49:55 PM CET 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="themeId" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nbQuestions" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "themeId",
    "nbQuestions"
})
@XmlRootElement(name = "getQuestionRequest")
public class GetQuestionRequest {

    protected int themeId;
    protected int nbQuestions;

    /**
     * Obtient la valeur de la propriété themeId.
     * 
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * Définit la valeur de la propriété themeId.
     * 
     */
    public void setThemeId(int value) {
        this.themeId = value;
    }

    /**
     * Obtient la valeur de la propriété nbQuestions.
     * 
     */
    public int getNbQuestions() {
        return nbQuestions;
    }

    /**
     * Définit la valeur de la propriété nbQuestions.
     * 
     */
    public void setNbQuestions(int value) {
        this.nbQuestions = value;
    }

}
