//
// 此檔案是由 Eclipse Implementation of JAXB, v3.0.0 所產生 
// 請參閱 https://eclipse-ee4j.github.io/jaxb-ri 
// 一旦重新編譯來源綱要, 對此檔案所做的任何修改都將會遺失. 
// 產生時間: 2024.07.28 於 08:55:03 PM CST 
//


package com.jj.webservice;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jj.webservice package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jj.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthorRequest }
     * 
     */
    public AuthorRequest createAuthorRequest() {
        return new AuthorRequest();
    }

    /**
     * Create an instance of {@link AuthorResponse }
     * 
     */
    public AuthorResponse createAuthorResponse() {
        return new AuthorResponse();
    }

    /**
     * Create an instance of {@link Author }
     * 
     */
    public Author createAuthor() {
        return new Author();
    }

    /**
     * Create an instance of {@link AuthorListRequest }
     * 
     */
    public AuthorListRequest createAuthorListRequest() {
        return new AuthorListRequest();
    }

    /**
     * Create an instance of {@link AuthorListResponse }
     * 
     */
    public AuthorListResponse createAuthorListResponse() {
        return new AuthorListResponse();
    }

}
