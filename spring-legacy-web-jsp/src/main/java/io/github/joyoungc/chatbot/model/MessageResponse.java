package io.github.joyoungc.chatbot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.joyoungc.chatbot.model.MessageRequest.MessageInput;
import lombok.Data;

@Data
public class MessageResponse {
	
	private MessageInput input; // (optional) The user input from the request.
	
	private List<RuntimeIntent> intents; // An array of intents recognized in the user input, sorted in descending order of confidence
	
	private List<RuntimeEntity> entities; // An array of entities identified in the user input
	
	@JsonProperty("alternate_intents")
	private Boolean alternateIntents; // (optional) Whether to return more than one intent. true indicates that all matching intents are returned.
	
	private Context context; // State information for the conversation
	
	private OutputData output; // Output from the dialog, including the response to the user, the nodes that were triggered, and log messages
	
	
	@Data
	public static class OutputData {
		
		/*	OutputData { 
		log_messages (Array[LogMessage]): Up to 50 messages logged with the request. ,
		text (Array[string]): An array of responses to the user. ,
		nodes_visited (Array[string], optional): An array of the nodes that were triggered to create the response. ,
		nodes_visited_details (Array[DialogNodeVisitedDetails], optional): An array of objects containing detailed diagnostic information about the nodes that were triggered during processing of the input message.
		}*/		
		
		@JsonProperty("log_messages")
		private List<Object> logMessage; // Up to 50 messages logged with the request.
		
		private List<String> text; // An array of responses to the user.
		
		@JsonProperty("nodes_visited")
		private List<String> nodesVisited; // (optional) An array of the nodes that were triggered to create the response.
		
		@JsonProperty("nodes_visited_details")
		private List<Object> nodesVisitedDetails; // (optional) An array of objects containing detailed diagnostic information about the nodes that were triggered during processing of the input message.
		
	}
	
	@Data
	public static class RuntimeIntent {
		private String intent; // The name of the recognized intent.
		private Double confidence; // A decimal percentage that represents Watson's confidence in the intent.
	}
	
	@Data
	public static class RuntimeEntity {
		private String entity; // The recognized entity from a term in the input.
		private List<Long> location; // Zero-based character offsets that indicate where the entity value begins and ends in the input text.
		private String value; // The term in the input text that was recognized.
		private Double confidence; // (optional) A decimal percentage that represents Watson's confidence in the entity.
		private Object metadata; // (optional) The metadata for the entity.
	}

}
