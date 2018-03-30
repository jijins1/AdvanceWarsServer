package advanceWar.action;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ActionDeserializer extends JsonDeserializer<Action> {

	@Override
	public Action deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
		ObjectCodec codec=p.getCodec();
		JsonNode tree=codec.readTree(p);
		//determiner le type d'action
		if(tree.has("dest")) {
			return codec.treeToValue(tree, ActionMove.class);
		}else if(tree.has("degat")) {
			System.out.println(tree);
			//TODO ameliorer ce mode la ? 
			return codec.treeToValue(tree, ActionAttack.class);
			//return new ActionAttack(tree.findValue("unitId").asInt(),tree.findValue("unitIdEnnemie").asInt(),tree.findValue("degat").asInt());
		}
	
	
	
        throw new UnsupportedOperationException("Cannot deserialize to a known type");
	}

}
