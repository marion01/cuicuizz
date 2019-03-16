//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.7 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.03.14 à 09:40:16 PM CET 
//


package io.spring.guides.gs_producing_web_service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour scoreDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="scoreDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="theme" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nbQuestions" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nbSuccess" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "scoreDto", propOrder = {
    "id",
    "userId",
    "mode",
    "theme",
    "nbQuestions",
    "nbSuccess",
    "value"
})
public class ScoreDto {

    protected int id;
    protected int userId;
    @XmlElement(required = true)
    protected String mode;
    @XmlElement(required = true)
    protected String theme;
    protected int nbQuestions;
    private int nbSuccess;
    protected String value;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété userId.
     * 
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Définit la valeur de la propriété userId.
     * 
     */
    public void setUserId(int value) {
        this.userId = value;
    }

    /**
     * Obtient la valeur de la propriété mode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMode() {
        return mode;
    }

    /**
     * Définit la valeur de la propriété mode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMode(String value) {
        this.mode = value;
    }

    /**
     * Obtient la valeur de la propriété theme.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTheme() {
        return theme;
    }

    /**
     * Définit la valeur de la propriété theme.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTheme(String value) {
        this.theme = value;
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

    /**
     * Obtient la valeur de la propriété value.
     * 
     */
    public String getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * Obtient la valeur de la propriété nbSuccess.
     * 
     */
    public int getNbSuccess() {
        return nbSuccess;
    }

    /**
     * Définit la valeur de la propriété nbSuccess.
     * 
     */
    public void setNbSuccess(int nbSuccess) {
        this.nbSuccess = nbSuccess;
    }

}
