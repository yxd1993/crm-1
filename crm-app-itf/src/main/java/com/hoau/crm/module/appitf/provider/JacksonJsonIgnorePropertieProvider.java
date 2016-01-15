package com.hoau.crm.module.appitf.provider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.jaxrs.Annotations;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
@Provider
@Consumes({MediaType.APPLICATION_JSON, "text/json"})
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public class JacksonJsonIgnorePropertieProvider extends JacksonJsonProvider{
	public static final Annotations[] BASIC_ANNOTATIONS = { Annotations.JACKSON };
    public JacksonJsonIgnorePropertieProvider()
    {	
        this(null, BASIC_ANNOTATIONS);
    }

    public JacksonJsonIgnorePropertieProvider(Annotations... annotationsToUse)
    {
        this(null, annotationsToUse);
    }
    
    public JacksonJsonIgnorePropertieProvider(ObjectMapper mapper, Annotations[] annotationsToUse)
    {
        super(mapper, annotationsToUse);
        if(mapper==null){
        	mapper = new ObjectMapper();
        }
        mapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        setMapper(mapper);
    }
}
