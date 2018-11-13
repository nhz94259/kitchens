package ant.kitchens.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by wolf   2018/10/23
 */
public class Date2LongUtil extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException , JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }

    public Long data2long(Date date){
       return date.getTime() / 1000;
    }

}
