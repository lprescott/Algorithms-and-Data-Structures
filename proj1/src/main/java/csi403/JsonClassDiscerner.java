//*******************************************************************
//  The supplied discerner class, to detect the object type of received JSON.
// 
//  Edits have been made to the main method for testing purposes.
//  Edits have also been in the discern function, implementing inList.
//*******************************************************************

package csi403; 

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

@SuppressWarnings("unused")
public class JsonClassDiscerner {

    public JsonClassDiscerner() {
    }

    public String discern(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try {
            inList list = mapper.readValue(jsonStr, inList.class);          
            return "inList"; 
        }
        catch (Exception e) {
            // e.printStackTrace(); 
            return "<unknown>"; 
        }
    }

    // test app 
    public static void main(String[] args) {
        String msg, msg2, msg3;
        JsonClassDiscerner discerner = new JsonClassDiscerner();
        System.out.println("************************************"); 
        
        msg = "{\"inList\" : [1,-4,2,8,5]}";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
        
        msg2 = "{\"inList\" : []}";
        System.out.println(msg2);
        System.out.println(discerner.discern(msg2));

        System.out.println("************************************"); 

        msg3 = "{\"list\" : [1,2,3,4,5]}";
        System.out.println(msg3);
        System.out.println(discerner.discern(msg3));

        System.out.println("************************************"); 
    }
}
