package dataFromJson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class JsonDataReader {

//    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
//        //rerad json to string
//
//        String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/dataFromJson/PurchaseOrder.json"));
//
//        //String to Hashmap Jackson databind
//        ObjectMapper mapper=new ObjectMapper();
//
//       List<HashMap<String,String>> data =mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>() {
//       });
//       //map,map
//        return data;
//    }
}
